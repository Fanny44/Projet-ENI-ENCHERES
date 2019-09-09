/**
 * fonction permettant la désactivation du bouton enchere en fonction des dates de ventes de l'article 
 */
window.addEventListener("load",init, finEnchere);

function init() {
	var debut = Date.parse(document.getElementById("debutEnchere").value);
	var fin = Date.parse(document.getElementById("finEnchere").value);
	var syst =Date.parse(new Date());

    if ((debut<syst) && (syst<fin)) {
      document.getElementById("encherir").disabled=false; 
   
     }
    finEnchere();
} 

//TODO faire en sorte que l'enchère se termine le jour indiqué à 23h59 et début le jour de bébut à 00h01

/**
 * fonction affichant un message en fonction du vendeur ou de l'acheteur qui a remporté l'enchère
 */
function finEnchere(){
	var fin=Date.parse(document.getElementById("finEnchere").value);
	var syst=Date.parse(new Date());
	var vendeur=document.getElementById("vendeur").value; 
	var acheteur=document.getElementById("acheteur").value;
	var para = document.getElementById("fin"); 
	
	if(acheteur!=""){	
		if(syst>fin&& vendeur==userSession){
			para.innerText= acheteur+' à remporté l\'enchère !';
			}
		else{
			if(syst>fin && acheteur==userSession){
				para.innerText='Vous avez remporté l\'enchère !';
			}
		}
	}else{
		para.innerText='Personne n\'a enchérit sur votre vente !';		
	}
}

/**
 * fonction permettant l'affichage de la page si user à remporté enchère 
 */
function gain(){
	
}

function vente(){
	
}

/**
*fonction permettant au vendeur de changer son article 
*/
//function modification(){
//	var vendeur = document.getElementById("vendeur").value; 
//	userSession; 
//	if ((syst<debut )&& (userSession).equals(vendeur)){
//		var input = document.createElement("input"); 
//		var divBouton = document.getElementById("boutons").value; 
//		input.setAttribute("type", "submit"); 
//		input.setAttribute("name", "boutons"); 
//		input.setAttribute("class", "boutons"); 
//		input.setAttribute("onclick", "modifier()");
//		divBouton.appendChild(input);
//	}
//	
//}