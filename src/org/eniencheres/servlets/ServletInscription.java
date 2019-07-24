package org.eniencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eniencheres.bll.BLLException;
import org.eniencheres.bll.UtilisateurManager;
import org.eniencheres.bo.ContratUrl;
import org.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletInscription
 * @author Christophe Michard
 * @since Créé le 23/07/2019
 */
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInscription() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(ContratUrl.URL_INSCRIPTION);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setPseudo(request.getParameter("txtPseudo"));
		utilisateur.setNom(request.getParameter("txtNom"));
		utilisateur.setPrenom(request.getParameter("txtPrenom"));
		utilisateur.setEmail(request.getParameter("txtEmail"));
		utilisateur.setTelephone(request.getParameter("txtTelephone"));
		utilisateur.setRue(request.getParameter("txtRue"));
		utilisateur.setCodePostal(request.getParameter("txtCodePostal"));
		utilisateur.setVille(request.getParameter("txtVille"));
		utilisateur.setMotDePasse(request.getParameter("txtMotDePasse"));

		UtilisateurManager um = UtilisateurManager.getInstance();
		
		boolean ok = false;
		
		try {
			um.insert(utilisateur);
			ok = true;
		} catch (BLLException e) {
			e.printStackTrace();
			request.setAttribute("messageErreur", e.getMessage());
		}
		
		RequestDispatcher rd = null;
		HttpSession hs = request.getSession();

		if(ok) {
			hs.setAttribute("connecter", true);
			rd = request.getRequestDispatcher(ContratUrl.URL_ACCUEIL);
		}else {
			rd = request.getRequestDispatcher(ContratUrl.URL_INSCRIPTION);
		}

		rd.forward(request, response);
	}

}