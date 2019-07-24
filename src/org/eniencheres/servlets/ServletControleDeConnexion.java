package org.eniencheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletControleDeConnexion
 */
@WebServlet("/ServletControleDeConnexion")
public class ServletControleDeConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String un=request.getParameter("uname");
        String pw=request.getParameter("pass");

        HttpSession session= request.getSession();
        session.setAttribute("name", un);

        response.sendRedirect("home.jsp");
				
	}

}
