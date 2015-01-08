package index.inverted;

import java.io.Serializable;

public class SeaWaterDataBean implements Serializable{
	private Integer id;
	private String time;
	private double lng;
	private double lat;
	private String sourceFileName;
	private double temperature;
	private double tempQuality;
	private double pscal;
	private double psaclQuality;
	public SeaWaterDataBean(){}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public String getSourceFileName() {
		return sourceFileName;
	}
	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getTempQuality() {
		return tempQuality;
	}
	public void setTempQuality(double tempQuality) {
		this.tempQuality = tempQuality;
	}
	public double getPscal() {
		return pscal;
	}
	public void setPscal(double pscal) {
		this.pscal = pscal;
	}
	public double getPsaclQuality() {
		return psaclQuality;
	}
	public void setPsaclQuality(double psaclQuality) {
		this.psaclQuality = psaclQuality;
	}
	@Override
	public String toString() {
		return "SeaWaterDataBean [id=" + id + ", time=" + time + ", lng=" + lng
				+ ", lat=" + lat + ", sourceFileName=" + sourceFileName
				+ ", temperature=" + temperature + ", tempQuality="
				+ tempQuality + ", pscal=" + pscal + ", psaclQuality="
				+ psaclQuality + "]";
	};
}
