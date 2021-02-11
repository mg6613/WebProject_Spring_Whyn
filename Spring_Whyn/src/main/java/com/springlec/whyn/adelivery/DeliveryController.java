package com.springlec.whyn.adelivery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class DeliveryController {

	@Autowired
	private SqlSession sqlSession;
	
	
	@RequestMapping("ADeliveryList")
	public String ADeliveryL(Model model) {
		DeliveryIDao dao = sqlSession.getMapper(DeliveryIDao.class);
		model.addAttribute("fivedate", dao.fivedate());
		model.addAttribute("tendate", dao.tendate());
		model.addAttribute("fifteen", dao.fifteen());
		
		return "delivery/deliveryManagement";
	}
	
	@RequestMapping("fiveContentView")
	public String fiveContentView(HttpServletRequest request, Model model) {
		DeliveryIDao dao = sqlSession.getMapper(DeliveryIDao.class);
		model.addAttribute("fiveContentView", dao.fiveContentView(request.getParameter("duserid")));
		
		return "delivery/deliveryView";
	}
	
	@RequestMapping("tenContentView")
	public String tenContentView(HttpServletRequest request, Model model) {
		DeliveryIDao dao = sqlSession.getMapper(DeliveryIDao.class);
		model.addAttribute("fiveContentView", dao.fiveContentView(request.getParameter("duserid")));

		return "delivery/deliveryView10";
	}
	
	@RequestMapping("fifteenContentView")
	public String fifteenContentView(HttpServletRequest request, Model model) {
		DeliveryIDao dao = sqlSession.getMapper(DeliveryIDao.class);
		model.addAttribute("fiveContentView", dao.fiveContentView(request.getParameter("duserid")));

		return "delivery/deliveryView15";
	}
	
	
	
	
	
	
}
