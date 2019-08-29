/**
 * fonction permettant la désactivation du bouton enchere en fonction des dates de ventes de l'article 
 */
window.addEventListener("load",init);
function init() {

    var debut = Date.parse( document.getElementById("debutEnchere").value);
    var fin = Date.parse(document.getElementById("finEnchere").value);
    var syst =Date.parse(new Date());

    if ((debut<syst) && (syst<fin)) {
      document.getElementById("encherir").disabled=false; 
   
     }
} 

//TODO faire en sorte que l'enchère se termine le jour indiqué à 23h59 et début le jour de bébut à 00h01