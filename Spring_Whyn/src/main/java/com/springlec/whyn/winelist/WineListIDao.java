package com.springlec.whyn.winelist;

import java.util.ArrayList;

public interface WineListIDao {
	
		// 셀렉트만
		public ArrayList<WineListDto> listDao(); 
		// 인설트
		public void writeDao(String pname, String pcount, String pcountry, String pcolor, String ptext, String pcontent);
		// 업데이트
		public void modifyDao(String pno, String pname, String pcount, String pcountry, String pcolor, String ptext, String pcontent);
		// 원하는 번호의 정보 셀렉트
		public WineListDto contentDao(String pno);
		// 삭제
		public void deleteDao(String pno);
		// 상품목록 페이징 갯수 세기
		public int wineListViewRowCount(); 
	}


