package siti.inf203.jena.outils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
	// lire fichier dans une liste de string
	public static List<String> readFileInStringList(String filePath) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
		List<String> setOfWords = new ArrayList<String>();
		String line;
		while(((line = bf.readLine())!=null)) {
			setOfWords.add(line);
		}
		return setOfWords;
	}
}
