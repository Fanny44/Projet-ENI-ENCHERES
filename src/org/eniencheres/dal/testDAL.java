package org.eniencheres.dal;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eniencheres.bo.Utilisateur;

@WebServlet("/test")
public class testDAL extends HttpServlet {
	
	/**
	* 
	*/
	private static final long serialVersionUID = -4435031577130853545L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DAOUtilisateur utilisateurDao = DAOFactory.getUtilisateurDAO();

		Utilisateur u1 = new Utilisateur(6, "toto", "toto", "toto", "toto@gmail.com", "0543698745", "avenue toto",
				"44750", "Nantes", "Pa$$w0rd", 150, true);
		Utilisateur u2 = new Utilisateur(7, "titi", "titi", "titi", "titi@gmail.com", "8956321475", "avenue titi",
				"44750", "Nantes", "Pa$$w0rd", 480, false);

		//ajout des users
//		try {
//			System.out.println("ajout des users");
//			utilisateurDao.insert(u1);
//			System.out.println("User ajouté : " + u1.toString());
//			utilisateurDao.insert(u2);
//			System.out.println("User ajouté : " + u2.toString());

			// selection par l'id
//			Utilisateur u = utilisateurDao.selectById(u1); 
//			Utilisateur t = utilisateurDao.selectById(u2); 
//			System.out.println("Selection de l'user par no utilisateur : " + u.toString() +"\n"+ t.toString());
//		
			// select par pseudo
//			Utilisateur x = utilisateurDao.selectByPseudo(u1.getPseudo());
//			Utilisateur y = utilisateurDao.selectByPseudo(u2.getPseudo());
//			System.out.println("selection par pseudo : "+ x.toString());
//			System.out.println("selection par pseudo : "+ y.toString());
//			
			// select par pseudo
//			Utilisateur x = utilisateurDao.selectByEmail(u1.getEmail());
//			Utilisateur y = utilisateurDao.selectByEmail(u2.getEmail());
//			System.out.println("selection par email : "+ x.toString());
//			System.out.println("selection par email : "+ y.toString());

//		//selection de tous les user
//			List<Utilisateur> utilisateurs =utilisateurDao.selectAll();	
//			System.out.println("Sélection de tous les users " + utilisateurs.toString());
//			
//		//modif user
//		System.out.println("user avant modif : " + u1.toString());
//		System.out.println("user avant modif : " + u2.toString());
//		u1.setPseudo("tata");
//		u2.setPseudo("tutu");
//		utilisateurDao.update(u1);
//		utilisateurDao.update(u2);
//		System.out.println("user après modif : " + u1.toString());
//		System.out.println("user après modif : " + u2.toString());
//		
//		//suppresssion d'un article 
//		System.out.println("suppression des user u1 et u2");
//		utilisateurDao.delete(u1);
//		utilisateurDao.delete(u2);
//		utilisateurDao.selectAll(); 
//		System.out.println("suppression effectuée");
//		} catch (DALException e) {
//			e.printStackTrace();
//		}
	}
}
