package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;

public class SeatSelectController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MovieDAO dao = MovieDAO.getInstance();
		int schNo = Integer.parseInt(req.getParameter("schNo"));
		ArrayList<Integer> list = dao.getRemainSeat(schNo);
		String path = "member/seatSelect.jsp";
		
		req.setAttribute("list", list);
		req.setAttribute("schNo", schNo);
		req.getRequestDispatcher(path).forward(req, res);
	}
}