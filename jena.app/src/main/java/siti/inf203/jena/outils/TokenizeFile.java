package siti.inf203.jena.outils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TokenizeFile {
	   
	// tokenization chaine de caractères
    public static void tokenizeFile(String inFile) throws IOException {
    	
    	List<String> result = new ArrayList<>(); 
    	
    	BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(inFile), "UTF-8"));
	    //Instantiating SimpleTokenizer class 
    	 Set<String> tokens = new TreeSet<String>();
	      //Tokenizer tokenizer = WhitespaceTokenizer.INSTANCE;  
			String line;
			while ((line = bf.readLine()) != null) {
				result.add(line);
			}
	       
	      for(int i = 0; i < result.size(); i++) {
	    	  System.out.println(result.get(i));
	    	 //tokens.addAll(Arrays.asList(tokenizer.tokenize(result.get(i))));
	        }
	      // écrire le résultat dans un fichier
	      //WriteToFile.writeToFile(tokens, "chemin");
	      
	      System.out.println(tokens.size());
    }

    
	public static void main(String[] args) throws IOException {
		
		tokenizeFile("resources/groupeTopo.txt");
		
       
	} 
}
