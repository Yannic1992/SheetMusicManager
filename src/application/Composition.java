package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Composition implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Composer composer;
	private String title;
	private String dataFormat; // in which format is the sheet music available i.e. printed or as pdf
	private String year;
	private int id;
	private static int nextId = 1;
	//private static int count=0;
	
	private static ArrayList<Composition> compositionList = new ArrayList<>();
	private static ObservableList<Composition> obsCompositionList = FXCollections.observableArrayList();
	
	public Composition(String title, String year, String dataFormat) {
		this.title = title;
		this.year = year;
		this.dataFormat = dataFormat;
		//count++;
		//this.id = count;
	}
	public Composition() {
		this.title = "";
		this.dataFormat = "";
		this.year = "";
		//count++;
		//this.id = count;
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
	/*public static int getCount() {
		return count;
	}*/
	public int getId() {
		return this.id;
	}
	public void setId(int i) {
		this.id = i;
	}
	public static int getNextId() {
		return nextId;
	}
	public static void setNextId() {
		nextId = getObsCompositionList().get(getObsCompositionList().size()-1).getId()+1;
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
		Composition.getObsCompositionList().add(composition);
	}
	public static void writeIntoFile(ObservableList<Composition> list) {
		 try {
			 ArrayList<Composition> arrayList = new ArrayList<>();
			 arrayList.addAll(list);
			 
	         FileOutputStream fileOut =
	         new FileOutputStream("src/application/CompositionList.ser", false);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(arrayList);
	         out.close();
	         fileOut.close();
	         System.out.println("Serialized data is saved in src/application/CompositionList.ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	@SuppressWarnings("unchecked")
	public static void readFile() {
	      try {
	         FileInputStream fileIn = new FileInputStream("src/application/CompositionList.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         compositionList = (ArrayList<Composition>) in.readObject();
	         in.close();
	         fileIn.close();
	    	 System.out.println("File found");
	    	 for(int i = 0; i<compositionList.size(); i++) {
	    		 Composer.addToComposerList(compositionList.get(i).getComposer());
	    		 obsCompositionList.add(compositionList.get(i));
	    	 }
	    	 Composer.setNextId();
	    	 System.out.println("Set next ID for Composer: " + Composer.getNextId());
	    	 setNextId();
	    	 System.out.println("Set next ID for Composition: " + getNextId());
	      }catch (FileNotFoundException f) {
	    	  System.out.println("File not found");
	    	 return;
	      }catch (IOException i) {
	         System.out.println("Test");
	    	 return;
	      }catch (ClassNotFoundException c) {
	    	 System.out.println("Composer class not found");
	         c.printStackTrace();
	         return;
	      }
	}
}

