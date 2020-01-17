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
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import siti.inf203.jena.app.SearchConsole;
import siti.inf203.jena.index.TextFileIndexer;

public class Main extends Application {
	final ListView listView = new ListView();
	public static List<String> linkList = new ArrayList<String>();
	Hyperlink[] hpls = new Hyperlink[linkList.size()];
	Stage window;
	Scene scene, scene2;
	Button button;
		
		
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		window = primaryStage;
		Label labelSearch = new Label("Entrer votre requête");		
		TextField text = new TextField();
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
			Hyperlink link;				
			//Button2
			Button button2 = new Button("Retour à la recherche");
			button2.setOnAction(c -> window.setScene(scene));
			//Layout2
			VBox layout2 = new VBox(20);
			Label labelResult = new Label("Résultats de la recherche - Cliquer pour explorer ");
			// récupérer la requête étendue et l'afficher
			Label newQuery = new Label("(Requête étendue : " + TextFileIndexer.getExpandedQuery() + ")");
			
			List<Hyperlink> links = new ArrayList<>();
			
			layout2.getChildren().add(labelResult);
			layout2.getChildren().add(newQuery);
			layout2.setPadding(new Insets(20,20,20,20));
			
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
	        
	        listView.getItems().addAll(links);
	        
			TextFileIndexer.getDocPathList().clear();
			layout2.getChildren().add(listView);
			layout2.getChildren().add(button2);
			layout2.setSpacing(5);	
			scene2 = new Scene(layout2, 600, 500);
			
			window.setScene(scene2);

			
		});
		
		//clear list
		TextFileIndexer.getDocPathList().clear();
		

		// Layout 1		
		VBox layout = new VBox(20);		
		layout.setPadding(new Insets(20,20,20,20));
		layout.getChildren().addAll(labelSearch, text, button);
//		layout.getChildren().addAll(hpls);
		layout.setSpacing(5);		
		scene = new Scene(layout, 600, 500);
	

		window.setScene(scene);
		window.setTitle("Seach Engine");
		window.show();
	}
	
	
}