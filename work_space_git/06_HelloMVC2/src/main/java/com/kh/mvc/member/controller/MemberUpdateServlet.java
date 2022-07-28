package com.kh.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.util.MyHttpServlet;
import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

@WebServlet("/member/update")
public class MemberUpdateServlet extends MyHttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService service = new MemberService();
	
	@Override
	public String getServletName() {
		return "MemberUpdateServlet";
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Member loginMember = getSessionMember(req);
			
			if(loginMember == null) {
				sendCommonPage("잘못된 접근입니다.", "/", req, resp);
				return;
			}
			
			Member newMember = new Member();
			newMember.setNo(loginMember.getNo());
			newMember.setId(req.getParameter("userId"));
			newMember.setName(req.getParameter("userName"));
			newMember.setPhone(req.getParameter("phone"));
			newMember.setEmail(req.getParameter("email"));
			newMember.setAddress(req.getParameter("address"));
			newMember.setHobby(String.join(",", req.getParameterValues("hobby")));
				
			if(loginMember.getId().equals(newMember.getId()) == false) {
				sendCommonPage("잘못된 아이디 입니다.", "/member/view", req, resp);
				return;
			}
			
			int result = service.save(newMember);
			
			if(result > 0) { // 업데이트 성공
				newMember = service.findMemberById(newMember.getId());
				setSessionMember(req, newMember);
				sendCommonPage("회원정보 수정하였습니다.", "/member/view", req, resp);
			} else {
				sendCommonPage("오류로 회원정보를 수정할수 없습니다.", "/member/view", req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendCommonPage("인자가 잘못되었습니다.", "/member/view", req, resp);
		}
	}

}
