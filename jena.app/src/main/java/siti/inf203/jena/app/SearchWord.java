package siti.inf203.jena.app;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

import siti.inf203.jena.outils.EditDistance;
import siti.inf203.jena.outils.ReadFile;

public class SearchWord {
	
	// recherche du mot rentré et calcul de similarité avec EditDistance
	// si similarité < 3 on utilise le mot le plus similaire sinon laisser tel que rentré par l'utilisateur
	public static String searchWord(String inputWord) throws IOException {
		String bestMatch = null;
		TreeMap<Integer, String> myMorphoMatchMap = new TreeMap<Integer, String>();
		List<String> maList = ReadFile.readFileInStringList("resources/vocabulaire.txt");
    	for (int i = 0; i < maList.size(); i++) {
    		myMorphoMatchMap.putAll(EditDistance.calculate(maList.get(i), inputWord));
		}
    	if (myMorphoMatchMap.firstKey() < 3) {
    		bestMatch = myMorphoMatchMap.get(myMorphoMatchMap.firstKey());
    	}
    	else {
    		bestMatch = "";
    	}
    	
    	return bestMatch;
    	
	}
}
