/**
 * désactivation de bouton radio 
 */

function choix(){
	var achat = document.getElementById("achats");
	var vente = document.getElementById("ventes"); 
	
	if(achat != null){
		document.getElementById("achat").disabled=true; 
//		var radioVente= document.getElementsByClassName("radioVente"); 
//		for (let i=0; i<radioVente.length; i++){
//			radioVente[i].disabled=true;
//		}
	}else if (vente != null){
		document.getElementById("vente").disabled=true;
//			var radioAchat = document.getElementsByClassName("radioAchat"); 
//			for (let i=0; i<radioAchat.length; i++){
//				radioAchat[i].disabled=true;
//			}
		}
		
	}
