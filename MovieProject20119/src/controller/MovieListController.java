package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;
import vo.MovieVO;

public class MovieListController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MovieDAO dao = MovieDAO.getInstance();
		ArrayList<MovieVO> list = dao.getMovieList();
		String path = "member/movieList.jsp";
		
		if(list == null) {
			req.setAttribute("error", "list does not exist");
			req.getRequestDispatcher(path).forward(req, res);
		} else  {
			req.setAttribute("list", list);
			req.getRequestDispatcher(path).forward(req, res);
		}
	}
}
