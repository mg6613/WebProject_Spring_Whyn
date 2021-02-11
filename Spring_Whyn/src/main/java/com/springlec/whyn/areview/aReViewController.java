package com.springlec.whyn.areview;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



public class aReViewController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("AReviewLCommand")
	public String AReviewLCommand(Model model) {
		aReViewIDao dao = sqlSession.getMapper(aReViewIDao.class);
		int noticeViewRowCount = dao.reviewViewRowCount();
		int totalPage=(noticeViewRowCount/10);
		if(noticeViewRowCount%10!=0) {
			totalPage++;
		}

		int beginNum=0;
		int endNum=9;
		model.addAttribute("beginNum",beginNum);
		model.addAttribute("endNum",endNum);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("reviewManagementList",dao.reviewManagementList());
		
		return "review/reviewManagement";
	}
	
	@RequestMapping("AReviewSearchCommand")
	public String AReviewSearchCommand(HttpServletRequest request, Model model) {
		
		aReViewIDao dao = sqlSession.getMapper(aReViewIDao.class);
		
		model.addAttribute("setReviewSearch",dao.searchdto(request.getParameter("reviewSearchBox"), request.getParameter("reviewSearchBar")));
		model.addAttribute("reviewManagementList", dao.reviewSearch(request.getParameter("reviewSearchBox"), request.getParameter("reviewSearchBar")));
		return "review/reviewManagement";
	}
	
	@RequestMapping("AReviewDeleteCommand")
	public String AReviewDeleteCommand(HttpServletRequest request, Model model) {
		
		aReViewIDao dao = sqlSession.getMapper(aReViewIDao.class);
		String[] deleteCheck = request.getParameterValues("deleteCheck");
		
	      for(int i=0;i<deleteCheck.length;i++) {
	    	  dao.reviewDelete(deleteCheck[i]);
	 	      }
		return "AReviewLCommand";
	}
	
	@RequestMapping("reviewContentView")
	public String reviewContentView(HttpServletRequest request, Model model) {
		
		aReViewIDao dao = sqlSession.getMapper(aReViewIDao.class);
		model.addAttribute("reviewContentView",dao.reviewContentView(request.getParameter("reno")));
		model.addAttribute("newReviewContentView",dao.newReviewContentView(request.getParameter("reno")));
		model.addAttribute("todayReviewContentView",dao.todayReviewContentView(request.getParameter("reno")));
		
		return "review/reviewView";
	}
	
	@RequestMapping("AReviewLPagingCommand")
	public String AReviewLPagingCommand(HttpServletRequest request, Model model) {
		
		aReViewIDao dao = sqlSession.getMapper(aReViewIDao.class);
		int RowCount = dao.reviewViewRowCount();
		
		int nowPage = Integer.parseInt(request.getParameter("page"));
		int pageRow=10;
		int beginNum=(nowPage*pageRow)-pageRow;
		int endNum=(nowPage*pageRow)-1;
		int totalPage=(RowCount/pageRow);
		if(RowCount%10!=0) {
			totalPage++;
		}
		model.addAttribute("reviewManagementList",dao.reviewManagementList());
		model.addAttribute("beginNum",beginNum);
		model.addAttribute("endNum",endNum);
		model.addAttribute("totalPage",totalPage);

		return "review/reviewManagement";
	}
	
	@RequestMapping("AReviewNewListCommand")
	public String AReviewNewListCommand(HttpServletRequest request, Model model) {
		
		aReViewIDao dao = sqlSession.getMapper(aReViewIDao.class);
		model.addAttribute("newReview",dao.newReview());
		return "review/newReviewList";
	}
	
	@RequestMapping("newReviewContentView")
	public String newReviewContentView(HttpServletRequest request, Model model) {
		
		aReViewIDao dao = sqlSession.getMapper(aReViewIDao.class);
		model.addAttribute("reviewContentView",dao.reviewContentView(request.getParameter("reno")));
		model.addAttribute("newReviewContentView",dao.newReviewContentView(request.getParameter("reno")));
		model.addAttribute("todayReviewContentView",dao.todayReviewContentView(request.getParameter("reno")));
		
		return "review/newReviewView";
	}
	
	@RequestMapping("reviewTodayList")
	public String reviewTodayList(HttpServletRequest request, Model model) {

		aReViewIDao dao = sqlSession.getMapper(aReViewIDao.class);
		model.addAttribute("reviewTodayList",dao.todayReviewContentView(request.getParameter("reno")));
		return "review/todayReviewList";
	}
	
	@RequestMapping("todayReviewContentView")
	public String todayReviewContentView(HttpServletRequest request, Model model) {

		aReViewIDao dao = sqlSession.getMapper(aReViewIDao.class);
		model.addAttribute("reviewContentView",dao.reviewContentView(request.getParameter("reno")));
		model.addAttribute("newReviewContentView",dao.newReviewContentView(request.getParameter("reno")));
		model.addAttribute("todayReviewContentView",dao.todayReviewContentView(request.getParameter("reno")));
		
		return "review/todayReviewView";
	}
	
}
