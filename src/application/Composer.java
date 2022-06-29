package application;

public class Composer {
	private String firstName;
	private String secondName;
	private String lastName;
	private int birthYear;
	private int deathYear;
	private int id;
	private static int count=0;
	
	public Composer(String firstName, String secondName, String lastName, int birthYear, int deathYear) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.lastName = lastName;
		this.birthYear = birthYear;
		this.deathYear = deathYear;
		count++;
		this.id = count;
	}
	public Composer() {
		super();
		this.firstName = "unknown";
		this.secondName = "unknown";
		this.lastName = "unknown";
		this.birthYear = 0;
		this.deathYear = 0;
		count++;
		this.id = count;
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
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	public int getDeathYear() {
		return deathYear;
	}
	public void setDeathYear(int deathYear) {
		this.deathYear = deathYear;
	}
	public String getComposer() {
		return getFullName() + " (" + birthYear + "-" + deathYear + ")";
	}
	public int getId() {
		return id;
	}
}
