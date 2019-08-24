package org.eniencheres.dal;

import org.eniencheres.bo.Utilisateur;

/**
 * Interface DAOUtilisateur héritant de DAO<T>
 * @author Christophe Michard //Fanny
 * @since Créé le 23/07/2019 // modifié le 23/07
 */

//DAOUtilisateur hérite des méthodes de DAO<T> 
//Mais possède aussi ses propres méthodes  

public interface DAOUtilisateur extends DAO<Utilisateur> {

/**
 * Selection un utilisateur grâce à son pseudo 	
 * @param pseudo
 * @return l'utilisateur qui correspond au pseudo passé en paramèrtre
 * @throws DALException
 */
	
	public Utilisateur selectByPseudo(String pseudo) throws DALException; 
	
/**
 * 	Selection un utilisateur grâce à son email
 * @param email
 * @return l'utilisateur qui correspond à l'email passé en paramèrtre
 * @throws DALException
 */
	public Utilisateur selectByEmail(String email) throws DALException ;
	
/**
 * Modification du crédit de l'utilisateur 	
 * @param credit
 * @param noUtilisateur
 * @throws DALException
 */

	void updateCreditUser(int credit, int noUtilisateur) throws DALException;

/**
 * Modification du crédit de l'utilisateur 	qui a fait une enchère inférieur	
 * @param noArticle
 * @param montantEnchere
 * @throws DALException
 */
	void modifCredAncienUser(int noArticle, int montantEnchere) throws DALException; 
	
}
