/**
 * désactivation de bouton radio 
 */

function clcBtn(){
	
	var achat = document.getElementById("achats");
	var vente = document.getElementById("ventes"); 
	var radioVente= document.getElementsByClassName("radioVente"); 
	var radioAchat = document.getElementsByClassName("radioAchat");
	
	if(achat.checked){

		//vente.disabled=true;  
		for (let i=0; i<radioVente.length; i++){
			radioVente[i].disabled=true;
			radioAchat[i].disabled=false;
		}
	}else if (vente.checked){
		//achat.disabled=true; 		
		for (let i=0; i<radioAchat.length; i++){
			radioAchat[i].disabled=true;
			radioVente[i].disabled=false;
		}
	}
		
}
