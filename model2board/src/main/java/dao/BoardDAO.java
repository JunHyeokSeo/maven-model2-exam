// DAO(Data Access Object) 

package dao;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.BoardBean;

public class BoardDAO {
	
	// 싱글톤 : 객체 생성을 한번만 수행 하는것.
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	private SqlSession getSession() {
		SqlSession session=null;
		Reader reader=null;
		try {
			reader = Resources.getResourceAsReader("mybatis-config.xml");
			SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(reader);
			session = sf.openSession(true);			// auto commit
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return session;
	}
	
		
	// 글작성 : 원문작성
	public int insert(BoardBean board) throws Exception{
		int result = 0;
		SqlSession session = getSession();
		result = session.insert("insert", board);
		
		return result;
	}

	// 총데이터 갯수 구하기
	public int getCount() throws Exception{
		int result = 0;
		SqlSession session = getSession();
		result = ((Integer)session.selectOne("count")).intValue();
			
		return result;
	}

	// 글목록 : 데이터 10개 구하기	
	public List<BoardBean> getList(Map map) throws Exception{
//	public List<BoardBean> getList(int page) throws Exception{
		List<BoardBean> list = new ArrayList<BoardBean>();
		SqlSession session = getSession();
		list = session.selectList("list", map);
		
		return list;
	}

	// 조회수 1증가
	public void readcountUpdate(int board_num) throws Exception {
		SqlSession session = getSession();
		session.update("updateCount", board_num);
	}

	// 상세 페이지
	public BoardBean getDetail(int board_num) throws Exception{
		BoardBean board = new BoardBean();
		SqlSession session =  getSession();
		board = (BoardBean)session.selectOne("content", board_num);
		
		return board;
	}

	// 댓글 출력 순서 : board_re_seq값 증가
	public void updateSeq(BoardBean board) throws Exception{
		SqlSession session = getSession();
		session.update("updateSeq", board);
	}

	// 댓글 작성
	public int boardReply(BoardBean board) throws Exception{
		int result = 0;
		SqlSession session = getSession();
		result = session.insert("replyInsert", board);
		return result;
	}

	// 글수정
	public int update(BoardBean board) throws Exception{
		int result = 0;
		SqlSession session = getSession();
		result = session.update("update", board);
		return result;
	}

	// 글삭제
	public int delete(int board_num) throws Exception{
		int result = 0;
		SqlSession session = getSession();
		result = session.delete("delete", board_num);
		return result;
	}
}



