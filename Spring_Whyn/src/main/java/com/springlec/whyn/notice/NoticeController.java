package com.springlec.whyn.notice;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import com.springlec.whyn.HomeController;
import com.springlec.whyn.auser.AuserDao;

@Controller
public class NoticeController {
	

	
	
	
	@Autowired
	private SqlSession sqlSession;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping("/notice")
	public String notice(HttpServletRequest request,Model model) {
		NoticeIDao dao = sqlSession.getMapper(NoticeIDao.class);
		

		// 공지사항 페이징
		int RowCount = dao.noticeViewRowCount();
		int totalPage=(RowCount/10);
		if(RowCount%10!=0) {
			totalPage++;
		}

		int beginNum=0;
		int endNum=9;
		
		model.addAttribute("beginNum",beginNum);
		model.addAttribute("endNum",endNum);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("endNum",endNum);
		
		
		model.addAttribute("notice",dao.noticeManagementList());
		return "notice/noticeManagement";
		
	}
	@RequestMapping("/noticeListPageing")
	public String noticeListPageing(HttpServletRequest request,Model model) {
		NoticeIDao dao = sqlSession.getMapper(NoticeIDao.class);

		
		
		
		int RowCount = dao.noticeViewRowCount();
		
		

		// 공지사항 페이징
		int nowPage = Integer.parseInt(request.getParameter("page"));
		int pageRow=10;
		int beginNum=(1*pageRow)-pageRow;
		int endNum=(1*pageRow)-1;
		int totalPage=(RowCount/pageRow);
		if(RowCount%10!=0) {
			totalPage++;
		}
		
		for(int i=1;i<=totalPage;i++) {
			if(nowPage==i) {
				beginNum=(i*pageRow)-pageRow;
				endNum=(i*pageRow)-1;
			}
		}
		
		model.addAttribute("beginNum",beginNum);
		model.addAttribute("endNum",endNum);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("endNum",endNum);
		model.addAttribute("notice",dao.noticeManagementList());
		return "notice/noticeManagement";
		
		
	}
	
	
	@RequestMapping("/noticeGoWrite")
	public String noticGoeWrite() {
		
		return "notice/noticeWrite";
	}
	@RequestMapping("/noticeWrite")
	public String noticeWrite(HttpServletRequest request,Model model) {
		NoticeIDao dao = sqlSession.getMapper(NoticeIDao.class);
		dao.noticeWrite(request.getParameter("ntitle"),request.getParameter("ncontent"));
		return "redirect:/notice";
	}
	
	
	
	
	@RequestMapping("/noticeView")
	public String noticeView(HttpServletRequest request,Model model) {
		System.out.println("noticeView");
		NoticeIDao dao = sqlSession.getMapper(NoticeIDao.class);
		String nno =request.getParameter("nno");
		String count = Integer.toString(dao.noticeCountSelect(nno));
		System.out.println(count);
		dao.noticeCount(nno,count);
		model.addAttribute("noticeView",dao.noticeContentView(nno));
		return "notice/noticeView";
		
		
	}
//	@RequestMapping("/noticeView")
//	public String noticeCount(HttpServletRequest request,Model model) {
//		NoticeIDao dao = sqlSession.getMapper(NoticeIDao.class);
//		dao.noticeCount(request.getParameter("nno"),request.getParameter("nviewcount"));
//		return "notice/noticeView";
//	}
//	@RequestMapping("/noticeView")
//	public String noticeCountSelect(HttpServletRequest request,Model model) {
//		NoticeIDao dao = sqlSession.getMapper(NoticeIDao.class);
//		model.addAttribute("nviewcount",dao.noticeCountSelect(request.getParameter("nno")));
//		return "notice/noticeView";
//			
//	}
	
	
	
	
	@RequestMapping("/noticeUpdate")
	public String noticeUpdate(HttpServletRequest request, Model model) {
		NoticeIDao dao = sqlSession.getMapper(NoticeIDao.class);
		dao.noticeUpdate(request.getParameter("nno"), request.getParameter("ntitle"), request.getParameter("ncontent"));
		return "redirect:/notice";
		
	}
	
	@RequestMapping("/noticeDelete")
	public String noticeDelete(HttpServletRequest request,Model model) {
		NoticeIDao dao = sqlSession.getMapper(NoticeIDao.class);
		String[] deleteCheck = request.getParameterValues("deleteCheck");
	      //AUserLDao userdao = new AUserLDao();
	      for(int i=0;i<deleteCheck.length;i++) {
	    	  dao.noticeDelete(deleteCheck[i]);
	    	  
	    	  //System.out.println(deleteCheck[i]);
	      }
		return "redirect:/notice";
	}
	
	@RequestMapping("/noticeSearch")
	public String noticeSearch(HttpServletRequest request,Model model ) {
		NoticeIDao dao = sqlSession.getMapper(NoticeIDao.class);
	
		model.addAttribute("notice",dao.noticeSearch(request.getParameter("nSearchBox"), request.getParameter("nSearchBar")));
		int beginNum=0; //이게 없으면 페이징이 안돼서 검색한게 몇개가 된지 모름 글서 하나만뜸. 글서 이거해야
		int endNum = 10;
		model.addAttribute("beginNum",beginNum);
		model.addAttribute("endNum",endNum);
		System.out.println("여기"+dao.noticeSearch(request.getParameter("nSearchBox"), request.getParameter("nSearchBar")).size());
		return "notice/noticeManagement";
		
		
	}
	
	
	

}
