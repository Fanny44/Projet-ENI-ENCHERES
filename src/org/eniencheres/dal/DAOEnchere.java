package org.eniencheres.dal;

import org.eniencheres.bo.Enchere;

public interface DAOEnchere extends DAO<Enchere>{

	void insertEnchere(Enchere pObject) throws DALException;

}
