<!-- 
	Page d'inscription
	Créé le 23/07/2019 par Christophe Michard 
-->
<%@page import="org.eniencheres.bo.ContratUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<jsp:include page="${ContratUrl.urlFrgBaliseHead}">
	<jsp:param value="ENI-Enchères inscription ..." name="titre"/>
</jsp:include>

<h2 class="centrerTitre">Mon profil</h2>

<form id="formInscription" action="inscription" method="post">
	<label for="txtPseudo">Pseudo :</label>
	<input type="text" name="txtPseudo" required="required">
	
	<label for="txtNom">Nom :</label>
	<input type="text" name="txtNom" required="required"><br>
	
	<label for="txtPrénom">Prénom :</label>
	<input type="text" name="txtPrénom" required="required">
	
	<label for="txtEmail">Email :</label>
	<input type="email" name="txtEmail"  required="required"><br>
	
	<label for="txtTelephone">Téléphone :</label>
	<input type="tel" name="txtTelephone">
	
	<label for="txtRue">Rue :</label>
	<input type="text" name="txtRue"><br>
	
	<label for="txtCodePostal">Code postal :</label>
	<input type="text" name="txtCodePostal">
	
	<label for="txtVille">Ville :</label>
	<input type="text" name="txtVille"><br>
	
	<label for="txtMotDePasse">Mot de passe :</label>
	<input type="password" name="txtMotDePasse" required="required">
	
	<label for="txtConfirmMotDePasse">Confirmation :</label>
	<input type="password" name="txtConfirmMotDePasse" required="required"><br>
	
	<input type="submit" value="Créer">
	<a href=""><input type="button" value="Annuler"></a>
</form>



</body>
</html>