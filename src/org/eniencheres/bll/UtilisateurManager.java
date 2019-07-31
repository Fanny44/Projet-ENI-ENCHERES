package org.eniencheres.bll;

import java.util.ArrayList;
import java.util.List;

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

	/**
	 * Contrôle du mot de passe
	 * @author Stéphane Thomarat
	 * @since Créé le 22/07/2019
	 * @since Modifié le 31/07/2019 par Christophe Michard
	 * 
	 * @param motdepasse
	 * @return
	 */
	private boolean isValidPassword(String motdepasse) {
		boolean valide = false;
		
		//valide = motdepasse.length()>= 8 && utilisateur.getMotDePasse().equals(motdepasse);	
		valide = utilisateur.getMotDePasse().equals(motdepasse);	
			
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
	 * Suppression d'un compte utilisateur
	 * @author Christophe Michard
	 * @since Créé le 31/07/2019
	 * 
	 * @param pUtilisateur
	 * @throws BLLException
	 */
	public void delete(Utilisateur pUtilisateur) throws BLLException {
		
		if(pUtilisateur == null) {
			throw new BLLException("Impossible de supprimer le compte, Aucun utilisateur passé en paramètre");
		}

		try {
			dao.delete(pUtilisateur);
		} catch (DALException e) {
			throw new BLLException("Une erreur et survenue pendant la suppression du compte.\n\n"+e.getMessage());
		}
		
	}
	
	/**
	 * Modification d'un utilisateur
	 * @author Christophe Michard
	 * @since Créé le 29/07/2019
	 * @since Modifié le 30/07/2019
	 * 
	 * @param pUtilisateur
	 * @param pConfirmationPWD
	 * @throws BLLException
	 */
	public void update(Utilisateur pUtilisateur, String pConfirmationPWD) throws BLLException {
		sansEspacesUtilisateur(pUtilisateur);
		pConfirmationPWD = pConfirmationPWD.trim();
		
		//Contrôle de la longueur du mot de passe, de sa validité et de lla validité du psudo
		if (pUtilisateur.getMotDePasse().length() < 8) {
			throw new BLLException("Le mot de passe doit comporté minimum 8 caratères");
		}else if (!pUtilisateur.getMotDePasse().equals(pConfirmationPWD)){
			throw new BLLException("Le mot de passe et la confirmation ne correspondent pas");
		}else if (pUtilisateur.getPseudo().matches(".*[^a-zA-Z0-9].*")) {
			throw new BLLException("Le pseudo ne peut contenir que des caratères alphanumériques");
		}
		
		update(pUtilisateur);
	}

	/**
	 * Modification d'un utilisateur
	 * @author Christophe Michard
	 * @since Créé le 30/07/2019
	 * 
	 * @param pUtilisateur
	 * @throws BLLException
	 */
	public void update(Utilisateur pUtilisateur) throws BLLException {
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

		//Contrôle du code postal
		if(!codePostalValide(pUtilisateur.getCodePostal())) {
			throw new BLLException("Le code postal doit comporter 5 chiffres");
		}
		
		//Contrôle du téléphone
		if(!telephoneValide(pUtilisateur.getTelephone())) {
			throw new BLLException("Le n° de téléphone doit comporter 10 chiffres");
		}
				
		try {
			//On contrôle si l'adresse mail n'est pas déjà enregistré pour un autre compte
			utilisateurs = dao.selectAll();
			
			for (Utilisateur utilisateur : utilisateurs) {
				if(!utilisateur.getPseudo().equals(pUtilisateur.getPseudo()) && utilisateur.getEmail().equals(pUtilisateur.getEmail())) {
					throw new BLLException("Un utilisateur avec la même adresse mail est déjà enregistré");
				}
			}
						
			dao.update(pUtilisateur);
		} catch (DALException e) {
			throw new BLLException("Une erreur est survenue lors de la modification de l'utilsiateur\n"+e.getMessage());
		}
	}
	
	/**
	 * Ajout d'un utilisateur
	 * @author Christophe Michard
	 * @since Créé le 24/07/2019
	 * @since Modifié le 30/07/2019
	 * 
	 * @param pUtilisateur
	 * @param pConfirmationPWD
	 * @throws BLLException
	 */
	public void insert(Utilisateur pUtilisateur, String pConfirmationPWD) throws BLLException {
		sansEspacesUtilisateur(pUtilisateur);
		pConfirmationPWD = pConfirmationPWD.trim();
		
		//Contrôle de la longueur du mot de passe, de sa validité et de lla validité du psudo
		if (pUtilisateur.getMotDePasse().length() < 8) {
			throw new BLLException("Le mot de passe doit comporté minimum 8 caratères");
		}else if (!pUtilisateur.getMotDePasse().equals(pConfirmationPWD)){
			throw new BLLException("Le mot de passe et la confirmation ne correspondent pas");
		}else if (pUtilisateur.getPseudo().matches(".*[^a-zA-Z0-9].*")) {
			throw new BLLException("Le pseudo ne peut contenir que des caratères alphanumériques");
		}
		
		//Contrôle du code postal
		if(!codePostalValide(pUtilisateur.getCodePostal())) {
			throw new BLLException("Le code postal doit comporter 5 chiffres");
		}

		//Contrôle du téléphone
		if(!telephoneValide(pUtilisateur.getTelephone())) {
			throw new BLLException("Le n° de téléphone doit comporter 10 chiffres");
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
			throw new BLLException("Une erreur est survenue lors de la modification de l'utilsiateur\n"+e.getMessage());
		}
	}
	
	/**
	 * Vérification du code postal
	 * @author Christophe Michard
	 * @since Créé le 31/07/2019
	 * 
	 * @param pCodePostal
	 * @return
	 */
	private boolean codePostalValide(String pCodePostal) {
		boolean codeValide = true;
		
		if(pCodePostal != null && !pCodePostal.isEmpty()) {
			codeValide = pCodePostal.length() == 5 && pCodePostal.matches(".*[0-9].*");
		}

		return codeValide;
	}
	
	/**
	 * Vérification de l'adresse mail
	 * @author Christophe Michard
	 * @since Créé le 31/07/2019
	 * 
	 * @param pTelephone
	 * @return
	 */
	private boolean telephoneValide(String pTelephone) {
		boolean telValide = false;
		
		if(pTelephone != null && !pTelephone.isEmpty()) {
			telValide = pTelephone.length() == 10 && pTelephone.matches(".*[0-9].*");
		}
		
		return telValide;
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
			throw new BLLException("Utilisateur inconnu", e);
		}
		
		return uTemp;
	}
	
	/**
	 * Retrait des éventuels espaces en début en fin de données utilisateur
	 * @author Christophe Michard
	 * @since Créé le 30/07/2019
	 * 
	 * @param pUtilisateur
	 */
	private void sansEspacesUtilisateur(Utilisateur pUtilisateur) {
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
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
