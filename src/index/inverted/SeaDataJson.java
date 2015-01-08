package index.inverted;

import java.io.Serializable;
import java.util.ArrayList;

public class SeaDataJson implements Serializable{
	private SeaWaterBean sevenWBean;
	private SeaWaterDataBean dataBean;
	private ArrayList<SeaDataJson> children;
	public SeaWaterBean getSevenWBean() {
		return sevenWBean;
	}
	public void setSevenWBean(SeaWaterBean sevenWBean) {
		this.sevenWBean = sevenWBean;
	}
	public SeaWaterDataBean getDataBean() {
		return dataBean;
	}
	public void setDataBean(SeaWaterDataBean dataBean) {
		this.dataBean = dataBean;
	}
	public ArrayList<SeaDataJson> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<SeaDataJson> children) {
		this.children = children;
	}
	
}
