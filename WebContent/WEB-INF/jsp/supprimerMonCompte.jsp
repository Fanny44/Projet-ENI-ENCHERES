<!-- 
	Page supprimer mon compte
	Créé le 31/07/2019 par Christophe Michard
	Modifié le 01/08/2019 par Christophe Michard
-->
<%@page import="org.eniencheres.bo.ContratUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<jsp:include page="${ContratUrl.URL_FRG_BALISE_HEAD}">
	<jsp:param value="ENI-Enchères Supprimer mon compte ..." name="titre"/>
</jsp:include>

<h2 id="inscriptionTitre">Supprimer mon compte</h2>

<br><br>
<% if(request.getAttribute("messageErreur") != null){ %>
	<p class="messageErreur"><%=request.getAttribute("messageErreur")%></p>
<%} %>

<form id="formInscription" action="supprimerCompte" method="post">
	<label id="suppressionCompte">Confirmer votre mot de passe pour supprimer votre compte</label><br><br>
	<label class="lbl" for="txtMotDePasse">Mot de passe :</label>
	<input type="password" name="txtMotDePasse" required="required" value=""><br>
	
	<input class="boutons" type="submit" value="Supprimer mon compte">
	<a href="<%=request.getContextPath()%>/monProfil"><input type="button" class="boutons" value="Annuler"></a>
</form>

</body>
</html>