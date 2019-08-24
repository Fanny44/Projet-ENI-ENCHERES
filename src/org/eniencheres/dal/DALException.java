package org.eniencheres.dal;
/**
 * gestion des erreurs sur la dal
 * @author Fanny
 *
 */

public class DALException extends Exception{
	/**
	 * pour la sérialisation 
	 */
	private static final long serialVersionUID = 1l;
	
//constructeur
	
	public DALException(String message, Throwable exception) {
		super(message, exception); 
	}
	
	public DALException(String message) {
		super(message); 
	}

/**
 * méthode retournant le message d'erreur de la dal 	
 */
	
	@Override
	public String getMessage() {
		StringBuilder sb = new StringBuilder(); 
		sb.append(super.getMessage()); 
		return sb.toString(); 
	}
}
