<%@page import="org.eniencheres.bo.ContratUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">

<!-- 
	Inclusion de la balise Head
	Créé le 23/07/2019 par Christophe Michard 
-->
<jsp:include page="${ContratUrl.urlFrgBaliseHead}">
	<jsp:param value="ENI-Enchères connexion ..." name="titre"/>
</jsp:include>

<body>
<!-- Titre de la page -->
<h1>ENI-enchères</h1>
<!--  Début du formulaire de complétion -->
<form action="ServletEniEncheres" method="post">
<!-- Récupération de l'identifiant -->
	<div>
		<label for="identifiant">Identifiant : </label> 
		<input type="text"
			name="identifiant" id="identifiant" required>
	</div>
<!-- Récupération du mot de passe -->
	<div>
		<label for="motdepasse">Mot de passe : </label> 
		<input type="text"
			name="motdepasse" id="motdepasse" required>
	</div>
<!-- Bouton d'envoi du formulaire -->
	<div>
		<button type="submit">Connexion</button>
	</div>
<!-- Boutons mot de passe oublié et Créer un compte -->
	<a href="">Mot de passe oublié</a> <a href="\Monprofil.jsp"><button>Créer un compte</button></a>

</form>

</body>
</html>