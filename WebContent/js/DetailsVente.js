/**
 * fonction permettant la désactivation du bouton enchere en fonction des dates de ventes de l'article 
 */
window.addEventListener("load",init);

function init() {
	finEnchere();
	var debut = Date.parse(document.getElementById("debutEnchere").value);
	var fin = Date.parse(document.getElementById("finEnchere").value);
	var syst =Date.parse(new Date());

    if ((debut<syst) && (syst<fin)) {
      document.getElementById("encherir").disabled=false; 
   
     }
} 

//TODO faire en sorte que l'enchère se termine le jour indiqué à 23h59 et début le jour de bébut à 00h01

/**
 * fonction affichant un message en fonction du vendeur ou de l'acheteur qui a remporté l'enchère
 */
function finEchere(){
	var fin=Date.parse(document.getElementById("finEnchere").value);
	var syst=Date.parse(new Date());
	var vendeur=document.getElementById("vendeur").value; 
	var acheteur=document.getElementById("acheteur").value;
	
	if(syst.after(fin) && vendeur.equals(userSession)){
		var para = document.createElement("p"); 
		para.innerText='${acheteur} à remporté l\'enchère !';
		
		var titre = document.getElementById("detailsTitre"); 
		titre.appendChild(para);
		}
	else{
		if(syst.after(fin) && acheteur.equals(userSession)){
			var para = document.createElement("p"); 
			para.innerText='Vous avez remporté l\'enchère !';
			
			var titre = document.getElementById("detailsTitre"); 
			titre.appendChild(para);
		}
	}
}

/**
*fonction permettant au vendeur de changer son article 
*/
//function modification(){
//	var vendeur = document.getElementById("vendeur").value; 
//	user; 
//	if (syst<debut ){//&& (user.getPseudo).equals(vendeur)){
//		var input = document.createElement("input"); 
//		var divBouton = document.getElementById("boutons").value; 
//		input.setAttribute("type", "submit"); 
//		input.setAttribute("name", "boutons"); 
//		input.setAttribute("class", "boutons"); 		
//		divBouton.appendChild(input);
//	}
//	
//}