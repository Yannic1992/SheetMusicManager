package com.yannic1992.sheetmusicmanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Composer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String secondName;
	private String lastName;
	private String birthYear;
	private String deathYear;
	private int id;
	//private static int nextId = 1;
	//private static int count=0;
	//private static String tempComposerFullName;
	
	private static ArrayList<Composer> composerList = new ArrayList<>();
	
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
			return this.lastName + " " + this.firstName;
		}else{
			return this.lastName + " " + this.firstName + " " + this.secondName;
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
	public String getComposerNameWithYears() {
		return getFullName() + " (" + this.birthYear + "-" + this.deathYear + ")" + ": " + this.id;
	}
	/*public static int getNextId() {
		return nextId;
	}
	public static void setNextId() {
		int maxId = 0;
		for(int i =0; i<composerList.size(); i++) {
			if(composerList.get(i).getId() > maxId) {
				maxId = composerList.get(i).getId();
			}
		}
		nextId = maxId+1;
	}*/
	public int getId() {
		return this.id;
	}
	public void setId() {
		if(composerList.size() == 0) {
			this.id = 1;
		} else {
			int maxId = 0;
			for(int i =0; i<composerList.size(); i++) {
				if(composerList.get(i).getId() > maxId) {
					maxId = composerList.get(i).getId();
				}
			}
			this.id = maxId+1;
		}
	}
	/*public static int getCount() {
		return count;
	}
	public static void setCount(int i) {
		count = i;
	}
	public static void startProgramNextId() {
		
	}*/
	public static ArrayList<Composer> getComposerList() {
		return composerList;
	}
	public static void setComposerList(ArrayList<Composer> c) {
		composerList = c;
	}
	public static Composer checkComposerList(Composer composer) {
		String tempComposerFullName = composer.getFullName();
		for(Composer element : getComposerList()) {
			if(tempComposerFullName.equals(element.getFullName())) {
				return element;
			}
		}
		return null;
	}
	public static void addToComposerList(Composer composer) {
		//composer.setId();
		if(checkComposerList(composer) != null) {
			System.out.println("Composer already in list");
		}else {
			Composer.getComposerList().add(composer);
			System.out.println("Composer ID: "+ composer.getId());
		}
		//setNextId();
	}
	public static Comparator<Composer> compListSortByName = new Comparator<Composer>() {
		@Override
		public int compare(Composer o1, Composer o2) {
			String compName1 = o1.getFullName().toUpperCase();
			String compName2 = o2.getFullName().toUpperCase();
			return compName1.compareTo(compName2);
		}
	};
}
