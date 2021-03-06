package siti.inf203.jena.app;

import java.io.IOException;

import org.apache.lucene.queryParser.ParseException;

import siti.inf203.jena.index.TextFileIndexer;
import siti.inf203.jena.outils.Sparql;

public class SearchConsole {

	static void affiche() {
		
		System.out.println("Rechercher un document / \"q\" pour quitter  ... ");
		System.out.println("Entrer votre requête : ");
		
	}
	

	public static void searchKey(String string) throws IOException, ParseException {
		
		//étendre la requête et chercher termes dans l'index
		String query = Sparql.getExpandString(string);
		TextFileIndexer.searchIndex(query);
		Sparql.getList().clear();
	}
}
