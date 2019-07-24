<!--
	Page connexion 
	Créé le 22/07/2019 par Stéphane Thomarat
-->
<%@page import="org.eniencheres.bo.ContratUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">

<!-- 
	Inclusion de la balise Head
	Modifié le 23/07/2019 par Christophe Michard 
-->
<jsp:include page="${ContratUrl.URL_FRG_BALISE_HEAD}">
	<jsp:param value="ENI-Enchères connexion ..." name="titre"/>
</jsp:include>

	<!--  Début du formulaire de complétion -->
	<form id="formConnexion" action="connection" method="post">
	<!-- Récupération de l'identifiant -->
		<div>
			<label id="lblFormConnexion" for="identifiant">Identifiant : </label> 
			<input type="text"
				name="identifiant" id="identifiant" required>
		</div>
	<!-- Récupération du mot de passe -->
		<div>
			<label id="lblFormConnexion" for="motdepasse">Mot de passe : </label> 
			<input type="password"
				name="motdepasse" id="motdepasse" required>
		</div>
	<!-- Bouton d'envoi du formulaire -->
		<div>
			<button type="submit">Connexion</button>
			<a href="">Mot de passe oublié</a> <a href="\Monprofil.jsp"></a>
		</div>
	<!-- Boutons mot de passe oublié et Créer un compte -->
		<button>Créer un compte</button>
	
	</form>

</body>
</html>