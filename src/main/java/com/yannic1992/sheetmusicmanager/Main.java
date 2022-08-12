package com.yannic1992.sheetmusicmanager;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@SuppressWarnings("exports")
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			stage.setScene(scene);
			stage.setTitle("Sheet Music Manager");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public static void main(String[] args) {
		Composition.readFile();
		launch(args);
	}
}
