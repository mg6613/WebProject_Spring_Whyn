package com.springlec.whyn.areview;

import java.util.ArrayList;


public interface aReViewIDao {

	// 리뷰 리스트 띄우기
	public ArrayList<aReViewDto> reviewManagementList();
	// 리뷰 검색
	public ArrayList<aReViewDto> reviewSearch(String result1, String result2);
	 //리뷰삭제 
	public void reviewDelete(String reno);
	// 리뷰 눌렀을때 보이기
	public aReViewDto reviewContentView(String reno);
	//페이징 갯수
	public int reviewViewRowCount();
	//리뷰 조회수 +1
	public void reviewCount(String reno);
	//리뷰  조회수 받아오기(출력)
	public int reviewSelect(String reno);
	// 메인리뷰 리스트 띄우기
	public ArrayList<aReViewDto> newReview(); 
	// 리뷰 눌렀을때 보이기
	public aReViewDto newReviewContentView(String reno);
	// 리뷰 눌렀을때 보이기
	public aReViewDto todayReviewContentView(String reno);
	
	public aReViewDto searchdto(String reviewSearchBox, String reviewSearchBar);
	

}
