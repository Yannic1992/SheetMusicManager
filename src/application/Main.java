package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//launch(args);
		Composer bach = new Composer("Johann", "Sebastian", "Bach", 1675, 1750);
		Composition toccata = new Composition(bach, "Toccata und Fugue", "Druck", 1700);
		Composition test = new Composition();
		System.out.println(toccata.toString());
		System.out.println(test.toString());
		
	}

	
}
