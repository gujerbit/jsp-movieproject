package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "front", urlPatterns = { "*.do" })
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String charset = null;
	HashMap<String, Controller> map = null;
       
    public FrontController() {}

	public void init(ServletConfig config) throws ServletException {
		charset = config.getInitParameter("charset");
		
		map = new HashMap<String, Controller>();
		map.put("/MovieList.do", new MovieListController());
		map.put("/Login.do", new LoginController());
		map.put("/Register.do", new RegisterController());
		map.put("/Logout.do", new LogoutController());
		map.put("/TimeSelect.do", new TimeSelectController());
		map.put("/SeatSelect.do", new SeatSelectController());
		map.put("/Reservation.do", new ReservationController());
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(charset);
		
		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = url.substring(contextPath.length());
		
		Controller subController = map.get(path);
		subController.execute(request, response);
	}
}