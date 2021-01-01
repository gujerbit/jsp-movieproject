package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;
import vo.ScheduleVO;

public class TimeSelectController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MovieDAO dao = MovieDAO.getInstance();
		String outMovieName = req.getParameter("movieName");
		ArrayList<ScheduleVO> list = dao.getScheduleList(outMovieName);
		String path = "member/timeSelect.jsp";
		
		if(list != null) {
			req.setAttribute("list", list);
			req.getRequestDispatcher(path).forward(req, res);
		} else {
			req.setAttribute("error", "list does not exits");
			req.getRequestDispatcher(path).forward(req, res);
		}
	}
}
