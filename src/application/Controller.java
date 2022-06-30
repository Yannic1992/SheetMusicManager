package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
	@FXML
	private TextField firstName;
	@FXML
	private TextField secondName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField birthYear;
	@FXML
	private TextField deathYear;
	@FXML
	private TextField titleOfComposition;
	@FXML
	private TextField yearOfComposition;
	@FXML
	private TextField dataFormatOfComposition;
	//private Button addComposition;
	@FXML
	private TextArea compositionTA;
	
	private static ArrayList<Composer> composerList = new ArrayList<>();
	private static ArrayList<Composition> compositionList = new ArrayList<>();
	
	public void add(ActionEvent e) throws IOException {
				
		Composer composer = new Composer(firstName.getText(), secondName.getText(), lastName.getText(), 
							birthYear.getText(), deathYear.getText());
		addToComposerList(composer);
		
		Composition composition = new Composition(composer, titleOfComposition.getText(), 
								yearOfComposition.getText(), dataFormatOfComposition.getText());
		addToCompositionList(composition);
		
		compositionTA.setText(compositionList.toString());
		
		firstName.clear();
		firstName.requestFocus();
		secondName.clear();
		lastName.clear();
		birthYear.clear();
		deathYear.clear();
		titleOfComposition.clear();
		yearOfComposition.clear();
		dataFormatOfComposition.clear();
		
	}
	
	public static void addToComposerList(Composer composer) {
		if(checkComposerList(composer)) {
			System.out.println("Komponist bereits vorhanden");
		}else {
			composerList.add(composer);
		}
		
	}
	
	public static boolean checkComposerList(Composer composer) {
		for(Composer element : composerList) {
			//String name = composerList.get(i).getFullName();
			if(composer.getFullName().equals(element.getFullName())) {
				return true;
			}
		}
		return false;
	}
	
	public static void addToCompositionList(Composition composition) {
		compositionList.add(composition);
	}
	
	public static String compositionListToString() {
		String output = "";
		for(int i = 0; i<compositionList.size(); i++) {
			output = output + compositionList.get(i).toString() + "\n";
		}
			
		return output;
	}
	

}
