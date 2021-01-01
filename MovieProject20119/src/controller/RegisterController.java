package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import vo.MemberVO;

public class RegisterController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MemberDAO dao = MemberDAO.getInstance();
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String email = req.getParameter("email");
		String outTel = req.getParameter("tel");
		String year = req.getParameter("year");
		String month = req.getParameter("month");
		String day = req.getParameter("day");
		Timestamp birth = dao.changeDate(year, month, day);
		String tel = dao.changeTel(outTel);
		String path = "register.jsp";
		
		boolean result = dao.getDuplicateUserData(id);
		
		if(result) {
			MemberVO vo = new MemberVO(id, pw, email, tel, birth);
			int registerResult = dao.register(vo);
			
			if(registerResult > 0) {
				req.setAttribute("register", "register success!");
				req.getRequestDispatcher(path).forward(req, res);
			} else {
				req.setAttribute("error", "register fail!");
				req.getRequestDispatcher(path).forward(req, res);
			}
		} else {
			req.setAttribute("error", "this user data is duplicate!");
			req.getRequestDispatcher(path).forward(req, res);
		}
	}
}
