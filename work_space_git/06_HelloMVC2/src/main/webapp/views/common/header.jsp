<%@page import="com.kh.mvc.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 헤더부 구현할 기능 리스트 -->
<!-- 1. 로그인 공통부 기능 구현 -->
<!-- 2. 메뉴(네비게이션) 기능 작성 -->

<%
	String path = request.getContextPath();
	String saveId = "";
	Member loginMember = (Member)session.getAttribute("loginMember");
	Cookie[] cookies = request.getCookies();
	
	if(cookies != null){
		for(Cookie c : cookies){
			if(c.getName().equals("saveId")){
				saveId = c.getValue();
				break;
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>KH 게시판</title>
	<link rel="stylesheet" href="<%=path%>/resources/css/main.css" >
	<script src="<%=path%>/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<header>
	<h2>Hello MVC2 - Board!</h2>
	<div class="login-container">
		<%if(loginMember == null){ %>
			<%-- 일반 로그인 Form 시작! --%>	
			<form id="loginFrm" action="<%=path%>/login" method="post">
				<table>
					<tr>
						<td>
							<input type="text" name="userId" id="userId" value="<%=saveId%>" placeholder="아이디" required="required">
						</td>
						<td></td>
					</tr>
					<tr>
						<td>
							<input type="password" name="userPwd" id="userPwd" placeholder="비밀번호" required="required">
						</td>
						<td>
							<input type="submit" value="로그인">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<label><input type="checkbox" name="saveId" <%= saveId.length() == 0 ? "":"checked" %>/> 아이디 저장</label>
							<input type="button" value="회원가입" onclick="location.href='<%=path%>/member/enroll';" /> 
						</td>
					</tr>
				</table>
			</form>
			<%-- 일반 로그인 Form 끝! --%>	
		<%} else { %>
			<%-- 로그인 성공부 시작!--%>
			<table>
				<tr>
					<td colspan="2">
						<%=loginMember.getName() %> 님 안녕하세요?
					</td>
				</tr>
				<tr>
					<td><button onclick="location.href='<%=path%>/member/view';">내정보</button></td>
					<td><button onclick="location.href='<%=path%>/logout';">로그아웃</button></td>
				</tr>
			</table>
			<%-- 로그인 성공부 끝!--%>
		<%} %>
	</div>

<nav>
	<ul class="main-nav">
		<li class="home"><a href="<%=path%>">Home</a></li> 
		<li class="notice"><a>공지사항</a></li> 
		<li class="board"><a href="<%=path%>/board/list">게시판</a></li> 
		<%if(loginMember != null && loginMember.getRole().equals("ROLE_ADMIN")){ %>
			<li class="admin-member"><a href="<%=path%>/admin/member">회원관리</a></li> 
		<%} %>
	</ul>
</nav>
</header>


