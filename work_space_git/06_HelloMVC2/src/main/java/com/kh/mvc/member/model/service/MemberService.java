package com.kh.mvc.member.model.service;

import static com.kh.common.jdbc.JDBCTemplate.*;

import java.sql.Connection;

import com.kh.mvc.member.model.dao.MemberDAO;
import com.kh.mvc.member.model.vo.Member;

/**
 * 멤버에 관련된 기능 구현
 * 기능 : 로그인, 로그아웃, 회원가입, 회원탈퇴, 정보수정
 */
public class MemberService {
	private MemberDAO dao = new MemberDAO();
	
	public Member findMemberById(String id) {
		Connection conn = getConnection();
		Member member = dao.findMemberById(conn, id);
		close(conn);
		return member;
	}
	
	// 로그인기능, id pw를 DB에서 대조하여 인증된 사용자인지 검증하는 기능
	public Member login(String id, String pw) {
		Member member = findMemberById(id);

		// 꼼수 기능, 향후 pw를 hash코드로 대체할건데, pw 변경이 필요함으로 admin 따로 기능구현 
		if(member != null && member.getId().equals("admin")) {
			return member;
		}
		
		if(member != null && member.getPassword().equals(pw)) {
			return member;
		}else {
			return null;
		}
	}
	
	
	public int save(Member member) {
		int result = 0;
		Connection conn = getConnection();
		
		if(member.getNo() != 0) {
			result = dao.updateMember(conn, member);
		}else {
			result = dao.insertMember(conn, member);
		}
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int delete(int no) {
		Connection conn = getConnection();
		int result = dao.updateStatus(conn, no, "N");
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int updatePassword(int no, String password) {
		Connection conn = getConnection();
		int result = dao.updatePassword(conn, no, password);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	// 중복 가입 방지용
	public boolean isDuplicated(String id) {
		Connection conn = getConnection();
		Member member = dao.findMemberById(conn, id);
		close(conn);

		if(member != null) {
			return true; // 중복됨
		}else {
			return false; // 중복되지 않음! -> 회원가입 가능
		}
	}

}
