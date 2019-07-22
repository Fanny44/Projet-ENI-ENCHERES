<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Entête -->
<title>A Web Page</title>
</head>
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