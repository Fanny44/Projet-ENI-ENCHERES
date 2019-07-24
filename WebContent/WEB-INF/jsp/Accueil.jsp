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

	<div>
	<a href="<%=request.getContextPath()%>/inscription" title="inscription">S'inscrire</a>
	</div>
	<div>
	<a href="<%=request.getContextPath()%>/connection" title="connexion">Se connecter</a>
	</div>
	
	
	<form id="filtrage" action="ServletAccueil" method="post">
		<div>
			<label for="filtre">Filtres : </label>
		</div>
		
		<div>
			<input type ="text" id="recherche" name="recherche" placeholder="Le nom de l'article">
		</div>
		
		<div>
			<label for="Categorie" >Catégorie :</label>
			<select id="Catégorie">
				<option value="Toutes">Toutes</option>
				<option value="Informatique">Informatique</option>
				<option value="Ameublement">Ameublement</option>
				<option value="Vêtement">Vêtement</option>
				<option value="Sport">Sport&Loisirs</option>	
			</select>
		</div>
		
		<div>
			<input type="button" name="recherche" value="Rechercher">
		</div>
	</form>

	<div>
		<section>
			
		
		</section>
	
	</div>
</body>
</html>