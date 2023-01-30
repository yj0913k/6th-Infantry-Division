package com.green.nowon.util;

import lombok.Getter;

@Getter
public class PageDTO {
	private int pageTotal;
	private int start;
	private int end;
	private int pLength;
	
	/**
	 * 페이지 개수 : defaut 5 
	 * @param page 페이지번호
	 * @param rowTotal 총 게시글 수
	 */
	public static PageDTO getInstance(int page, int rowTotal) {
		return new PageDTO(page, rowTotal);
	}
	
	/**
	 * 
	 * @param page 페이지번호
	 * @param rowTotal 총 게시글 수
	 * @param pLength 보여지는 페이지 번호 개수 숫자로 입력
	 */
	public static PageDTO getInstance(int page, int rowTotal, int pLength) {
		return new PageDTO(page, rowTotal, pLength);
	}
	
	/**
	 * 
	 * @param page 페이지번호
	 * @param rowTot 총 게시글 수
	 * @param limit 한페이지에 보여지는 게시글 수 
	 * @param pLength 보여지는 페이지 번호 개수 숫자로 입력
	 */
	public static PageDTO getInstance(int page, int rowTot, int limit, int pLength) {
		return new PageDTO(page, rowTot, limit, pLength);
	}
	///////////////////////////////////////////////////////
	////// 생성자  //////////////
	///////////////////////////////////////////////////////
	private PageDTO(int page, int rowTotal) {
		this(page, rowTotal, 5);
	}
	
	private PageDTO(int page, int rowTotal, int pLength) {
		this(page, rowTotal, 10, pLength);
	}

	private PageDTO(int page, int rowTotal, int limit, int pLength) {
		this.pLength=pLength;
		pageTotal=rowTotal / limit; //총페이지 수
		if(rowTotal % limit != 0) {
			pageTotal++;
		}
		int pGroup=page/pLength; 
		// 1/5,2/5,3/5,4/5,5/5 == 1
		//  0 , 0 , 0 , 0 , 1  == pGroup가 1이되도록
		if(page%pLength !=0) pGroup++;
		end=pGroup*pLength;
		start=end-pLength+1;
		
		//혹시 마지막페이지번호는? 총 페이지수 보다 클수는 없어요
		if(end > pageTotal) 
			end=pageTotal;
		
		
	}

	
}
