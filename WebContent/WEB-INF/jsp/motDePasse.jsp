<!-- 
	Page mot de passe oublié
	Créé le 12/09/2019 par Fanny Radigois

-->
<%@page import="org.eniencheres.bo.ContratUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<jsp:include page="${ContratUrl.URL_FRG_BALISE_HEAD}">
	<jsp:param value="ENI-Enchères Supprimer mon compte ..." name="titre"/>
</jsp:include>

<h1 id="inscriptionTitre">Mot de passe oublié</h1>

<br><br>
<% if(request.getAttribute("messageErreur") != null){ %>
	<p class="messageErreur"><%=request.getAttribute("messageErreur")%></p>
<%} %>

	<form id="formMotDePasse" action="motDePasse" method="POST">
		<label class="lbl" for="txtEmail">Email</label>
		<input type="email" name="txtEmail" required>
		
		<label class="lbl" for="txtMotDePasse">Nouveau mot de passe</label>
		<input type="password" name="txtMotDePasse" required>
		
		<label class="lbl" for="txtConfirmationMotDePasse">Confirmation mot de passe</label>
		<input type="password" name="txtConfirmationMotDePasse" required>
		
		<input class="boutons" type="submit" value="Valider">
		<a href="<%=request.getContextPath()%>/Accueil"><input type="button" class="boutons" value="Annuler"></a>
	
	</form>


</body>
</html>