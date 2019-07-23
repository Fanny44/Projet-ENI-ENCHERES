package org.eniencheres.dal;

import java.util.List;

/**
 * 
 * @author Christophe Michard
 * @since Créé le 23/07/2019
 */
public interface DAO<T> {
	
	/**
	 * Insertion
	 * @param pObject
	 * @return
	 * @throws DALException
	 */
	T insert(T pObject) throws DALException;
	
	/**
	 * Update
	 * @param pObject
	 * @return
	 * @throws DALException
	 */
	T update(T pObject) throws DALException;
	
	/**
	 * Delete
	 * @param pObject
	 * @return
	 * @throws DALException
	 */
	boolean delete(T pObject) throws DALException;
	
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
	
}
