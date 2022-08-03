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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
	private Label compositionCount;
	@FXML
	private Label composerCount;
	@FXML
	private ListView<String> composerListLV;
	@FXML
	private TableView<Composition> compositionTable = new TableView<Composition>();
	@FXML
	private TableColumn<Composition, String> compTableLastName = new TableColumn<Composition, String>();
	@FXML
	private TableColumn<Composition, String> compTableFirstName= new TableColumn<Composition, String>();
	@FXML
	private TableColumn<Composition, String> compTableSecondName = new TableColumn<Composition, String>();
	@FXML
	private TableColumn<Composition, String> compTableTitle = new TableColumn<Composition, String>();
	@FXML
	private TableColumn<Composition, String> compTableYear = new TableColumn<Composition, String>();
	@FXML
	private TableColumn<Composition, String> compTableFormat = new TableColumn<Composition, String>();
	
	// Hilfsvariablen
	private int selectedComposerInListView;
	private static String tempComposerFullName;
	
	
	@SuppressWarnings("exports")
	public void add(ActionEvent e) throws IOException {
				
		Composer composer = new Composer(firstName.getText(), secondName.getText(), lastName.getText(), 
							birthYear.getText(), deathYear.getText());
		
		
		Composition composition = new Composition(titleOfComposition.getText(), 
								yearOfComposition.getText(), dataFormatOfComposition.getText());
		
		Composer tempComposer = checkComposerList(composer);
		
		if(tempComposer == null) { //When true, then composer not in composer list
			//Composer.setNextId();
			composer.setId(Composer.getNextId());
			composition.setComposer(composer);
			addToComposerList(composer);
			Composer.setNextId();
		} else {
			composition.setComposer(tempComposer);
		}
		
		refreshComposerList();
		
		addToCompositionList(composition);
		
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
		compositionTable.getItems().add(Composition.getCompositionList().get(Composition.getCompositionList().size()-1));
		compositionCount.setText("Anzahl: " + Composition.getCompositionList().size());
	}
	
	public static void addToComposerList(Composer composer) {
		if(checkComposerList(composer) != null) {
			System.out.println("Komponist bereits vorhanden");
		}else {
			Composer.getComposerList().add(composer);
		}
		
	}
	
	public static Composer checkComposerList(Composer composer) {
		tempComposerFullName = composer.getFullName();
		for(Composer element : Composer.getComposerList()) {
			if(tempComposerFullName.equals(element.getFullName())) {
				return element;
			}
		}
		return null;
	}
	
	public static void addToCompositionList(Composition composition) {
		Composition.getCompositionList().add(composition);
	}
	
	public static String compositionListToString() {
		String output = "";
		for(int i = 0; i<Composition.getCompositionList().size(); i++) {
			output = output + Composition.getCompositionList().get(i).toString() + "\n";
		}
			
		return output;
	}
	
	public void refreshComposerList() {
		String[] tempComposerList = new String[Composer.getComposerList().size()];
		for(int i = 0; i<Composer.getComposerList().size(); i++) {
			tempComposerList[i] = Composer.getComposerList().get(i).getComposer();
		}
		
		composerListLV.getItems().clear();
		composerListLV.getItems().addAll(tempComposerList);
		System.out.println("Refreshed composer list");
		composerCount.setText("Anzahl: " + Composer.getComposerList().size());
		System.out.println("Refreshed composer number");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		refreshComposerList();
		
		compTableLastName.setCellValueFactory(new PropertyValueFactory<Composition, String>("lastName"));
		compTableFirstName.setCellValueFactory(new PropertyValueFactory<Composition, String>("firstName"));
		compTableSecondName.setCellValueFactory(new PropertyValueFactory<Composition, String>("secondName"));
		compTableTitle.setCellValueFactory(new PropertyValueFactory<Composition, String>("title"));
		compTableYear.setCellValueFactory(new PropertyValueFactory<Composition, String>("year"));
		compTableFormat.setCellValueFactory(new PropertyValueFactory<Composition, String>("dataFormat"));
		
		compositionTable.getItems().addAll(Composition.getCompositionList());
		compositionCount.setText("Anzahl: " + Composition.getCompositionList().size());
		

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
