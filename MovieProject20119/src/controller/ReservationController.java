package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;

public class ReservationController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MovieDAO dao = MovieDAO.getInstance();
		int schNo = Integer.parseInt(req.getParameter("schNo"));
		int seatNo = Integer.parseInt(req.getParameter("seatNo"));
		String id = req.getParameter("id");
		String path = "member/seatSelect.jsp";
		
		int updateResult = dao.updateRoomInfo(schNo);
		int insertResult = dao.reservationTicket(schNo, seatNo, id);
		
		if(updateResult > 0 && insertResult > 0) {
			req.setAttribute("success", "reservation success!");
			req.getRequestDispatcher(path).forward(req, res);
		} else {
			System.out.println(updateResult);
			System.out.println(insertResult);
			req.setAttribute("error", "reservation error");
			req.getRequestDispatcher(path).forward(req, res);
		}
	}
}
