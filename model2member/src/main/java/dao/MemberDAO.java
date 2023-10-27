// DAO(Data Access Object)

package dao;

import model.MemberDTO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {

	// 싱글톤 : 객체 생성을 한번만 수행 하는것.
	private static final MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}

	private SqlSession getSession() {
		SqlSession session=null;
		Reader reader=null;
		try {
			//reader 객체 생성
			reader = Resources.getResourceAsReader("mybatis-config.xml");
			//SqlSessionFactory : DB 연결을 수행하는 MyBatis 핵심 인터페이스
			SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(reader);
			session = sf.openSession(true);			// auto commit
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return session;
	}

	// 회원가입
	public int insert(MemberDTO member) {
		int result = 0;
		SqlSession session = null;
		try {
			session = getSession();
			result = session.insert("insert", member);
			session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// ID중복 검사(ajax)
	public int idcheck(String id) {
		int result = 0;
		SqlSession session = null;
		try {
			session = getSession();
			MemberDTO member = session.selectOne("idcheck", id);
			if(member != null) {	// 중복 ID
				result = 1;
			}else {					// 사용 가능한 ID
				result = -1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 로그인(회원인증)
	public int memberAuth(String id, String passwd) {
		int result = 0;
		SqlSession session = null;
		try {
			session = getSession();
			MemberDTO member = session.selectOne("idcheck", id);
			if(member != null) {	// 중복 ID
				if(member.getPasswd().equals(passwd)) {
					result = 1;      // 회원인증 성공
				}else {
					result = -1;	 // 회원인증 실패
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 회원 1명 상세 정보 구하기 : 수정폼, 수정, 삭제
	public MemberDTO getMember(String id) {
		MemberDTO member = new MemberDTO();
		SqlSession session = null;
		try {
			session = getSession();
			member = session.selectOne("idcheck", id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	// 회원정보 수정
	public int update(MemberDTO member) {
		int result = 0;
		SqlSession session = null;
		try {
			session = getSession();
			result = session.update("update", member);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 회원탈퇴
	public int delete(String id) {
		int result = 0;
		SqlSession session = null;
		try {
			session = getSession();
			result = session.delete("delete", id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
