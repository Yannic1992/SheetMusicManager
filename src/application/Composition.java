package application;

import java.util.ArrayList;

public class Composition {
	private Composer composer;
	private String title;
	private String dataFormat; // in which format is the sheet music available i.e. printed or as pdf
	private int year;
	private int id;
	private static int count=0;
	private static ArrayList<Composition> compositionList = new ArrayList<>();
	
	public Composition(Composer composer, String title, String dataFormat, int year) {
		this.composer = composer;
		this.title = title;
		this.dataFormat = dataFormat;
		this.year = year;
		count++;
		this.id = count;
	}
	
	public Composition() {
		this.title = "";
		this.dataFormat = "";
		this.year = 0;
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
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
		String idOutput;
		if(composer == null) {
			composerOutput = "Unbekannt";
			//return "Composer: Nicht vorhanden" + "\n" + "Title: " + title + "\n" + "Year: " + year + "\n" +
				//	"Data Format: " + dataFormat + "\n" + "ID: " + id + "\n";
		} else {
			 composerOutput = composer.getComposer();
		} 
		if(title == "") {
			titleOutput = "Unbekannt";
		} else {
			titleOutput = title;
		}
		if(year == 0) {
			yearOutput = "Unbekannt";
		} else {
			yearOutput = String.valueOf(year);
		}
		if(dataFormat == "") {
			dataFormatOutput = "Unbekannt";
		}
		else {
			dataFormatOutput = dataFormat;
		}
		return "Composer: " + composerOutput + "\n" + "Title: " + titleOutput + "\n" + "Year: " + yearOutput + "\n" +
				"Data Format: " + dataFormatOutput + "\n" + "ID: " + id + "\n";
	}
}

