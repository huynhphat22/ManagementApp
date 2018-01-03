package Model.MODEL;

public class PageQuery {

	private int page;
	private String sortBy;
	private int size;
	private boolean asc;
	private String searchText;
	private String searchBy;
	
	public PageQuery() {
		
	}

	public PageQuery(int page, String sortBy, int size, boolean asc, String searchText, String searchBy) {
		super();
		this.page = page;
		this.sortBy = sortBy;
		this.size = size;
		this.asc = asc;
		this.searchText = searchText;
		this.searchBy = searchBy;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
	
}
