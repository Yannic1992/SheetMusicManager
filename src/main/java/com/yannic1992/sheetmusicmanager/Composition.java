package com.yannic1992.sheetmusicmanager;

import java.io.*;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Composition implements Serializable{
	
	@Serial
	private static final long serialVersionUID = 1L;
	private Composer composer;
	private String title;
	private String dataFormat; // in which format is the sheet music available i.e. printed or as pdf
	private String year;
	private int id;

	private static ObservableList<Composition> obsCompositionList = FXCollections.observableArrayList();
	
	public Composition(String title, String year, String dataFormat) {
		this.title = title;
		this.year = year;
		this.dataFormat = dataFormat;
	}
	public Composition() {
		this.title = "";
		this.dataFormat = "";
		this.year = "";
	}
	
	public Composer getComposer() {
		return composer;
	}
	public void setComposer(Composer composer) {
		this.composer = composer;
	}
	public String getLastName() {
		return this.composer.getLastName();
	}
	public String getFirstName() {
		return this.composer.getFirstName();
	}
	public String getSecondName() {
		return this.composer.getSecondName();
	}
	public String getBirtYear() {
		return this.composer.getBirthYear();
	}
	public String getDeathYear() {
		return this.composer.getDeathYear();
	}
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDataFormat() {
		return this.dataFormat;
	}
	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}
	public String getYear() {
		return this.year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getId() {
		return this.id;
	}
	public void setId() {
		if(obsCompositionList.size() == 0) {
			this.id = 1;
		} else {
			int maxId = 0;
			for (Composition composition : obsCompositionList) {
				if (composition.getId() > maxId) {
					maxId = composition.getId();
				}
			}
			this.id = maxId+1;
		}
	}
	/*public String toString() {
		String composerOutput;
		String titleOutput;
		String yearOutput;
		String dataFormatOutput;
		if(composer == null) {
			composerOutput = "Unbekannt";
		} else {
			 composerOutput = composer.getComposerNameWithYears();
		} 
		if(title == "") {
			titleOutput = "Unbekannt";
		} else {
			titleOutput = title;
		}
		if(year == "") {
			yearOutput = "Unbekannt";
		} else {
			yearOutput = year;
		}
		if(dataFormat == "") {
			dataFormatOutput = "Unbekannt";
		}
		else {
			dataFormatOutput = dataFormat;
		}
		return "Composer: " + composerOutput + "\n" + "Composer-ID: " + String.valueOf(composer.getId()) + 
				"\n" + "Title: " + titleOutput + "\n" + "Year: " + yearOutput + "\n" +
				"Data Format: " + dataFormatOutput + "\n" + "ID: " + id + "\n";
	}*/
	public static ObservableList<Composition> getObsCompositionList() {
		return obsCompositionList;
	}
	public static void setObsCompositionList(ObservableList<Composition> c) {
		obsCompositionList = c;
	}
	public static void addToObsCompositionList(Composition composition) {
		composition.setId();
		Composition.getObsCompositionList().add(composition);
		System.out.println("Composition ID: " + composition.getId());
	}
	public static void writeIntoFile(ObservableList<Composition> list) {
		 try {
			 ArrayList<Composition> arrayList = new ArrayList<>();
			 arrayList.addAll(list);
	         FileOutputStream fileOut =
	         new FileOutputStream("CompositionList.ser", false);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(arrayList);
	         out.close();
	         fileOut.close();
	         System.out.println("Serialized data is saved in SheetMusicManager-1.0-SNAPSHOT/bin/CompositionList.ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	@SuppressWarnings("unchecked")
	public static void readFile() {
	      try {
	         FileInputStream fileIn = new FileInputStream("CompositionList.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
			  ArrayList<Composition> compositionList = (ArrayList<Composition>) in.readObject();
	         in.close();
	         fileIn.close();
	    	 //System.out.println("File found");
			  for (Composition composition : compositionList) {
				  Composer.addToComposerList(composition.getComposer());
				  obsCompositionList.add(composition);
			  }
	      }catch (FileNotFoundException f) {
			  //System.out.println("File not found");

		  }catch (IOException i) {
			  //System.out.println("IOException");
			  i.printStackTrace();
		  }catch (ClassNotFoundException c) {
			  //System.out.println("Composition class not found");
			  c.printStackTrace();
		  }
	}
}

