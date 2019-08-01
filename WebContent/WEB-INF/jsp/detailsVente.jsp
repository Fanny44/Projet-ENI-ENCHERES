<%@page import="org.eniencheres.bo.ContratUrl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<jsp:include page="${ContratUrl.URL_FRG_BALISE_HEAD}">
	<jsp:param value="ENI-Enchères ..." name="titre"/>
</jsp:include>
 
 	<h1>Détails vente</h1>
 	
 	<img src="http://placehold.it/150x150" alt="" id="image"/>
 	<form action="" method="post">

		 	<label>Description : </label>
		 	<textarea rows="5" cols="40"></textarea>
		 	
		 	<label>Catégorie </label>
		 	
								
			
 	
 			<label>Meilleure offre :</label>
 			
 			<label>Mise à prix : </label>
 			
 			<label>Fin de l'enchère :</label>
 			
 			<label>Retrait : </label>
 	
 			<label>Vendeur : </label>
 			
 			<label>MA proposition : </label>
 			<input type="number" id="proposition" 
 			name="proposition" min="5" max="100000000000">
 			
 			
 	<button type="submit">Enchérir</button>
 	
 	
 	
 	
 	</form>
 

	

</body>
</html>