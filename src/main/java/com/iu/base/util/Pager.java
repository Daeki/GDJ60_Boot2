package com.iu.base.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pager {
	
	//page 번호 담을 변수
	private Long page;
	
	//한페이지에 보여줄 글의 갯수
	private Long perPage;
	
	//시작 index 번호
	private Long startRow;
	
	//시작 page 번호
	private Long startNum;
	//끝 page 번호
	private Long lastNum;
	
	//검색 종류
	private String kind;
	
	//검색어
	private String search;
	
	//이전 블럭 유무
	private boolean pre; //false 이전 X, true O
	
	//다음 블럭 유무
	private boolean next;//false 다음 X, true O
	
	//page 계산하는 메서드
	public void makeNum(Long totalCount) {
		//1. 전체 글의 갯수를 받아옴
		
		//2. 전체 글의 갯수로 전체 페이지 갯수 구하기
		Long totalPage = totalCount/this.getPerPage();
		if(totalCount%this.getPerPage() != 0) {
			totalPage++;
		}
		
		//3. 전체 페이지로 전체 블럭의 갯수 구하기
		Long totalBlock = totalPage/5;
		if(totalPage%5 != 0) {
			totalBlock++;
		}
		
		//4. page 번호로 현재 블럭 번호 구하기
		Long curBlock = this.getPage()/5;
		if(this.getPage()%5 !=0) {
			curBlock++;
		}
		
		
		//5. 현재 블럭 번호로 시작 번호와 끝번호 구하기
		startNum = (curBlock-1)*5+1;
		lastNum = curBlock*5;
		
		//6. 현재블럭 번호가 마지막 블럭 이라면 끝번호는 전체 페이지 번호와 같음
		if(curBlock==totalBlock) {
			lastNum=totalPage;
		}
		
		//7. 이전블럭, 다음블럭 가능한지 유무
		if(curBlock != 1) {
			this.pre= !this.pre;
		}
		
		if(curBlock != totalBlock) {
			this.next=true;
		}
		
		
		
	}
	
	//시작 index 번호를 계산하는 메서드
	public void makeStartRow() {
		//page = 1 , startRow=0
		//page = 2 , startRow=10
		//page = 3 , startRow=20
		this.startRow=(this.getPage()-1)*this.getPerPage();
	}
	
	public Long getPage() {
		if(this.page == null || this.page ==0) {
			this.page=1L;
		}
		return this.page;		
	}
	
	public Long getPerPage() {
		if(this.perPage == null || this.perPage ==0) {
			this.perPage=10L;
		}
		
		return this.perPage;
	}
	
	public String getSearch() {
		if(this.search == null) {
			this.search="";
		}
		
		return this.search;
	}
	

}
