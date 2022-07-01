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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Composer composer;
	private String title;
	private String dataFormat; // in which format is the sheet music available i.e. printed or as pdf
	private String year;
	private int id;
	private static int count=0;
	
	private static ArrayList<Composition> compositionList = new ArrayList<>();
	
	
	public Composition(String title, String year, String dataFormat) {
		this.title = title;
		this.year = year;
		this.dataFormat = dataFormat;
		count++;
		this.id = count;
	}
	
	public Composition() {
		this.title = "";
		this.dataFormat = "";
		this.year = "";
		count++;
		this.id = count;
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
	public static int getCount() {
		return count;
	}
	public int getId() {
		return id;
	}
	public String toString() {
		String composerOutput;
		String titleOutput;
		String yearOutput;
		String dataFormatOutput;
		if(composer == null) {
			composerOutput = "Unbekannt";
		} else {
			 composerOutput = composer.getComposer();
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
	}
	public static ArrayList<Composition> getCompositionList() {
		return compositionList;
	}
	public static void setCompositionList(ArrayList<Composition> c) {
		compositionList = c;
	}
	public static void writeIntoFile(ArrayList<Composition> list) {
		 try {
	         FileOutputStream fileOut =
	         new FileOutputStream("src/application/ComposerList.ser", false);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(list);
	         out.close();
	         fileOut.close();
	         System.out.println("Serialized data is saved in src/application/ComposerList.ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	@SuppressWarnings("unchecked")
	public static void readFile() {
	      try {
	         FileInputStream fileIn = new FileInputStream("src/application/ComposerList.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         compositionList = (ArrayList<Composition>) in.readObject();
	         in.close();
	         fileIn.close();
	    	 System.out.println("File found");
	    	 for(int i = 0; i<compositionList.size(); i++) {
	    		 Controller.addToComposerList(compositionList.get(i).getComposer());
	    	 }
	    	 Composer.setNextId();
	    	 System.out.println("Set next ID for Composer: " + Composer.getNextId());
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

