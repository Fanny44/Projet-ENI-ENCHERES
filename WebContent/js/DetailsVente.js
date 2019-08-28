/**
 * fonction permettant la désactivation du bouton enchere en fonction des dates de ventes de l'article 
 */
window.addEventListener("load",init);
function init() {

    var debut = document.getElementById("debutEnchere").value;
    var fin = document.getElementById("finEnchere").value;
    var syst = new Date();


    var debutEnchere = formatDate(debut); 
    var finEnchere = formatDate(fin); 
    var dateSysteme = formatDate(syst); 
console.log("debutEnchere");
console.log("finEnchere");
console.log("dateSystem");
    if (!(debutEnchere<dateSysteme) &&!(dateSysteme<finEnchere)) {
     console.log("ok");
     document.getElementById("encherir").disabled=true;

    }
} 

function formatDate(data){
    
    var jour=data.getDate();
    var mois=data.getMonth()+1;
    var annee=data.getFullYear();
    var date = annee+"-"+mois+"-"+jour;
    return date;
}