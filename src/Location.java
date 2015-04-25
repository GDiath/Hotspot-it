
public class Location {

	Double latitude;
	Double longitude;
	String accuracy;
	String context;
	String place_id;
	String woeid;
	Content_PlaceID_WoeID neighbourhood;
	Content_PlaceID_WoeID locality;
	Content_PlaceID_WoeID county;
	Content_PlaceID_WoeID region;
	Content_PlaceID_WoeID country;
	
	
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitute() {
		return longitude;
	}
	public void setLongitute(Double longitute) {
		this.longitude = longitute;
	}
	public String getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getPlace_id() {
		return place_id;
	}
	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}
	public String getWoeid() {
		return woeid;
	}
	public void setWoeid(String woeid) {
		this.woeid = woeid;
	}
	public Content_PlaceID_WoeID getNeighbourhood() {
		return neighbourhood;
	}
	public void setNeighbourhood(Content_PlaceID_WoeID neighbourhood) {
		this.neighbourhood = neighbourhood;
	}
	public Content_PlaceID_WoeID getLocality() {
		return locality;
	}
	public void setLocality(Content_PlaceID_WoeID locality) {
		this.locality = locality;
	}
	public Content_PlaceID_WoeID getCounty() {
		return county;
	}
	public void setCounty(Content_PlaceID_WoeID county) {
		this.county = county;
	}
	public Content_PlaceID_WoeID getRegion() {
		return region;
	}
	public void setRegion(Content_PlaceID_WoeID region) {
		this.region = region;
	}
	public Content_PlaceID_WoeID getCountry() {
		return country;
	}
	public void setCountry(Content_PlaceID_WoeID country) {
		this.country = country;
	}
	
	
}
