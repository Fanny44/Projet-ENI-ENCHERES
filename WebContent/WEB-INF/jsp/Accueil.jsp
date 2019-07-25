<%@page import="org.eniencheres.bo.ContratUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- 
	Inclusion de la balise Head
	Modifié le 23/07/2019 par Christophe Michard 
-->
<jsp:include page="${ContratUrl.URL_FRG_BALISE_HEAD}">
	<jsp:param value="ENI-Enchères connexion ..." name="titre"/>
</jsp:include>

	<div id="user">
	<a href="<%=request.getContextPath()%>/inscription" title="inscription">S'inscrire</a>
	<a href="<%=request.getContextPath()%>/connection" title="connexion">Se connecter</a>
	</div>
	
	
	<form class="formRecherche" action="ServletAccueil" method="post">
		
		<div>
			<label for="filtre" id="filtre">Filtres : </label>
			<br>
			<input type ="text" id="recherche" name="recherche" placeholder="Le nom de l'article">
			<input type="button" name="recherche" value="Rechercher" >
			<br>
			<label for="Categorie" id="filtreCat" >Catégorie :</label>
			<select class="Categorie">
				<option value="Toutes">Toutes</option>
				<option value="Informatique">Informatique</option>
				<option value="Ameublement">Ameublement</option>
				<option value="Vêtement">Vêtement</option>
				<option value="Sport">Sport&Loisirs</option>	
			</select>
		</div>
	
	</form>

	<div>
		<section>
			
		
		</section>
	
	</div>
</body>
</html>