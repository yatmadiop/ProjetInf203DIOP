package siti.inf203.jena.javafx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryParser.ParseException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import siti.inf203.jena.app.SearchConsole;
import siti.inf203.jena.index.TextFileIndexer;

public class Main extends Application {
	
	public static List<String> linkList = new ArrayList<String>();
	Hyperlink[] hpls = new Hyperlink[linkList.size()];
	Stage window;
	Scene scene;
	Scene scene2;
	Button button, button2;
	VBox layout, layout2;
	Hyperlink link;	
	TextField text = new TextField();
	public static List<Hyperlink> links2 = new ArrayList<>();
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		window = primaryStage;
		Label labelSearch = new Label("Entrer votre requête");		
//		TextField text = new TextField();
		//Button1
		button = new Button("Rechercher");
		button.setOnAction(e -> {
			try {
				SearchConsole.searchKey(text.getText());
				linkList = TextFileIndexer.getDocPathList();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			List<Hyperlink> links = new ArrayList<>();
			
			// create hyperlink
			for (int i = 0; i < linkList.size(); i++) {
				links.add(new Hyperlink(linkList.get(i)));
			}
			
			// allow to click on hyperlink and explore documents
	        for(final Hyperlink hyperlink : links) {
	            hyperlink.setOnAction(new EventHandler<ActionEvent>() {

	                @Override
	                public void handle(ActionEvent t) {
	                    getHostServices().showDocument(hyperlink.getText());
	                }
	            });
	        }       
	        ListView listView = new ListView(); 
	    	ListView listQuery = new ListView();
	    	
	        listView.getItems().addAll(links);
	        TextFileIndexer.getDocPathList().clear();
	        
			//Layout2
			layout2 = new VBox(20);
			button2 = new Button("Retour à la recherche");
			button2.setOnAction(c -> {
				
				links2.add(new Hyperlink(TextFileIndexer.getExpandedQuery()));
				listQuery.getItems().addAll(links2);
				Label historic = new Label("Recherches récentes : ");
				layout.getChildren().clear();			
				layout.getChildren().addAll(labelSearch, text, button, historic, listQuery);
				window.setScene(scene);				
  
			});
			
			Label labelResult = new Label("Résultats de la recherche - Cliquer sur un lien pour afficher le document ");
			// récupérer la requête étendue et l'afficher
			TextArea newQuery = new TextArea();			
			newQuery.setText("Requête étendue : " + TextFileIndexer.getExpandedQuery() + "");
			newQuery.setMaxHeight(5);
			layout2.getChildren().add(labelResult);
			layout2.getChildren().add(newQuery);
			layout2.getChildren().add(listView);
			layout2.getChildren().add(button2);
			layout2.setPadding(new Insets(20,20,20,20));
			layout2.setSpacing(5);				        
			
			scene2 = new Scene(layout2, 600, 500);			
			window.setScene(scene2);
			
		});
		
		//clear list
		linkList.clear();
		TextFileIndexer.getDocPathList().clear();
		
		//layout2.getChildren().clear();
		// Layout 1		
		
	
		layout = new VBox(20);		
		layout.setPadding(new Insets(20,20,20,20));
		layout.getChildren().addAll(labelSearch, text, button);
		
		layout.setSpacing(5);
		
		scene = new Scene(layout, 600, 500);
		window.setScene(scene);
		window.setTitle("Seach Engine");
		window.show();
	}
	
	
}
