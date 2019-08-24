package org.eniencheres.dal;

import java.util.List;


/**
 * interface générique commun au classe nécessitant ces méthodes 
 * @author Christophe Michard
 * @since Créé le 23/07/2019
 */
public interface DAO<T> {
	
	/**
	 * Insertion
	 * @since modifié le 23/07 
	 * @author Fanny
	 * @param T pObject
	 * @throws DALException, SQLException
	 */
	void insert(T pObject) throws DALException;
	
	/**
	 * Update
	 * @param pObject
	 * @author Fanny
	 * @param T pObject
	 * @throws DALException
	 */
	void update(T pObject) throws DALException;
	
	/**
	 * Delete
	 *@param pObject
	 *@author Fanny
	 *@param T pObject
	 *@throws DALException
	 */
	void delete(T pObject) throws DALException;
	
	/**
	 * Select All
	 * @return une liste
	 * @throws DALException
	 */
	List<T> selectAll() throws DALException;
	
	/**
	 * Select By Id
	 * @param pObject
	 * @return une valeur
	 * @throws DALException
	 */
	T selectById(T pObject) throws DALException;


	
}
