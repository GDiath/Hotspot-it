
public class PhotoEntry{

	private String id;
	private Double lat;
	private Double lon;
	private String keywords;
	private String neighbourhood;
	private String locality;
	

	public PhotoEntry() {

		this.id = "";
		this.lat = 0.0;
		this.lon = 0.0;
		this.keywords = "";
		this.neighbourhood = "";
		this.locality = "";
	}
	

	public String toString(){
		return String.format("{$}%s{#}%f{#}%f{#}%s{#}%s{#}%s", id,lat,lon,keywords,neighbourhood,locality);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}
	
}
