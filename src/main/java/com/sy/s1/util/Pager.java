package com.sy.s1.util;

public class Pager {
	
	private Long curPage;
	private Integer perPage;
	
	private long totalCount;
	private long startRow;
	private long lastRow;
	private long totalPage;
	
	private long startNum;
	private long lastNum;
	private long totalBlock;
	private long curBlock;
	
	private String kind;
	private String search;
	
	
	public long getTotalBlock() {
		return totalBlock;
	}
	public void setTotalBlock(long totalBlock) {
		this.totalBlock = totalBlock;
	}
	public long getCurBlock() {
		return curBlock;
	}
	public void setCurBlock(long curBlock) {
		this.curBlock = curBlock;
	}
	public void setCurPage(Long curPage) {
		this.curPage = curPage;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public long getStartRow() {
		return startRow;
	}
	public void setStartRow(long startRow) {
		this.startRow = startRow;
	}
	public long getLastRow() {
		return lastRow;
	}
	public void setLastRow(long lastRow) {
		this.lastRow = lastRow;
	}
	public long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}
	public long getCurPage() {
		if(this.curPage==null||this.curPage==0) {
			this.curPage = 1L;
		}
		return curPage;
	}
	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}
	public Integer getPerPage() {
		if(this.perPage==null || this.perPage==0) {
			this.perPage=10;
		}
		
		return perPage;
	}
	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}
	public long getStartNum() {
		return startNum;
	}
	public void setStartNum(long startNum) {
		this.startNum = startNum;
	}
	public long getLastNum() {
		return lastNum;
	}
	public void setLastNum(long lastNum) {
		this.lastNum = lastNum;
	}
	
	
	public void makeRow() {
//		this.startRow = (this.getCurPage()-1)*this.getPerPage();		
		this.lastRow = this.getCurPage()*this.getPerPage();
		//pageable 사용 시
		this.startRow = this.getCurPage()-1;
	}
	
	
	public void makePage(long totalCount) {
		
		this.totalCount=totalCount;
		this.totalPage = totalCount/this.getPerPage();		
		if(totalCount%this.getPerPage()!=0) {
			this.totalPage ++;
		}
			long perBlock = 5L;
			this.totalBlock = this.totalPage/perBlock;
			if(totalPage%perBlock!=0) {
				this.totalBlock++;
			}
			
			// curPage로 curBlock 찾기
			this.curBlock = this.curPage/perBlock;
			if(this.curPage%perBlock!=0) {
				this.curBlock++;		
			}	
			
			this.lastNum = this.curBlock*perBlock;
			this.startNum = (this.curBlock-1)*perBlock+1;
			
			if(this.curBlock == this.totalBlock) {
				this.lastNum = this.totalPage;
			}
	
		
	}
	
	
	public String getKind() {
		if(this.kind==null || this.kind.equals("")) {
			this.kind="title";
		}
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getSearch() {
		if(this.search==null) {
			this.search="";
		}
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
	
	
	
	
	

}
