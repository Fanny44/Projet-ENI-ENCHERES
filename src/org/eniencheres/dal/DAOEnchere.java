package org.eniencheres.dal;

import org.eniencheres.bo.Enchere;
import org.eniencheres.bo.Utilisateur;

//DAOEnchere hérite des méthodes de DAO<T> 
//Mais possède aussi ses propres méthodes  
/**
* DAOEnchere héritant de DAO<T>
* @author Fanny
*
*/
public interface DAOEnchere extends DAO<Enchere>{
	
	//méthode spécifique à DAOEnchère

	/**
	 * insertion d'une enchère dans la BDD
	 * @param pObject
	 * @throws DALException
	 */

	void insertEnchere(Enchere pObject) throws DALException;

	
/**
 * 
 * @param montantEnchere
 * @param noArticle
 * @return retourne l'utilisateur ayant fait l'enchere avec le montant passé en paramètre et sur l'article passé en paramètre
 * @throws DALException
 */
	Utilisateur selectPseudo(int montantEnchere, int noArticle) throws DALException;

}
