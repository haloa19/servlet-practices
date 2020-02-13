package jstl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/_03")
public class _03Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 *  1. 객체가 오래 지속되는 순서
		 *  Application(Context) Scope > Session Scope > Request Scope > Page Scope
		 * 
		 *  2. EL이 같은 이름에 값을 찾는 순서
		 *  Page Scope > Request Scope > Session Scope > Application(Context) Scope
		 * 
		 *  주의 : 같은 이름으로 여러 범위에 값을 저장하지 말 것!!!!!
		 * */
		// request scope
		List<UserVo> list = new ArrayList<>();
		UserVo vo1 = new UserVo();
		vo1.setNo(1L);
		vo1.setName("정혜진1");
		list.add(vo1);

		
		// session scope
		UserVo vo2 = new UserVo();
		vo2.setNo(2L);
		vo2.setName("정혜진2");
		list.add(vo2);
		
		UserVo vo3 = new UserVo();
		vo3.setNo(3L);
		vo3.setName("정혜진3");
		list.add(vo3);
	
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/03.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
