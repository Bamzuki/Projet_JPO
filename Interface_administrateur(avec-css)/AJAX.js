var listeFormations = [];
var listeFilieres = [];
var listeBatiments = [];
var listeEcoles = [];

function getListeNomNiveaux(idSelectNiveau){
  // Mise à jour de la liste des noms des niveaux depuis le serveur
  var ajax = new XMLHttpRequest();
  ajax.open('GET', 'serveur.php/?request=listeNomObjets&&nomTable=niveaux');
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     var listeNomNiveaux = JSON.parse(ajax.response);
     var select_niveau = document.getElementById(idSelectNiveau);
	 var newOption = document.createElement("option");
     newOption.value = '*';
	 newOption.text = 'Tous';
     select_niveau.appendChild(newOption);
     for (var i = 0; i < listeNomNiveaux.length; i++){
       var newOption = document.createElement("option");
       newOption.value = listeNomNiveaux[i];
	   newOption.text = listeNomNiveaux[i];
       select_niveau.appendChild(newOption);
     }

   });
  ajax.send('request=listeNomObjets&&nomTable=niveaux');
}

function getListeNomEcoles(idSelectEcole){
  // Mise à jour de la liste des noms des écoles depuis le serveur
  var ajax = new XMLHttpRequest();
  ajax.open('GET', 'serveur.php/?request=listeNomObjets&&nomTable=ecoles');
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     var listeNomEcoles = JSON.parse(ajax.response);
     var select_ecole = document.getElementById(idSelectEcole);
	 var newOption = document.createElement("option");
     newOption.value = '*';
	 newOption.text = 'Toutes';
     select_ecole.appendChild(newOption);
     for (var i = 0; i < listeNomEcoles.length; i++){
       var newOption = document.createElement("option");
       newOption.value = listeNomEcoles[i];
	   newOption.text = listeNomEcoles[i];
       select_ecole.appendChild(newOption);
	 }

   });
  ajax.send('request=listeNomObjets&&nomTable=ecoles');
}

function getListeNomBatiments(idSelectBatiment){
  // Mise à jour de la liste des noms des filieres depuis le serveur
  var ajax = new XMLHttpRequest();
  ajax.open('GET', 'serveur.php/?request=listeNomObjets&&nomTable=batiments');
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     var listeNomBatiments = JSON.parse(ajax.response);
     var select_batiment = document.getElementById(idSelectBatiment);
	 var newOption = document.createElement("option");
     newOption.value = '*';
	 newOption.text = 'Tous';
     select_batiment.appendChild(newOption);
     for (var i = 0; i < listeNomBatiments.length; i++){
       var newOption = document.createElement("option");
       newOption.value = listeNomBatiments[i];
	   newOption.text = listeNomBatiments[i];
       select_batiment.appendChild(newOption);
     }

   });
  ajax.send('request=listeNomObjets&&nomTable=batiments');
}

function getListeNomFilieres(idSelectFiliere){
  // Mise à jour de la liste des noms des filieres depuis le serveur
  var ajax = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?request=listeNomObjets&&nomTable=filieres');
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     var listeNomFilieres = JSON.parse(ajax.response);
     var select_filiere = document.getElementById(idSelectFiliere);
	 var newOption = document.createElement("option");
     newOption.value = '*';
	 newOption.text = 'Toutes';
     select_filiere.appendChild(newOption);
     for (var i = 0; i < listeNomFilieres.length; i++){
       var newOption = document.createElement("option");
       newOption.value = listeNomFilieres[i];
	   newOption.text = listeNomFilieres[i];
       select_filiere.appendChild(newOption);
     }

   });
  ajax.send('request=listeNomObjets&&nomTable=filieres');
}

function getListeNomFonctions(idSelectFonction){

  var ajax = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?request=listeNomObjets&&nomTable=fonctions');
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     var listeNomFonctions = JSON.parse(ajax.response);
     var select_fonction = document.getElementById(idSelectFonction);
	 var newOption = document.createElement("option");
     newOption.value = '*';
	 newOption.text = 'Toutes';
     select_fonction.appendChild(newOption);
     for (var i = 0; i < listeNomFonctions.length; i++){
       var newOption = document.createElement("option");
       newOption.value = listeNomFonctions[i];
	   newOption.text = listeNomFonctions[i];
       select_fonction.appendChild(newOption);
     }

   });
  ajax.send('request=listeNomObjets&&nomTable=filieres');
}




function getListeFormation(){

  // Mise à jour de la liste des formations depuis le serveur en fonction des filtres choisis
  var filtre_niveau    = document.getElementById("filtre-niveau").value;
  var filtre_ecole     = document.getElementById("filtre-ecole2").value;
  var filtre_batiment  = document.getElementById("filtre-batiment2").value;
  var filtre_filiere   = document.getElementById("filtre-filiere2").value;
  var request = "request=listeFormations"
  if (filtre_niveau != "*"){
    request += "&&filtreNiveau=" + filtre_niveau;
  }
  if (filtre_ecole != "*"){
    request += "&&filtreEcole=" + filtre_ecole;
  }
  if (filtre_batiment != "*"){
    request += "&&filtreBatiment=" + filtre_batiment;
  }
  if (filtre_filiere != "*"){
    request += "&&filtreFiliere=" + filtre_filiere;
  }
  var ajax = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?'+request);
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     // Récupération des données :
     listeFormations = JSON.parse(ajax.response);

     // Remplissage du tableau
     var tableau = document.getElementById("resultats_formations");
	 tableau.innerHTML="";
	 var ligne;
     var element;
     ligne = document.createElement("tr");
     element = document.createElement("td");
     element.innerHTML = "Nom";
     ligne.appendChild(element);
     element = document.createElement("td");
     element.innerHTML = "Niveau";
     ligne.appendChild(element);
     element = document.createElement("td");
     element.innerHTML = "Ecole";
     ligne.appendChild(element);
     element = document.createElement("td");
     element.innerHTML = "Batiment";
     ligne.appendChild(element);
     element = document.createElement("td");
     element.innerHTML = "Filiere";
     ligne.appendChild(element);
     element = document.createElement("td");
     element.innerHTML = "Modifier";
     ligne.appendChild(element);
     element = document.createElement("td");
     element.innerHTML = "Supprimer";
     ligne.appendChild(element);

     tableau.appendChild(ligne);
    

	   for (var i = 0; i < listeFormations.length; i++){
	    ligne = document.createElement("tr");
        ligne.setAttribute("id", i);
        element = document.createElement("td");
        element.innerHTML = listeFormations[i].nom;
        ligne.appendChild(element);
        element = document.createElement("td");
        element.innerHTML = listeFormations[i].niveau;
        ligne.appendChild(element);
        element = document.createElement("td");
        element.innerHTML = listeFormations[i].ecole;
        ligne.appendChild(element);
        element = document.createElement("td");
        element.innerHTML = listeFormations[i].batiment;
        ligne.appendChild(element);
        element = document.createElement("td");
        element.innerHTML = listeFormations[i].filiere;
        ligne.appendChild(element);
        element = document.createElement("td");
        boutonModifier = document.createElement("button");
        boutonModifier.setAttribute("id", "modifier");
        boutonModifier.innerHTML = "<img src='boutons/modifier.png' onClick='changeFormation()'alt='Oups !'>";
        element.appendChild(boutonModifier);
        ligne.appendChild(element);
        element = document.createElement("td");
        boutonSupprimer = document.createElement("button");
        boutonSupprimer.setAttribute("id", "supprimer");
        boutonSupprimer.innerHTML = "<img src='boutons/supprimer.png' alt='Oups !'>";
        element.appendChild(boutonSupprimer);
        ligne.appendChild(element);

        tableau.appendChild(ligne);
     }
   });
  ajax.send(request);

}


function getListeEcole(){


  var request = "request=listeEcoles"

 
  
  
  var ajax = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?'+request);
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     // Récupération des données :
     listeEcoles = JSON.parse(ajax.response);

     // Remplissage du tableau
     var tableau = document.getElementById("resultats_ecoles");
	 tableau.innerHTML="";
	 var ligne;
     var element;
     ligne = document.createElement("tr");
     element = document.createElement("td");
     element.innerHTML = "Nom";
     ligne.appendChild(element);
     element = document.createElement("td");
     element.innerHTML = "Site";
     ligne.appendChild(element);
     element = document.createElement("td");
     element.innerHTML = "Description";
     ligne.appendChild(element);
     element = document.createElement("td");
     element.innerHTML = "Modifier";
     ligne.appendChild(element);
     element = document.createElement("td");
     element.innerHTML = "Supprimer";
     ligne.appendChild(element);

     tableau.appendChild(ligne);
    

	   for (var i = 0; i < listeEcoles.length; i++){
	    ligne = document.createElement("tr");
        ligne.setAttribute("id", i);
        element = document.createElement("td");
        element.innerHTML = listeEcoles[i].nom;
        ligne.appendChild(element);
        element = document.createElement("td");
        element.innerHTML = listeEcoles[i].site;
        ligne.appendChild(element);
        element = document.createElement("td");
        element.innerHTML = listeEcoles[i].description;
        ligne.appendChild(element);
        element = document.createElement("td");
        boutonModifier = document.createElement("button");
        boutonModifier.setAttribute("id", "bouton-modifier-ecole"+i);
        boutonModifier.innerHTML = "<img src='boutons/modifier.png' onClick='changeEcole("+i+")'alt='Oups !'>";
        element.appendChild(boutonModifier);
        ligne.appendChild(element);
        element = document.createElement("td");
        boutonSupprimer = document.createElement("button");
        boutonSupprimer.setAttribute("id", "supprimer");
        boutonSupprimer.innerHTML = "<img src='boutons/supprimer.png' alt='Oups !'>";
        element.appendChild(boutonSupprimer);
        ligne.appendChild(element);

        tableau.appendChild(ligne);
     }
   });
  ajax.send(request);

  
  
  
  
  
  
}

function getListeFiliere(){


  var request = "request=listeFilieres"

  var ajax = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?'+request);
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     listeFilieres = JSON.parse(ajax.response);


	 var select_filiere=[];
	 var ligne = ("<tr></tr>");
	 select_filiere[0]=ligne;
	 for (var i = 1; i < listeFilieres.length; i++){
	   var ligne = ("<tr></tr>");
	   ligne=ligne+("<td>" + listeFilieres[i].nom + "</td>");
	   select_filiere[i]=ligne;
     }
	 var results=document.getElementById("resultats_filieres");
	 results.innerHTML=select_filiere;
   });
  ajax.send(request);

}


function getListeBatiment(){


  var filtre_fonction    = document.getElementById("filtre-fonction").value;

  var request = "request=listeBatiments"
  if (filtre_fonction != "*"){
    request += "&&filtreFonction=" + filtre_fonction;
  }

  var ajax = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?'+request);
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     listeBatiments = JSON.parse(ajax.response);


	 var select_batiment=[];
	 var ligne = ("<tr></tr>");
	 ligne=ligne+("<th>" +"Nom" + "</th>");
	 ligne=ligne+("<th>" +"Fonction" + "</th>");
	 ligne=ligne+("<th>" +"Latitude" + "</th>");
	 ligne=ligne+("<th>" +"Longitude" + "</th>");
	 select_batiment[0]=ligne;
	 for (var i = 1; i < listeBatiments.length; i++){
	   var ligne = ("<tr></tr>");
	   ligne=ligne+("<td>" + listeBatiments[i].nom + "</td>");
	   ligne=ligne+("<td>" + listeBatiments[i].fonction + "</td>");
	   ligne=ligne+("<td>" + listeBatiments[i].lat + "</td>");
	   ligne=ligne+("<td>" + listeBatiments[i].lng + "</td>");
	   select_batiment[i]=ligne;
     }
	 var results=document.getElementById("resultats_batiments");
	 results.innerHTML=select_batiment;
   });
  ajax.send(request);

}


function saveEcole(){
  // Enregistrement d'une école dans la base de données

  var input_nom         = document.getElementById("input_ecole").value;
  var input_site        = document.getElementById("input_site").value;
  var input_description = document.getElementById("input_description").value;
  var ajax = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?request=saveEcole&&nom=' + input_nom + '&&site=' + input_site + '&&description=' + input_description);
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     var response = ajax.response;
     console.log(response);

   });
  ajax.send('request=saveEcole&&nom=' + input_nom + '&&site=' + input_site + '&&description=' + input_description);
}

function saveBatiment(){
  // Enregistrement d'un batiment dans la base de données

  var input_nom      = document.getElementById("input_batiment").value;
  var input_fonction = document.getElementById("input_fonction").value;
  var input_lat      = document.getElementById("input_latitude").value;
  var input_lng      = document.getElementById("input_longitude").value;
  var ajax = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?request=saveBatiment&&nom=' + input_nom + '&&fonction=' + input_fonction + '&&lat=' + input_lat + '&&lng=' + input_lng);
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     var response = ajax.response;
     console.log(response);

   });
  ajax.send('request=saveBatiment&&nom=' + input_nom + '&&fonction=' + input_fonction + '&&lat=' + input_lat + '&&lng=' + input_lng);
}

function saveFiliere(){
  // Enregistrement d'une filière dans la base de données

  var input_nom = document.getElementById("input_filiere").value;
  var ajax = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?request=saveFiliere&&nom=' + input_nom);
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     var response = ajax.response;
     console.log(response);

   });
  ajax.send('request=saveFiliere&&nom=' + input_nom);
}

function saveFormation(){
  // Enregistrement d'une formations dans la base de données

  var input_nom      = document.getElementById("input_formation").value;
  var input_niveau   = document.getElementById("input_niveau").value;
  var input_ecole    = document.getElementById("filtre-ecole1").value;
  var input_batiment = document.getElementById("filtre-batiment1").value;
  var input_filiere  = document.getElementById("filtre-filiere1").value;
  var ajax = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?request=saveFormation&&nom=' + input_nom + '&&niveau=' + input_niveau + '&&ecole=' + input_ecole + '&&batiment=' + input_batiment + '&&filiere=' + input_filiere);
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     var response = ajax.response;
     console.log(response);

   });

  ajax.send('request=saveFormation&&nom=' + input_nom + '&&niveau=' + input_niveau + '&&ecole=' + input_ecole + '&&batiment=' + input_batiment + '&&filiere=' + input_filiere);
}


function changeEcole(i){
 
 bouton=document.getElementById("bouton-modifier-ecole"+i);
  //boucle qui transforme la ligne
 var papa=bouton.parentNode;
 var papi=papa.parentNode;
 var nom=papi.children[0];
 nom.innerHTML="<input>";
 var site=papi.children[1];
 site.innerHTML="<input>";
 var description=papi.children[2];
 description.innerHTML="<input>";
 

  
  //on met en place les deux nouveaux boutons
  var bouton_valider=papi.children[3];
  bouton_valider.innerHTML="<button  id='bouton-valider-modifications-ecole"+i+"'  onClick='validechangeEcole("+i+")'> <img src='boutons/valider.png' alt='Oups'> </button>"
  var bouton_annuler=papi.children[4];
  bouton_annuler.innerHTML="<button  id='bouton-annuler-modifications-ecole"+i+"' onClick='annulechangeEcole("+i+")'> <img src='boutons/supprimer.png' alt='Oups'> </button>"

  


  
 
  
  

 }
  
function validechangeEcole(i){
 
 bouton=document.getElementById("bouton-valider-modifications-ecole"+i);
 var papa=bouton.parentNode;
 var papi=papa.parentNode;



	
	

  var input_id     = listeEcoles[i].id;
  console.log(listeEcoles);
  var input_nom   = papi.children[0];
  console.log(input_nom);
  var input_site   = papi.children[1];
  console.log(input_site);
  var input_description = papi.children[2];
  console.log(input_description);
  
  if(input_nom == undefined){
	  // input_nom= listeEcoles[input_id].nom;
	  input_nom= "nom";
  }

  
  if(input_site == undefined){
	  // input_site=listeEcoles[input_id].site;
	  input_site= "site";
  }
 
  
  if(input_description == undefined){
	  // input_description=listeEcoles[input_id].description;
	  input_description= "description";
  }
  console.log(listeEcoles);
  
  var ajax = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?request=changeEcole&&id=' + input_id +'&&nom=' + input_nom + '&&site=' + input_site + '&&description=' + input_description );
	ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	ajax.addEventListener('load',  function () {
		var response = ajax.response;
		 console.log(response);
     
   });

  ajax.send('request=changeEcole&&id=' + input_id + '&&nom=' + input_nom + '&&site=' + input_site + '&&description=' + input_description);
	
  // on change de nouveau la ligne (normalement avec le changement)
  
  
  getListeEcole();
  
  //boucle qui retransforme la ligne
 
 var nom=papi.children[0];
 nom.innerHTML=listeEcoles[i].nom;
 var site=papi.children[1];
 site.innerHTML=listeEcoles[i].site;
 var description=papi.children[2];
 description.innerHTML=listeEcoles[i].description;
 

  
  //on remet en place les deux anciens boutons
  var bouton_valider=papi.children[3];
  bouton_valider.innerHTML="<button  id='bouton-modifier-ecole"+i+"'  onClick='changeEcole("+i+")'> <img src='boutons/modifier.png' alt='Oups'> </button>"
  var bouton_annuler=papi.children[4];
  bouton_annuler.innerHTML="<button  id='supprimer' > <img src='boutons/supprimer.png' alt='Oups'> </button>"
  
  
  
  
 
 
  
}


function annulechangeEcole(i){
	//on refait la fonction de changement à l'envers
 bouton=document.getElementById("bouton-valider-modifications-ecole"+i);
 getListeEcole();
  //boucle qui transforme la ligne
 var papa=bouton.parentNode;
 var papi=papa.parentNode;
 var nom=papi.children[0];
 nom.innerHTML=listeEcoles[i].nom;
 var site=papi.children[1];
 site.innerHTML=listeEcoles[i].site;
 var description=papi.children[2];
 description.innerHTML=listeEcoles[i].description;
 

  
  //on met en place les deux nouveaux boutons
  var bouton_valider=papi.children[3];
  bouton_valider.innerHTML="<button  id='bouton-modifier-ecole"+i+"'  onClick='changeEcole("+i+")'> <img src='boutons/modifier.png' alt='Oups'> </button>"
  var bouton_annuler=papi.children[4];
  bouton_annuler.innerHTML="<button  id='supprimer' > <img src='boutons/supprimer.png' alt='Oups'> </button>"

  
}
  
  



function changeBatiment(){
	
}


function changeFiliere(){
	
}


function changeFormation(){
	
}

// I - Chargement des listes des noms des différentes
getListeNomNiveaux("filtre-niveau");
getListeNomEcoles("filtre-ecole1");
getListeNomBatiments("filtre-batiment1");
getListeNomFilieres("filtre-filiere1");
getListeNomEcoles("filtre-ecole2");
getListeNomBatiments("filtre-batiment2");
getListeNomFilieres("filtre-filiere2");
getListeNomFonctions("filtre-fonction");
getListeEcole();
getListeFiliere();
