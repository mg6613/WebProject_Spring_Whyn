package com.springlec.whyn.auser;

import javax.servlet.http.HttpServletRequest;


import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AuserController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/AuserList")
	public String auserList(Model model) {
		
		AuserDao auserDao = sqlSession.getMapper(AuserDao.class);
		
		
		// 유저정보 페이징
		int RowCount = auserDao.userViewRowCount();
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
		model.addAttribute("userList",auserDao.userManagementList());
		
		
		return "jiseokAuserViews/userManagement";
		
	}
	
	@RequestMapping("/AuserListPageing")
	public String auserListPageing(HttpServletRequest request,Model model) {
		
		AuserDao auserDao = sqlSession.getMapper(AuserDao.class);
		
		
		
		int RowCount = auserDao.userViewRowCount();
		
		

		// 유저정보 페이징
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
		model.addAttribute("userList",auserDao.userManagementList());
		
		
		return "jiseokAuserViews/userManagement";
	}
	
	@RequestMapping("AuserListDelete")
	public String auserListDelete(HttpServletRequest request, Model model) {
			
			AuserDao auserDao = sqlSession.getMapper(AuserDao.class);
			String[] deleteCheck = request.getParameterValues("deleteCheck");
	      //AUserLDao userdao = new AUserLDao();
	      for(int i=0;i<deleteCheck.length;i++) {
	    	  auserDao.userDelete(deleteCheck[i]);
	    	  
	    	  //System.out.println(deleteCheck[i]);
	      }
	      return "redirect:AuserList";
	}
	
	
}
