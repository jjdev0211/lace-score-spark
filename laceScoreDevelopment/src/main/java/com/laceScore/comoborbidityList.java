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
    	cmListMap.put("COPD","HistoryofMechanicalVentilation, SleepApnea, Respiratordependence/tracheostomystatus, "
    			+ "Cardio-respiratoryfailureorcardio-respiratoryshock, Congestiveheartfailure, Acutecoronarysyndrome, "
    			+ "Coronaryatherosclerosisoranginacerebrovasculardisease, Specifiedarrhythmias, OtherandUnspecifiedHeartDisease, "
    			+ "Vascularorcirculatorydisease, Fibrosisoflungandotherchroniclungdisorders, Pneumonia, Historyofinfection, "
    			+ "Metastaticcanceroracuteleukemia, LungUpperDigestiveTractandOtherSevereCancers, LymphaticHeadandNeckBrainandOtherMajorCancers;BreastColorectalandotherCancersandTumors;OtherRespiratoryandHeartNeoplasms,"
    			+ "OtherDigestiveandUrinaryNeoplasms, "
    			+ "Diabetesmellitus(DM)orDMcomplications, Protein-caloriemalnutrition, Disordersoffluidelectrolyteacid-base, "
    			+ "OtherEndocrine/Metabolic/NutritionalDisorders, PancreaticDisease, PepticUlcerHemorrhageOtherSpecifiedGastrointestinalDisorders, "
    			+ "OtherGastrointestinalDisorders, SevereHematologicalDisorders, Irondeficiencyorotheranemiasandblooddisease, "
    			+ "Dementiaorotherspecifiedbraindisorders, Drug/AlcoholInducedDependence/Psychosis, MajorPsychiatricDisorders, "
    			+ "Depression, AnxietyDisorders, OtherPsychiatricDisorders, QuadriplegiaParaplegiaParalysisFunctionalDisability, "
    			+ "Polyneuropathy, HypertensiveHeartandRenalDiseaseorEncephalopathy");
    	cmListMap.put("HF","HistoryofCABG, Septicemia/shock, Congestiveheartfailure, Acutecoronarysyndrome, "
    			+ "Coronaryatherosclerosisoranginacerebrovasculardisease, Valvularorrheumaticheartdisease, Specifiedarrhythmias, "
    			+ "Vascularorcirculatorydisease, OtherandUnspecifiedHeartDisease, Metastaticcanceroracuteleukemia, "
    			+ "Cancer, Diabetesmellitus(DM)orDMcomplications, Protein-caloriemalnutrition, Disordersoffluidelectrolyteacid-base, "
    			+ "Liverandbiliarydisease, PepticUlcerHemorrhageOtherSpecifiedGastrointestinalDisorders, OtherGastrointestinalDisorders, "
    			+ "SevereHematologicalDisorders, Irondeficiencyorotheranemiasandblooddisease, Dementiaorotherspecifiedbraindisorders, "
    			+ "Drug/AlcoholInducedDependence/Psychosis, MajorPsychiatricDisorders, Depression, OtherPsychiatricDisorders, "
    			+ "Hemiplegiaparaplegiaparalysisfunctionaldisability, Stroke, Chronicobstructivepulmonarydisease, "
    			+ "Fibrosisoflungandotherchroniclungdisorders, Asthma, Pneumonia, End-stagerenaldiseaseordialysis, "
    			+ "Renalfailure, Nephritis, Otherurinarytractdisorders, Decubitusulcerorchronicskinulcer");
        cmListMap.put("HWR","Historyofinfection, Pneumonia, Metastaticcanceroracuteleukemia, LungUpperDigestiveTractandOtherSevereCancers, "
        		+ "LymphaticHeadandNeckBrainandOtherMajorCancers;BreastColorectalandotherCancersandTumors;OtherRespiratoryandHeartNeoplasms, "
        		+ "Diabetesmellitus(DM)orDMcomplications, Protein-caloriemalnutrition, End-stageliverdisease, Coagulationdefectsandotherspecifiedhematologicaldisorders,"
        		+ " Drug/AlcoholInducedDependence/Psychosis, MajorPsychiatricDisorders,Hemiplegiaparaplegiaparalysisfunctionaldisability, Seizuredisordersandconvulsions, "
        		+ "Chronicheartfailure, Coronaryatherosclerosisoranginacerebrovasculardisease, Specifiedarrhythmias, Chronicobstructivepulmonarydisease, "
        		+ "Fibrosisoflungandotherchroniclungdisorders, Dialysisstatus, Decubitusulcerorchronicskinulcer, Septicemia/shock, "
        		+ "Disordersoffluidelectrolyteacid-base, Irondeficiencyorotheranemiasandblooddisease, Renalfailure, PancreaticDisease, "
        		+ "Rheumatoidarthritisandinflammatoryconnectivetissuedisease, Respiratordependence/tracheostomystatus, Transplants, "
        		+ "Coagulationdefectsandotherspecifiedhematologicaldisorders, Hipfracture/dislocation");
        cmListMap.put("PN","HistoryofCABG, Historyofinfection, Septicemia/shock, Metastaticcanceroracuteleukemia, LungUpperDigestiveTractandOtherSevereCancers, "
        		+ "LymphaticHeadandNeckBrainandOtherMajorCancers;BreastColorectalandotherCancersandTumors;OtherRespiratoryandHeartNeoplasms, "
        		+ "Diabetesmellitus(DM)orDMcomplications, Protein-caloriemalnutrition, Disordersoffluidelectrolyteacid-base, "
        		+ "OtherGastrointestinalDisorders, SevereHematologicalDisorders, Irondeficiencyorotheranemiasandblooddisease, "
        		+ "Dementiaorotherspecifiedbraindisorders, Drug/AlcoholInducedDependence/Psychosis, MajorPsychiatricDisorders, "
        		+ "OtherPsychiatricDisorders, Hemiplegiaparaplegiaparalysisfunctionaldisability, Cardio-respiratoryfailureorcardio-respiratoryshock, "
        		+ "Congestiveheartfailure, Acutecoronarysyndrome, Coronaryatherosclerosisoranginacerebrovasculardisease, Valvularorrheumaticheartdisease, "
        		+ "Specifiedarrhythmias, Stroke, Vascularorcirculatorydisease, Chronicobstructivepulmonarydisease, Fibrosisoflungandotherchroniclungdisorders, "
        		+ "Asthma, Pneumonia, Pleuraleffusion/pneumothorax, End-stagerenaldiseaseordialysis, Renalfailure, Urinarytractinfection, "
        		+ "Otherurinarytractdisorders, Decubitusulcerorchronicskinulcer, VertebralFractures, Otherinjuries");
        cmListMap.put("THA-TKA","Skeletaldeformities, Posttraumaticosteoarthritis, Morbidobesity, Historyofinfection, Metastaticcanceroracuteleukemia, "
        		+ "Cancer, Diabetesmellitus(DM)orDMcomplications, Protein-caloriemalnutrition, Disordersoffluidelectrolyteacid-base, "
        		+ "Rheumatoidarthritisandinflammatoryconnectivetissuedisease, SevereHematologicalDisorders, Dementiaorotherspecifiedbraindisorders, "
        		+ "MajorPsychiatricDisorders, Hemiplegiaparaplegiaparalysisfunctionaldisability, Polyneuropathy, Congestiveheartfailure, "
        		+ "Coronaryatherosclerosis, Hypertension, Specifiedarrhythmias, Stroke, Vascularorcirculatorydisease, Chronicobstructivepulmonarydisease, "
        		+ "Pneumonia, End-stagerenaldiseaseordialysis, Renalfailure, Decubitusulcerorchronicskinulcer, CellulitisLocalSkinInfection, Otherinjuries");
    }
}
