package com.sample.Model;

public class PagePaginationObj {
	
	private Integer totalPage;
	private Integer currentPage;
	private Integer totalRecord;
	private Integer recordPerPage;
	private Integer gotoPage;
	private Integer startRecord;
	private Integer endRecord;
	
	public PagePaginationObj(int totalReco, int gotoPage) {
		this.totalRecord = totalReco;
		this.currentPage = gotoPage;
		this.recordPerPage = 10;
		this.gotoPage = gotoPage;
		this.totalPage = totalReco / recordPerPage + 1;
		this.startRecord = (gotoPage-1) * recordPerPage + 1;
		this.endRecord = startRecord + recordPerPage - 1;
	}
	
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}
	public Integer getRecordPerPage() {
		return recordPerPage;
	}
	public void setRecordPerPage(Integer recordPerPage) {
		this.recordPerPage = recordPerPage;
	}
	public Integer getGotoPage() {
		return gotoPage;
	}
	public void setGotoPage(Integer gotoPage) {
		this.gotoPage = gotoPage;
	}

	public Integer getStartRecord() {
		return startRecord;
	}

	public void setStartRecord(Integer startRecord) {
		this.startRecord = startRecord;
	}

	public Integer getEndRecord() {
		return endRecord;
	}

	public void setEndRecord(Integer endRecord) {
		this.endRecord = endRecord;
	}
}
