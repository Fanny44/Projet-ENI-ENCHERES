<!-- 
	Page modification ventre
	Créé le 09/09/2019 par Fanny Radigois
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="${ContratUrl.URL_FRG_BALISE_HEAD}">
	<jsp:param value="ENI-Enchères Modification vente ..." name="titre" />
</jsp:include>

<h1 id="inscriptionTitre">Modification de ma Vente</h1>

<br><br>
<% if(request.getAttribute("messageErreur") != null){ %>
	<p class="messageErreur"><%=request.getAttribute("messageErreur")%></p>
<%} %>

<form id="formDetailsVente" action="modification" method="post">
	
	<div id="image">
		<img src="http://placehold.it/150x150" alt="" id="image" />
	</div>
	
	<!-- Titre de l'article dont on regarde le détails -->
	<div id="nomArticle">
		<input id="nomArticle" type="text" required="required" value ="${article.getNomArticle()}">
		<input type="hidden" name="numArticle" value="${article.getNoArticle()}">
	</div>
	
	<!-- Description de l'article dont on regarde le détails -->
	<div id="descriptionArticle">
		<label class="lblDetail">Description : </label>
		<textarea id="textarea" rows="5" cols="40" required="required">${article.getDescription()}</textarea>
	</div>

	<!-- Affiche la catégorie de l'article dont on regarde le détails -->
	<div id="categorieArticle">
		<label class="lblDetail">Catégorie </label>
		<p class="txtDetail">${article.getLibelle()}</p>
	</div>

	<!-- Mise à prix de l'article -->
	<div id="miseAPrix">
		<label class="lblDetail">Mise à prix : </label>
		<input class="txtDetail" required="required" value="${article.getMiseAPrix()}">points
	</div>

	<!-- date du début de l'enchère -->
	<div id="debutEnchereArticle">
		<label class="lblDetail">Début de l'enchère :</label> 
		<input class="txtDetail" name="debutEnchere" id="debutEnchere"
			value="${article.getDebutEnchere()}" required="required">
	</div>


	<!-- date de la fin de l'enchère -->
	<div id="finEnchereArticle">
		<label class="lblDetail">Fin de l'enchère :</label> 
		<input class="txtDetail" name="finEnchere" id="finEnchere"
			value="${article.getFinEnchere()}" required="required">
	</div>

	<!-- adresse du retrait de l'article -->
	<div id="retrait">
		<label class="lblDetail">Retrait : </label>
		<input class="txtDetail" value="${article.getRue()}" required="required">
		<input class="txtDetail" value="${article.getCodePostal()}" required="required">
		<input class="txtDetail" value="${article.getVille()}" required="required">
	</div>

	<div id=boutons>


		<input class="boutons" type="submit" value="Enregistrer">
		<a href="<%=request.getContextPath()%>/Accueil"><input
			class="boutons" type="button" id="annuler" value="Annuler"></a>

	</div>
</form>


</body>
</html>