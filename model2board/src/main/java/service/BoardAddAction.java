package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDAO;
import model.BoardBean;

public class BoardAddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardAddAction");
		
		String path = request.getRealPath("boardupload");
		System.out.println("path:"+ path);
		
		int size = 1024 * 1024;		// 1MB
		
		// 첨부파일은 MultipartRequest 클래스로 객체를 생성하면서 
		// 파일 업로드가 수행된다.
		MultipartRequest  multi = 
			new MultipartRequest(request, 
								 path, 	   // 업로드 디렉토리	
								 size, 	   // 업로드 파일크기(1MB)	
								 "utf-8",  // 한글 인코딩 설정
			new DefaultFileRenamePolicy());// 파일 중복문제 해결
		
		BoardBean board = new BoardBean();
		board.setBoard_name(multi.getParameter("board_name"));
		board.setBoard_pass(multi.getParameter("board_pass"));
		board.setBoard_subject(multi.getParameter("board_subject"));
		board.setBoard_content(multi.getParameter("board_content"));
		board.setBoard_file(multi.getFilesystemName("board_file"));
		
		BoardDAO dao = BoardDAO.getInstance();
		int result = dao.insert(board);	// 원문 글작성
		if(result == 1) System.out.println("글작성 성공");
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./BoardListAction.do");
		
		return forward;
	}

}
