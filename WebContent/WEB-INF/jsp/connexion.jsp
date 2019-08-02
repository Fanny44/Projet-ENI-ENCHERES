<!--
	Page connexion 
	Créé le 22/07/2019 par Stéphane Thomarat
-->
<%@page import="org.eniencheres.bo.ContratUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">

<!-- 
	Inclusion de la balise Head
	Modifié le 23/07/2019 par Christophe Michard 
-->
<jsp:include page="${ContratUrl.URL_FRG_BALISE_HEAD}">
	<jsp:param value="ENI-Enchères Connexion ..." name="titre"/>
</jsp:include>

<h1 id="inscriptionTitre">Connexion</h1>

<br><br>
<% if(request.getAttribute("messageErreur") != null){ %>
	<p class="messageErreur"><%=request.getAttribute("messageErreur")%></p>
<%} %>

	<!--  Début du formulaire de complétion -->
	<form id="formInscription" action="connection" method="post">
	<!-- Récupération de l'identifiant -->
		<div>
			<label class="lbl" for="identifiant">Identifiant : </label> 
			<input type="text" name="identifiant" id="identifiant" required>
		</div>
	<!-- Récupération du mot de passe -->
		<div>
			<label class="lbl" for="motdepasse">Mot de passe : </label> 
			<input type="password" name="motdepasse" id="motdepasse" required>
		</div>
	<!-- Bouton d'envoi du formulaire -->
		<div>
			<input class="boutons" type="submit" value="Connexion"/>
			<!-- <a href="">Mot de passe oublié</a> <a href="\Monprofil.jsp"></a><br> -->
			<a href="<%=request.getContextPath()%>/inscription"><input type="button" class="creercompte" value="Créer un compte"/></a>
			<a href="<%=request.getContextPath()%>/Accueil"><input type="button" class="creercompte" value="Annuler"/></a>
		</div>
	
	</form>
</body>
</html>