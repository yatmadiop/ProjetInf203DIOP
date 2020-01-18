package siti.inf203.jena.outils;

import java.util.HashSet;

public class RemoveDuplicateWord {
	
	public static String removeDuplicateWord(String string) {
		HashSet<String> deduplicateList = new HashSet<String>();
		String[] wordList = string.toLowerCase().trim().split("\\s+");
		for (int i = 0; i < wordList.length; i++) {
			deduplicateList.add(wordList[i]);
		}
		string = String.join(" ", deduplicateList);
		
		return string;
	}
	
}
