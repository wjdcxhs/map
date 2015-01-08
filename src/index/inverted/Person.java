package index.inverted;

import java.io.Serializable;

public class Person implements Serializable{
	private String nameString;
	private String hobby;
	public Person(String nameString, String hobby) {
		super();
		this.nameString = nameString;
		this.hobby = hobby;
	}
	public String getNameString() {
		return nameString;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
}
