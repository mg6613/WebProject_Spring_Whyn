package com.springlec.whyn.asimplecounseling;

import java.util.ArrayList;

public interface AsimpleCounselingDao {

	public ArrayList<AsimpleCounselingDto> simpleConList();//select
	public AsimpleCounselingDto simpleContentView(String scno);//select
	public void scviewcount(String scno);
	public int sviewSelect(String scno);//select int
	public ArrayList<AsimpleCounselingDto> newSimpleList();//select
	public void simpleDelete(String scno);//delete
}
