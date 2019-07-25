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
		utilisateur.setCredit(100);
		
		UtilisateurManager um = UtilisateurManager.getInstance();
		
		boolean controleOk = true;
		boolean insertOk = false;
		
		//Contrôle de la longueur du mot de passe, de sa validité et de lla validité du psudo
		if (utilisateur.getMotDePasse().length() < 8) {
			request.setAttribute("messageErreur", "Le mot de passe doit comporté minimum 8 caratères");
			controleOk = false;
		}else if (!utilisateur.getMotDePasse().equals(request.getParameter("txtConfirmMotDePasse"))){
			request.setAttribute("messageErreur", "Le mot de passe et la confirmation ne correspondent pas");
			controleOk = false;
		}else if (utilisateur.getPseudo().matches(".*[^a-zA-Z0-9].*")) {
			request.setAttribute("messageErreur", "Le pseudo ne peut contenir que des caratères alphanumériques");
			controleOk = false;
			
		}

		//Insert de l'utilisateur
		if (controleOk) {
			try {
				um.insert(utilisateur);
				insertOk = true;
			} catch (BLLException e) {
				e.printStackTrace();
				request.setAttribute("messageErreur", e.getMessage());
			}	
		}
		
		RequestDispatcher rd = null;
		HttpSession hs = request.getSession();

		//Redirection
		if(insertOk) {
			hs.setAttribute("connecter", true);
			hs.setAttribute("utilisateur", utilisateur);
			rd = request.getRequestDispatcher(ContratUrl.URL_ACCUEIL);
		}else {
			rd = request.getRequestDispatcher(ContratUrl.URL_INSCRIPTION);
		}

		rd.forward(request, response);
	}

}
