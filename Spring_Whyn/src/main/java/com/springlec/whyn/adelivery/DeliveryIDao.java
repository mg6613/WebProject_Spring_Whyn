package com.springlec.whyn.adelivery;

import java.util.ArrayList;


public interface DeliveryIDao {

	//method 
	//매달 5일
	public ArrayList<DeliveryDto> fivedate();
	//매달 10일
	public ArrayList<DeliveryDto> tendate();
	//매달 15일 
	public ArrayList<DeliveryDto> fifteen();
	//매달5일 리스트 클릭시 뷰
	public DeliveryDto fiveContentView(String duserid);
	
	
	
	
	
	
}
