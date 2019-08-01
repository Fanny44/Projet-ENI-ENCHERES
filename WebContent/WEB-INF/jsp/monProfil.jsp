<!-- 
	Page mon profil
	Créé le 29/07/2019 par Christophe Michard 
-->
<%@page import="org.eniencheres.bo.ContratUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<jsp:include page="${ContratUrl.URL_FRG_BALISE_HEAD}">
	<jsp:param value="ENI-Enchères Mon profil ..." name="titre"/>
</jsp:include>

<h1 id="inscriptionTitre">Mon Profil</h1>

<br><br>
<% if(request.getAttribute("messageErreur") != null){ %>
	<p class="messageErreur"><%=request.getAttribute("messageErreur")%></p>
<%} %>

<form id="formInscription" action="monProfil" method="post">
	<label class="lbl" for="txtPseudo">Pseudo :</label>
	<input type="text" name="txtPseudo" required="required" value="${utilisateur.getPseudo()}" readonly="readonly">
	
	<label class="lbl" for="txtNom">Nom :</label>
	<input type="text" name="txtNom" required="required" value="${utilisateur.getNom()}"><br>
	
	<label class="lbl" for="txtPrenom">Prénom :</label>
	<input type="text" name="txtPrenom" required="required" value="${utilisateur.getPrenom()}">
	
	<label class="lbl" for="txtEmail">eMail :</label>
	<input type="email" name="txtEmail"  required="required" value="${utilisateur.getEmail()}"><br>
	
	<label class="lbl" for="txtTelephone">Téléphone :</label>
	<input type="tel" name="txtTelephone" value="${utilisateur.getTelephone()}">
	
	<label class="lbl" for="txtRue">Rue :</label>
	<input type="text" name="txtRue" value="${utilisateur.getRue()}"><br>
	
	<label class="lbl" for="txtCodePostal">Code postal :</label>
	<input type="text" name="txtCodePostal" value="${utilisateur.getCodePostal()}">
	
	<label class="lbl" for="txtVille">Ville :</label>
	<input type="text" name="txtVille" value="${utilisateur.getVille()}"><br>
	
	<label class="lbl" for="txtMotDePasseActuel">Mot de passe actuel :</label>
	<input type="password" name="txtMotDePasseActuel" required="required" value=""><br>

	<label class="lbl" for="txtMotDePasseNouveau">Nouveau mot de passe :</label>
	<input type="password" name="txtMotDePasseNouveau" value="">
	
	<label class="lbl" for="txtConfirmMotDePasse">Confirmation :</label>
	<input type="password" name="txtConfirmMotDePasse" ><br>
	
	<label class="lbl" for="txtCredit">Crédit : ${utilisateur.getCredit()}</label><br>
	
	<input class="boutons" type="submit" value="Enregistrer">
	<a href="<%=request.getContextPath()%>/Accueil"><input type="button" class="boutons" value="Annuler"></a>
	<a href="<%=request.getContextPath()%>/supprimerCompte"><input type="button" class="boutons" value="Supprimer mon compte"></a>
</form>

</body>
</html>