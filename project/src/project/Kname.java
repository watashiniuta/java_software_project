package project;

public class Kname {
	String lastName;
	String firstName;
	
	public Kname(String lastName, String firstName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public boolean equal(Kname kname) {
		boolean isEqual = false;
		
		if (kname.firstName.equals(firstName)) {
			isEqual = true;
		}
		
		return isEqual;
	}
	
	public boolean isKim() {
		boolean isTrue = false;
		
		if (lastName == "Kim") {
			isTrue = true;
		}
		
		return isTrue;
	}
	
	public void printEnglishStyle() {
		System.out.println("English Name Expressions: " + firstName + " " + lastName);
	}
}
