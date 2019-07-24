<!-- 
	Page d'inscription
	Créé le 23/07/2019 par Christophe Michard 
-->
<%@page import="org.eniencheres.bo.ContratUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<jsp:include page="${ContratUrl.URL_FRG_BALISE_HEAD}">
	<jsp:param value="ENI-Enchères inscription ..." name="titre"/>
</jsp:include>

<h2 id="inscriptionTitre">Mon Profil</h2>

<br><br>
<% if(request.getAttribute("messageErreur") != null){ %>
	<p class="messageErreur"><%=request.getAttribute("messageErreur")%></p>
<%} %>

<form id="formInscription" action="inscription" method="post">
	<label class="lbl" for="txtPseudo">Pseudo :</label>
	<input type="text" name="txtPseudo" required="required">
	
	<label class="lbl" for="txtNom">Nom :</label>
	<input type="text" name="txtNom" required="required"><br>
	
	<label class="lbl" for="txtPrenom">Prénom :</label>
	<input type="text" name="txtPrenom" required="required">
	
	<label class="lbl" for="txtEmail">Email :</label>
	<input type="email" name="txtEmail"  required="required"><br>
	
	<label class="lbl" for="txtTelephone">Téléphone :</label>
	<input type="tel" name="txtTelephone">
	
	<label class="lbl" for="txtRue">Rue :</label>
	<input type="text" name="txtRue"><br>
	
	<label class="lbl" for="txtCodePostal">Code postal :</label>
	<input type="text" name="txtCodePostal">
	
	<label class="lbl" for="txtVille">Ville :</label>
	<input type="text" name="txtVille"><br>
	
	<label class="lbl" for="txtMotDePasse">Mot de passe :</label>
	<input type="password" name="txtMotDePasse" required="required">
	
	<label class="lbl" for="txtConfirmMotDePasse">Confirmation :</label>
	<input type="password" name="txtConfirmMotDePasse" required="required"><br>
	
	<input class="boutons" type="submit" value="Créer">
	<a href="Accueil"><input class="boutons" type="button" value="Annuler"></a>
</form>

</body>
</html>