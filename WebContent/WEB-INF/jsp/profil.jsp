<!-- 
	Page d'inscription
	Créé le 23/07/2019 par Christophe Michard 
-->
<%@page import="org.eniencheres.bo.Utilisateur"%>
<%@page import="org.eniencheres.bo.ContratUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<jsp:include page="${ContratUrl.URL_FRG_BALISE_HEAD}">
	<jsp:param value="ENI-Enchères inscription ..." name="titre"/>
</jsp:include>

<form id="formInscription" action="inscription" method="post">
	<label class="lbl" for="txtPseudo">Pseudo :</label>
	<input type="text" name="txtPseudo" value="${utilisateur.getPseudo()}"><br>
	
	<label class="lbl" for="txtNom">Nom :</label>
	<input type="text" name="txtNom" value=""><br>
	
	<label class="lbl" for="txtPrenom">Prénom :</label>
	<input type="text" name="txtPrenom" value=""><br>
	
	<label class="lbl" for="txtEmail">eMail :</label>
	<input type="email" name="txtEmail"  value=""><br>
	
	<label class="lbl" for="txtTelephone">Téléphone :</label>
	<input type="tel" name="txtTelephone" value=""><br>
	
	<label class="lbl" for="txtRue">Rue :</label>
	<input type="text" name="txtRue" value=""><br>
	
	<label class="lbl" for="txtCodePostal">Code postal :</label>
	<input type="text" name="txtCodePostal" value=""><br>
	
	<label class="lbl" for="txtVille">Ville :</label>
	<input type="text" name="txtVille" value=""><br>
</form>

</body>
</html>