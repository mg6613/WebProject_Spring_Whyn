package com.springlec.whyn.notice;

import java.util.ArrayList;




public interface NoticeIDao {
	
	
	
	public ArrayList<NoticeDto> noticeManagementList(); //공지리스트 불러오기
	public void noticeDelete(String nno); //공지삭제
	public ArrayList<NoticeDto> noticeSearch(String nSearchBox, String nSearchBar); //검색
	public NoticeDto noticeContentView(String nno); // 공지클릭시 보이기
	public void noticeWrite(String ntitle,String ncontent); //공지 작성
	public int noticeViewRowCount(); //공지 페이징 갯수 세어주기 
	public void noticeCount(String nno,String nviewcount); //공지 조회수+1
	public int noticeCountSelect(String nno); //공지 조회수값 가져오기 
	public void noticeUpdate(String nno,String ntitle,String ncontent); //공지수정
	
	

}//--------------------------------
