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

@WebServlet(urlPatterns = { "/ReservationController" })
public class ReservationController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReservationService ms;

	public ReservationController() {
		super();
		ms = new ReservationService();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {



		if (request.getParameter("op") == null) {
			String ref = request.getParameter("ref");
			String nomClient = request.getParameter("nomClient");
			String dateReservation = request.getParameter("dateReservation").replace("-", "/");
			String idSalle = request.getParameter("salle");
			ms.create(new Reservation(ref, new Date(dateReservation), nomClient, Integer.parseInt(idSalle)));
		}else if(request.getParameter("op").equals("delete")) {
			String id = request.getParameter("indice");
            ms.delete(ms.findById(Integer.parseInt(id)));
            
		}else if(request.getParameter("op").equals("update")) {
			String id = request.getParameter("indice1");
			String ref = request.getParameter("indice2");
			String dateR = request.getParameter("indice3").replace("-", "/");
			String nomClient = request.getParameter("indice4");
			String idS = request.getParameter("indice5");
			Reservation m = ms.findById(Integer.parseInt(id));
			m.setReference(ref);
			m.setDateReservation(new Date(dateR));
			m.setnomClient(nomClient);
			m.setIdSalle(Integer.parseInt(idS));
			ms.update(m);

		}

		response.setContentType("application/json");
		List<Salle> salles = ms.findAllSalle();
		List<Reservation> reservations = ms.findAll2();
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
