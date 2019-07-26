<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>A Web Page</title>
</head>
<body>
	<h1>Nouvelle vente</h1>

	<form id="formNouvelleVente" action="nouvelleVente" method="post">



		<!-- Récupération du nom de l'article -->
		<div>
			<label id="lblFormArticle" for="article">Article : </label> <input
				type="text" name="article" id="article" required>
		</div>

		<!-- Récupération de la description de l'article -->
		<div>
			<label id="lblFormDescription" for="description">Description : </label>
			<textarea form="formNouvelleVente" rows="4" cols="50"> </textarea>
		</div>

		<!-- Récupération de la catégorie de l'article -->
		<div>
			<label id="categorieArticle">Catégorie</label> <select
				id="categorieArticle">
				<option value=""></option>
				<option value="informatique">Informatique</option>
				<option value="ameublement">Ameublement</option>
				<option value="vêtement">Vêtement</option>
				<option value="sportLoisirs">Sport & Loisirs</option>
			</select>
		</div>

		<!-- Récupération de la photo de l'article et affichage de la photo -->

		<input type='file' name="photoArticle" accept="image/*"
			onchange="readURL(this)" /> <img id="userPic" src="#" alt="image" />

		<!-- Récupération de la date de début de l'enchère -->
		<input id="dateDebutEnchere" type="date">

		<!-- Récupération de la date de fin de l'enchère -->
		<input id="dateFinEnchere" type="date">

		<!-- Récupération de l'adresse de retrait -->

		<fieldset form="adresseRetrait">
			<legend>Retrait</legend>
			Rue : <input type="text" name="rue"><br> Code Postal : <input
				type="text" name="codepostal"><br> Ville : <input
				type="text" name="ville"><br>
		</fieldset>

		<!-- Validation du formulaire -->
		<div>
			<button type="submit">Enregistrer</button>
			<button>Annuler</button>
		</div>

	</form>
</body>
</html>