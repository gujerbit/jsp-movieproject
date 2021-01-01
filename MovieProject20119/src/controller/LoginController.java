package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import vo.MemberVO;

public class LoginController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MemberDAO dao = MemberDAO.getInstance();
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String path = "login.jsp";
		
		MemberVO vo = dao.getLoginData(id, pw);
		
		if(vo != null) {
			req.setAttribute("login", vo);
			req.getRequestDispatcher(path).forward(req, res);
		} else {
			req.setAttribute("error", "this user does not exits");
			req.getRequestDispatcher(path).forward(req, res);
		}
	}
}
