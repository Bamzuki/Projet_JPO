var listeFormations = [];
var listeFilieres = [];
var listeBatiments = [];
var listeEcoles = [];
var listeUtilisateurs=[];
var listeEvenements=[];

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
  // Mise à jour de la liste des noms des batiments depuis le serveur
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
  // Mise à jour de la liste des noms des fonctions des ba depuis le serveur
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
      boutonModifier.setAttribute("id", "bouton-modifier-formation"+i);
      boutonModifier.setAttribute("class", "modifier_formation");
      boutonModifier.innerHTML = "<img src='boutons/modifier.png' onClick='changeFormation("+i+")'alt='Oups !'>";
      element.appendChild(boutonModifier);
      ligne.appendChild(element);
      element = document.createElement("td");
      boutonSupprimer = document.createElement("button");
      boutonSupprimer.setAttribute("id", "supprimer-formation"+i);
      boutonSupprimer.setAttribute("class", "supprimer_formation")
      boutonSupprimer.innerHTML = "<img src='boutons/supprimer.png' onClick='deleteFormation("+i+")' alt='Oups !'>";
      element.appendChild(boutonSupprimer);
      ligne.appendChild(element);

      tableau.appendChild(ligne);
    }
  });
  ajax.send(request);
}


function getListeEcole(){

  var request = "request=listeEcoles";
  var ajax    = new XMLHttpRequest();
  ajax.open('GET', 'serveur.php/?'+request);
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.addEventListener('load',  function () {
    // Récupération des données :
    listeEcoles = JSON.parse(ajax.response);

    // Remplissage du tableau
    var tableau       = document.getElementById("resultats_ecoles");
    tableau.innerHTML = "";
    var ligne;
    var element;
    ligne             = document.createElement("tr");
    element           = document.createElement("td");
    element.innerHTML = "Nom";
    ligne.appendChild(element);
	element           = document.createElement("td");
    element.innerHTML = "Image";
    ligne.appendChild(element);
	element           = document.createElement("td");
    element.innerHTML = "Adresse";
    ligne.appendChild(element);
    element           = document.createElement("td");
    element.innerHTML = "Site";
    ligne.appendChild(element);
    element           = document.createElement("td");
    element.innerHTML = "Description";
    ligne.appendChild(element);
    element           = document.createElement("td");
    element.innerHTML = "Modifier";
    ligne.appendChild(element);
    element           = document.createElement("td");
    element.innerHTML = "Supprimer";
    ligne.appendChild(element);

    tableau.appendChild(ligne);

    for (var i = 0; i < listeEcoles.length; i++){
      ligne                     = document.createElement("tr");
      ligne.setAttribute("id", i);
      element                   = document.createElement("td");
      element.innerHTML         = listeEcoles[i].nom;
      ligne.appendChild(element);
	  element                   = document.createElement("td");
      element.innerHTML         = "<img src='image_ecole/"+listeEcoles[i].image+"' width='150' height='150' alt='Image Ecole'  >"
      ligne.appendChild(element);
	  element                   = document.createElement("td");
      element.innerHTML         = listeEcoles[i].adresse;
      ligne.appendChild(element);
      element                   = document.createElement("td");
      element.innerHTML         = listeEcoles[i].site;
      ligne.appendChild(element);
      element                   = document.createElement("td");
      element.innerHTML         = listeEcoles[i].description;
      ligne.appendChild(element);
      element                   = document.createElement("td");
      boutonModifier            = document.createElement("button");
      boutonModifier.setAttribute("id", "bouton-modifier-ecole" + i);
      boutonModifier.setAttribute("class", "modifier_ecole");
      boutonModifier.innerHTML  = "<img src='boutons/modifier.png' onClick='changeEcole(" + i + ") 'alt='Oups !'>";
      element.appendChild(boutonModifier);
      ligne.appendChild(element);
      element                   = document.createElement("td");
      boutonSupprimer           = document.createElement("button");
      boutonSupprimer.setAttribute("id", "supprimer-ecole"+i);
      boutonSupprimer.setAttribute("class", "supprimer_ecole");
      boutonSupprimer.innerHTML = "<img src='boutons/supprimer.png' onClick='deleteEcole(" + i + ") 'alt='Oups !'>";
      element.appendChild(boutonSupprimer);
      ligne.appendChild(element);

      tableau.appendChild(ligne);
     }
   });
  ajax.send(request);
}


function getListeFiliere(){
  var request = "request=listeFilieres"
  var ajax    = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?'+request);
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.addEventListener('load',  function () {
    // Récupération des données :
    listeFilieres= JSON.parse(ajax.response);

    // Remplissage du tableau
    var tableau = document.getElementById("resultats_filieres");
    tableau.innerHTML = "";
    var ligne;
    var element;
    ligne               = document.createElement("tr");
    element             = document.createElement("td");
    element.innerHTML   = "Nom";
    ligne.appendChild(element);
    element             = document.createElement("td");
    element.innerHTML   = "Modifier";
    ligne.appendChild(element);
    element             = document.createElement("td");
    element.innerHTML   = "Supprimer";
    ligne.appendChild(element);

    tableau.appendChild(ligne);

    for (var i = 0; i < listeFilieres.length; i++){
      ligne = document.createElement("tr");
      ligne.setAttribute("id", i);
      element = document.createElement("td");
      element.innerHTML = listeFilieres[i].nom;
      ligne.appendChild(element);
      element = document.createElement("td");
      boutonModifier = document.createElement("button");
      boutonModifier.setAttribute("id", "bouton-modifier-filiere"+i);
      boutonModifier.setAttribute("class", "modifier_filiere");
      boutonModifier.innerHTML = "<img src='boutons/modifier.png' onClick='changeFiliere("+i+")'alt='Oups !'>";
      element.appendChild(boutonModifier);
      ligne.appendChild(element);
      element = document.createElement("td");
      boutonSupprimer = document.createElement("button");
      boutonSupprimer.setAttribute("id", "supprimer-filiere"+i);
      boutonSupprimer.setAttribute("class", "supprimer_filiere");
      boutonSupprimer.innerHTML = "<img src='boutons/supprimer.png' onClick='deleteFiliere("+i+")' alt='Oups !'>";
      element.appendChild(boutonSupprimer);
      ligne.appendChild(element);

      tableau.appendChild(ligne);
    }
  });
  ajax.send(request);
}


function getListeBatiment(){
  var request = "request=listeBatiments";
  var ajax    = new XMLHttpRequest();
  ajax.open('GET', 'serveur.php/?'+request);
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.addEventListener('load',  function () {
    // Récupération des données :
    listeBatiments = JSON.parse(ajax.response);

    // Remplissage du tableau
    var tableau = document.getElementById("resultats_batiments");
    tableau.innerHTML = "";
    var ligne;
    var element;
    ligne = document.createElement("tr");
    element = document.createElement("td");
    element.innerHTML = "Nom";
    ligne.appendChild(element);
    element = document.createElement("td");
    element.innerHTML = "Fonction";
    ligne.appendChild(element);
    element = document.createElement("td");
    element.innerHTML = "Latitude";
    ligne.appendChild(element);
    element = document.createElement("td");
    element.innerHTML = "Longitude";
    ligne.appendChild(element);
    element = document.createElement("td");
    element.innerHTML = "Modifier";
    ligne.appendChild(element);
    element = document.createElement("td");
    element.innerHTML = "Supprimer";
    ligne.appendChild(element);

    tableau.appendChild(ligne);


    for (var i = 0; i < listeBatiments.length; i++){
      ligne   = document.createElement("tr");
      ligne.setAttribute("id", i);
      element = document.createElement("td");
      element.innerHTML = listeBatiments[i].nom;
      ligne.appendChild(element);
      element = document.createElement("td");
      element.innerHTML = listeBatiments[i].fonction;
      ligne.appendChild(element);
      element = document.createElement("td");
      element.innerHTML = listeBatiments[i].lat;
      ligne.appendChild(element);
      element = document.createElement("td");
      element.innerHTML = listeBatiments[i].lng;
      ligne.appendChild(element);
      element = document.createElement("td");
      boutonModifier = document.createElement("button");
      boutonModifier.setAttribute("id", "bouton-modifier-batiment" + i);
      boutonModifier.setAttribute("class", "modifier_batiment")
      boutonModifier.innerHTML = "<img src='boutons/modifier.png' onClick='changeBatiment(" + i + ")' alt='Oups !'>";
      element.appendChild(boutonModifier);
      ligne.appendChild(element);
      element = document.createElement("td");
      boutonSupprimer = document.createElement("button");
      boutonSupprimer.setAttribute("id", "supprimer-batiment"+i);
      boutonSupprimer.setAttribute("class", "supprimer_batiment");
      boutonSupprimer.innerHTML = "<img src='boutons/supprimer.png' onClick='deleteBatiment(" + i + ")' alt='Oups !'>";
      element.appendChild(boutonSupprimer);
      ligne.appendChild(element);

      tableau.appendChild(ligne);
    }
  });
  ajax.send(request);
}


function getListeUtilisateur(){
  var filtre_admin   = document.getElementById("filtre-admin").value;
  console.log(filtre_admin);
  var request = "request=listeUtilisateurs"
  if (filtre_admin != "*"){
    request += "&&filtreAdmin=" + filtre_admin;
  }
 
  var ajax    = new XMLHttpRequest();
  ajax.open('GET', 'serveur.php/?'+request);
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.addEventListener('load',  function () {
    // Récupération des données :
    listeUtilisateurs = JSON.parse(ajax.response);

    // Remplissage du tableau
    var tableau = document.getElementById("resultats_utilisateurs");
    tableau.innerHTML = "";
    var ligne;
    var element;
    ligne = document.createElement("tr");
    element = document.createElement("td");
    element.innerHTML = "Prenom";
    ligne.appendChild(element);
    element = document.createElement("td");
    element.innerHTML = "Nom";
    ligne.appendChild(element);
    element = document.createElement("td");
    element.innerHTML = "pseudo";
    ligne.appendChild(element);
    element = document.createElement("td");
    element.innerHTML = "email";
    ligne.appendChild(element);
    element = document.createElement("td");
    element.innerHTML = "admin";
    ligne.appendChild(element);
	element = document.createElement("td");
    element.innerHTML = "Modifier";
    ligne.appendChild(element);
    element = document.createElement("td");
    element.innerHTML = "Supprimer";
    ligne.appendChild(element);

    tableau.appendChild(ligne);


    for (var i = 0; i < listeUtilisateurs.length; i++){
      ligne   = document.createElement("tr");
      ligne.setAttribute("id", i);
      element = document.createElement("td");
      element.innerHTML = listeUtilisateurs[i].prenom;
      ligne.appendChild(element);
      element = document.createElement("td");
      element.innerHTML = listeUtilisateurs[i].nom;
      ligne.appendChild(element);
      element = document.createElement("td");
      element.innerHTML = listeUtilisateurs[i].pseudo;
      ligne.appendChild(element);
      element = document.createElement("td");
      element.innerHTML = listeUtilisateurs[i].email;
      ligne.appendChild(element);
	  element = document.createElement("td");
      element.innerHTML = listeUtilisateurs[i].admin;
      ligne.appendChild(element);
      element = document.createElement("td");
      boutonModifier = document.createElement("button");
      boutonModifier.setAttribute("id", "bouton-modifier-utilisateur" + i);
      boutonModifier.setAttribute("class", "modifier_filiere")
      boutonModifier.innerHTML = "<img src='boutons/modifier.png' onClick='changeUtilisateur(" + i + ")' alt='Oups !'>";
      element.appendChild(boutonModifier);
      ligne.appendChild(element);
      element = document.createElement("td");
      boutonSupprimer = document.createElement("button");
      boutonSupprimer.setAttribute("id", "supprimer-utilisateur"+i);
      boutonSupprimer.setAttribute("class", "supprimer_filiere");
      boutonSupprimer.innerHTML = "<img src='boutons/supprimer.png' onClick='deleteUtilisateur(" + i + ")' alt='Oups !'>";
      element.appendChild(boutonSupprimer);
      ligne.appendChild(element);

      tableau.appendChild(ligne);
    }
  });
  ajax.send(request);
}


function getListeEvenement(){
  var filtre_ecole  = document.getElementById("filtre-ecole4").value;
  var filtre_batiment   = document.getElementById("filtre-batiment4").value;
  console.log(filtre_ecole);
  console.log(filtre_batiment);
  var request = "request=listeEvenements"
  if (filtre_ecole != "*"){
    request += "&&filtreEcole=" + filtre_ecole;
  }
  if (filtre_batiment != "*"){
    request += "&&filtreBatiment=" + filtre_batiment;
  }
 
  var ajax    = new XMLHttpRequest();
  ajax.open('GET', 'serveur.php/?'+request);
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.addEventListener('load',  function () {
    // Récupération des données :
    listeEvenements = JSON.parse(ajax.response);

    // Remplissage du tableau
    var tableau = document.getElementById("resultats_evenements");
    tableau.innerHTML = "";
    var ligne;
    var element;
    ligne = document.createElement("tr");
    element = document.createElement("td");
    element.innerHTML = "Nom";
    ligne.appendChild(element);
    element = document.createElement("td");
    element.innerHTML = "Début";
    ligne.appendChild(element);
    element = document.createElement("td");
    element.innerHTML = "Fin";
    ligne.appendChild(element);
    element = document.createElement("td");
    element.innerHTML = "Ecole";
    ligne.appendChild(element);
    element = document.createElement("td");
    element.innerHTML = "Batiment";
    ligne.appendChild(element);
    element = document.createElement("td");
    element.innerHTML = "Modifier";
    ligne.appendChild(element);
    element = document.createElement("td");
    element.innerHTML = "Supprimer";
    ligne.appendChild(element);


    tableau.appendChild(ligne);


    for (var i = 0; i < listeEvenements.length; i++){
      ligne   = document.createElement("tr");
      ligne.setAttribute("id", i);
      element = document.createElement("td");
      element.innerHTML = listeEvenements[i].nom;
      ligne.appendChild(element);
      element = document.createElement("td");
      element.innerHTML = listeEvenements[i].debut;
      ligne.appendChild(element);
      element = document.createElement("td");
      element.innerHTML = listeEvenements[i].fin;
      ligne.appendChild(element);
      element = document.createElement("td");
      element.innerHTML = listeEvenements[i].ecole;
      ligne.appendChild(element);
	  element = document.createElement("td");
      element.innerHTML = listeEvenements[i].batiment;
      ligne.appendChild(element);
      element = document.createElement("td");
      boutonModifier = document.createElement("button");
      boutonModifier.setAttribute("id", "bouton-modifier-evenement" + i);
      boutonModifier.setAttribute("class", "modifier_batiment")
      boutonModifier.innerHTML = "<img src='boutons/modifier.png' onClick='changeEvenement(" + i + ")' alt='Oups !'>";
      element.appendChild(boutonModifier);
      ligne.appendChild(element);
      element = document.createElement("td");
      boutonSupprimer = document.createElement("button");
      boutonSupprimer.setAttribute("id", "supprimer-evenement"+i);
      boutonSupprimer.setAttribute("class", "supprimer_batiment");
      boutonSupprimer.innerHTML = "<img src='boutons/supprimer.png' onClick='deleteEvenement(" + i + ")' alt='Oups !'>";
      element.appendChild(boutonSupprimer);
      ligne.appendChild(element);

      tableau.appendChild(ligne);
    }
  });
  ajax.send(request);
}

function saveEcole(){
	
 
  // Enregistrement d'une école dans la base de données
  var input_nom         = document.getElementById("input_ecole").value;
  var input_image       = document.getElementById("fileselect").value;
  console.log(input_image);
  var input_adresse        = document.getElementById("input_adresse").value;
  var input_site        = document.getElementById("input_site").value;
  var input_description = document.getElementById("input_description").value;
  // var ajax = new XMLHttpRequest();
  // ajax.open('GET', 'serveur.php/?request=saveEcole&&nom=' + input_nom + '&&image=' + input_image + '&&adresse=' + input_adresse +  '&&site=' + input_site + '&&description=' + input_description);
  // ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  // ajax.addEventListener('load',  function () {
     // var response = ajax.response;
     // console.log(response);
  // });
  // ajax.send('request=saveEcole&&nom=' + input_nom + '&&adresse=' + input_adresse + '&&site=' + input_site + '&&description=' + input_description);

  //Ajout d'une nouvelle ligne dans la table
  var id                    = listeEcoles.length + 1;
  console.log(id);
  
}


function saveBatiment(){
  // Enregistrement d'un batiment dans la base de données
  var input_nom      = document.getElementById("input_batiment").value;
  var input_fonction = document.getElementById("input_fonction").value;
  var input_lat      = document.getElementById("input_latitude").value;
  var input_lng      = document.getElementById("input_longitude").value;
  var ajax           = new XMLHttpRequest();
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

function saveUtilisateur(){
  // Enregistrement d'une formations dans la base de données
  var input_prenom_utilisateur      = document.getElementById("input_prenom_utilisateur").value;
  var input_nom_utilisateur  = document.getElementById("input_nom_utilisateur").value;
  var input_pseudo    = document.getElementById("input_pseudo").value;
  var input_email = document.getElementById("input_email").value;
  var input_mdp = document.getElementById("input_mdp").value;
   var input_admin  = document.getElementById("input_admin").value;
  var ajax = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?request=saveUtilisateur&&prenom=' + input_prenom_utilisateur + '&&nom=' + input_nom_utilisateur + '&&pseudo=' + input_pseudo + '&&email=' + input_email + '&&mdp=' + input_mdp + '&&admin=' + input_admin);
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.addEventListener('load',  function () {
    var response = ajax.response;
    console.log(response);
	getListeUtilisateur();
  });
  ajax.send('request=saveUtilisateur&&prenom=' + input_prenom_utilisateur + '&&nom=' + input_nom_utilisateur + '&&pseudo=' + input_pseudo + '&&email=' + input_email + '&&mdp=' + input_mdp + '&&admin=' + input_admin);
} 



function saveEvenement(){
  // Enregistrement d'une formations dans la base de données
  var input_nom_evenement     = document.getElementById("input_evenement").value;
  var input_date_evenement    =document.getElementById("input_date").value;
  var input_debut_evenement  = document.getElementById("input_debut").value;
  var input_fin_evenement   = document.getElementById("input_fin").value;
  var input_ecole_evenement = document.getElementById("filtre-ecole3").value;
  var input_batiment_evenement = document.getElementById("filtre-batiment3").value;
  var ajax = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?request=saveEvenement&&nom=' + input_nom_evenement + '&&debut=' + input_date_evenement +" "+input_debut_evenement + '&&fin=' + input_date_evenement +" "+ input_fin_evenement +  '&&id_ecole=' + input_ecole_evenement + '&&id_batiment=' + input_batiment_evenement);
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.addEventListener('load',  function () {
    var response = ajax.response;
    console.log(response);
	getListeEvenement();
  });
  
  ajax.send('request=saveEvenement&&nom=' + input_nom_evenement + '&&debut=' + input_date_evenement + input_debut_evenement + '&&fin=' + input_date_evenement + input_fin_evenement + '&&id_ecole=' + input_ecole_evenement + '&&id_batiment=' + input_batiment_evenement)
  } 
  


function changeEcole(i){

  // Modification des informations d'une école
  bouton                = document.getElementById("bouton-modifier-ecole"+i);

  //Boucle transformant la ligne
  var papa              = bouton.parentNode;
  var papi              = papa.parentNode;
  var nom               = papi.children[0];
  nom.innerHTML         = "<input id='modif_nom_ecole" + i + "' placeholder='"+listeEcoles[i].nom+"'>";
  var adresse              = papi.children[1];
  adresse.innerHTML         = "<input id='modif_adresse_ecole" + i + "' placeholder='"+listeEcoles[i].adresse+"'>";
  var site              = papi.children[2];
  site.innerHTML        = "<input id='modif_site_ecole" + i + "' placeholder='"+listeEcoles[i].site+"'>";
  var description       = papi.children[3];
  description.innerHTML = "<input id='modif_description_ecole" + i + "' placeholder='"+listeEcoles[i].description+"'>";

  //Mise en place les deux nouveaux boutons
  var bouton_valider        = papi.children[4];
  bouton_valider.innerHTML  = "<button  id='bouton-valider-modifications-ecole" + i + "' class='modifier_ecole' onClick='validechangeEcole(" + i + ")'> <img src='boutons/valider.png' alt='Oups'> </button>"
  var bouton_annuler        = papi.children[5];
  bouton_annuler.innerHTML  = "<button  id='bouton-annuler-modifications-ecole" + i + "' class='supprimer_ecole' onClick='annulechangeEcole(" + i + ")'> <img src='boutons/supprimer.png' alt='Oups'> </button>"
}


function validechangeEcole(i){
  bouton                = document.getElementById("bouton-valider-modifications-ecole"+i);
  var papa              = bouton.parentNode;
  var papi              = papa.parentNode;
  var input_id          = listeEcoles[i].id;
  var input_nom         = document.getElementById("modif_nom_ecole" + i + "").value;
  var input_adresse     = document.getElementById("modif_adresse_ecole" + i + "").value;
  var input_site        = document.getElementById("modif_site_ecole" + i + "").value;
  var input_description = document.getElementById("modif_description_ecole" + i + "").value;


  if(input_nom == ""){
    // input_nom= listeEcoles[input_id].nom;
    input_nom= listeEcoles[i].nom;
  }
  
  if(input_adresse == ""){
    // input_nom= listeEcoles[input_id].nom;
    input_adresse= listeEcoles[i].adresse;
  }
  
  if(input_site == ""){
	  // input_site=listeEcoles[input_id].site;
	  input_site= listeEcoles[i].site;
  }

  if(input_description == ""){
	  // input_description=listeEcoles[input_id].description;
	  input_description= listeEcoles[i].description;
  }

  var ajax = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?request=changeEcole&&id=' + input_id +'&&nom=' + input_nom +'&&adresse=' + input_adresse + '&&site=' + input_site + '&&description=' + input_description );
	ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	ajax.addEventListener('load',  function () {
		var response = ajax.response;
		 console.log(response);
		 getListeEcole();

   });

  ajax.send('request=changeEcole&&id=' + input_id + '&&nom=' + input_nom +'&&adresse=' + input_adresse + '&&site=' + input_site + '&&description=' + input_description);

  // on change de nouveau la ligne (normalement avec le changement)
  //boucle qui retransforme la ligne
  var nom                 = papi.children[0];
  nom.innerHTML           = listeEcoles[i].nom;
  var adresse             = papi.children[1];
  adresse.innerHTML       = listeEcoles[i].adresse;
  var site                = papi.children[2];
  site.innerHTML          = listeEcoles[i].site;
  var description         = papi.children[3];
  description.innerHTML   = listeEcoles[i].description;

  //on remet en place les deux anciens boutons
  var bouton_valider        = papi.children[4];
  bouton_valider.innerHTML  = "<button  id='bouton-modifier-ecole" + i + "' class='modifier_ecole'  onClick='changeEcole(" + i + ")'> <img src='boutons/modifier.png' alt='Oups'> </button>"
  var bouton_annuler        = papi.children[5];
  bouton_annuler.innerHTML  = "<button  id='supprimer-ecole"+i+"' class='supprimer_ecole' > <img src='boutons/supprimer.png' onClick='deleteEcole(" + i + ")' alt='Oups'> </button>"
}


function annulechangeEcole(i){

  //on refait la fonction de changement à l'envers
  bouton=document.getElementById("bouton-annuler-modifications-ecole"+i);
  getListeEcole();

  //boucle qui transforme la ligne
  var papa=bouton.parentNode;
  var papi=papa.parentNode;
  var nom=papi.children[0];
  nom.innerHTML=listeEcoles[i].nom;
  var adresse=papi.children[1];
  adresse.innerHTML=listeEcoles[i].adresse;
  var site=papi.children[2];
  site.innerHTML=listeEcoles[i].site;
  var description=papi.children[3];
  description.innerHTML=listeEcoles[i].description;

  //on met en place les deux nouveaux boutons
  var bouton_valider=papi.children[4];
  bouton_valider.innerHTML="<button  id='bouton-modifier-ecole" + i + "' class='modifier_ecole'  onClick='changeEcole("+i+")'> <img src='boutons/modifier.png' alt='Oups'> </button>"
  var bouton_annuler=papi.children[5];
  bouton_annuler.innerHTML="<button  id='supprimer-ecole"+i+"' class='supprimer_ecole' > <img src='boutons/supprimer.png' onClick='deleteEcole(" + i + ")' alt='Oups'> </button>"
}


function changeBatiment(i){

 bouton = document.getElementById("bouton-modifier-batiment" + i);

 //boucle qui transforme la ligne
 var papa               = bouton.parentNode;
 var papi               = papa.parentNode;
 var nom                = papi.children[0];
 nom.innerHTML          = "<input id='modif_nom_batiment" + i + "' placeholder='"+listeBatiments[i].nom+"'>";
 var fonction           = papi.children[1];
 fonction.innerHTML     = "<input id='modif_fonction_batiment" + i + "' placeholder='"+listeBatiments[i].fonction+"'>";
 var latitude           = papi.children[2];
 latitude.innerHTML     = "<input id='modif_latitude_batiment" + i + "' placeholder='"+listeBatiments[i].lat+"'>";
 var longitude          = papi.children[3];
 longitude.innerHTML    = "<input id='modif_longitude_batiment" + i + "' placeholder='"+listeBatiments[i].lng+"'>";

  //on met en place les deux nouveaux boutons
  var bouton_valider        = papi.children[4];
  bouton_valider.innerHTML  = "<button  id='bouton-valider-modifications-batiment" + i + "' class='modifier_batiment'  onClick='validechangeBatiment(" + i + ")'> <img src='boutons/valider.png' alt='Oups'> </button>"
  var bouton_annuler        = papi.children[5];
  bouton_annuler.innerHTML  = "<button  id='bouton-annuler-modifications-batiment" + i + "' class='supprimer_batiment' onClick='annulechangeBatiment(" + i + ")'> <img src='boutons/supprimer.png' alt='Oups'> </button>"
 }


function validechangeBatiment(i){

  bouton    = document.getElementById("bouton-valider-modifications-batiment"+i);
  var papa  = bouton.parentNode;
  var papi  = papa.parentNode;

  var input_id     = listeBatiments[i].id;

  var input_nom         = document.getElementById("modif_nom_batiment" + i + "").value;
  var input_fonction    = document.getElementById("modif_fonction_batiment" + i + "").value;
  var input_latitude    = document.getElementById("modif_latitude_batiment" + i + "").value;
  var input_longitude   = document.getElementById("modif_longitude_batiment" + i + "").value;

  if(input_nom == ""){
    // input_nom= listeEcoles[input_id].nom;
    input_nom= listeBatiments[i].nom;
  }

  if(input_fonction == ""){
    // input_site=listeEcoles[input_id].site;
    input_fonction= listeBatiments[i].fonction;
  }

  if(input_latitude == ""){
    // input_description=listeEcoles[input_id].description;
    input_latitude= listeBatiments[i].lat;
  }

  if(input_longitude == ""){
    // input_description=listeEcoles[input_id].description;
    input_longitude= listeBatiments[i].lng;
  }

  var ajax = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?request=changeBatiment&&id=' + input_id +'&&nom=' + input_nom + '&&fonction=' + input_fonction + '&&lat=' + input_latitude + '&&lng=' + input_longitude);
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.addEventListener('load',  function () {
    var response = ajax.response;
    console.log(response);
    getListeBatiment();
  });

  ajax.send('request=changeBatiment&&id=' + input_id + '&&nom=' + input_nom + '&&fonction=' + input_fonction + '&&lat=' + input_latitude + '&&lng=' + input_longitude);

  // on change de nouveau la ligne (normalement avec le changement)

  //boucle qui retransforme la ligne

  var nom=papi.children[0];
  nom.innerHTML=listeEcoles[i].nom;
  var fonction=papi.children[1];
  fonction.innerHTML=listeBatiments[i].fonction;
  var latitude=papi.children[2];
  latitude.innerHTML=listeBatiments[i].lat;
  var longitude=papi.children[3];
  longitude.innerHTML=listeBatiments[i].lng;

  //on remet en place les deux anciens boutons
  var bouton_valider=papi.children[4];
  bouton_valider.innerHTML="<button  id='bouton-modifier-batiment" + i + "'class='modifier_batiment'  onClick='changeBatiment("+i+")'> <img src='boutons/modifier.png' alt='Oups'> </button>"
  var bouton_annuler=papi.children[5];
  bouton_annuler.innerHTML="<button  id='supprimer-batiment' class='supprimer_batiment'> <img src='boutons/supprimer.png' alt='Oups'> </button>"
}


function annulechangeBatiment(i){
	//on refait la fonction de changement à l'envers
 bouton = document.getElementById("bouton-annuler-modifications-batiment" + i);
 getListeBatiment();
  //boucle qui transforme la ligne
 var papa           = bouton.parentNode;
 var papi           = papa.parentNode;
 var nom            = papi.children[0];
 nom.innerHTML      = listeBatiments[i].nom;
 var fonction       = papi.children[1];
 fonction.innerHTML = listeBatiments[i].fonction;
 var latitude       = papi.children[2];
 latitude.innerHTML = listeBatiments[i].lat;
 var longitude      = papi.children[3];
 longitude.innerHTML= listeBatiments[i].lng;

  //on met en place les deux nouveaux boutons
  var bouton_valider        = papi.children[4];
  bouton_valider.innerHTML  = "<button  id='bouton-modifier-batiment" + i + "' class='modifier_batiment'  onClick='changeBatiment("+i+")'> <img src='boutons/modifier.png' alt='Oups'> </button>"
  var bouton_annuler        = papi.children[5];
  bouton_annuler.innerHTML  = "<button  id='supprimer-batiment"+i+"' class='supprimer_batiment' > <img src='boutons/supprimer.png' onClick='deleteBatiment(" + i + ")' alt='Oups'> </button>"
}


function changeFiliere(i){

  bouton  = document.getElementById("bouton-modifier-filiere"+i);
  //boucle qui transforme la ligne
  var papa        = bouton.parentNode;
  var papi        = papa.parentNode;
  var nom         = papi.children[0];
  nom.innerHTML   = "<input id='modif_nom_filiere"+i+"' placeholder='"+listeFilieres[i].nom+"'>";

  //on met en place les deux nouveaux boutons
  var bouton_valider        = papi.children[1];
  bouton_valider.innerHTML  = "<button  id='bouton-valider-modifications-filiere" + i + "' class='modifier_filiere'  onClick='validechangeFiliere("+i+")'> <img src='boutons/valider.png' alt='Oups'> </button>"
  var bouton_annuler        = papi.children[2];
  bouton_annuler.innerHTML  = "<button  id='bouton-annuler-modifications-filiere" + i + "' class='supprimer_filiere' onClick='annulechangeFiliere("+i+")'> <img src='boutons/supprimer.png' alt='Oups'> </button>"
}


function validechangeFiliere(i){

  bouton    = document.getElementById("bouton-valider-modifications-filiere"+i);
  var papa  = bouton.parentNode;
  var papi  = papa.parentNode;

  var input_id     = listeFilieres[i].id;
  var input_nom    = document.getElementById("modif_nom_filiere"+i+"").value;

  if(input_nom == ""){
    // input_nom= listeEcoles[input_id].nom;
    input_nom= listeFilieres[i].nom;
  }

  var ajax = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?request=changeFiliere&&id=' + input_id +'&&nom=' + input_nom  );
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.addEventListener('load',  function () {
    var response = ajax.response;
    getListeFiliere();
  });

  ajax.send('request=changeFiliere&&id=' + input_id + '&&nom=' + input_nom );

  // on change de nouveau la ligne (normalement avec le changement)

  //boucle qui retransforme la ligne

  var nom         = papi.children[0];
  nom.innerHTML   = listeFilieres[i].nom;

  //on remet en place les deux anciens boutons
  var bouton_valider        = papi.children[1];
  bouton_valider.innerHTML  = "<button  id='bouton-modifier-filiere" + i + "' class='modifier_filiere' onClick='changeFiliere(" + i + ")'> <img src='boutons/modifier.png' alt='Oups'> </button>"
  var bouton_annuler        = papi.children[2];
  bouton_annuler.innerHTML  = "<button  id='supprimer-filiere"+i+"' class='supprimer_filiere' > <img src='boutons/supprimer.png' onClick='deleteFiliere(" + i + ")' alt='Oups'> </button>"
}


function annulechangeFiliere(i){
  //on refait la fonction de changement à l'envers
  bouton = document.getElementById("bouton-valider-modifications-filiere"+i);
  getListeFiliere();
  //boucle qui transforme la ligne
  var papa  = bouton.parentNode;
  var papi  = papa.parentNode;
  var nom   = papi.children[0];
  nom.innerHTML=listeFilieres[i].nom;

  //on met en place les deux nouveaux boutons
  var bouton_valider        = papi.children[1];
  bouton_valider.innerHTML  = "<button  id='bouton-modifier-filiere" + i + "' class='modifier_filiere'  onClick='changeFiliere("+i+")'> <img src='boutons/modifier.png' alt='Oups'> </button>"
  var bouton_annuler        = papi.children[2];
  bouton_annuler.innerHTML  = "<button  id='supprimer-filiere"+i+"' class='supprimer_filiere'> <img src='boutons/supprimer.png' onClick='deleteFiliere(" + i + ")' alt='Oups'> </button>"
}


function changeFormation(i){
  bouton  = document.getElementById("bouton-modifier-formation"+i);
  //boucle qui transforme la ligne
  var papa              = bouton.parentNode;
  var papi              = papa.parentNode;
  var nom               = papi.children[0];
  nom.innerHTML         = "<input id='modif_nom_formation" + i + "' placeholder='"+listeFormations[i].nom+"'>";
  var niveau            = papi.children[1];
  niveau.innerHTML      = "<input id='modif_niveau_formation" + i + "' placeholder='"+listeFormations[i].niveau+"'>";
  var id_ecole          = papi.children[2];
  id_ecole.innerHTML    = "<select id='modif_id_ecole_formation" + i + "'></select>";
  getListeNomEcoles("modif_id_ecole_formation" + i + "");
  var id_batiment       = papi.children[3];
  id_batiment.innerHTML = "<select id='modif_id_batiment_formation" + i + "'></select>";
  getListeNomBatiments("modif_id_batiment_formation" + i + "");
  var id_filiere        = papi.children[4];
  id_filiere.innerHTML  = "<select id='modif_id_filiere_formation" + i + "'></select>";
  getListeNomFilieres("modif_id_filiere_formation" + i + "");

  //on met en place les deux nouveaux boutons
  var bouton_valider         = papi.children[5];
  bouton_valider.innerHTML   = "<button  id='bouton-valider-modifications-formation" + i + "' class='modifier_formation'  onClick='validechangeFormation("+i+")'> <img src='boutons/valider.png' alt='Oups'> </button>"
  var bouton_annuler         = papi.children[6];
  bouton_annuler.innerHTML   = "<button  id='bouton-annuler-modifications-formation" + i + "' class='supprimer_formation' onClick='annulechangeFormation("+i+")'> <img src='boutons/supprimer.png' alt='Oups'> </button>"
 }


function validechangeFormation(i){

  bouton    = document.getElementById("bouton-valider-modifications-formation" + i);
  var papa  = bouton.parentNode;
  var papi  = papa.parentNode;

  var input_id     = listeFormations[i].id;

  var input_nom         = document.getElementById("modif_nom_formation" + i + "").value;
  console.log(input_nom);
  var input_niveau      = document.getElementById("modif_niveau_formation" + i + "").value;
  console.log(input_niveau);
  var input_id_ecole    = document.getElementById("modif_id_ecole_formation" + i + "").value;
  console.log(input_id_ecole);
  var input_id_batiment = document.getElementById("modif_id_batiment_formation" + i + "").value;
  console.log(input_id_batiment);
  var input_id_filiere  = document.getElementById("modif_id_filiere_formation" + i + "").value;
  console.log(input_id_filiere);

  if(input_nom == ""){
    // input_nom= listeEcoles[input_id].nom;
    input_nom = listeFormations[i].nom;
  }


  if(input_niveau == ""){
    // input_site=listeEcoles[input_id].site;
    input_niveau = listeFormations[i].niveau;
  }


  if(input_id_ecole == "*"){
    // input_description=listeEcoles[input_id].description;
    input_id_ecole = listeFormations[i].input_id_ecole;
  }

  if(input_id_batiment == "*"){
    // input_description=listeEcoles[input_id].description;
    input_id_batiment = listeFormations[i].input_id_batiment;
  }

  if(input_id_filiere == "*"){
    // input_description=listeEcoles[input_id].description;
    input_id_filiere = listeFormations[i].input_id_filiere;
  }

  var ajax = new XMLHttpRequest();

  ajax.open('GET', 'serveur.php/?request=changeFormation&&id=' + input_id + '&&nom=' + input_nom + '&&niveau=' + input_niveau + '&&id_ecole=' + input_id_ecole + '&&id_batiment=' + input_id_batiment + '&&id_filiere=' + input_id_filiere );
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.addEventListener('load',  function () {
    var response = ajax.response;
    getListeFormation();
	console.log(response);
  });

  ajax.send('request=changeFormation&&id=' + input_id + '&&nom=' + input_nom + '&&niveau=' + input_niveau + '&&id_ecole=' + input_id_ecole + '&&id_batiment=' + input_id_batiment + '&&id_filiere=' + input_id_filiere );

  // on change de nouveau la ligne (normalement avec le changement)
  //boucle qui retransforme la ligne

  var nom               = papi.children[0];
  nom.innerHTML         = listeFormations[i].nom;
  var niveau            = papi.children[1];
  niveau.innerHTML      = listeFormations[i].niveau;
  var id_ecole          = papi.children[2];
  id_ecole.innerHTML    = listeFormations[i].id_ecole;
  var id_batiment       = papi.children[3];
  id_batiment.innerHTML = listeFormations[i].id_batiment;
  var id_filiere        = papi.children[4];
  id_filiere.innerHTML  = listeFormations[i].id_filiere;

  //on remet en place les deux anciens boutons
  var bouton_valider        = papi.children[5];
  bouton_valider.innerHTML  = "<button  id='bouton-modifier-formation" + i + "' class='modifier_formation'  onClick='changeFormation("+i+")'> <img src='boutons/modifier.png' alt='Oups'> </button>"
  var bouton_annuler        = papi.children[6];
  bouton_annuler.innerHTML  = "<button  id='supprimer-formation"+i+"' class='supprimer_formation' > <img src='boutons/supprimer.png' onClick='deleteFormation(" + i + ")'alt='Oups'> </button>"
}


function annulechangeFormation(i){
  //on refait la fonction de changement à l'envers
  bouton  = document.getElementById("bouton-valider-modifications-formation"+i);
  getListeFormation();
  //boucle qui transforme la ligne
  var papa               = bouton.parentNode;
  var papi               = papa.parentNode;
  var nom                = papi.children[0];
  nom.innerHTML          = listeFormations[i].nom;
  var niveau             = papi.children[1];
  niveau.innerHTML       = listeFormations[i].niveau;
  var id_ecole           = papi.children[2];
  id_ecole.innerHTML     = listeFormations[i].id_ecole;
  var id_batiment        = papi.children[3];
  id_batiment.innerHTML  = listeFormations[i].id_batiment;
  var id_filiere         = papi.children[4];
  id_filiere.innerHTML   = listeFormations[i].id_filiere;

  //on met en place les deux nouveaux boutons
  var bouton_valider        = papi.children[5];
  bouton_valider.innerHTML  = "<button  id='bouton-modifier-formation" + i + "' class='modifier_formation' onClick='changeFormation("+i+")'> <img src='boutons/modifier.png' alt='Oups'> </button>"
  var bouton_annuler        = papi.children[6];
  bouton_annuler.innerHTML  = "<button  id='supprimer-formation"+i+"' class='supprimer_formation'> <img src='boutons/supprimer.png' onClick='deleteFormation(" + i + ")'alt='Oups'> </button>"
}

function changeUtilisateur(i){
  bouton  = document.getElementById("bouton-modifier-utilisateur"+i);
  //boucle qui transforme la ligne
  var papa              = bouton.parentNode;
  var papi              = papa.parentNode;
  var prenom               = papi.children[0];
  prenom.innerHTML         = "<input id='modif_prenom_utilisateur" + i + "' placeholder='"+listeUtilisateurs[i].prenom+"'>";
  var nom           = papi.children[1];
  nom.innerHTML      = "<input id='modif_nom_utilisateur" + i + "' placeholder='"+listeUtilisateurs[i].nom+"'>";
  var pseudo         = papi.children[2];
  pseudo.innerHTML    = "<input id='modif_pseudo_utilisateur" + i + "' placeholder='"+listeUtilisateurs[i].pseudo+"'>";
  var email      = papi.children[3];
  email.innerHTML = "<input id='modif_email_utilisateur" + i + "' placeholder='"+listeUtilisateurs[i].email+"'>";
  var admin        = papi.children[4];
  admin.innerHTML  = "<select id='modif_admin_utilisateur" + i + "'><option value='t'>Oui</option><option value='f'>Non</option></select>";
  admin.checked='false';
  console.log(admin.checked);
  console.log(admin);


  //on met en place les deux nouveaux boutons
  var bouton_valider         = papi.children[5];
  bouton_valider.innerHTML   = "<button  id='bouton-valider-modifications-utilisateur" + i + "' class='modifier_filiere'  onClick='validechangeUtilisateur("+i+")'> <img src='boutons/valider.png' alt='Oups'> </button>"
  var bouton_annuler         = papi.children[6];
  bouton_annuler.innerHTML   = "<button  id='bouton-annuler-modifications-utilisateur" + i + "' class='supprimer_filiere' onClick='annulechangeUtilisateur("+i+")'> <img src='boutons/supprimer.png' alt='Oups'> </button>"
 }

 function check_admin(i){
  bouton  = document.getElementById("bouton-valider-modifications-utilisateur"+i);
  var papa               = bouton.parentNode;
  var papi               = papa.parentNode;
  var admin        = papi.children[5];

  if(admin.checked=='false'){
  admin.innerHTML="<input type='checkbox' id='modif_admin_utilisateur" + i + "' value='t' onclick='check_admin("+i+")' checked='true''>";
  admin.checked='true';
  admin.value='t';
  console.log(admin.value);
  }else{
    admin.innerHTML="<input type='checkbox' id='modif_admin_utilisateur" + i + "' value='f' onclick='check_admin("+i+")' '>";
	admin.value='f';
	admin.checked='false';
    console.log(admin.value); 
  }
  
  
 }

function validechangeUtilisateur(i){

  bouton    = document.getElementById("bouton-valider-modifications-utilisateur" + i);
  var papa  = bouton.parentNode;
  var papi  = papa.parentNode;

  var input_id     = listeUtilisateurs[i].id;

  var input_prenom         = document.getElementById("modif_prenom_utilisateur" + i + "").value;
  var input_nom      = document.getElementById("modif_nom_utilisateur" + i + "").value;
  var input_pseudo    = document.getElementById("modif_pseudo_utilisateur" + i + "").value;
  var input_email = document.getElementById("modif_email_utilisateur" + i + "").value;
  // var input_mdp  = document.getElementById("modif_mdp_utilisateur" + i + "").value;
  var input_admin  =  document.getElementById("modif_admin_utilisateur" + i + "").value;
  console.log(input_admin);


  if(input_prenom == ""){
    // input_nom= listeEcoles[input_id].nom;
    input_prenom = listeUtilisateurs[i].prenom;
  }


  if(input_nom == ""){
    // input_site=listeEcoles[input_id].site;
    input_nom = listeUtilisateurs[i].nom;
  }


  if(input_pseudo == ""){
    // input_description=listeEcoles[input_id].description;
    input_pseudo = listeUtilisateurs[i].pseudo;
  }

  if(input_email == ""){
    // input_description=listeEcoles[input_id].description;
    input_email = listeUtilisateurs[i].email;
  }

  // if(input_mdp == ""){

    // input_mdp = listeUtilisateurs[i].mdp;
  // }
  
  if(input_admin == ""){
    // input_description=listeEcoles[input_id].description;
    input_admin = listeUtilisateurs[i].admin;
  }

  var ajax = new XMLHttpRequest();

  ajax.open('GET', 'serveur.php/?request=changeUtilisateur&&id=' + input_id + '&&prenom=' + input_prenom + '&&nom=' + input_nom + '&&pseudo=' + input_pseudo + '&&email=' + input_email + '&&mdp=' + input_mdp + '&&admin=' + input_admin );
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.addEventListener('load',  function () {
    var response = ajax.response;
    getListeUtilisateur();
	console.log(response);
  });

  ajax.send('request=changeUtilisateur&&id=' + input_id + '&&prenom=' + input_prenom + '&&nom=' + input_nom + '&&pseudo=' + input_pseudo + '&&email=' + input_email + '&&mdp=' + input_mdp + '&&admin=' + input_admin  );
  
  // on change de nouveau la ligne (normalement avec le changement)
  //boucle qui retransforme la ligne

 
  getListeUtilisateur();
  //boucle qui transforme la ligne
  var papa               = bouton.parentNode;
  var papi               = papa.parentNode;
  var prenom                = papi.children[0];
  prenom.innerHTML          = listeUtilisateurs[i].prenom;
  var nom             = papi.children[1];
  nom.innerHTML       = listeUtilisateurs[i].nom;
  var pseudo           = papi.children[2];
  pseudo.innerHTML     = listeUtilisateurs[i].pseudo;
  var email        = papi.children[3];
  email.innerHTML  = listeUtilisateurs[i].email;
  var mdp         = papi.children[4];
  mdp.innerHTML   = listeUtilisateurs[i].mdp;
  var admin       = papi.children[5];
  admin.innerHTML   = listeUtilisateurs[i].admin;

  //on met en place les deux nouveaux boutons
  var bouton_valider        = papi.children[6];
  bouton_valider.innerHTML  = "<button  id='bouton-modifier-utilisateur" + i + "' class='modifier_filiere' onClick='changeUtilisateur("+i+")'> <img src='boutons/modifier.png' alt='Oups'> </button>"
  var bouton_annuler        = papi.children[7];
  bouton_annuler.innerHTML  = "<button  id='supprimer-utilisateur"+i+"' class='supprimer_filiere'> <img src='boutons/supprimer.png' onClick='deleteUtilisateur(" + i + ")'alt='Oups'> </button>"
}


function annulechangeUtilisateur(i){
  //on refait la fonction de changement à l'envers
  bouton  = document.getElementById("bouton-valider-modifications-utilisateur"+i);
  getListeUtilisateur();
  //boucle qui transforme la ligne
  var papa               = bouton.parentNode;
  var papi               = papa.parentNode;
  var prenom                = papi.children[0];
  prenom.innerHTML          = listeUtilisateurs[i].prenom;
  var nom             = papi.children[1];
  nom.innerHTML       = listeUtilisateurs[i].nom;
  var pseudo           = papi.children[2];
  pseudo.innerHTML     = listeUtilisateurs[i].pseudo;
  var email        = papi.children[3];
  email.innerHTML  = listeUtilisateurs[i].email;
  var mdp         = papi.children[4];
  mdp.innerHTML   = listeUtilisateurs[i].mdp;
  var admin       = papi.children[5];
  admin.innerHTML   = listeUtilisateurs[i].admin;

  //on met en place les deux nouveaux boutons
  var bouton_valider        = papi.children[6];
  bouton_valider.innerHTML  = "<button  id='bouton-modifier-utilisateur" + i + "' class='modifier_filiere' onClick='changeUtilisateur("+i+")'> <img src='boutons/modifier.png' alt='Oups'> </button>"
  var bouton_annuler        = papi.children[7];
  bouton_annuler.innerHTML  = "<button  id='supprimer-utilisateur"+i+"' class='supprimer_filiere'> <img src='boutons/supprimer.png' onClick='deleteUtilisateur(" + i + ")'alt='Oups'> </button>"
}

function changeEvenement(i){
  bouton  = document.getElementById("bouton-modifier-evenement"+i);
  //boucle qui transforme la ligne
  var papa              = bouton.parentNode;
  var papi              = papa.parentNode;
  var nom               = papi.children[0];
  nom.innerHTML         = "<input id='modif_nom_evenement" + i + "' placeholder='"+listeEvenements[i].nom+"'>";
  var debut          = papi.children[1];
  debut.innerHTML      = "<input type='time' id='modif_temps_debut_evenement" + i + "'><input type='date' id='modif_date_debut_evenement" + i + "'>";
  var fin         = papi.children[2];
  fin.innerHTML    = "<input type='time' id='modif_temps_fin_evenement"+i+"'><input type='date' id='modif_date_fin_evenement" + i + "'>";
  var id_ecole      = papi.children[3];
  id_ecole.innerHTML = "<select id='modif_id_ecole_evenement" + i + "'></select>";
  getListeNomEcoles("modif_id_ecole_evenement" + i + "");
  var id_batiment       = papi.children[4];
  id_batiment.innerHTML  = "<select id='modif_id_batiment_evenement" + i + "'></select>";
  getListeNomBatiments("modif_id_batiment_evenement" + i + "");


  //on met en place les deux nouveaux boutons
  var bouton_valider         = papi.children[5];
  bouton_valider.innerHTML   = "<button  id='bouton-valider-modifications-evenement" + i + "' class='modifier_filiere'  onClick='validechangeEvenement("+i+")'> <img src='boutons/valider.png' alt='Oups'> </button>"
  var bouton_annuler         = papi.children[6];
  bouton_annuler.innerHTML   = "<button  id='bouton-annuler-modifications-evenement" + i + "' class='supprimer_filiere' onClick='annulechangeEvenement("+i+")'> <img src='boutons/supprimer.png' alt='Oups'> </button>"
 }

 

function validechangeEvenement(i){

  bouton    = document.getElementById("bouton-valider-modifications-evenement" + i);
  var papa  = bouton.parentNode;
  var papi  = papa.parentNode;

  var input_id     = listeEvenements[i].id;

  var input_nom         = document.getElementById("modif_nom_evenement" + i + "").value;
  var input_date_debut      = document.getElementById("modif_date_debut_evenement" + i + "").value;
  var input_date_fin    = document.getElementById("modif_date_fin_evenement" + i + "").value;
  var input_temps_debut      = document.getElementById("modif_temps_debut_evenement" + i + "").value;
  var input_temps_fin    = document.getElementById("modif_temps_fin_evenement" + i + "").value;
  var input_ecole = document.getElementById("modif_id_ecole_evenement" + i + "").value;
  var input_batiment  = document.getElementById("modif_id_batiment_evenement" + i + "").value;



  if(input_nom == ""){
    // input_nom= listeEcoles[input_id].nom;
    input_nom = listeEvenements[i].nom;
  }


  // if(input_debut == ""){
   
    // input_debut = listeEvenements[i].debut;
  // }


  // if(input_fin == ""){
    
    // input_fin = listeEvenements[i].fin;
  // }

  if(input_ecole == ""){
    // input_description=listeEcoles[input_id].description;
    input_ecole = listeEvenements[i].id_ecole;
  }

  if(input_batiment == ""){
    // input_description=listeEcoles[input_id].description;
    input_batiment = listeEvenements[i].id_batiment;
  }
  


  var ajax = new XMLHttpRequest();

  ajax.open('GET', 'serveur.php/?request=changeEvenement&&id=' + input_id + '&&nom=' + input_nom + '&&debut=' + input_date_debut +" "+ input_temps_debut +'&&fin=' + input_date_fin +" "+input_temps_fin +'&&id_ecole=' + input_ecole + '&&id_batiment=' + input_batiment );
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.addEventListener('load',  function () {
    var response = ajax.response;
    getListeEvenement();
	console.log(response);
  });

  ajax.send('request=changeEvenement&&id=' + input_id + '&&nom=' + input_nom + '&&debut=' + input_debut + '&&fin=' + input_fin + '&&id_ecole=' + input_ecole + '&&id_batiment=' + input_batiment);
  // on change de nouveau la ligne (normalement avec le changement)
  //boucle qui retransforme la ligne

 
  getListeEvenement();
  //boucle qui transforme la ligne
  var papa               = bouton.parentNode;
  var papi               = papa.parentNode;
  var nom                = papi.children[0];
  nom.innerHTML          = listeEvenements[i].nom;
  var debut             = papi.children[1];
  debut.innerHTML       = listeEvenements[i].debut;
  var fin           = papi.children[2];
  fin.innerHTML     = listeEvenements[i].fin;
  var id_ecole       = papi.children[3];
  id_ecole.innerHTML  = listeEvenements[i].id_ecole;
  var id_batiment         = papi.children[4];
  id_batiment.innerHTML   = listeEvenements[i].id_batiment;


  //on met en place les deux nouveaux boutons
  var bouton_valider        = papi.children[5];
  bouton_valider.innerHTML  = "<button  id='bouton-modifier-utilisateur" + i + "' class='modifier_filiere' onClick='changeUtilisateur("+i+")'> <img src='boutons/modifier.png' alt='Oups'> </button>"
  var bouton_annuler        = papi.children[6];
  bouton_annuler.innerHTML  = "<button  id='supprimer-utilisateur"+i+"' class='supprimer_filiere'> <img src='boutons/supprimer.png' onClick='deleteUtilisateur(" + i + ")'alt='Oups'> </button>"
}


function annulechangeEvenement(i){
  //on refait la fonction de changement à l'envers
  bouton  = document.getElementById("bouton-valider-modifications-evenement"+i);
  getListeEvenement();
  //boucle qui transforme la ligne
  var papa               = bouton.parentNode;
  var papi               = papa.parentNode;
  var nom                = papi.children[0];
  nom.innerHTML          = listeEvenements[i].nom;
  var debut             = papi.children[1];
  debut.innerHTML       = listeEvenements[i].debut;
  var fin           = papi.children[2];
  fin.innerHTML     = listeEvenements[i].fin;
  var id_ecole        = papi.children[3];
  id_ecole.innerHTML  = listeEvenements[i].id_ecole;
  var id_batiment         = papi.children[4];
  id_batiment.innerHTML   = listeEvenements[i].id_batiment;


  //on met en place les deux nouveaux boutons
  var bouton_valider        = papi.children[5];
  bouton_valider.innerHTML  = "<button  id='bouton-modifier-evenement" + i + "' class='modifier_filiere' onClick='changeEvenement("+i+")'> <img src='boutons/modifier.png' alt='Oups'> </button>"
  var bouton_annuler        = papi.children[6];
  bouton_annuler.innerHTML  = "<button  id='supprimer-evenement"+i+"' class='supprimer_filiere'> <img src='boutons/supprimer.png' onClick='deleteEvenement(" + i + ")'alt='Oups'> </button>"
}


function deleteBatiment(i) {
  // Suppression d'un batiment dans la base de données
  var input_id  = listeBatiments[i].id;
  var ajax      = new XMLHttpRequest();
  ajax.open('GET', 'serveur.php/?request=deleteBatiment&&id=' + input_id );
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.addEventListener('load',  function () {
    console.log(input_id);
    var response = ajax.response;
    getListeBatiment();
	console.log(response);
  });

  ajax.send('request=deleteBatiment&&id=' + input_id );
}


function deleteEcole(i) {
  // Suppression d'un batiment dans la base de données
  var input_id  = listeEcoles[i].id;
  var ajax      = new XMLHttpRequest();
  ajax.open('GET', 'serveur.php/?request=deleteEcole&&id=' + input_id );
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.addEventListener('load',  function () {
    console.log(input_id);
    var response = ajax.response;
    getListeEcole();
	console.log(response);
  });

  ajax.send('request=deleteEcole&&id=' + input_id );
}


function deleteFiliere(i) {
  // Suppression d'une filiere dans la base de données

  
  
  var input_id  = listeFilieres[i].id;
  var ajax      = new XMLHttpRequest();
  ajax.open('GET', 'serveur.php/?request=deleteFiliere&&id=' + input_id );
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.addEventListener('load',  function () {
    console.log(input_id);
    var response = ajax.response;
    getListeFiliere();
	console.log(response);
  });

  ajax.send('request=deleteFiliere&&id=' + input_id );
}


function deleteFormation(i) {
  // Suppression d'un batiment dans la base de données
  var input_id  = listeFormations[i].id;
  var ajax      = new XMLHttpRequest();
  ajax.open('GET', 'serveur.php/?request=deleteFormation&&id=' + input_id );
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.addEventListener('load',  function () {
    console.log(input_id);
    var response = ajax.response;
    getListeFormation();
	console.log(response);
  });

  ajax.send('request=deleteFormation&&id=' + input_id );
}



function deleteUtilisateur(i) {
  // Suppression d'un batiment dans la base de données
  var input_id  = listeUtilisateurs[i].id;
  var ajax      = new XMLHttpRequest();
  ajax.open('GET', 'serveur.php/?request=deleteUtilisateur&&id=' + input_id );
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.addEventListener('load',  function () {
    console.log(input_id);
    var response = ajax.response;
    getListeUtilisateur();
	console.log(response);
  });

  ajax.send('request=deleteUtilisateur&&id=' + input_id );
}

function deleteEvenement(i) {
  // Suppression d'un batiment dans la base de données
  var input_id  = listeEvenements[i].id;
  var ajax      = new XMLHttpRequest();
  ajax.open('GET', 'serveur.php/?request=deleteEvenement&&id=' + input_id );
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.addEventListener('load',  function () {
    console.log(input_id);
    var response = ajax.response;
    getListeEvenement();
	console.log(response);
  });

  ajax.send('request=deleteEvenement&&id=' + input_id );
}



// I - Chargement des listes des noms des différentes
getListeNomNiveaux("filtre-niveau");
getListeNomEcoles("filtre-ecole1");
getListeNomBatiments("filtre-batiment1");
getListeNomFilieres("filtre-filiere1");
getListeNomEcoles("filtre-ecole2");
getListeNomBatiments("filtre-batiment2");
getListeNomEcoles("filtre-ecole3");
getListeNomBatiments("filtre-batiment3");
getListeNomEcoles("filtre-ecole4");
getListeNomBatiments("filtre-batiment4");
getListeNomFilieres("filtre-filiere2");
getListeNomFonctions("filtre-fonction");
getListeEcole();
getListeFiliere();
getListeUtilisateur();

