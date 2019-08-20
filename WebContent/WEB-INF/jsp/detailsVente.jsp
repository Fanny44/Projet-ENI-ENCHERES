<%@page import="org.eniencheres.bo.ContratUrl"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<jsp:include page="${ContratUrl.URL_FRG_BALISE_HEAD}">
	<jsp:param value="ENI-Enchères ..." name="titre"/>
</jsp:include>
 <div>
 	<h1 id="detailsTitre">Détails vente</h1>
 	
 	<br><br>
<% if(request.getAttribute("messageErreur") != null){ %>
	<p class="messageErreur"><%=request.getAttribute("messageErreur")%></p>
<%} %>
 	
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
		 		<p id="txtProfil">${article.getLibelle()}</p>
			
			
 	
 			<label class="lblDetail">Meilleure offre :</label>
 			<!-- meilleurs enchères faite  -->
 			<input id="txtProfil" name="montantEnchere" value="${article.getMontantEnchere()}" readonly="readonly">points par
 		<!-- reste a afficher l'user qui a fait la meilleure offre -->		
 			<input id="txtProfil" name="pseudoMeilleureOffre" value="${user.getPseudo()}">
 			
 			
 			<label class="lblDetail">Mise à prix : </label>
 			<!-- Mise à prix de l'article -->
 			<p id="txtProfil">${article.getMiseAPrix()} points</p>
 			
 			<label class="lblDetail">Début de l'enchère :</label>
 			<!-- date duè début de l'enchère -->
 			<input id="txtProfil" name="debutEnchere" value="${article.getDebutEnchere()}">
 			
 			<label class="lblDetail">Fin de l'enchère :</label>
 			<!-- date de la fin de l'enchère -->
 			<input id="txtProfil" name="finEnchere" value="${article.getFinEnchere()}">
 			
 			
 			<label class="lblDetail">Retrait : </label>
 			<!-- adresse du retrait de l'article -->
 			<p id="txtProfil">${article.getRue()}</p>
 			<p id="txtProfil">${article.getCodePostal()}</p>
 			<p id="txtProfil">${article.getVille()}</p>
 			
 		
 	
 			<label class="lblDetail">Vendeur : </label>
 			<!-- vendeur de l'article -->
			<input id="txtProfil" name="vendeur" value="${article.getPseudo()}">
 			
 			<label class="lblDetail">Ma proposition : </label>
 			<input id="txtProfil" type="number" id="proposition" 
 			name="proposition" min="5" max="100000000000">
 			
 			
 <!-- Désactiver le bouton enchère lorsque la date du début de l'enchère n'est pas encore arriver -->	
 	<%-- <%
 		String dateDeb =request.getParameter("debutEnchere");
 		String dateFin=request.getParameter("finEnchere");
 		System.out.print(dateDeb);
 		System.out.print(dateFin);
 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
 		Date debut = sdf.parse(dateDeb);
 		Date fin=sdf.parse(dateFin);
 		Date d = new Date(); 
 	%>	
 	<c:choose>	
	 	<c:when test="${debut<d<fin}">	 --%>
	 		 <input class="boutons" type="submit" value="Enchérir"> 
	 <%-- 	</c:when>
	 	<c:otherwise>	
	 		<input class="boutons" type="submit" value="Enchérir" disabled>
	 	</c:otherwise>	
	 	
	</c:choose> --%>
	
	
	<%-- <%
		String dateDeb =request.getParameter("debutEnchere");
		String dateFin=request.getParameter("finEnchere");
		/* System.out.print(dateDeb + "\n");
		System.out.print(dateFin); */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		Date debut = sdf.parse(dateDeb);
		Date fin=sdf.parse(dateFin);
		Date d = new Date();
		String syst = sdf.format(d);
		Date system = sdf.parse(syst);
		//System.out.println("\n"+syst);
		
		if (debut.equals(system) || debut.before(system) && fin.after(system)) {
	%>
		<input class="boutons" type="submit" value="Enchérir">
	<%	
		}else {
	%>		
		<input class="boutons" type="submit" value="Enchérir" disabled>
	<%
		}
	%> --%>
	
 	<a href="<%=request.getContextPath()%>/Accueil"><input class="boutons" type="button" value="Annuler"></a>
 	
 	

 	
 	</form>
 
</div>
	

</body>
</html>