<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Ent�te -->
<title>A Web Page</title>
</head>
<body>
<!-- Titre de la page -->
<h1>ENI-ench�res</h1>
<!--  D�but du formulaire de compl�tion -->
<form action="ServletEniEncheres" method="post">
<!-- R�cup�ration de l'identifiant -->
	<div>
		<label for="identifiant">Identifiant : </label> 
		<input type="text"
			name="identifiant" id="identifiant" required>
	</div>
<!-- R�cup�ration du mot de passe -->
	<div>
		<label for="motdepasse">Mot de passe : </label> 
		<input type="text"
			name="motdepasse" id="motdepasse" required>
	</div>
<!-- Bouton d'envoi du formulaire -->
	<div>
		<button type="submit">Connexion</button>
	</div>
<!-- Boutons mot de passe oubli� et Cr�er un compte -->
	<a href="">Mot de passe oubli�</a> <a href="\Monprofil.jsp"><button>Cr�er un compte</button></a>

</form>

</body>
</html>