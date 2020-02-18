package jstl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/_01")
public class _01Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 값	
		int iVal = 10;
		long lVal = 10;
		float fVal = 1.4f;
		boolean bVal = true;
		String sVal = "가\n나\n다\n라\n마";
		
		// 객체 테스트
		Object obj = null;
		UserVo vo = new UserVo();
		vo.setNo(1L);
		vo.setName("정혜진!!!!!!!");
		
		// map을 사용해서 여러 값을 한번에 넘기는 방법
		Map<String, Object> map = new HashMap<>();
		map.put("iVal", iVal);
		map.put("lVal", lVal);
		map.put("fVal", fVal);
		map.put("bVal", bVal);
		
		// 리퀘스트 범위에 담기
		request.setAttribute("ival", iVal);
		request.setAttribute("lval", lVal);
		request.setAttribute("fval", fVal);
		request.setAttribute("bval", bVal);
		request.setAttribute("sval", sVal);
		request.setAttribute("obj", obj);
		request.setAttribute("vo", vo);
		request.setAttribute("map", map);
		
		request.getRequestDispatcher("/WEB-INF/views/01.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
