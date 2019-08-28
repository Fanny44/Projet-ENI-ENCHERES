/**
 * désactivation de bouton radio 
 */
window.addEventListener("load",choix);

function choix(){

	var achat = document.getElementById("achats");
	var vente = document.getElementById("ventes"); 
	console.log(achat);
	console.log(vente);
	
	if(achat.checked==true){
		vente.disabled=true;  
		var radioVente= document.getElementsByClassName("radioVente"); 
		for (let i=0; i<radioVente.length; i++){
			radioVente[i].disabled=true;
		}
	}else if (vente.checked==true){
		var radioAchat = document.getElementsByClassName("radioAchat");
		achat.disabled=true; 
			for (let i=0; i<radioAchat.length; i++){
				radioAchat[i].disabled=true;
			}
		}
		
	}

function remiseAZero(){
	achat.disabled=false;
	vente.disabled=false;
}