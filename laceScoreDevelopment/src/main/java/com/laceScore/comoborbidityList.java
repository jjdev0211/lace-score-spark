package com.laceScore;

import java.util.HashMap;

public class comoborbidityList {
	static HashMap<String, String> cmListMap = new HashMap<String, String>();
    static {
    	cmListMap.put(
				  "AMI","HistoryofPTCA, HistoryofCABG, Congestiveheartfailure, Acutecoronarysyndrome, "
				  +
				  "Anteriormyocardialinfarction, Otherlocationofmyocardialinfarction, Anginapectorisoldmyocardialinfarction, Coronaryatherosclerosis, "
				  +
				  "Valvularorrheumaticheartdisease, Specifiedarrhythmias, Historyofinfection, "
				  +
				  "Metastaticcanceroracuteleukemia, Cancer, Diabetesmellitus(DM)orDMcomplications, "
				  + "Protein-caloriemalnutrition, Disordersoffluidelectrolyteacid-base, " +
				  "Irondeficiencyorotheranemiasandblooddisease, Dementiaorotherspecifiedbraindisorders,"
				  +
				  "Hemiplegiaparaplegiaparalysisfunctionaldisability, Stroke, Cerebrovasculardisease, Vascularorcirculatorydisease, Chronicobstructivepulmonarydisease, Asthma, Pneumonia, "
				  +
				  "End-stagerenaldiseaseordialysis, Renalfailure, Otherurinarytractdisorders, Decubitusulcerorchronicskinulcer"
				  ); 
    }
}
