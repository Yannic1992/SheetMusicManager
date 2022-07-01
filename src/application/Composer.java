package application;

public class Composer {
	private String firstName;
	private String secondName;
	private String lastName;
	private String birthYear;
	private String deathYear;
	private int id;
	private static int nextId;
	private static int count=0;
	
	
	public Composer(String firstName, String secondName, String lastName, String birthYear, String deathYear) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.lastName = lastName;
		this.birthYear = birthYear;
		this.deathYear = deathYear;
	}
	public Composer() {
		this.firstName = "";
		this.secondName = "";
		this.lastName = "";
		this.birthYear = "";
		this.deathYear = "";
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFullName() {
		if(this.secondName == "") {
			return this.firstName + " " + this.lastName;
		}else{
			return this.firstName + " " + this.secondName + " " + this.lastName;
		}
	}
	public String getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}
	public String getDeathYear() {
		return deathYear;
	}
	public void setDeathYear(String deathYear) {
		this.deathYear = deathYear;
	}
	public String getComposer() {
		return getFullName() + " (" + birthYear + "-" + deathYear + ")";
	}
	public int getNextId() {
		return ++nextId;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int i) {
		this.id = i;
	}
	public static int getCount() {
		return count;
	}
	public static void setCount(int i) {
		count = i;
	}
	
}
