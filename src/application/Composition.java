package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Composition implements Serializable{

	private static final long serialVersionUID = 49809285897L;
	private Composer composer;
	private String title;
	private String dataFormat; //In which format is the sheet music available i.e. printed or as pdf
	private String year;
	private int id;
	private static transient int nextId; //Needed to get unique ID for next composition even if previous compositions have been deleted
	private static transient int count=0;
	
	private static ArrayList<Composition> compositionList = new ArrayList<>();
	
	public Composition(String title, String year, String dataFormat) {
		this.title = title;
		this.year = year;
		this.dataFormat = dataFormat;
	}

	public Composer getComposer() {
		return composer;
	}
	public void setComposer(Composer composer) {
		this.composer = composer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDataFormat() {
		return dataFormat;
	}
	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getId() {
		return id;
	}
	public void setId(int i) {
		this.id = i;
		System.out.println("Set ID for new Composition");
	}
	public static int getNextId() {
		return nextId;
	}
	public static void setNextId(int i) {
		nextId = i;
		System.out.println("Set ID for next composition: " + getNextId());
	}
	public static int getCount() {
		return count;
	}
	public static void setCount(int i) {
		count = i;
		System.out.println("Set new number of compositions");
	}
	public String toString() {
		String composerOutput;
		String titleOutput;
		String yearOutput;
		String dataFormatOutput;
		if(composer == null) {
			composerOutput = "Unbekannt";
		} else {
			 composerOutput = composer.getComposerWithAge();
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
		return "Composer: " + composerOutput + "\n" + "Composer-ID: " + composer.getId() + 
				"\n" + "Title: " + titleOutput + "\n" + "Year: " + yearOutput + "\n" +
				"Data Format: " + dataFormatOutput + "\n" + "ID: " + id + "\n";
	}
	public static ArrayList<Composition> getCompositionList() {
		return compositionList;
	}
	public static void setCompositionList(ArrayList<Composition> c) {
		compositionList = c;
	}
	public static void addToCompositionList(Composition composition) {
		compositionList.add(composition);
		System.out.println("Added new composition to composition list");
	}
	public static String compositionListToString() {
		String output = "";
		for(int i = 0; i<compositionList.size(); i++) {
			output = output + compositionList.get(i).toString() + "\n";
		}
			
		return output;
	}
	public static void writeIntoFile(ArrayList<Composition> list) {
		 try {
	         FileOutputStream fileOut =
	         new FileOutputStream("src/application/CompositionList.ser", false);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(list);
	         out.close();
	         fileOut.close();
	         System.out.println("Composition list is saved in src/application/CompositionList.ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	@SuppressWarnings("unchecked")
	public static void readFile() {
	      try {
	         FileInputStream fileIn = new FileInputStream("src/application/CompositionList.ser");
	         System.out.println("File found");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         compositionList = (ArrayList<Composition>) in.readObject();
	         System.out.println("Composition list refilled with saved data");
	         in.close();
	         fileIn.close();
	         setCount(compositionList.size());
	    	 
	    	 setNextId(getCompositionList().get(getCompositionList().size()-1).getId()+1);
	    	 
	    	 for(int i = 0; i<compositionList.size(); i++) {
	    		 Composer.addToComposerList(compositionList.get(i).getComposer());
	    	 }
	    	 System.out.println("Composer list refilled with saved data");
	    	 Composer.setCount(Composer.getComposerList().size());
	    	 Composer.setNextId(Composer.getComposerList().get(Composer.getComposerList().size()-1).getId()+1);
	    	 System.out.println("Reading file finished");
	      }catch (FileNotFoundException f) {
	    	  System.out.println("File not found");
	    	  setNextId(1);
	    	  Composer.setNextId(1);
	    	  return;
	      }catch (IOException i) {
	         System.out.println("Good luck finding the bug");
	    	 return;
	      }catch (ClassNotFoundException c) {
	    	 System.out.println("Composer class not found");
	         c.printStackTrace();
	         return;
	      }
	}
}

