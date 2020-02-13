package com.douzone.guestbook.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.guestbook.dao.GuestBookDao;
import com.douzone.guestbook.vo.GuestBookVo;

public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("a");
		
		if("add".equals(action)) {

			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String contents = request.getParameter("message");
			String ngDate = format1.format(date);	
			
			GuestBookVo vo = new GuestBookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContents(contents);
			vo.setNgDate(ngDate);
			
			new GuestBookDao().insert(vo);
			
			response.sendRedirect(request.getContextPath() + "/el");
			
		} else if("deleteform".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp"); // 보내야할 경로
			rd.forward(request, response);
		
		} else if("delete".equals(action)) {
			Long no = Long.parseLong(request.getParameter("no"));
			String password = request.getParameter("password");
			
			GuestBookVo vo = new GuestBookVo();
			vo.setNo(no);
			vo.setPassword(password);
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter pw = response.getWriter();
			boolean result = new GuestBookDao().delete(vo);
			
			if(result) {
				response.sendRedirect(request.getContextPath() + "/el");
			} else {
				pw.println("<script>alert('비밀번호가 틀렸습니다!!!'); location.href='" + request.getContextPath() + "/el';</script>");
				pw.flush();
			}
		
		} else {
			// list(default) 처리
			List<GuestBookVo> list = new GuestBookDao().findAll();
			
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp"); // 보내야할 경로
			rd.forward(request, response);
		}		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
