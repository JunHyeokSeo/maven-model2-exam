package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import model.BoardBean;

public class BoardModify implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardModify");
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String page = request.getParameter("page");
		String board_pass = request.getParameter("board_pass");
		
		BoardBean board = new BoardBean();
		board.setBoard_num(board_num);
		board.setBoard_name(request.getParameter("board_name"));
		board.setBoard_subject(request.getParameter("board_subject"));		
		board.setBoard_content(request.getParameter("board_content"));
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardBean db = dao.getDetail(board_num); // 상세 정보 구하기
		
		// 비번 비교
		if(db.getBoard_pass().equals(board_pass)) {	// 비번 일치시
			int result = dao.update(board);	// update SQL문 실행
			if(result == 1) System.out.println("글수정 성공");
			
			out.println("<script>");
			out.println("location.href='./board/update.jsp?page="+page+"'");
			out.println("</script>");
			out.close();	
			
			return null;
			
		}else {	// 비번 불일치시
			out.println("<script>");
			out.println("alert('비번이 일치하지 않습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			
			return null;			
		}
		
//		ActionForward forward = new ActionForward();
//		forward.setRedirect(true);
//		forward.setPath("./BoardListAction.do?page="+page);
		
//		return forward;
	}

}
