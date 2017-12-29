package Model.MODEL;

public class Page {

	private Iterable<?> content;
	private long totalPages;
	
	public Page() {
		this.content = null;
		this.totalPages = 1;
	}
	
	
	
	public Page(Iterable<?> content, long totalPages) {
		this.content = content;
		this.totalPages = totalPages;
	}



	public Iterable<?> getContent() {
		return content;
	}
	public void setContent(Iterable<Object> content) {
		this.content = content;
	}
	public long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
}
