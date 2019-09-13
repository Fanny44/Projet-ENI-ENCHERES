package org.eniencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eniencheres.bll.BLLException;
import org.eniencheres.bll.UtilisateurManager;
import org.eniencheres.bo.ContratUrl;
import org.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletMotDePasse
 */
public class ServletMotDePasse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMotDePasse() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(ContratUrl.URL_MOT_DE_PASSE);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		boolean actionOk = false; 
		String motDePasse = request.getParameter("txtMotDePasse");
		String confirmation = request.getParameter("txtConfirmationMotDePasse");
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setEmail(request.getParameter("txtEmail").trim());
		UtilisateurManager um = UtilisateurManager.getInstance(); 
		
		if(!motDePasse.isEmpty() && !confirmation.isEmpty()) {
			if(motDePasse.equals(confirmation)) {
				try {
					um.getUpdateMotPasse(utilisateur.getEmail(), motDePasse);
					actionOk=true; 
				} catch (BLLException e) {
					e.printStackTrace();
					request.setAttribute("messageErreur", e.getMessage());
				}
			}
		}
		
		if(actionOk) {
			response.sendRedirect(request.getContextPath()+"/connection");
			return;
		}else {
			request.getRequestDispatcher(ContratUrl.URL_ACCUEIL).forward(request, response);
		}
		
	}

}
