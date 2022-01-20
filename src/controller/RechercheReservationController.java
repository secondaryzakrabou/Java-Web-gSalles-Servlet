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

@WebServlet(urlPatterns = { "/RechercheReservationController" })
public class RechercheReservationController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReservationService ms;
	private String idSalle;
	private List<Reservation> reservations;

	public RechercheReservationController() {
		super();
		ms = new ReservationService();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 if(request.getParameter("op").equals("search")) {
			idSalle = request.getParameter("indice");
			reservations = ms.findReservationBySalle((Integer.parseInt(idSalle)));
			System.out.println(ms.findReservationBySalle((Integer.parseInt(idSalle))).toString());
		}


		response.setContentType("application/json");
		List<Salle> salles = ms.findAllSalle();
		Gson json = new Gson();
		String bothList = "[" + json.toJson(salles) + "," + json.toJson(reservations) + "]";
		response.getWriter().write(bothList);

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
