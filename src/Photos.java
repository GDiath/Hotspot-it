
public class Photos{	//Structure twn epistrefomenwn results 
	Integer page;
	Integer pages;
	Integer perpage;
	String total;
	Photo[] photo;
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public Integer getPerpage() {
		return perpage;
	}
	public void setPerpage(Integer perpage) {
		this.perpage = perpage;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public Photo[] getPhoto() {
		return photo;
	}
	public void setPhoto(Photo[] photo) {
		this.photo = photo;
	}

}
