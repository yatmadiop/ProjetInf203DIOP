package siti.inf203.jena.app;
	

import com.hp.hpl.jena.iri.impl.Main;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.FileManager;

/**
 * Hello world!
 *
 */
public class App 
{
	static void sparqlTest() {
		FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
        Model model = FileManager.get().loadModel("resources/cancers.rdf");
        String queryString = 
        		// libelleTopoCIMO3 du code 3379
//        		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
//        		+ "PREFIX neovoc: <neo4j://vocabulary#>"
//        		+ "SELECT * WHERE {"
//        		+ "?3379 neovoc:libelleTopoCIMO3 ?x ."
//        		+ "}";
        		
        		// chercher les éléments qui ont un code topo et renvoyer les codes topo
        		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
        		+ "PREFIX neovoc: <neo4j://vocabulary#>"
        		+ "PREFIX neoind: <neo4j://individuals#>"
        		+ "SELECT * WHERE {"
        		+ "?neoind neovoc:libelleCodeCIMO3 ?x ."
        		+ "?neoind neovoc:appartient ?groupe ."
        		+ "?groupe neovoc:codeGroupeMorpho ?y ."
        		+ "FILTER(?y = \"8\")"
        		// + "FILTER(?x = \"98973\")"
        		+ "}";
         
        Query query = QueryFactory.create(queryString);
        QueryExecution qExec = QueryExecutionFactory.create(query, model);
        try {
        	ResultSet results = qExec.execSelect();
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				Literal name = soln.getLiteral("x");
				System.out.println(name);
			}
		} finally {
			qExec.close();
			// TODO: handle finally clause
		}
	}
    public static void main( String[] args )
    {
        
    	sparqlTest();
    	
  	
    	
    }
    

}
