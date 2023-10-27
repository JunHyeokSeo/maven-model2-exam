package service;

import dao.MemberDAO;
import model.MemberDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class Delete implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Delete");

		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");

		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO db = dao.getMember(id);
		if (db.getPasswd().equals(passwd)) {
			int result = dao.delete(id);
			if (result == 1) System.out.println("회원탈퇴 성공");
			session.invalidate();
		} else {
			out.println("<script>");
			out.println("alert('비번이 일치하지 않습니다.')");
			out.println("history.go(-1)");
			out.println("</script>");
			out.close();
		}
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./member/loginform.jsp");
		return forward;
	}
}
