package com.yannic1992.sheetmusicmanager;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
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
	//private Button editButton;
	@FXML
	private Label compositionCount;
	@FXML
	private Label composerCount;
	@FXML
	private ListView<String> composerListView;
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
	
	/*private ObservableList<String> obsComposerList = FXCollections.observableArrayList();
	private SortedList<String> sortedComposerList;*/
	// Auxiliary variable
	private int selectedComposerIdInComposerList;
	private int selectedCompositionIdInCompositionTable;
	
	@SuppressWarnings("exports")
	public void add(ActionEvent e) throws IOException {
		Composer composer = new Composer(firstName.getText(), secondName.getText(), lastName.getText(), 
							birthYear.getText(), deathYear.getText());
		Composition composition = new Composition(titleOfComposition.getText(), 
								yearOfComposition.getText(), dataFormatOfComposition.getText());
		Composer tempComposer = Composer.checkComposerList(composer);
		
		if(tempComposer == null) { //When true, then composer not in composer list
			composer.setId();
			composition.setComposer(composer);
			Composer.addToComposerList(composer);
		} else {
			composition.setComposer(tempComposer);
		}
		refreshComposerListView();
		Composition.addToObsCompositionList(composition);
		clearTextFields();
		firstName.requestFocus();		
		Composition.writeIntoFile(Composition.getObsCompositionList());
		compositionTable.getItems().add(Composition.getObsCompositionList().get(Composition.getObsCompositionList().size()-1));
		compositionCount.setText("Anzahl: " + Composition.getObsCompositionList().size());
	}
	@SuppressWarnings("exports")
	public void edit(ActionEvent e) {
		if(titleOfComposition.getText() == "") { // Edit composer only
			for(int i = 0; i<=Composer.getComposerList().size(); i++) {
				if(Composer.getComposerList().get(i).getId() == selectedComposerIdInComposerList) {
					selectedComposerIdInComposerList = i;
					break;
				}
			}
			Composer.getComposerList().get(selectedComposerIdInComposerList).setFirstName(firstName.getText());
			Composer.getComposerList().get(selectedComposerIdInComposerList).setSecondName(secondName.getText());
			Composer.getComposerList().get(selectedComposerIdInComposerList).setLastName(lastName.getText());
			Composer.getComposerList().get(selectedComposerIdInComposerList).setBirthYear(birthYear.getText());
			Composer.getComposerList().get(selectedComposerIdInComposerList).setDeathYear(deathYear.getText());
			refreshComposerListView();
			compositionTable.getItems().clear();
			compositionTable.getItems().addAll(Composition.getObsCompositionList());
			Composition.writeIntoFile(Composition.getObsCompositionList());
		} else { // Edit composition and/or composer
			for(int i = 0; i<=Composition.getObsCompositionList().size(); i++) {
				if(Composition.getObsCompositionList().get(i).getId() == selectedCompositionIdInCompositionTable) {
					selectedCompositionIdInCompositionTable = i;
					break;
				}
			}
			Composition.getObsCompositionList().get(selectedCompositionIdInCompositionTable).
			getComposer().setFirstName(firstName.getText());
			Composition.getObsCompositionList().get(selectedCompositionIdInCompositionTable).
				getComposer().setSecondName(secondName.getText());
			Composition.getObsCompositionList().get(selectedCompositionIdInCompositionTable).
				getComposer().setLastName(lastName.getText());
			Composition.getObsCompositionList().get(selectedCompositionIdInCompositionTable).
				getComposer().setBirthYear(birthYear.getText());
			Composition.getObsCompositionList().get(selectedCompositionIdInCompositionTable).
				getComposer().setDeathYear(deathYear.getText());
			Composition.getObsCompositionList().get(selectedCompositionIdInCompositionTable).
				setTitle(titleOfComposition.getText());
			Composition.getObsCompositionList().get(selectedCompositionIdInCompositionTable).
				setYear(yearOfComposition.getText());
			Composition.getObsCompositionList().get(selectedCompositionIdInCompositionTable).
				setDataFormat(dataFormatOfComposition.getText());
			refreshComposerListView();
			compositionTable.getItems().clear();
			compositionTable.getItems().addAll(Composition.getObsCompositionList());
			Composition.writeIntoFile(Composition.getObsCompositionList());
		}
	}
	
	@SuppressWarnings("exports")
	public void delete(ActionEvent e) {
		if(titleOfComposition.getText() == "") {
			for(int i = 0; i<=Composer.getComposerList().size(); i++) {
				if(Composer.getComposerList().get(i).getId() == selectedComposerIdInComposerList) {
					selectedComposerIdInComposerList = i;
					break;
				}
			}
			Composer.getComposerList().remove(selectedComposerIdInComposerList);
			refreshComposerListView();
			compositionTable.getItems().clear();
			compositionTable.getItems().addAll(Composition.getObsCompositionList());
			Composition.writeIntoFile(Composition.getObsCompositionList());
		} else {
			for(int i = 0; i<=Composition.getObsCompositionList().size(); i++) {
				if(Composition.getObsCompositionList().get(i).getId() == selectedCompositionIdInCompositionTable) {
					selectedCompositionIdInCompositionTable = i;
					break;
				}
			}
			Composition.getObsCompositionList().remove(selectedCompositionIdInCompositionTable);
			refreshComposerListView();
			compositionTable.getItems().clear();
			compositionTable.getItems().addAll(Composition.getObsCompositionList());
			Composition.writeIntoFile(Composition.getObsCompositionList());
		}
	}
	/*public static String compositionListToString() {
		String output = "";
		for(int i = 0; i<Composition.getCompositionList().size(); i++) {
			output = output + Composition.getCompositionList().get(i).toString() + "\n";
		}
		return output;
	}*/
	public void clearTextFields() {
		firstName.clear();
		secondName.clear();
		lastName.clear();
		birthYear.clear();
		deathYear.clear();
		titleOfComposition.clear();
		yearOfComposition.clear();
		dataFormatOfComposition.clear();
	}
	public void refreshComposerListView() {
		Collections.sort(Composer.getComposerList(), Composer.compListSortByName);
		//composerListView.setItems(sortedComposerList.sorted());
		String[] tempComposerList = new String[Composer.getComposerList().size()];
		for(int i = 0; i<Composer.getComposerList().size(); i++) {
			tempComposerList[i] = Composer.getComposerList().get(i).getComposerNameWithYears();
		}
		composerListView.getItems().clear();
		composerListView.getItems().addAll(tempComposerList);
		System.out.println("Refreshed composer list");
		composerCount.setText("Anzahl: " + Composer.getComposerList().size());
		System.out.println("Refreshed composer number");
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/*for(int i = 0; i<Composer.getComposerList().size(); i++) {
			obsComposerList.add(Composer.getComposerList().get(i).getComposerNameWithYears());
		}
		sortedComposerList = new SortedList<String>(obsComposerList);*/
		refreshComposerListView();
		
		compTableLastName.setCellValueFactory(new PropertyValueFactory<Composition, String>("lastName"));
		compTableFirstName.setCellValueFactory(new PropertyValueFactory<Composition, String>("firstName"));
		compTableSecondName.setCellValueFactory(new PropertyValueFactory<Composition, String>("secondName"));
		compTableTitle.setCellValueFactory(new PropertyValueFactory<Composition, String>("title"));
		compTableYear.setCellValueFactory(new PropertyValueFactory<Composition, String>("year"));
		compTableFormat.setCellValueFactory(new PropertyValueFactory<Composition, String>("dataFormat"));
		compositionTable.getItems().addAll(Composition.getObsCompositionList());
		compositionCount.setText("Anzahl: " + Composition.getObsCompositionList().size());
		composerListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				
				/*String selectedComposerInListView = composerListView.getSelectionModel().getSelectedItem();
				System.out.println(selectedComposerInListView);*/
				
				int selectedComposerIndexInList = composerListView.getSelectionModel().getSelectedIndex();
				//System.out.println(obsComposerList.get(selectedComposerIndexInList));
				if (selectedComposerIndexInList != -1) {
					clearTextFields();
					firstName.setText(Composer.getComposerList().get(selectedComposerIndexInList).getFirstName());
					secondName.setText(Composer.getComposerList().get(selectedComposerIndexInList).getSecondName());
					lastName.setText(Composer.getComposerList().get(selectedComposerIndexInList).getLastName());
					birthYear.setText(Composer.getComposerList().get(selectedComposerIndexInList).getBirthYear());
					deathYear.setText(Composer.getComposerList().get(selectedComposerIndexInList).getDeathYear());
					selectedComposerIdInComposerList = Composer.getComposerList().get(selectedComposerIndexInList).getId();
					//System.out.println(selectedComposerIdInComposerList);
				}	
			}
		});
		compositionTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Composition>() {
			@Override
			public void changed(ObservableValue<? extends Composition> arg0, Composition arg1, Composition arg2) {
				int selectedCompositionInTableView = compositionTable.getSelectionModel().getSelectedIndex();
				if (selectedCompositionInTableView != -1) {
					clearTextFields();
					firstName.setText(compositionTable.getSelectionModel().getSelectedItem().getFirstName());
					secondName.setText(compositionTable.getSelectionModel().getSelectedItem().getSecondName());
					lastName.setText(compositionTable.getSelectionModel().getSelectedItem().getLastName());
					birthYear.setText(compositionTable.getSelectionModel().getSelectedItem().getBirtYear());
					deathYear.setText(compositionTable.getSelectionModel().getSelectedItem().getDeathYear());
					titleOfComposition.setText(compositionTable.getSelectionModel().getSelectedItem().getTitle());
					yearOfComposition.setText(compositionTable.getSelectionModel().getSelectedItem().getYear());
					dataFormatOfComposition.setText(compositionTable.getSelectionModel().getSelectedItem().getDataFormat());
					selectedCompositionIdInCompositionTable = compositionTable.getSelectionModel().getSelectedItem().getId();
				}
			}
		});
	}
}
