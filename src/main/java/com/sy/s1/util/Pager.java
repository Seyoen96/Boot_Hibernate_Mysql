package com.sy.s1.util;

import org.springframework.stereotype.Component;

import lombok.Data;


	@Data
	@Component
	public class Pager {
	
		private Integer page;
		private Integer size = 10;
		
		private Integer perBlock = 5;
		private Integer curBlock;
		
		private Integer startRow;
		private Integer lastRow;
		
		private Integer totalPage;
		private Integer totalBlock;
		
		private Integer startNum;
		private Integer lastNum;
		
		private String kind;
		private String search;
	
		
		
		// 현재 페이지에서 보여줘야하는 데이터 시작 Row / 끝 Row
		// 예) 1p = 1 ~ 10 / 2p = 11 ~ 20 
		public void makeRow() {
			this.startRow = this.getPage() - 1;
			//this.lastRow = this.getCurPage() * this.getPerPage();		// mysql에서는 사용할 필요 X
		}
	
		// 페이지 갯수 만들기
		public void makePage(int totalPage) {
			//----- Page의 getTotalPage로 전체 갯수를 받아와서 계산 안해도됨	
			// totalCount로 totalPage 계산
			// 예시 ) 게시글 전체가 105개 / 페이지당 게시글 10개  = 10p,
			// 나누어 떨어지는게 아니면 1페이지 더 필요하므로 총 11p
//			totalPage = totalCount/this.size;
//			if(totalCount%this.size != 0) {
//				totalPage++;
//			}
			this.setTotalPage(totalPage);
			
			// totalPage로 totalBlock 계산
			// 블록은 한번에 보여줄 페이지탭의 갯수
			// 예시 ) 전체 총 11p / 노출 페이지 5p = 2block
			// 나누어 떨어지는게 아니면 1block 더 필요하므로 총 3block
			this.totalBlock = totalPage/perBlock;
			if(totalPage%perBlock != 0) {
				this.totalBlock++;
			}
			
			// 현재 블록 구하기
			// 예시 ) 현재 페이지가 11p 중 7p, / 블록당 5p
			// 현재페이지가 블록 갯수로 나눠떨어지지 않으면 + 1block 현재 2block
			this.curBlock = this.page/this.perBlock;
			if(this.page%this.perBlock != 0) {
				this.curBlock++;
			}
			
			// startNum = 5 * 1 + 1 = 6;
			// lastNum = 5 * 2 = 10;
			// 현재 노출되는 페이지 : 6 7 8 9 10
			// block에서 보여줄 pageNum : 6 ~ 10
			this.startNum = this.perBlock*(this.curBlock-1) + 1;
			this.lastNum = this.perBlock*this.curBlock;
			if(curBlock == this.totalBlock) {
				this.lastNum = this.totalPage;
			}
		}
		
		
		// getCurPage
		public Integer getPage() {
			if(this.page == null || this.page == 0)
				this.page = 1;
			return this.page;
		}
		
		//getPerpage
		
		public Integer getSize() {
			if(this.size == null || this.size == 0)
				this.setSize(10);
			return size;
		}
		

		public String getKind() {
			if(this.kind==null || this.kind.equals("")) {
				this.kind="title";
			}
			return kind;
		}
		
		public String getSearch() {
			
			if(this.search == null)
				this.search = "";
			
			return search;
		}
		
	}
