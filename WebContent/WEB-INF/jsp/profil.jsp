<!-- 
	Page d'inscription
	Créé le 23/07/2019 par Christophe Michard 
-->
<%@page import="org.eniencheres.bo.Utilisateur"%>
<%@page import="org.eniencheres.bo.ContratUrl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<jsp:include page="${ContratUrl.URL_FRG_BALISE_HEAD}">
	<jsp:param value="ENI-Enchères inscription ..." name="titre"/>
</jsp:include>

<% if(request.getAttribute("messageErreur") != null){ %>
	<p class="messageErreur"><%=request.getAttribute("messageErreur")%></p>
<%} %>

<form id="formInscription" action="inscription" method="post">
	<label class="lbl" for="txtPseudo">Pseudo :</label>
	<input type="text" name="txtPseudo" value="${profilVendeur.getPseudo()}"><br>
	
	<label class="lbl" for="txtNom">Nom :</label>
	<input type="text" name="txtNom" value="${profilVendeur.getNom()}"><br>
	
	<label class="lbl" for="txtPrenom">Prénom :</label>
	<input type="text" name="txtPrenom" value="${profilVendeur.getPrenom()}"><br>
	
	<label class="lbl" for="txtEmail">eMail :</label>
	<input type="email" name="txtEmail"  value="${profilVendeur.getEmail()}"><br>
	
	<label class="lbl" for="txtTelephone">Téléphone :</label>
	<input type="tel" name="txtTelephone" value="${profilVendeur.getTelephone()}"><br>
	
	<label class="lbl" for="txtRue">Rue :</label>
	<input type="text" name="txtRue" value="${profilVendeur.getRue()}"><br>
	
	<label class="lbl" for="txtCodePostal">Code postal :</label>
	<input type="text" name="txtCodePostal" value="${profilVendeur.getCodePostal()}"><br>
	
	<label class="lbl" for="txtVille">Ville :</label>
	<input type="text" name="txtVille" value="${profilVendeur.getVille()}"><br>
</form>

</body>
</html>