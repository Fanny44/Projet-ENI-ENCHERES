package org.eniencheres.bll;

import org.eniencheres.bo.Utilisateur;
import org.eniencheres.dal.DALException;
import org.eniencheres.dal.DAOFactory;
import org.eniencheres.dal.DAOUtilisateur;

public class UtilisateurManager {

	// déclaration des variables
	private static UtilisateurManager instance;
	private DAOUtilisateur dao = null;
	private Utilisateur utilisateur = null;

	// Création d'une Design Pattern Singleton
	public static UtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}

	// constructeur afin de créer une instance d'UtilisateurManager
	private UtilisateurManager() {
		dao = DAOFactory.getUtilisateurDAO(); // GET d'une instance de DAOUtilisateur
	}

	/**
	 * Classe verifIdentite Vérifications concernant l'identité
	 * 
	 * @param identifiant
	 * @param motdepasse
	 * @return utilisateur
	 */
	public Utilisateur verifIdentite(String identifiant, String motdepasse) {

		if ((isValidPseudo(identifiant) || isValidEmail(identifiant)) && !isValidPassword(motdepasse)) {
			utilisateur = null;
		}

		return utilisateur;

	}

	// Méthode pour savoir si le mot de passe fait huit caractères à l'aide d'une
	// expression régulière
	private boolean isValidPassword(String motdepasse) {
//		String regEx = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-z^A-Z^0-9]).{8,}$";
//		return motdepasse.matches(regEx);
		boolean valide = false;
		
		valide = motdepasse.length()>= 8 && utilisateur.getMotDePasse().equals(motdepasse);	
			
		return valide;
	}

	// Méthode pour savoir si l'identifiant correspond à une adresse mail
	private boolean isValidEmail(String identifiant) {
		boolean valide = false;

		try {
			utilisateur = dao.selectByEmail(identifiant);
			if (utilisateur != null && utilisateur.getEmail().equals(identifiant)) {
				valide = true;
			}

		} catch (DALException e) {
			e.printStackTrace();
		}

		return valide;
	}

	// Méthode qui dit si l'identifiant existe en tant que pseudo dans la bdd
	private boolean isValidPseudo(String identifiant) {
		boolean valide = false;

		try {
			utilisateur = dao.selectByPseudo(identifiant);
			if (utilisateur != null && utilisateur.getPseudo().equals(identifiant)) {
				valide = true;
			}

		} catch (DALException e) {
			e.printStackTrace();
		}

		return valide;
	}
	
	/**
	 * Modification d'un utilisateur
	 * @author Christophe Michard
	 * @since Créé le 24/07/2019
	 * 
	 * @param pUtilisateur
	 * @throws BLLException
	 */
	public void update(Utilisateur pUtilisateur, String confirmationPWD) {
		
		try {
			dao.update(pUtilisateur);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Ajout d'un utilisateur
	 * @author Christophe Michard
	 * @since Créé le 24/07/2019
	 * 
	 * @param pUtilisateur
	 * @throws BLLException
	 */
	public void insert(Utilisateur pUtilisateur, String confirmationPWD) throws BLLException {
		//Retrait des éventuels espaces en début en fin de données
		pUtilisateur.setPseudo(pUtilisateur.getPseudo().trim());
		pUtilisateur.setNom(pUtilisateur.getNom().trim());
		pUtilisateur.setPrenom(pUtilisateur.getPrenom().trim());
		pUtilisateur.setEmail(pUtilisateur.getEmail().trim());
		pUtilisateur.setTelephone(pUtilisateur.getTelephone().trim());
		pUtilisateur.setRue(pUtilisateur.getRue().trim());
		pUtilisateur.setCodePostal(pUtilisateur.getCodePostal().trim());
		pUtilisateur.setVille(pUtilisateur.getVille().trim());
		pUtilisateur.setMotDePasse(pUtilisateur.getMotDePasse().trim());
		confirmationPWD = confirmationPWD.trim();
		
		//Contrôle de la longueur du mot de passe, de sa validité et de lla validité du psudo
		if (pUtilisateur.getMotDePasse().length() < 8) {
			throw new BLLException("Le mot de passe doit comporté minimum 8 caratères");
		}else if (!pUtilisateur.getMotDePasse().equals(confirmationPWD)){
			throw new BLLException("Le mot de passe et la confirmation ne correspondent pas");
		}else if (pUtilisateur.getPseudo().matches(".*[^a-zA-Z0-9].*")) {
			throw new BLLException("Le pseudo ne peut contenir que des caratères alphanumériques");
		}
		
		Utilisateur uTemp = null;
		
		try {
			//On contrôle si le pseudo saisi n'est pas déjà utilisé par un autre utilisateur
			uTemp = dao.selectByPseudo(pUtilisateur.getPseudo());
			if (uTemp != null) {
				throw new BLLException("Un utilisateur portant le même pseudo existe déjà");
			}else {
				//On contrôle si l'adresse mail n'est pas déjà enregistré pour un autre compte
				uTemp = dao.selectByEmail(pUtilisateur.getEmail());
				
				if (uTemp != null) {
					throw new BLLException("Un utilisateur avec la même adresse mail est déjà enregistré");
				}
			}
			
			dao.insert(pUtilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e.getMessage());
		}
	}
	
	/**
	 * Recherche d'un utilisateur par le pseudo
	 * @author Christophe Michard
	 * @since Créé le 26/07/2019
	 * 
	 * @param pPseudo
	 * @throws BLLException 
	 */
	public Utilisateur selectByPseudo(String pPseudo) throws BLLException {
		Utilisateur uTemp = null;
		
		try {
			uTemp = dao.selectByPseudo(pPseudo);
			
			if (uTemp == null) {
				throw new BLLException("Utilisateur "+pPseudo+" inconnu !");
			}
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e.getMessage());
		}
		
		return uTemp;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
