<%@page import="org.eniencheres.bo.ContratUrl"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.eniencheres.bo.Utilisateur"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<jsp:include page="${ContratUrl.URL_FRG_BALISE_HEAD}">
	<jsp:param value="ENI-Enchères ..." name="titre"/>
</jsp:include>
<script type="text/javascript" src="./js/DetailsVente.js"></script>
 <div>
 	<h1 id="detailsTitre">Détails vente</h1>
 	<p id="fin"></p>
 	<br><br>
<% if(request.getAttribute("messageErreur") != null){ %>
	<p class="messageErreur"><%=request.getAttribute("messageErreur")%></p>
<%} %>
 	

 	<%Utilisateur user =(Utilisateur)request.getSession().getAttribute("utilisateur");
 	String pseudo = user.getPseudo();
 	%>
 <script type="text/javascript">	
 	var userSession = '<%=pseudo%>'; 
 </script>
 
 	<form id="formDetailsVente" action="detailsVente" method="post">
 	<img src="http://placehold.it/150x150" alt="" id="image"/>
 
			<!-- Titre de l'article dont on regarde le détails -->
			<p id="nomArticle">${article.getNomArticle()}</p>
			<input type ="hidden" name="numArticle" value="${article.getNoArticle()}">
			

		 	<label class="lblDetail">Description : </label>
		 	<textarea id="textarea" rows="5" cols="40">${article.getDescription()}</textarea>
		 	<!-- Description de l'article   -->
		 	
		 	<label class="lblDetail">Catégorie </label>
		 	<!-- Affiche la catégorie de l'article dont on regarde le détails -->
		 		<p class="txtDetail">${article.getLibelle()}</p>
			
			
 	
 			<label class="lblDetail">Meilleure offre :</label>
 			<!-- meilleurs enchères faite  -->
 			<input class="txtDetail" name="montantEnchere" value="${article.getMontantEnchere()}" readonly="readonly">points par
 		<!-- reste a afficher l'user qui a fait la meilleure offre -->		
 			<input class="txtDetail" name="pseudoMeilleureOffre" id="acheteur" value="${user.getPseudo()}" readonly="readonly">
 			
 			
 			<label class="lblDetail">Mise à prix : </label>
 			<!-- Mise à prix de l'article -->
 			<p class="txtDetail">${article.getMiseAPrix()} points</p>
 			
 			<label class="lblDetail">Début de l'enchère :</label>
 			<!-- date duè début de l'enchère -->
 			<input class="txtDetail" name="debutEnchere" id="debutEnchere" value="${article.getDebutEnchere()}" readonly="readonly">
 			
 			<label class="lblDetail">Fin de l'enchère :</label>
 			<!-- date de la fin de l'enchère -->
 			<input class="txtDetail" name="finEnchere" id="finEnchere" value="${article.getFinEnchere()}" readonly="readonly">
 			
 			
 			<label class="lblDetail">Retrait : </label>
 			<!-- adresse du retrait de l'article -->
 			<p class="txtDetail">${article.getRue()}</p>
 			<p class="txtDetail">${article.getCodePostal()}</p>
 			<p class="txtDetail">${article.getVille()}</p>
 			
 		
 	
 			<label class="lblDetail">Vendeur : </label>
 			<!-- vendeur de l'article -->
			<input class="txtDetail" id="vendeur" name="vendeur" value="${article.getPseudo()}" readonly="readonly">
 			
 			<label class="lblDetail">Ma proposition : </label>
 			<input class="txtDetail" type="number" id="proposition" 
 			name="proposition" min="5" max="100000000000">
 			
 		
 		<div id=boutons>
 		
	
	 		 <input class="boutons" type="submit" id="encherir" value="Enchérir" disabled> 

	
 			<a href="<%=request.getContextPath()%>/Accueil"><input class="boutons" type="button" value="Annuler"></a>
 	
 		</div>
	
 	</form>
 
</div>
	
</body>
</html>