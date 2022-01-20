package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.Salle;
import service.SalleService;

/**
 * Servlet implementation class MarqueController
 */
@WebServlet("/SalleController")
public class SalleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SalleService mas;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalleController() {
        super();
        mas = new SalleService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("op")==null) {
			
			String type = request.getParameter("type");
			String libelle = request.getParameter("libelle");
			mas.create(new Salle(type, libelle));
		}else if(request.getParameter("op").equals("delete")) {
			String id = request.getParameter("indice");
            mas.delete(mas.findById(Integer.parseInt(id)));
            
		}else if(request.getParameter("op").equals("update")) {
			String id = request.getParameter("indice1");
			String type = request.getParameter("type");
			String libelle = request.getParameter("libelle");
			Salle m = mas.findById(Integer.parseInt(id));
			m.setType(type);
			m.setLibelle(libelle);
			mas.update(m);

		}
		
		response.setContentType("application/json");
		List<Salle> salles =  mas.findAll();
		Gson json = new Gson();
		response.getWriter().write(json.toJson(salles));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
