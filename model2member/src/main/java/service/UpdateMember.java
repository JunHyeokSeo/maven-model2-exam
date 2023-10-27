package service;

import dao.MemberDAO;
import model.MemberDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateMember implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UpdateMember");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		// 1명의 상세 정보 구하기
		MemberDTO member = dao.getMember(id);
		System.out.println("수정폼:"+ member);
		
		String hobby = member.getHobby(); //hobby="공부-게임-등산-";
		String[] h = hobby.split("-");//h[0]="공부",h[1]="게임"
		
		// 공유 설정
		request.setAttribute("member", member);
		request.setAttribute("h", h);		
		
		// request객체로 공유한 경우에는 dispatcher 방식으로 포워딩을 해야
		// view 페이지에서 공유한 값에 접근할 수 있다.
		ActionForward forward = new ActionForward();
		forward.setRedirect(false); // dispatcher방식으로 포워딩
		forward.setPath("./member/updateform.jsp");
		
		return forward;
	}

}
