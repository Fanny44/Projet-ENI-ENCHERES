package org.eniencheres.dal;

import org.eniencheres.bo.Utilisateur;

/**
 * Interface DAOUtilisateur
 * @author Christophe Michard //Fanny
 * @since Créé le 23/07/2019 // modifié le 23/07
 */
public interface DAOUtilisateur extends DAO<Utilisateur> {

	public Utilisateur selectByPseudo(String pseudo) throws DALException; 
	
}
