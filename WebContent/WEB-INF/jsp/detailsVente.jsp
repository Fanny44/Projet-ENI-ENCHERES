<%@page import="org.eniencheres.bo.ContratUrl"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.eniencheres.bo.Utilisateur"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<jsp:include page="${ContratUrl.URL_FRG_BALISE_HEAD}">
	<jsp:param value="ENI-Enchères ..." name="titre" />
</jsp:include>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="./js/DetailsVente.js"></script>
<div>
	<h1 id="detailsTitre">Détails vente</h1>
	<p id="fin"></p>
	<br>
	<br>
	<%
		if (request.getAttribute("messageErreur") != null) {
	%>
	<p class="messageErreur"><%=request.getAttribute("messageErreur")%></p>
	<%
		}
	%>


	<%
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String pseudo = user.getPseudo();
	%>
	<script type="text/javascript">	
 	var userSession = '<%=pseudo%>';
	</script>

	<form id="formDetailsVente" action="detailsVente" method="post">
		<div id="image">
			<img src="./images/chatons.jpg" alt="" id="image" />
		</div>
		<!-- Titre de l'article dont on regarde le détails -->
		<div id="nomArticle">
			<p id="nomArticle">${article.getNomArticle()}</p>
			<input type="hidden" name="numArticle"
				value="${article.getNoArticle()}">
		</div>
		<!-- Description de l'article dont on regarde le détails -->
		<div id="descriptionArticle">
			<label class="lblDetail">Description : </label>
			<textarea id="textarea" rows="5" cols="40" readonly="readonly">${article.getDescription()}</textarea>
		</div>

		<!-- Affiche la catégorie de l'article dont on regarde le détails -->
		<div id="categorieArticle">
			<label class="lblDetail">Catégorie </label>
			<p class="txtDetail">${article.getLibelle()}</p>
		</div>


		<!-- meilleurs enchères faite  -->
		<div id="meilleurOffre">
			<label class="lblDetail">Meilleure offre :</label> <input
				class="txtDetail" name="montantEnchere"
				value="${article.getMontantEnchere()}" readonly="readonly">points
			par <input class="txtDetail" name="pseudoMeilleureOffre"
				id="acheteur" value="${user.getPseudo()}" readonly="readonly">
		</div>


		<!-- Mise à prix de l'article -->
		<div id="miseAPrix">
			<label class="lblDetail">Mise à prix : </label>
			<p class="txtDetail">${article.getMiseAPrix()}points</p>
		</div>

		<!-- date du début de l'enchère -->
		<div id="debutEnchereArticle">
			<label class="lblDetail">Début de l'enchère :</label> <input
				class="txtDetail" name="debutEnchere" id="debutEnchere"
				value="${article.getDebutEnchere()}" readonly="readonly">
		</div>


		<!-- date de la fin de l'enchère -->
		<div id="finEnchereArticle">
			<label class="lblDetail">Fin de l'enchère :</label> <input
				class="txtDetail" name="finEnchere" id="finEnchere"
				value="${article.getFinEnchere()}" readonly="readonly">
		</div>

		<!-- adresse du retrait de l'article -->
		<div id="retrait">
			<label class="lblDetail">Retrait : </label>
			<p class="txtDetail">${article.getRue()}</p>
			<p class="txtDetail">${article.getCodePostal()}</p>
			<p class="txtDetail">${article.getVille()}</p>
		</div>


		<!-- vendeur de l'article -->
		<div id="vendeurArticle">
			<label class="lblDetail">Vendeur : </label> <input class="txtDetail"
				id="vendeur" name="vendeur" value="${article.getPseudo()}"
				readonly="readonly">
		</div>
		
			<!-- numéro de téléphone du vendeur de l'article -->
		<div id="numVendeur">
			<label class="lblDetail" >Telephone : </label> <input class="txtDetail"
				id="numeroTel" name="numeroTel" value="${article.getTelephone()}"
				readonly="readonly">
		</div>

		<div id="proposition">
			<label class="lblDetail">Ma proposition : </label> <input
				class="txtDetail" type="number" id="proposition" name="proposition"
				min="5" max="100000000000">
		</div>

		<div id="boutons">

			<div id="btnEnchere">
				<input class="boutons" type="submit" id="encherir" value="Enchérir"> 
				<a href="<%=request.getContextPath()%>/Accueil"><input
					class="boutons" type="button" id= "annuler" value="Annuler"></a>
			</div>
			<div id="btn">
				<a href="<%=request.getContextPath()%>/modifVente"><input
				class="boutons" type="button" id= "modification" value="Modifier"></a>
				<input class="boutons" type="submit" id= "supprimer" name="supprimer" value="Supprimer">
			</div>	
			<div id="btnBack">
				<a href="<%=request.getContextPath()%>/Accueil"><input
				class="boutons" type="button" id= "back" value="Back"></a>
			</div>
			<div id="btnRetait">
				<a href="<%=request.getContextPath()%>/Accueil"><input
				class="boutons" type="button" id= "retrait" value="Retrait effectué"></a>
			</div>
				

		</div>

	</form>

</div>

</body>
</html>