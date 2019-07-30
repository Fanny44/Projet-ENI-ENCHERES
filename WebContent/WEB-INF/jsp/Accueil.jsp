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
				<a href="<%=request.getContextPath()%>/nouvelleVente" title="encheres">Enchères</a>
				<a href="<%=request.getContextPath()%>/nouvelleVente" title="vendreArticle">Vendre article</a>
				<a href="<%=request.getContextPath()%>/profil" title="profil">Mon profil</a>
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
			
				<c:forEach var="cat" items="${listeCat}">
					<option value="${cat.noCategorie}">${cat.libelle}</option>
				</c:forEach>					
			</select>
			<%
			if ((boolean)session.getAttribute("connecter") == true){
			%>
		<div id="choix">	
			<div id="achat">
				<input type="radio" name="choix" value="achats">Achats<br>
				<input type="radio" name="achat" value="enOu">enchères ouvertes<br>
				<input type="radio" name="achat" value="mesEn">mes enchères<br>
				<input type="radio" name="achat" value="mesEnRem">mes enchères remportées<br>
			</div>
			<div id="vente">
				<input type="radio" name="choix" value="ventes">Mes ventes<br>
				<input type="radio" name="vente" value="venteCours">mes ventes en cours<br>
				<input type="radio" name="vente" value="venteNnDebut">ventes non débutées<br>
				<input type="radio" name="vente" value="venteTerm">ventes terminées<br>
			</div>
		</div>			
			<%
			}
			%> 
				
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
									<aside class="text">
										<%
											if ((boolean)session.getAttribute("connecter") == false){
										%>
											<p><c:out value="${liste.article}" /></p>
										<%
											}else{
										%>	
											<p><a href="<%=request.getContextPath()%>/detailsVente" title="details"><c:out value="${liste.article}"/></a></p>
										<%
											} 
										%>											
											
										<p>Prix : <c:out value="${liste.montant}" /></p>
										<p>Fin de l'enchère :<c:out value="${liste.dateFin}" /></p>
										<p>Vendeur :<a href="./profil?profilVendeur=${liste.vendeur}"> <c:out value="${liste.vendeur}" /> </a></p>		
									</aside>
							</fieldset>	  			    
 
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