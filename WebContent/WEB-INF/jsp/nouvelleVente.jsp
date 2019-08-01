<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<br>
	<br>
	<%
		if(request.getAttribute("messageErreur")!=null){
	%>
		<p>${messageErreur}</p>
	<%
		}
	%>
	
	<form id="formNouvelleVente" action="nouvelleVente" method="post">


		<!-- Récupération du nom de l'article -->
		<div>
			<label id="lblFormArticle" for="article">Article : </label> <input
				type="text" name="article" id="article" required>
		</div>


		<!-- Récupération de la description de l'article -->
		<div>
			<label id="lblFormDescription" for="description">Description
				: </label>
			<textarea name="description" form="formNouvelleVente" rows="4"
				cols="50" required> </textarea>
		</div>

		<!-- Récupération de la catégorie de l'article -->
		<div>
			<label for="categorieArticle">Catégorie</label> <select
				name="categorieArticle" id="categorieArticle" required>
				<c:forEach items="${categories}" var="cat">
					<option value="${cat.noCategorie }">${cat.libelle}</option>
				</c:forEach>
			</select>
		</div>


		<!-- Récupération de la photo de l'article et affichage de la photo -->
		<div>
			<input type='file' name="photoArticle" accept="image/*"
				onchange="readURL(this)" /> <img id="userPic" src="#" alt="image" />
		</div>


		<!-- Récupération du prix de départ -->
		<div>
			<br> <label for="miseAPrix">Mise à prix :</label> <input
				type="number" id="miseAPrix" name="miseAPrix" min="5" max="10000"
				required>
		</div>


		<!-- Récupération de la date de début de l'enchère -->
		<div>
			<label for="dateDebutEnchere">Début de l'enchère :</label><input
				name="dateDebutEnchere" type="date" required>
		</div>



		<!-- Récupération de la date de fin de l'enchère -->
		<div>
			<label for="dateFinEnchere">Fin de l'enchère :</label><input
				name="dateFinEnchere" type="date" required>
		</div>


		<!-- Récupération de l'adresse de retrait -->
		<div>
			<fieldset form="adresseRetrait">
				<legend>
					<label for="adresseRetrait">Retrait</label>
				</legend>
				<label for="rue">Rue :</label> <input type="text" name="rue" value="${utilisateur.getRue()}"
					required><br><label for="codepostal"> Code Postal :</label> <input type="text"
					name="codepostal" value="${utilisateur.getCodePostal()}" required><br>
				<label for="ville">Ville :</label> <input type="text" name="ville"
					value="${utilisateur.getVille()}" required>
			</fieldset>
		</div>


		<!-- Validation du formulaire -->
		<div>
			<input value="Enregistrer" type="submit">
			<a href="<%=request.getContextPath()%>/Accueil"><button>Annuler</button></a>
		</div>

	</form>
</body>
</html>