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
				<a href="<%=request.getContextPath()%>/inscription" title="inscription"><button class="btnAccueil">S'inscrire</button></a>
				<a href="<%=request.getContextPath()%>/connection" title="connexion"><button class="btnAccueil">Se connecter</button></a>
		<%	
			}else {
		%>		
				<a href="<%=request.getContextPath()%>/nouvelleVente" title="encheres"><button class="btnAccueil">Enchères</button></a>
				<a href="<%=request.getContextPath()%>/nouvelleVente" title="vendreArticle"><button class="btnAccueil">Vendre article</button></a>
				<a href="<%=request.getContextPath()%>/profil" title="profil"><button class="btnAccueil">Mon profil</button></a>
				<a href="<%=request.getContextPath()%>/deconnexion" title="déconnexion"><button class="btnAccueil">Déconnexion</button></a>
		<%
			}
		%>
	</div>
	
	<br><br><br>
	<form id="formRecherche" action="Accueil" method="post">
		
		<div>
			<label for="filtre" id="filtre">Filtres : </label>
			<br>
			<input type ="text" id="recherche" name="recherche" placeholder="   Nom de l'article">
			<input class="boutons" type="submit" name="recherche" value="Rechercher" >
			<br>
			<label for="Categorie" id="filtreCat" >Catégorie :</label>
			<select class="Categorie" name="categorie">
				<option value="-1">Toutes</option>
			
				<c:forEach var="cat" items="${listeCat}">
					<option value="${cat.noCategorie}">${cat.libelle}</option>
				</c:forEach>					
			</select>
			<%
			if ((boolean)session.getAttribute("connecter") == true){
			%>
		<div id="choix">	
			<input type="radio" name="choix" id="achats" value="achats">Achats<br>
			<div id="achat">
				<input type="radio" name="achat" value="enOu">enchères ouvertes<br>
				<input type="radio" name="achat" value="mesEn">mes enchères<br>
				<input type="radio" name="achat" value="mesEnRem">mes enchères remportées<br>
			</div>
			<input type="radio" name="choix" id="ventes" value="ventes">Mes ventes<br>
			<div id="vente">
				<input type="radio" name="achat" value="venteCours">mes ventes en cours<br>
				<input type="radio" name="achat" value="venteNnDebut">ventes non débutées<br>
				<input type="radio" name="achat" value="venteTerm">ventes terminées<br>
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
					<c:when test="${listeArticles.size()>0}">
						<c:forEach var="liste" items="${listeArticles}">
								    
							<fieldset>
								<img src="http://placehold.it/150x150" alt="" id="image"/>
									<aside class="text">
										<%
											if ((boolean)session.getAttribute("connecter") == false){
										%>
											<p>${liste.article}</p>
										<%
											}else{
										%>	
											<p><a href="<%=request.getContextPath()%>/detailsVente?numArticle=${liste.noArticle}&nomArticle=${liste.article}" title="details">${liste.article}</a></p>
										<%
											} 
										%>											
											
										<p>Prix : ${liste.montant}</p>
										<p>Fin de l'enchère : ${liste.dateFin}</p>
										<p>Vendeur :<a href="./profil?profilVendeur=${liste.vendeur}">${liste.vendeur}</a></p>		
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