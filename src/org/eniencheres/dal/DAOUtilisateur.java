package org.eniencheres.dal;

import org.eniencheres.bo.Utilisateur;

/**
 * Interface DAOUtilisateur
 * @author Christophe Michard
 * @since Créé le 23/07/2019
 */
public interface DAOUtilisateur extends DAO<Utilisateur> {

	public Utilisateur selectByPseudo(Utilisateur pObject) throws DALException; 
	
}
