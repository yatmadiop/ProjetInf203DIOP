package siti.inf203.jena.outils;

import java.io.IOException;

public class ExpandSearch {

	//exécute la méthode d'expansion de la requête
	public static void expandSearch(String input) throws IOException {
		Sparql.getExpandString(input);
	}
	
}
