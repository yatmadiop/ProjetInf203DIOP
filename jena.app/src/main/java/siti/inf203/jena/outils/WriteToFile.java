package siti.inf203.jena.outils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class WriteToFile {
    
    static void writeToFile(Set<String> words, String path) throws IOException {
    	FileWriter writer = new FileWriter(path); 
    	for(String str: words) {
    	  writer.write(str + System.lineSeparator());
    	}
    	writer.close();
    }
   
}
