package service;

import dao.MemberDAO;
import model.MemberDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class Update implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Update");

		//alter 메세지 박스의 한글값 인코딩을 위한 코드
		response.setContentType("text/html; charset=utf-8");

		//post로 공유된 값의 인코딩
		request.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();

		MemberDTO member = new MemberDTO();
		member.setId(request.getParameter("id"));
		member.setPasswd(request.getParameter("passwd"));
		member.setName(request.getParameter("name"));
		member.setJumin1(request.getParameter("jumin1"));
		member.setJumin2(request.getParameter("jumin2"));
		member.setMailid(request.getParameter("mailid"));
		member.setDomain(request.getParameter("domain"));
		member.setTel1(request.getParameter("tel1"));
		member.setTel2(request.getParameter("tel2"));
		member.setTel3(request.getParameter("tel3"));
		member.setPhone1(request.getParameter("phone1"));
		member.setPhone2(request.getParameter("phone2"));
		member.setPhone3(request.getParameter("phone3"));
		member.setPost(request.getParameter("post"));
		member.setAddress(request.getParameter("address"));
		member.setGender(request.getParameter("gender"));

		String[] hobby = request.getParameterValues("hobby");
		String h = "";			// h = "공부-게임-";
		for(String h1 :  hobby) {
			h += h1 + "-";
		}
		member.setHobby(h);
		member.setIntro(request.getParameter("intro"));

		MemberDAO dao = MemberDAO.getInstance();
		//1명의 상세정보 구하기
		MemberDTO db = dao.getMember(member.getId());

		if (db.getPasswd().equals(member.getPasswd())) {
			int result = dao.update(member);
			if (result == 1) System.out.println("회원수정 성공");
		} else {
			out.println("<script>");
			out.println("alert('비번이 일치하지 않습니다.')");
			out.println("history.go(-1)");
			out.println("</script>");
			out.close();
			return null;
		}
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./member/main.jsp");
		return forward;
	}
}
