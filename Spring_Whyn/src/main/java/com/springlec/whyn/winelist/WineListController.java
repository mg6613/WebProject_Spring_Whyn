package com.springlec.whyn.winelist;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springlec.whyn.notice.NoticeIDao;



@Controller
public class WineListController {
	
	private static final Logger logger = LoggerFactory.getLogger(WineListController.class);
	
	@Autowired
	private SqlSession sqlSession;
	
		// 리스트 띄우기
		@RequestMapping("/winelist")
		public String winelist(Model model) {
			// 
			WineListIDao dao = sqlSession.getMapper(WineListIDao.class); 
			
			// 와인리스트 페이징
						int RowCount = dao.wineListViewRowCount();
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
						
						// 와인리스트 목록 불러오기
						model.addAttribute("winelist",dao.listDao());
			
			return "/wineList/winelist";
		}
		
		// 리스트 내용보기
		@RequestMapping("wineContentView")
		public String view(HttpServletRequest request, Model model) {
			WineListIDao dao = sqlSession.getMapper(WineListIDao.class);
			model.addAttribute("wineContent_View", dao.contentDao(request.getParameter("pno")));
			return "/wineList/contentView";
		}
		
		// 불러온 내용 수정하기
		@RequestMapping("/modify")
		public String modify(HttpServletRequest request, Model model) {
			WineListIDao dao = sqlSession.getMapper(WineListIDao.class);
			dao.modifyDao(request.getParameter("pno"), request.getParameter("pname"), request.getParameter("pcount"), request.getParameter("pcountry"), request.getParameter("pcolor"), request.getParameter("ptext"), request.getParameter("pcontent"));
			return "redirect:/winelist";
		}
		
		// 작성 페이지 띄우기
		@RequestMapping("write_view") 
			public String writeForm() {
			return "wineList/wineWrite";
		}
		// 작성 액션
		@RequestMapping("write")
		public String write(HttpServletRequest request, Model model) {
			WineListIDao dao = sqlSession.getMapper(WineListIDao.class);
			dao.writeDao(request.getParameter("pname"), request.getParameter("pcount"), request.getParameter("pcountry"), request.getParameter("pcolor"), request.getParameter("ptext"), request.getParameter("pcontent"));
			return "redirect:/winelist";
		}
		// 삭제 액션
		@RequestMapping("delete")
		public String delete(HttpServletRequest request, Model model) {
			WineListIDao dao = sqlSession.getMapper(WineListIDao.class);
			String[] deleteCheck = request.getParameterValues("deleteCheck");
		      //AUserLDao userdao = new AUserLDao();
		      for(int i=0;i<deleteCheck.length;i++) {
		    	  dao.deleteDao(deleteCheck[i]);
		    	 
		      }
//			
//			
//			dao.deleteDao(request.getParameter("pno"));
			return "redirect:/winelist";
		}
		
		@RequestMapping("/AWineLPaging")
		public String wineListPageing(HttpServletRequest request,Model model) {
			WineListIDao dao = sqlSession.getMapper(WineListIDao.class);

			int RowCount = dao.wineListViewRowCount();
			
			

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
			model.addAttribute("winelist",dao.listDao());
			return "/wineList/winelist";
			
			
		}
		
		
	

}
