package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller implements Initializable {
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
	@FXML
	private ListView<String> composerListLV;
	
	private int selectedComposerInListView;
	
	private static ArrayList<Composer> composerList = new ArrayList<>();
	private static ArrayList<Composition> compositionList = new ArrayList<>();
	
	public void add(ActionEvent e) throws IOException {
				
		Composer composer = new Composer(firstName.getText(), secondName.getText(), lastName.getText(), 
							birthYear.getText(), deathYear.getText());
		
		
		Composition composition = new Composition(titleOfComposition.getText(), 
								yearOfComposition.getText(), dataFormatOfComposition.getText());
		
		for(Composer element : composerList) {
			if(composer.getFullName().equals(element.getFullName())) {
				composition.setComposer(element);
			}
		}
		if(!checkComposerList(composer)) {
			composition.setComposer(composer);
		}
		addToComposerList(composer);
		
		initialize(null, null);
		
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String[] tempComposerList = new String[composerList.size()];
		for(int i = 0; i<composerList.size(); i++) {
			tempComposerList[i] = composerList.get(i).getComposer();
		}
		
		composerListLV.getItems().clear();
		composerListLV.getItems().addAll(tempComposerList);
		
		composerListLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				
				selectedComposerInListView = composerListLV.getSelectionModel().getSelectedIndex();
				
				firstName.setText(composerList.get(selectedComposerInListView).getFirstName());
				secondName.setText(composerList.get(selectedComposerInListView).getSecondName());
				lastName.setText(composerList.get(selectedComposerInListView).getLastName());
				birthYear.setText(composerList.get(selectedComposerInListView).getBirthYear());
				deathYear.setText(composerList.get(selectedComposerInListView).getDeathYear());
				
				
			}
			
		});
	}
	

}
