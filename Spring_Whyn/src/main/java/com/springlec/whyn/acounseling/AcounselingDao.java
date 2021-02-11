package com.springlec.whyn.acounseling;

import java.util.ArrayList;

public interface AcounselingDao {
	
	
	public ArrayList<AcounselingDto> counselingManagementList();//select
	public ArrayList<AcounselingDto> counselingSearch(String result1, String result2);//select
	public void counselingDelete(String cno);//delete
	public AcounselingDto counselingContentView(String cno);//select
	public int counselingViewRowCount();//select int
	public void cviewcount(String cno);//update
	public int cviewSelect(String cno);//select int
	public ArrayList<AcounselingDto> newCounseling();//select
	public AcounselingDto todayContentView(String cno);//select
	public void counselingAnswer(String canswer,String cno);//update
	public String answer(String cno);//select
	
	
	
}
