/**
 * fonction permettant la désactivation du bouton enchere en fonction des dates de ventes de l'article 
 */
window.addEventListener("load",init, finEnchere, modification);

function init() {
	var debut = Date.parse(document.getElementById("debutEnchere").value);
	var fin = Date.parse(document.getElementById("finEnchere").value);
	var syst =Date.parse(new Date());

    if ((debut<syst) && (syst<fin)) {
      document.getElementById("encherir").disabled=false; 
   
     }
    finEnchere();
    modification();
   // document.getElementById("numVendeur").style.display="none";
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
	var btn = document.getElementById("boutons");
	
	if(acheteur!=""){	
		if(syst>fin&& vendeur==userSession){
			para.innerText= acheteur+' à remporté l\'enchère !';
			vente();
			var retrait= document.createElement("input"); 
			retrait.setAttribute("type", "submit"); 
			retrait.setAttribute("id", "retraitFait"); 
			retrait.setAttribute("class", "boutons"); 
			retrait.setAttribute("value", "Retrait effectué");
			btn.appendChild(retrait);
			
			}
		else{
			if(syst>fin && acheteur==userSession){
				para.innerText='Vous avez remporté l\'enchère !';
				gain();
				var back= document.createElement("input"); 
				back.setAttribute("type", "submit"); 
				back.setAttribute("id", "back"); 
				back.setAttribute("class", "boutons"); 
				back.setAttribute("value", "Back");
				btn.appendChild(back);			
				
			}
		}
	}else{
		if(syst>fin){
		para.innerText='Personne n\'a enchérit sur votre vente !';	
		btn.style.display="none";
		}
	}
}

function gain(){
	document.getElementById("categorieArticle").style.display="none";
	document.getElementById("acheteur").style.display="none";
	document.getElementById("debutEnchereArticle").style.display="none";
	document.getElementById("finEnchereArticle").style.display="none";
	document.getElementById("proposition").style.display="none";
	document.getElementById("encherir").style.display="none";
	document.getElementById("annuler").style.display="none";
	//document.getElementById("numVendeur").style.display="";
	
}

function vente(){
	document.getElementById("categorieArticle").style.display="none";
	document.getElementById("finEnchereArticle").style.display="none";
	document.getElementById("proposition").style.display="none";
	document.getElementById("encherir").style.display="none";
	document.getElementById("annuler").style.display="none";	

}

/**
*fonction permettant au vendeur de changer son article 
*/
function modification(){
	var debut = Date.parse(document.getElementById("debutEnchere").value);
	var syst =Date.parse(new Date());
	var vendeur = document.getElementById("vendeur").value; 
	if (syst>debut && vendeur!=userSession){
		var btn = document.getElementById("btn");
		btn.style.display="none";

	}
	
}
