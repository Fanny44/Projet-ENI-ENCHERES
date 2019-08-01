<%@page import="org.eniencheres.bo.ContratUrl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<jsp:include page="${ContratUrl.URL_FRG_BALISE_HEAD}">
	<jsp:param value="ENI-Enchères ..." name="titre"/>
</jsp:include>
 
 	<h3>Détails vente</h3>
 	
 	<img src="http://placehold.it/150x150" alt="" id="image"/>
 	<form action="detailsVente" method="post">
 
			<!-- Titre de l'article dont on regarde le détails -->
			<p>${article.getNomArticle()}</p>
			

		 	<label>Description : </label>
		 	<textarea rows="5" cols="40">${article.getDescription()}</textarea>
		 	<!-- Description de l'article   -->
		 	
		 	<label>Catégorie </label>
		 	<!-- Affiche la catégorie de l'article dont on regarde le détails -->
			
			
 	
 			<label>Meilleure offre :</label>
 			<!-- meilleurs enchères faite  -->
 			
 			
 			<label>Mise à prix : </label>
 			<!-- Mise à prix de l'article -->
 			<p>${article.getMiseAPrix()}</p>
 			
 			<label>Fin de l'enchère :</label>
 			<!-- date de la fin de l'enchère -->
 			<p>${article.getDateFinEncheres()}</p>
 			
 			
 			<label>Retrait : </label>
 			<!-- adresse du retrait de l'article -->
 		
 	
 			<label>Vendeur : </label>
 			<!-- vendeur de l'article -->
 			

 			
 			<label>MA proposition : </label>
 			<input type="number" id="proposition" 
 			name="proposition" min="5" max="100000000000">
 			
 			
 	<button type="submit">Enchérir</button>
 	
 	
 	
 	
 	</form>
 

	

</body>
</html>