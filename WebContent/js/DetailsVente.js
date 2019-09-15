/**
 * fonction permettant la désactivation du bouton enchere en fonction des dates de ventes de l'article 
 */
window.addEventListener("load",init, finEnchere, modification);


function init() {
	var debut = Date.parse(document.getElementById("debutEnchere").value);
	var fin = Date.parse(document.getElementById("finEnchere").value);
	var syst =Date.parse(new Date());
	var btnEnchere = document.getElementById("btnEnchere");
	var back = document.getElementById("btnBack");
	var retrait = document.getElementById("btnRetait");
	var modif = document.getElementById("btn");
	var tel = document.getElementById("numVendeur");

    if ((debut<syst) && (syst<fin)) { 
	  	back.style.display="none";
	  	retrait.style.display="none";	
	  	modif.style.display="none";
	  	tel.style.display="none";
     }
    finEnchere();
    modification();
   
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
	var back = document.getElementById("btnBack");
	var retrait = document.getElementById("btnRetait");
	var ench = document.getElementById("btnEnchere");
	var modif = document.getElementById("btn");
	var tel = document.getElementById("numVendeur");
	
	if(acheteur!=""){	
		if(syst>fin&& vendeur==userSession){
			para.innerText= acheteur+' à remporté l\'enchère !';
			vente();
			back.style.display="none";
			ench.style.display="none";
			modif.style.display="none";
			tel.style.display="none";
			
		}	
		else{
			if(syst>fin && acheteur==userSession){
				para.innerText='Vous avez remporté l\'enchère !';
				gain();
				retrait.style.display="none";		
				ench.style.display="none";
				modif.style.display="none";
			}
		}
	}else{
		if(syst>fin){
		para.innerText='Personne n\'a enchérit sur votre vente !';	
		retrait.style.display="none";
		ench.style.display="none";
		modif.style.display="none";
		tel.style.display="none";
		}
	}
}

/**
 * fonction permettant de "cacher" des divs lorsque l'acheteur à remporté une enchère
 *
 */

function gain(){
	document.getElementById("categorieArticle").style.display="none";
	document.getElementById("acheteur").style.display="none";
	document.getElementById("debutEnchereArticle").style.display="none";
	document.getElementById("finEnchereArticle").style.display="none";
	document.getElementById("proposition").style.display="none";
	document.getElementById("encherir").style.display="none";
	document.getElementById("annuler").style.display="none";

}

/**
 * fonction permettant de "cacher" des divs lorsque le vendeur à vendu son article
 *
 */

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
	var back = document.getElementById("btnBack");
	var retrait = document.getElementById("btnRetait");
	var ench = document.getElementById("btnEnchere");
	var tel = document.getElementById("numVendeur");
	
	if (syst<debut && vendeur==userSession){
		retrait.style.display="none";
		ench.style.display="none";
		back.style.display="none";
		tel.style.display="none";
	}
	
}
