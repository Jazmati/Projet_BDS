package bds.devweb.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bds.devweb.metier.Manager;
import bds.devweb.model.Etudiant;
import bds.devweb.model.Sport;


public class AdministratifBDSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdministratifBDSServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Listes de la barre de menu du header
			List<Sport> lisports = Manager.getInstance().listerLiSports();
			request.setAttribute("lisports", lisports);

		//Donn��es relatives �� la page
			List<Etudiant> listeEtudiantNotOK = Manager.getInstance().listerEtudiantNotOk();
			request.setAttribute("listeEtudiantNotOK", listeEtudiantNotOK);
		//Fin Donn��es relatives �� la page
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Pages/administratifBDS.jsp");
			view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if(type.equals("recherche_etudiant")){
			String id_etudiant = request.getParameter("id_etudiant");
			Etudiant etudiant = Manager.getInstance().getEtudiant(id_etudiant);
			Gson gson = new Gson();
			String etudiantJson = gson.toJson(etudiant);
			response.setContentType("application/json");
			response.getWriter().write(etudiantJson);
			System.out.println("Ca marche");
		}
	}

}
