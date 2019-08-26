/**
 * fonction permettant la désactivation du bouton enchere en fonction des dates de ventes de l'article 
 */
function encherir() {

//    var debut = document.getElementById("debutEnchere").value;
//    var fin = document.getElementById("finEnchere").value;
//    var syst = new Date();
//
//
//    var debutEnchere = formatDate(debut); 
//    var finEnchere = formatDate(fin); 
//    var dateSysteme = formatDate(syst); 
//
//    if (!(debutEnchere<dateSysteme) &&!(dateSysteme<finEnchere)) {
      document.getElementById("encherir").disabled=true;

//    }
} 

//function formatDate(date){
//	var jour=date.getDate();
//	var mois=date.getMonth()+1;
//	var annee=date.getFullYear();
//	var date = annee+"-"+mois+"-"+jour;
//	return date;	
//}