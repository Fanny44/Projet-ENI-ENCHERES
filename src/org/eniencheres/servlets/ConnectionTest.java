package org.eniencheres.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eniencheres.dal.ConnectionProvider;
import org.eniencheres.dal.DALException;

/**
 * Servlet implementation class ConnectionTest
 */
@WebServlet("/ConnectionTest")
public class ConnectionTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Connection cnx = null; 
	try {
		cnx=ConnectionProvider.getConnection(); 
		System.out.println("La connexion est" + (cnx.isClosed()?"fermée":"ouverte")+".");
	}catch(DALException|SQLException e) {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		System.out.println("Une erreur est survenue lors de l'utilisation de la base de donnée : "+ e.getMessage());
	}finally {
		try {
			cnx.close();
		}catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println("Une erreur est survenue lors de la fermeture de la connexion : "+ e.getMessage());

			
		}
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
