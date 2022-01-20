package controller;

import beans.Reservation;
import beans.Salle;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.ReservationService;

@WebServlet(urlPatterns = { "/EntreDateController" })
public class EntreDateController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReservationService ms;
	private List<Reservation> reservations;

	public EntreDateController() {
		super();
		ms = new ReservationService();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String d1 = request.getParameter("dateUn").replace("-", "/");
		String d2 = request.getParameter("dateDeux").replace("-", "/");

		response.setContentType("application/json");
		List<Reservation> reservations =  ms.findReservationBetweenDate(d1,d2);
		Gson json = new Gson();
		response.getWriter().write(json.toJson(reservations));

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
