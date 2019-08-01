<!-- 
	Page d'inscription
	Créé le 23/07/2019 par Christophe Michard 
	Modifié le 01/08/2019 par Christophe Michard
-->
<%@page import="org.eniencheres.bo.ContratUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<jsp:include page="${ContratUrl.URL_FRG_BALISE_HEAD}">
	<jsp:param value="ENI-Enchères inscription ..." name="titre"/>
</jsp:include>

<h1 id="inscriptionTitre">Mon Profil</h1>

<br><br>
<% if(request.getAttribute("messageErreur") != null){ %>
	<p class="messageErreur"><%=request.getAttribute("messageErreur")%></p>
<%} %>

<form id="formInscription" action="inscription" method="post">
	<label class="lbl" for="txtPseudo">Pseudo :</label>
	<input type="text" name="txtPseudo" required="required" value="<%=request.getParameter("txtPseudo") != null ? request.getParameter("txtPseudo") : ""%>">
	
	<label class="lbl" for="txtNom">Nom :</label>
	<input type="text" name="txtNom" required="required" value="<%=request.getParameter("txtNom") != null ? request.getParameter("txtNom") : ""%>"><br>
	
	<label class="lbl" for="txtPrenom">Prénom :</label>
	<input type="text" name="txtPrenom" required="required" value="<%=request.getParameter("txtPrenom") != null ? request.getParameter("txtPrenom") : ""%>">
	
	<label class="lbl" for="txtEmail">eMail :</label>
	<input type="email" name="txtEmail"  required="required" value="<%=request.getParameter("txtEmail") != null ? request.getParameter("txtEmail") : ""%>"><br>
	
	<label class="lbl" for="txtTelephone">Téléphone :</label>
	<input type="tel" name="txtTelephone" value="<%=request.getParameter("txtTelephone") != null ? request.getParameter("txtTelephone") : ""%>">
	
	<label class="lbl" for="txtRue">Rue :</label>
	<input type="text" name="txtRue" value="<%=request.getParameter("txtRue") != null ? request.getParameter("txtRue") : ""%>"><br>
	
	<label class="lbl" for="txtCodePostal">Code postal :</label>
	<input type="text" name="txtCodePostal" value="<%=request.getParameter("txtCodePostal") != null ? request.getParameter("txtCodePostal") : ""%>">
	
	<label class="lbl" for="txtVille">Ville :</label>
	<input type="text" name="txtVille" value="<%=request.getParameter("txtVille") != null ? request.getParameter("txtVille") : ""%>"><br>
	
	<label class="lbl" for="txtMotDePasse">Mot de passe :</label>
	<input type="password" name="txtMotDePasse" required="required" value="<%=request.getParameter("txtMotDePasse") != null ? request.getParameter("txtMotDePasse") : ""%>">
	
	<label class="lbl" for="txtConfirmMotDePasse">Confirmation :</label>
	<input type="password" name="txtConfirmMotDePasse" required="required"><br>
	
	<input class="boutons" type="submit" value="Créer">
	<a href="<%=request.getContextPath()%>/Accueil"><input class="boutons" type="button" value="Annuler"></a>
</form>

</body>
</html>