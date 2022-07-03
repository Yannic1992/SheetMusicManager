package application;

import java.io.Serializable;
import java.util.ArrayList;

public class Composer implements Serializable {
	
	private static final long serialVersionUID = 4980234L;
	private String firstName;
	private String secondName;
	private String lastName;
	private String birthYear;
	private String deathYear;
	private int id;
	private static transient int count=0;
	private static transient String tempComposerFullName;
	
	private static ArrayList<Composer> composerList = new ArrayList<>();
	
	public Composer(String firstName, String secondName, String lastName, String birthYear, String deathYear) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.lastName = lastName;
		this.birthYear = birthYear;
		this.deathYear = deathYear;
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
	public String getComposerWithAge() {
		return getFullName() + " (" + birthYear + "-" + deathYear + ")";
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
	public static ArrayList<Composer> getComposerList() {
		return composerList;
	}
	public static void setComposerList(ArrayList<Composer> c) {
		composerList = c;
	}
	public static Composer checkComposerList(Composer composer) {
		tempComposerFullName = composer.getFullName();
		for(Composer element : composerList) {
			if(tempComposerFullName.equals(element.getFullName())) {
				return element; //Composer is in composer list
			}
		}
		return null; //Composer isn't in composer list
	}
	public static void addToComposerList(Composer composer) {
		if(checkComposerList(composer) != null) {
			System.out.println("Composer already in list");
		}else {
			composerList.add(composer);
			System.out.println("New composer added to list");
		}
		
	}
		
}
