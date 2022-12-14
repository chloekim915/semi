package com.kh.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.common.util.MyHttpServlet;
import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

@WebServlet(name = "updatePwd", urlPatterns = "/member/updatePwd")
public class MemberUpdatePwdServlet extends MyHttpServlet{
	private static final long serialVersionUID = 1L;

	private MemberService service = new MemberService();
	
	
	@Override
	public String getServletName() {
		return "MemberUpdatePwdServlet";
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/member/updatePwd.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			Member loginMember = (Member) session.getAttribute("loginMember");
			String newPwd = req.getParameter("userPwd");
			
			if(loginMember == null ) {
				sendCommonPage("잘못된 접근입니다.", "/", req, resp);
				return;
			}
			
			int result = service.updatePassword(loginMember.getNo(), newPwd);
			
			if(result > 0) {
				sendCommonPage("비밀번호가 수정되었습니다.","/","self.close()",req,resp);
				return ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 예외와 비밀번호 수정이 불가능할때
		sendCommonPage("비밀번호가 수정할수 없습니다.", "/member/updatePwd", req, resp);
	}

	
}
