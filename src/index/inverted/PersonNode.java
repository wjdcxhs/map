package index.inverted;

import java.util.ArrayList;

public class PersonNode {
	private Integer id;
	private String name;
	private ArrayList<PersonNode> pNodes;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<PersonNode> getpNodes() {
		return pNodes;
	}
	public void setpNodes(ArrayList<PersonNode> pNodes) {
		this.pNodes = pNodes;
	}
	
}
