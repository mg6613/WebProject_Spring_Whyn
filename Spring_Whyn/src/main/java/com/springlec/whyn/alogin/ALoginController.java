package com.springlec.whyn.alogin;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springlec.whyn.HomeController;
import com.springlec.whyn.acounseling.AcounselingDao;
import com.springlec.whyn.asimplecounseling.AsimpleCounselingDao;


@Controller
public class ALoginController {

	
	@Autowired
	private SqlSession sqlSession;//sql 세션 불러오기
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	
	
	@RequestMapping("adminloginCeked")
	public String adminloginCeked(HttpServletRequest request, Model model) {
		ALoginDao alogindao = sqlSession.getMapper(ALoginDao.class);
		// 아이디값 카운트를 해서 해당 값이 있으면 1, 없으면 0으로 저장시킴
		//즉 체크값이 0혹은 1
		int checked = alogindao.aloginchecked(request.getParameter("id"),request.getParameter("pw"));
		//model.addAttribute("logincheck", checked);
		System.out.println(checked);
		
		if(checked == 1 ) {
			return "adminloginAlert";
		}else {
			return "redirect:adminLogin";
		}
	}
	
	
	//로그인 메인 띄우는 창
	@RequestMapping("/adminLogin")
	public String adminLogin(Model model) {
		
		return "/adminLogin";
	}
	
	//알럿 ㄷ안떠서 테스트중 
	@RequestMapping("/adminloginAlert")
	public String adminloginAlert(Model model) {
		
		return "/adminloginAlert";
	}
	
	//알럿 ㄷ안떠서 테스트중 
	@RequestMapping("/adminMain")
	public String adminMain(Model model) {
		
		return "/adminMain";
	}
	
}
