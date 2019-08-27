/**
 * désactivation de bouton radio 
 */

function choix(){
	var achat = document.getElementById("achats");
	var vente = document.getElementById("ventes"); 
	
	if(achat != null){
		var radioVente= document.getElementsByClassName("radioVente"); 
		for (let i=0; i<radioVente.length; i++){
			radioVente.disabled=true;
		}
	}else if (vente != null){
			var radioAchat = document.getElementsByClassName("radioAchat"); 
			for (let i=0; i<radioAchat.length; i++){
				radioAchat.disabled=true;
			}
		}
		
	}
