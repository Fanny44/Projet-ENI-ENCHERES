package org.eniencheres.dal;

import org.eniencheres.bo.Enchere;
import org.eniencheres.bo.Utilisateur;

public interface DAOEnchere extends DAO<Enchere>{

	void insertEnchere(Enchere pObject) throws DALException;

	Utilisateur selectPseudo(int montantEnchere, int noArticle) throws DALException;

}
