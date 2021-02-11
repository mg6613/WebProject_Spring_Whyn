package com.springlec.whyn.auser;

import java.util.ArrayList;


public interface AuserDao {
	public ArrayList<AUserLDto> userManagementList();
	public void userDelete(String userno);
	public ArrayList<AUserLDto> userSearch(String result1, String result2);
	public AUserLDto userContentView(String userno); // 유저 눌럿을때유저정보보기
	public int userViewRowCount();// 유저 페이지 넘기기
	public void userUpdate(String userno,String userid,String username,String usertel, String useraddress1,String useraddress2, String useraddress3, String admincomment, int usersubscribe);
	public ArrayList<AUserLDto> newSubscribeList();
	public void uviewcount(String userno); //조회수 +1
	public int uviewSelect(String userno); // 조회수값 가져오기
	public AUserLDto newSubscribeContentView(String userno); //유저눌렀을때 보이기
	public AUserLDto subscribeContentView(String userno);//유저 눌렀을때 보이기
	
	
	

}
