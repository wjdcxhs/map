package index.inverted;

import java.io.Serializable;

public class SeaWaterBean implements Serializable{
	private Integer id;
	private long when;
	private String whenString;
	private String attribute;
	private double value;
	private String what;
	private String who;
	private String where;
	private String from;
	private String why;
	private String how;
	public SeaWaterBean(){};
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public long getWhen() {
		return when;
	}
	public void setWhen(long when) {
		this.when = when;
	}
	public String getWhenString() {
		return whenString;
	}
	public void setWhenString(String whenString) {
		this.whenString = whenString;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getWhat() {
		return what;
	}
	public void setWhat(String what) {
		this.what = what;
	}
	public String getWho() {
		return who;
	}
	public void setWho(String who) {
		this.who = who;
	}
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getWhy() {
		return why;
	}
	public void setWhy(String why) {
		this.why = why;
	}
	public String getHow() {
		return how;
	}
	public void setHow(String how) {
		this.how = how;
	}
	@Override
	public String toString() {
		return "SeaWaterBean [id=" + id + ", when=" + when + ", whenString="
				+ whenString + ", attribute=" + attribute + ", value=" + value
				+ ", what=" + what + ", who=" + who + ", where=" + where
				+ ", from=" + from + ", why=" + why + ", how=" + how + "]";
	}
}
