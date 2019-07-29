<%@page import="org.eniencheres.bo.ContratUrl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<jsp:include page="${ContratUrl.URL_FRG_BALISE_HEAD}">
	<jsp:param value="ENI-Enchères ..." name="titre"/>
</jsp:include>

	<div id="user">
		<%
			if ((boolean)session.getAttribute("connecter") == false){
		%>
				<a href="<%=request.getContextPath()%>/inscription" title="inscription">S'inscrire</a>
				<a href="<%=request.getContextPath()%>/connection" title="connexion">Se connecter</a>
		<%	
			}else {
		%>
				<a href="<%=request.getContextPath()%>/deconnexion" title="déconnexion">Déconnexion</a>
		<%
			}
		%>
	</div>
	
	<form class="formRecherche" action="Accueil" method="post">
		
		<div>
			<label for="filtre" id="filtre">Filtres : </label>
			<br>
			<input type ="text" id="recherche" name="recherche" placeholder="Le nom de l'article">
			<input type="submit" name="recherche" value="Rechercher" >
			<br>
			<label for="Categorie" id="filtreCat" >Catégorie :</label>
			<select class="Categorie" name="categorie">
				<option value="Toutes">Toutes</option>
				<option value="Informatique">Informatique</option>
				<option value="Ameublement">Ameublement</option>
				<option value="Vêtement">Vêtement</option>
				<option value="Sport">Sport&Loisirs</option>	
			</select>
		</div>
	
	</form>

	<div>
		<section class="espaceListe" style="line-height: 116%; letter-spacing:0.042em; font-size:17px; font-weight: 300;">
			<table>
			
				<c:choose>
					<c:when test="${listeEncheres.size()>0}">
						<c:forEach var="liste" items="${listeEncheres}">
								    
							<fieldset>
								<img src="http://placehold.it/150x150" alt="" id="image"/>
									<aside classe="text">
										<p><c:out value="${liste.article}" /></p>
										<p>Prix : <c:out value="${liste.montant}" /></p>
										<p>Fin de l'enchère :<c:out value="${liste.dateFin}" /></p>
										<p>Vendeur :<a href="./profil?profilVendeur=${liste.vendeur}"> <c:out value="${liste.vendeur}" /> </a></p>		
									</aside>
							</fieldset>	  			    
								    
								    
								    
<%-- 							<tr>
								 <th id="titre"><c:out value="${liste.article}" /></th>
								 <th>Prix : </th>
									<td><c:out value="${liste.montant}" /></td>
								 <th>Fin de l'enchère : </th>
								 	<td><c:out value="${liste.dateFin}" /></td>
								<th>Vendeur : </th>
									<td><a href="./profil?profilVendeur=${liste.vendeur}"> <c:out value="${liste.vendeur}" /> </a></td>
							 </tr> --%>
								  
						</c:forEach>
					</c:when>
					<c:otherwise>
						<p>Pas de liste actuellement.<p>
					</c:otherwise>	
				</c:choose>
					
				</table>
		
		</section>
	
	</div>
</body>
</html>