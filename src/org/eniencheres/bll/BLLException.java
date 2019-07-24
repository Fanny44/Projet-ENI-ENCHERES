package org.eniencheres.bll;
/**
 * gestion des erreurs sur la bll
 * @author Christophe Michard
 * @since Créé le 24/07/2019
 */
public class BLLException extends Exception{
	
	/**
	 * Pour la sérialisation 
	 */
	private static final long serialVersionUID = -3649895372114373784L;

	/**
	 * Constructeur
	 * @param message
	 * @param exception
	 */
	public BLLException(String message, Throwable exception) {
		super(message, exception); 
	}
	
	/**
	 * Constructeur
	 * @param message
	 */
	public BLLException(String message) {
		super(message); 
	}

	/**
	 * Retourne le message d'erreur
	 */
	@Override
	public String getMessage() {
		StringBuilder sb = new StringBuilder(); 
		sb.append("Couche BLL - ").append(super.getMessage()); 
		return sb.toString(); 
	}
}
