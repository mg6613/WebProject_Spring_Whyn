package com.springlec.whyn.acounseling;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springlec.whyn.HomeController;
import com.springlec.whyn.asimplecounseling.AsimpleCounselingDao;

@Controller
public class AcounselingController {
	
	@Autowired
	private SqlSession sqlSession;//sql 세션 불러오기
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	

	@RequestMapping("/counselingManagementList")
	public String counselingManagementList(HttpServletRequest request, Model model) {
		AcounselingDao acounselingDao = sqlSession.getMapper(AcounselingDao.class);
		AsimpleCounselingDao asimpleCounselingDao = sqlSession.getMapper(AsimpleCounselingDao.class);
		
		
		////////////////////페이징///////////////////////////////////////
		int rowCount = acounselingDao.counselingViewRowCount();
		int totalPage=(rowCount/10);
		if(rowCount%10!=0) {
			totalPage++;
		}
		int beginNum=0;
		int endNum=9;
		////////////////////페이징///////////////////////////////////////
		
		
		model.addAttribute("counselingManagementList",acounselingDao.counselingManagementList());
		model.addAttribute("simpleConList",asimpleCounselingDao.simpleConList());
		model.addAttribute("beginNum", beginNum);
		model.addAttribute("endNum", endNum);
		model.addAttribute("totalPage", totalPage);
		return "counseling/counselingManagement";
	}
	////////////////////페이징///////////////////////////////////////
	@RequestMapping("/ACounselingLPaging")
	public String ACounselingLPaging(HttpServletRequest request, Model model) {
		AcounselingDao acounselingDao = sqlSession.getMapper(AcounselingDao.class);
		AsimpleCounselingDao asimpleCounselingDao = sqlSession.getMapper(AsimpleCounselingDao.class);
		int nowPage = Integer.parseInt(request.getParameter("page"));
		int rowCount = acounselingDao.counselingViewRowCount();
		
		int pageRow=10;
		int beginNum=(nowPage*pageRow)-pageRow;
		int endNum=(nowPage*pageRow)-1;
		int totalPage=(rowCount/pageRow);
		if(rowCount%10!=0) {
			totalPage++;
		}
		model.addAttribute("counselingManagementList",acounselingDao.counselingManagementList());
		model.addAttribute("simpleConList",asimpleCounselingDao.simpleConList());
		model.addAttribute("beginNum", beginNum);
		model.addAttribute("endNum", endNum);
		model.addAttribute("totalPage", totalPage);
		return "counseling/counselingManagement";
	}
	
	////////////////////카운슬상세페이지///////////////////////////////////////
	@RequestMapping("/counselingContentView")
	public String counselingContentView(HttpServletRequest request, Model model) {
		AcounselingDao acounselingDao = sqlSession.getMapper(AcounselingDao.class);
		model.addAttribute("counselingContentView",acounselingDao.counselingContentView(request.getParameter("cno")));
		return "counseling/counselingView";
	}
	////////////////////간편카운슬상세페이지///////////////////////////////////////
	@RequestMapping("/simpleconView")
	public String simpleConList(HttpServletRequest request, Model model) {
		AsimpleCounselingDao asimpleCounselingDao = sqlSession.getMapper(AsimpleCounselingDao.class);
		model.addAttribute("simpleConList",asimpleCounselingDao.simpleContentView(request.getParameter("scno")));
		return "counseling/simpleconView";
	}
	////////////////////간편카운슬삭제///////////////////////////////////////
	@RequestMapping("/simpleDelete")
	public String simpleDelete(HttpServletRequest request,Model model) {
		AsimpleCounselingDao asimpleCounselingDao = sqlSession.getMapper(AsimpleCounselingDao.class);
		asimpleCounselingDao.simpleDelete(request.getParameter("deleteCheck1"));
		return "redirect:counseling/counselingManagementList";
	}
	////////////////////일반카운슬삭제///////////////////////////////////////
	@RequestMapping("/counselingDelete")
	public String counselingDelete(HttpServletRequest request,Model model) {
		AcounselingDao acounselingDao = sqlSession.getMapper(AcounselingDao.class);
		acounselingDao.counselingDelete(request.getParameter("deleteCheck"));
		return "redirect:counseling/counselingManagementList";
	}
	////////////////////일반카운슬검색///////////////////////////////////////
	@RequestMapping("/ACounselingSearch")
	public String ACounselingSearch(HttpServletRequest request,Model model) {
		AcounselingDao acounselingDao = sqlSession.getMapper(AcounselingDao.class);
		AsimpleCounselingDao asimpleCounselingDao = sqlSession.getMapper(AsimpleCounselingDao.class);
		
		////////////////////페이징///////////////////////////////////////
		int rowCount = acounselingDao.counselingViewRowCount();
		int totalPage=(rowCount/10);
		if(rowCount%10!=0) {
			totalPage++;
		}
		int beginNum=0;
		int endNum=9;
		////////////////////페이징///////////////////////////////////////
		model.addAttribute("beginNum", beginNum);
		model.addAttribute("endNum", endNum);
		model.addAttribute("simpleConList",asimpleCounselingDao.simpleConList());
		model.addAttribute("counselingManagementList",acounselingDao.counselingSearch(request.getParameter("counselingSearchBox"), request.getParameter("counselingSearchBar")));
		return "counseling/counselingManagement";
	}
	
}
	
	
	

