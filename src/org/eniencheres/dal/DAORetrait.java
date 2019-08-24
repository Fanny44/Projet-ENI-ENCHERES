package org.eniencheres.dal;

import java.util.List;

import org.eniencheres.bo.Retrait;
//DAORetrait hérite des méthodes de DAO<T> 
//Mais possède aussi ses propres méthodes  
/**
* DAORetrait héritant de DAO<T>
* @author Stephane
*
*/
public interface DAORetrait extends DAO<Retrait>{
	
	//méthode spécifique à DAORetrait 
//TODO est ce utile ? 	
	
/**
 * 	
 * @return liste des retraits
 * @throws DALException
 */
	List<Retrait> selectRetraits() throws DALException;
}
