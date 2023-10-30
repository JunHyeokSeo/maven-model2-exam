package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import model.BoardBean;

public class BoardModifyForm implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardModifyForm");
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String page = request.getParameter("page");
		
		BoardDAO dao = BoardDAO.getInstance();
		
		// 상세 정보 구하기
		BoardBean board = dao.getDetail(board_num);
		
		// 공유 설정
		request.setAttribute("board", board);
		request.setAttribute("page", page);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);  // dispatcher 방식으로 포워딩
		forward.setPath("./board/board_modify.jsp");
		
		return forward;
	}

}
