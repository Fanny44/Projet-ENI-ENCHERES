package org.eniencheres.dal;

import java.util.List;

import org.eniencheres.bo.ListeEncheres;
import org.eniencheres.bo.Retrait;

/**
 * 
 * @author Christophe Michard
 * @since Créé le 23/07/2019
 */
public interface DAO<T> {
	
	/**
	 * Insertion
	 * @since modifié le 23/07 
	 * @author Fanny
	 * @throws DALException, SQLException
	 */
	void insert(T pObject) throws DALException;
	
	/**
	 * Update
	 * @param pObject
	 * @author Fanny
	 * @throws DALException
	 */
	void update(T pObject) throws DALException;
	
	/**
	 * Delete
	 * @param pObject
	 *	@author Fanny
	 * @throws DALException
	 */
	void delete(T pObject) throws DALException;
	
	/**
	 * Select All
	 * @return
	 * @throws DALException
	 */
	List<T> selectAll() throws DALException;
	
	/**
	 * Select By Id
	 * @param pObject
	 * @return
	 * @throws DALException
	 */
	T selectById(T pObject) throws DALException;

	List<Retrait> selectRetraits() throws DALException;
	
}
