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
	<jsp:param value="ENI-Enchères profil vendeur ..." name="titre"/>
</jsp:include>

<%	if(request.getParameter("profilVendeur") != null){%>
		<h2 id="inscriptionTitre">Profil vendeur "${profilVendeur.getPseudo()}"</h2>
<%}else{ %>
		<h2 id="inscriptionTitre">Profil utilisateur "${utilisateur.getPseudo()}"</h2>
<%} %>

<% if(request.getAttribute("messageErreur") != null){ %>
	<p class="messageErreur"><%=request.getAttribute("messageErreur")%></p>
<%} %>

<%	if(request.getParameter("profilVendeur") == null){%>
		<form id="formInscription" action="" method="post">
			<label class="lbl" for="txtPseudo">Pseudo :</label>
			<input id="txtProfil" class="pseudo" type="text" name="txtPseudo" value="${utilisateur.getPseudo()}" readonly="readonly"><br>
			
			<label class="lbl" for="txtNom">Nom :</label>
			<input id="txtProfil" type="text" name="txtNom" value="${utilisateur.getNom()}" readonly="readonly"><br>
			
			<label class="lbl" for="txtPrenom">Prénom :</label>
			<input id="txtProfil" type="text" name="txtPrenom" value="${utilisateur.getPrenom()}" readonly="readonly"><br>
			
			<label class="lbl" for="txtEmail">eMail :</label>
			<input id="txtProfil" type="email" name="txtEmail"  value="${utilisateur.getEmail()}" readonly="readonly"><br>
			
			<label class="lbl" for="txtTelephone">Téléphone :</label>
			<input id="txtProfil" type="tel" name="txtTelephone" value="${utilisateur.getTelephone()}" readonly="readonly"><br>
			
			<label class="lbl" for="txtRue">Rue :</label>
			<input id="txtProfil" type="text" name="txtRue" value="${utilisateur.getRue()}" readonly="readonly"><br>
			
			<label class="lbl" for="txtCodePostal">Code postal :</label>
			<input id="txtProfil" type="text" name="txtCodePostal" value="${utilisateur.getCodePostal()}" readonly="readonly"><br>
			
			<label class="lbl" for="txtVille">Ville :</label>
			<input id="txtProfil" type="text" name="txtVille" value="${utilisateur.getVille()}" readonly="readonly"><br>
			
			<a href="./monProfil"><input type="button" class="boutons" value="Modifier"></a>
			<a href="./Accueil"><input type="button" class="boutons" value="Retour"></a>
		</form>
<%	}else{%>
		<form id="formInscription" action="" method="post">
			<label class="lbl" for="txtPseudo">Pseudo :</label>
			<input id="txtProfil" class="pseudo" type="text" name="txtPseudo" value="${profilVendeur.getPseudo()}" readonly="readonly"><br>
			
			<label class="lbl" for="txtNom">Nom :</label>
			<input id="txtProfil" type="text" name="txtNom" value="${profilVendeur.getNom()}" readonly="readonly"><br>
			
			<label class="lbl" for="txtPrenom">Prénom :</label>
			<input id="txtProfil" type="text" name="txtPrenom" value="${profilVendeur.getPrenom()}" readonly="readonly"><br>
			
			<label class="lbl" for="txtEmail">eMail :</label>
			<input id="txtProfil" type="email" name="txtEmail"  value="${profilVendeur.getEmail()}" readonly="readonly"><br>
			
			<label class="lbl" for="txtTelephone">Téléphone :</label>
			<input id="txtProfil" type="tel" name="txtTelephone" value="${profilVendeur.getTelephone()}" readonly="readonly"><br>
			
			<label class="lbl" for="txtRue">Rue :</label>
			<input id="txtProfil" type="text" name="txtRue" value="${profilVendeur.getRue()}" readonly="readonly"><br>
			
			<label class="lbl" for="txtCodePostal">Code postal :</label>
			<input id="txtProfil" type="text" name="txtCodePostal" value="${profilVendeur.getCodePostal()}" readonly="readonly"><br>
			
			<label class="lbl" for="txtVille">Ville :</label>
			<input id="txtProfil" type="text" name="txtVille" value="${profilVendeur.getVille()}" readonly="readonly"><br>
			
			<a href="./Accueil"><input type="button" class="boutons" value="Retour"></a>
		</form>
<%	}%>


</body>
</html>