package com.laceScore;

import static org.apache.spark.sql.functions.col;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import scala.Tuple2;

public class calculateLaceScore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger.getLogger("org.apache").setLevel(Level.WARN);

		//Initialzing Spark Context and getting the measure
		String measureName = args[0];
		SparkConf conf = new SparkConf().setAppName("laceScore").setMaster("local[*]").set("spark.driver.allowMultipleContexts", "true");
		JavaSparkContext sc = new JavaSparkContext(conf);
		
        
		//Accessing the Diagnosis Code map
		HashMap<String, String> dgCodes = diagnosisCodes.dgCodeMap;
		String dgCodeList = dgCodes.get(measureName);
		String[] dgCodeVal = dgCodeList.trim().split("\\s*,\\s*");

		//Accessing the Comorbidity List map
		HashMap<String, String> cmList = comoborbidityList.cmListMap;
		String cmListMatch = cmList.get(measureName);
		String[] cmListVal = cmListMatch.trim().split("\\s*,\\s*");

		//Build the SparkSession, CSVData with valid rows for the Measure specified
		SparkSession spark = SparkSession.builder().appName("laceScore").master("local[*]")
				.config("spark.sql.warehouse.dir", "file:///D:/spark/grayMatter/laceScoreDevelopment")
				.config("spark.debug.maxToStringFields", 100).getOrCreate();
        Dataset<Row> csvData = spark.read().format("csv").option("header", "true").option("inferSchema", "true")
				.load("Sample_Data_2016.csv");
		csvData.createOrReplaceTempView("csvView");
		
		//Removing the rows with no encounter_id
		Dataset<Row> csvDataNoNull = spark.sql("SELECT * FROM csvView").filter(col("encounter_id").isNotNull());
		
		//Build the table with the the valid columns for the measure from Comorbidity list
		String firstCol = "encounter_id";
		String[] restCols = Arrays.copyOfRange(cmListVal, 0, cmListVal.length);
        Dataset<Row> csvDataFinal = csvDataNoNull.filter(col("diagnosis_code").isin(dgCodeVal));
        Dataset<Row> csvDataValidCols = csvDataFinal.select(firstCol, restCols);
        
        //Writing the table to a temp file "enc_id_yes_count.csv" to generate the yes count for each row
		JavaRDD<String> csvDataRDD = csvDataValidCols.javaRDD().map(x -> x.toString());
		//List<String> csvDataString = csvDataRDD.collect();
		//JavaRDD<String> csvDataParallel = sc.parallelize(csvDataString);
		JavaRDD<String> csvFlatMap = csvDataRDD
				.flatMap(csvLines -> Arrays.asList(csvLines.split("\n")).iterator());
		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("enc_id_yes_count.csv"), "utf-8"))) {
			writer.write("encounter_id" + "," + "yes_count" + "\n");
			for (String line : csvFlatMap.collect()) {
				String[] csvSplit = line.split(",");
				String enc_id = csvSplit[0].replaceAll("[\\[\\]]", "");
				JavaPairRDD<String, Long> csvDataPair = sc.parallelize(Arrays.asList(line))
						.flatMap(csvLines -> Arrays.asList(csvLines.split(",")).iterator())
						.filter(csvStr -> (csvStr.equalsIgnoreCase("yes")))
						.mapToPair(csvString -> new Tuple2<>(csvString, 1L)).reduceByKey((val1, val2) -> val1 + val2);
				JavaRDD<Long> newRDD = csvDataPair.map(x -> x._2);
				String yes_count = newRDD.collect().toString().replaceAll("[\\[\\]]", "");
				if (yes_count.equalsIgnoreCase("")) {
					yes_count = "0";
				} else {
				}
				writer.write(enc_id + "," + Integer.parseInt(yes_count) + "\n");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		//Reading the temp file generated with yes count to compute the Lace Score
		Dataset<Row> csvEncIdMatch = spark.read().format("csv").option("header", "true").option("inferSchema", "true")
				.load("enc_id_yes_count.csv");
		Dataset<Row> csvTableWithYesCount = csvDataNoNull
				.join(csvEncIdMatch, csvDataNoNull.col("encounter_id").equalTo(csvEncIdMatch.col("encounter_id")))
				.drop(csvEncIdMatch.col("encounter_id")); //Join with the original table
		csvTableWithYesCount.createOrReplaceTempView("laceScoreTable");
		Dataset<Row> csvSqlYesCount = spark.sql("SELECT encounter_id, yes_count AS Comorbidity_Score, ED_visits, (CASE "
				+ "WHEN LengthofStay < 1 THEN 0 " + "WHEN LengthofStay IN (1,2,3) THEN LengthofStay "
				+ "WHEN LengthofStay BETWEEN 4 AND 6 THEN 4 " + "WHEN LengthofStay BETWEEN 7 AND 13 THEN 5 "
				+ "WHEN LengthofStay >= 14 THEN 7 " + "ELSE 0 " + "END) +" + "(CASE "
				+ "WHEN yes_count IN (0,1,2,3) THEN yes_count " + "WHEN yes_count >= 4 THEN 5 " + "ELSE 0 " + "END) +"
				+ "(CASE " + "WHEN ED_visits IN (0,1,2,3) THEN ED_visits " + "WHEN ED_visits >= 4 THEN 4 " + "ELSE 0 "
				+ "END) + Inpatient_visits  AS LACE_SCORE FROM laceScoreTable");
		csvSqlYesCount.createOrReplaceTempView("laceScoreView");
		Dataset<Row> laceScoreCount = spark.sql("SELECT * from laceScoreView where LACE_SCORE > 9");
		Long laceScoreGtNine = laceScoreCount.count();
		Long rowCount = csvSqlYesCount.count();
		Double measureScore = ((double) laceScoreGtNine / rowCount);
		/*
		 * Dataset<Row> csvSqlYesCount = spark.sql("SELECT (CASE " +
		 * "WHEN LengthofStay < 1 THEN 0 " +
		 * "WHEN LengthofStay IN (1,2,3) THEN LengthofStay " +
		 * "WHEN LengthofStay BETWEEN 4 AND 6 THEN 4 " +
		 * "WHEN LengthofStay BETWEEN 7 AND 13 THEN 5 " +
		 * "WHEN LengthofStay >= 14 THEN 7 " + "ELSE 0 " + "END) +" + "(CASE " +
		 * "WHEN yes_count IN (0,1,2,3) THEN yes_count " + "WHEN yes_count >= 4 THEN 5 "
		 * + "ELSE 0 " + "END) +" + "(CASE " +
		 * "WHEN ED_visits IN (0,1,2,3) THEN ED_visits " + "WHEN ED_visits >= 4 THEN 4 "
		 * + "ELSE 0 " +
		 * "END) + Inpatient_visits  AS LACE_SCORE, FROM csvTableWithYesCount");
		 */
		// laceScoreCount.show();
		System.out.println("Rowcount is " + rowCount + " Lace Score > 9 " + laceScoreGtNine);
		System.out.println("Final Score is " + measureScore);
		
		sc.close();
        spark.stop();
	}

}
