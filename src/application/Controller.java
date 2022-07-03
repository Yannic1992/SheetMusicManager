package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
	@FXML
	private Label compositionCount;
	@FXML
	private TextArea compositionTA;
	@FXML
	private Label composerCount;
	@FXML
	private ListView<String> composerListLV;
	
	private int selectedComposerInListView;
	
	public void add(ActionEvent e) throws IOException {
				
		Composer composer = new Composer(firstName.getText(), secondName.getText(), lastName.getText(), 
							birthYear.getText(), deathYear.getText());
		
		
		Composition composition = new Composition(titleOfComposition.getText(), 
								yearOfComposition.getText(), dataFormatOfComposition.getText());
		
		Composer tempComposer = Composer.checkComposerList(composer);
		
		if(tempComposer == null) { //When true, then composer not in composer list
			composition.setComposer(composer);
			Composer.addToComposerList(composer);
		} else {
			composition.setComposer(tempComposer);
		}
		
		Composition.addToCompositionList(composition);
		
		initialize(null, null);
		
		firstName.clear();
		firstName.requestFocus();
		secondName.clear();
		lastName.clear();
		birthYear.clear();
		deathYear.clear();
		titleOfComposition.clear();
		yearOfComposition.clear();
		dataFormatOfComposition.clear();
		
		Composition.writeIntoFile(Composition.getCompositionList());
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String[] tempComposerList = new String[Composer.getComposerList().size()];
		for(int i = 0; i<Composer.getComposerList().size(); i++) {
			tempComposerList[i] = Composer.getComposerList().get(i).getComposerWithAge();
		}
		
		composerListLV.getItems().clear();
		System.out.println("Cleared GUI composer list");
		composerListLV.getItems().addAll(tempComposerList);
		System.out.println("Refreshed GUI composer list");
		composerCount.setText("Anzahl: " + Composer.getComposerList().size());
		System.out.println("Refreshed label for number of composers");
		compositionCount.setText("Anzahl: " + Composition.getCount());
		System.out.println("Refreshed label for number of compositions");
		compositionTA.setText(Composition.getCompositionList().toString());
		System.out.println("Refreshed composition text area");
		
		composerListLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				
				selectedComposerInListView = composerListLV.getSelectionModel().getSelectedIndex();
				
				if (selectedComposerInListView != -1) {
					firstName.setText(Composer.getComposerList().get(selectedComposerInListView).getFirstName());
					secondName.setText(Composer.getComposerList().get(selectedComposerInListView).getSecondName());
					lastName.setText(Composer.getComposerList().get(selectedComposerInListView).getLastName());
					birthYear.setText(Composer.getComposerList().get(selectedComposerInListView).getBirthYear());
					deathYear.setText(Composer.getComposerList().get(selectedComposerInListView).getDeathYear());
				}
				
				
			}
			
		});
	}
	
	
}
