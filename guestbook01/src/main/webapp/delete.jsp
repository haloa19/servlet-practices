<%@page import="java.io.PrintWriter"%>
<%@page import="com.douzone.guestbook.dao.GuestBookDao"%>
<%@page import="com.douzone.guestbook.vo.GuestBookVo"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
    
<%
	request.setCharacterEncoding("utf-8");
	
	Long no = Long.parseLong(request.getParameter("no"));
	String password = request.getParameter("password");
	
	GuestBookVo vo = new GuestBookVo();
	vo.setNo(no);
	vo.setPassword(password);
	
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter pw = response.getWriter();
	boolean result = new GuestBookDao().delete(vo);
	
	if(result) {
		response.sendRedirect("/guestbook01");
	} else {
		pw.println("<script>alert('비밀번호가 틀렸습니다!!!'); location.href='/guestbook01';</script>");
		pw.flush();
	}
	
	
%>
