<%@page import="org.eniencheres.bo.ContratUrl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<jsp:include page="${ContratUrl.URL_FRG_BALISE_HEAD}">
	<jsp:param value="ENI-Enchères ..." name="titre"/>
</jsp:include>
	<script type="text/javascript" src="./js/Accueil.js"></script>
	<div id="user">
		<%
			if ((boolean)session.getAttribute("connecter") == false){
		%>
				<a href="<%=request.getContextPath()%>/inscription" title="inscription"><button class="btnAccueil">S'inscrire</button></a>
				<a href="<%=request.getContextPath()%>/connection" title="connexion"><button class="btnAccueil">Se connecter</button></a>
		<%	
			}else {
				if(request.getParameter("choix")!=null && request.getParameter("choix").equals("ventes")){
		
		%>				
				<a href="<%=request.getContextPath()%>/nouvelleVente" title="encheres"><button class="btnAccueil">Enchères</button></a>
		<%
				}else{
		%>		
				<a href="<%=request.getContextPath()%>/Accueil" title="encheres"><button class="btnAccueil">Enchères</button></a>
		<%
				}
		%>		
				
				<a href="<%=request.getContextPath()%>/nouvelleVente" title="vendreArticle"><button class="btnAccueil">Vendre article</button></a>
				<a href="<%=request.getContextPath()%>/profil" title="profil"><button class="btnAccueil">Mon profil</button></a>
				<a href="<%=request.getContextPath()%>/deconnexion" title="déconnexion"><button class="btnAccueil">Déconnexion</button></a>
				<p id="userConnecte">Bonjour ${utilisateur.getPseudo()} !</p>
				<p id="userConnecte">Votre crédit est de : ${utilisateur.getCredit()} points!</p>
		<%
			}
		%>
	</div>
	
	<br><br><br>
	<form id="formRecherche" action="Accueil" method="post">
		
		<div>
			<label id="filtre">Filtres :</label>
			<br>
			<input type ="text" id="recherche" name="recherche" placeholder="   Nom de l'article">
			<input class="boutons" type="submit" name="recherche" value="Rechercher" onclick="remiseAZero();" >
			<br>
			<label for="categorie" id="filtreCat" >Catégorie :</label>
			<select class="categorie" name="categorie">
				<option value="-1">Toutes</option>
			
				<c:forEach var="cat" items="${listeCat}">
					<option value="${cat.noCategorie}">${cat.libelle}</option>
				</c:forEach>					
			</select>
			
			<%
				if ((boolean)session.getAttribute("connecter") == true){
			%>
			
			<div id="choix">	
				<input class="choixRadio" type="radio" name="choix" value="achats" id="achats" onclick="click()">&nbsp;Achats<br>
				<div id="achat">
					<input class="radioAchat" type="radio" name="achat" value="mesEn">&nbsp;Toutes les enchères<br>
					<input class="radioAchat" type="radio" name="achat" value="enOu">&nbsp;Enchères ouvertes<br>
					<input class="radioAchat" type="radio" name="achat" value="mesEnRem">&nbsp;Enchères remportées<br>
				</div>
				<input class="choixRadio" type="radio" name="choix" value="ventes" id="ventes" onclick="click()">&nbsp;Mes ventes<br>
				<div id="vente">
					<input class="radioVente" type="radio" name="achat" value="venteCours">&nbsp;Ventes en cours<br>
					<input class="radioVente" type="radio" name="achat" value="venteNnDebut">&nbsp;Ventes non débutées<br>
					<input class="radioVente" type="radio" name="achat" value="venteTerm">&nbsp;Ventes terminées<br>
				</div>
			</div>		
				
			<%
			}
			%> 
			
		</div>
	
	</form>

	<div>
		<section id="espaceListe">
			<table>
			
				<c:choose>
					<c:when test="${listeArticles.size()>0}">
						<c:forEach var="liste" items="${listeArticles}">
								    
							<fieldset id="articles">
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
											
										<p>Prix : ${liste.montant} points</p>
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