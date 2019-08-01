package org.eniencheres.dal;

import java.util.List;

import org.eniencheres.bo.Retrait;

public interface DAORetrait extends DAO<Retrait>{
	List<Retrait> selectRetraits() throws DALException;
}
