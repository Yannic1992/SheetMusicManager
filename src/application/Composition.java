package application;

public class Composition {
	private Composer composer;
	private String title;
	private String dataFormat; // in which format is the sheet music available i.e. printed or as pdf
	private int year;
	private int id;
	private static int count=0;
	
	public Composition(Composer composer, String title, String dataFormat, int year) {
		composer.addToComposerList(composer);
		this.composer = composer;
		this.title = title;
		this.dataFormat = dataFormat;
		this.year = year;
		count++;
		this.id = count;
	}
	
	public Composition() {
		this.composer.setFirstName("unknown");
		this.composer.setSecondName("unknown");
		this.composer.setLastName("unknown");
		this.title = "unknown";
		this.dataFormat = "unknown";
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
		return "Composer: " + composer.getComposer() + "\n" + "Title: " + title + "\n" + "Year: " + year + "\n" +
				"Data Format: " + dataFormat + "\n" + "ID: " + id + "\n";
	}
}

