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
  // Mise à jour de la liste des noms des fonctions depuis le serveur
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
      boutonSupprimer.setAttribute("id", "supprimer-formation");
      boutonSupprimer.setAttribute("class", "supprimer_formation")
      boutonSupprimer.innerHTML = "<img src='boutons/supprimer.png' alt='Oups !'>";
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
      boutonSupprimer.setAttribute("id", "supprimer-ecole");
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
      boutonSupprimer.setAttribute("id", "supprimer-filiere");
      boutonSupprimer.setAttribute("class", "supprimer_filiere");
      boutonSupprimer.innerHTML = "<img src='boutons/supprimer.png' alt='Oups !'>";
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
      boutonModifier.setAttribute("class", "supprimer_batiment")
      boutonModifier.innerHTML = "<img src='boutons/modifier.png' onClick='changeBatiment(" + i + ")' alt='Oups !'>";
      element.appendChild(boutonModifier);
      ligne.appendChild(element);
      element = document.createElement("td");
      boutonSupprimer = document.createElement("button");
      boutonSupprimer.setAttribute("id", "supprimer-batiment");
      boutonSupprimer.setAttribute("class", "supprimer_batiment");
      boutonSupprimer.innerHTML = "<img src='boutons/supprimer.png' onClick='deleteBatiment(" + i + ")' alt='Oups !'>";
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
  var input_site        = document.getElementById("input_site").value;
  var input_description = document.getElementById("input_description").value;
  var ajax = new XMLHttpRequest();
  ajax.open('GET', 'serveur.php/?request=saveEcole&&nom=' + input_nom + '&&site=' + input_site + '&&description=' + input_description);
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.addEventListener('load',  function () {
     var response = ajax.response;
     console.log(response);
  });
  ajax.send('request=saveEcole&&nom=' + input_nom + '&&site=' + input_site + '&&description=' + input_description);

  //Ajout d'une nouvelle ligne dans la table
  var id                    = listeEcoles.length + 1;
  console.log(id);
  // var tableau               = document.getElementById("resultats_ecoles");
  // ligne                     = document.createElement("tr");
  // ligne.setAttribute("id", i);
  // element                   = document.createElement("td");
  // element.innerHTML         = listeEcoles[i].nom;
  // ligne.appendChild(element);
  // element                   = document.createElement("td");
  // element.innerHTML         = listeEcoles[i].site;
  // ligne.appendChild(element);
  // element                   = document.createElement("td");
  // element.innerHTML         = listeEcoles[i].description;
  // ligne.appendChild(element);
  // element                   = document.createElement("td");
  // boutonModifier            = document.createElement("button");
  // boutonModifier.setAttribute("id", "bouton-modifier-ecole" + i);
  // boutonModifier.setAttribute("class", "modifier_ecole");
  // boutonModifier.innerHTML  = "<img src='boutons/modifier.png' onClick='changeEcole(" + i + ") 'alt='Oups !'>";
  // element.appendChild(boutonModifier);
  // ligne.appendChild(element);
  // element                   = document.createElement("td");
  // boutonSupprimer           = document.createElement("button");
  // boutonSupprimer.setAttribute("id", "supprimer-ecole");
  // boutonSupprimer.setAttribute("class", "supprimer_ecole");
  // boutonSupprimer.innerHTML = "<img src='boutons/supprimer.png' onClick='deleteEcole(" + i + ") 'alt='Oups !'>";
  // element.appendChild(boutonSupprimer);
  // ligne.appendChild(element);
  //
  // tableau.appendChild(ligne);
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


function changeEcole(i){

  // Modification des informations d'une école
  bouton                = document.getElementById("bouton-modifier-ecole"+i);

  //Boucle transformant la ligne
  var papa              = bouton.parentNode;
  var papi              = papa.parentNode;
  var nom               = papi.children[0];
  nom.innerHTML         = "<input id='modif_nom_ecole" + i + "' placeholder='"+listeEcoles[i].nom+"'>";
  var site              = papi.children[1];
  site.innerHTML        = "<input id='modif_site_ecole" + i + "' placeholder='"+listeEcoles[i].site+"'>";
  var description       = papi.children[2];
  description.innerHTML = "<input id='modif_description_ecole" + i + "' placeholder='"+listeEcoles[i].description+"'>";

  //Mise en place les deux nouveaux boutons
  var bouton_valider        = papi.children[3];
  bouton_valider.innerHTML  = "<button  id='bouton-valider-modifications-ecole" + i + "' class='modifier_ecole' onClick='validechangeEcole(" + i + ")'> <img src='boutons/valider.png' alt='Oups'> </button>"
  var bouton_annuler        = papi.children[4];
  bouton_annuler.innerHTML  = "<button  id='bouton-annuler-modifications-ecole" + i + "' class='supprimer_ecole' onClick='annulechangeEcole(" + i + ")'> <img src='boutons/supprimer.png' alt='Oups'> </button>"
}


function validechangeEcole(i){
  bouton                = document.getElementById("bouton-valider-modifications-ecole"+i);
  var papa              = bouton.parentNode;
  var papi              = papa.parentNode;
  var input_id          = listeEcoles[i].id;
  console.log(listeEcoles);
  var input_nom         = document.getElementById("modif_nom_ecole" + i + "").value;
  console.log(input_nom);
  var input_site        = document.getElementById("modif_site_ecole" + i + "").value;
  console.log(input_site);
  var input_description = document.getElementById("modif_description_ecole" + i + "").value;
  console.log(input_description);

  if(input_nom == ""){
    // input_nom= listeEcoles[input_id].nom;
    input_nom= listeEcoles[i].nom;
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
  ajax.open('GET', 'serveur.php/?request=changeEcole&&id=' + input_id +'&&nom=' + input_nom + '&&site=' + input_site + '&&description=' + input_description );
	ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	ajax.addEventListener('load',  function () {
		var response = ajax.response;
		 console.log(response);
		 getListeEcole();

   });

  ajax.send('request=changeEcole&&id=' + input_id + '&&nom=' + input_nom + '&&site=' + input_site + '&&description=' + input_description);

  // on change de nouveau la ligne (normalement avec le changement)
  //boucle qui retransforme la ligne
  var nom                 = papi.children[0];
  nom.innerHTML           = listeEcoles[i].nom;
  var site                = papi.children[1];
  site.innerHTML          = listeEcoles[i].site;
  var description         = papi.children[2];
  description.innerHTML   = listeEcoles[i].description;

  //on remet en place les deux anciens boutons
  var bouton_valider        = papi.children[3];
  bouton_valider.innerHTML  = "<button  id='bouton-modifier-ecole" + i + "' class='modifier_ecole'  onClick='changeEcole(" + i + ")'> <img src='boutons/modifier.png' alt='Oups'> </button>"
  var bouton_annuler        = papi.children[4];
  bouton_annuler.innerHTML  = "<button  id='supprimer-ecole' class='supprimer_ecole' > <img src='boutons/supprimer.png' alt='Oups'> </button>"
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
  var site=papi.children[1];
  site.innerHTML=listeEcoles[i].site;
  var description=papi.children[2];
  description.innerHTML=listeEcoles[i].description;

  //on met en place les deux nouveaux boutons
  var bouton_valider=papi.children[3];
  bouton_valider.innerHTML="<button  id='bouton-modifier-ecole" + i + "' class='modifier_ecole'  onClick='changeEcole("+i+")'> <img src='boutons/modifier.png' alt='Oups'> </button>"
  var bouton_annuler=papi.children[4];
  bouton_annuler.innerHTML="<button  id='supprimer-ecole' class='supprimer_ecole' > <img src='boutons/supprimer.png' alt='Oups'> </button>"
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
  bouton_annuler.innerHTML  = "<button  id='supprimer-batiment' class='supprimer_batiment' > <img src='boutons/supprimer.png' alt='Oups'> </button>"
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
  bouton_annuler.innerHTML  = "<button  id='supprimer-filiere' class='supprimer_filiere' > <img src='boutons/supprimer.png' alt='Oups'> </button>"
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
  bouton_annuler.innerHTML  = "<button  id='supprimer-filiere' class='supprimer_filiere'> <img src='boutons/supprimer.png' alt='Oups'> </button>"
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
  id_ecole.innerHTML    = "<input id='modif_id_ecole_formation" + i + "' placeholder='"+listeFormations[i].id_ecole+"'>";
  var id_batiment       = papi.children[3];
  id_batiment.innerHTML = "<input id='modif_id_batiment_formation" + i + "' placeholder='"+listeFormations[i].id_batiment+"'>";
  var id_filiere        = papi.children[4];
  id_filiere.innerHTML  = "<input id='modif_id_filiere_formation" + i +"' placeholder='"+listeFormations[i].id_filiere+"'>";

  //on met en place les deux nouveaux boutons
  var bouton_valider         = papi.children[5];
  bouton_valider.innerHTML   = "<button  id='bouton-valider-modifications-formation" + i + "' class='modifier_formation'  onClick='validechangeFormation("+i+")'> <img src='boutons/valider.png' alt='Oups'> </button>"
  var bouton_annuler         = papi.children[6];
  bouton_annuler.innerHTML   = "<button  id='bouton-annuler-modifications-formation" + i + "' class='supprimer_formation onClick='annulechangeFormation("+i+")'> <img src='boutons/supprimer.png' alt='Oups'> </button>"
 }


function validechangeFormation(i){

  bouton    = document.getElementById("bouton-valider-modifications-formation" + i);
  var papa  = bouton.parentNode;
  var papi  = papa.parentNode;

  var input_id     = listeFormations[i].id;

  var input_nom         = document.getElementById("modif_nom_formation" + i + "").value;
  var input_niveau      = document.getElementById("modif_niveau_formation" + i + "").value;
  var input_id_ecole    = document.getElementById("modif_id_ecole_formation" + i + "").value;
  var input_id_batiment = document.getElementById("modif_id_batiment_formation" + i + "").value;
  var input_id_filiere  = document.getElementById("modif_id_filiere_formation" + i + "").value;

  if(input_nom == ""){
    // input_nom= listeEcoles[input_id].nom;
    input_nom = listeFormations[i].nom;
  }


  if(input_niveau == ""){
    // input_site=listeEcoles[input_id].site;
    input_niveau = listeFormations[i].niveau;
  }


  if(input_id_ecole == ""){
    // input_description=listeEcoles[input_id].description;
    input_id_ecole = listeFormations[i].input_id_ecole;
  }

  if(input_id_batiment == ""){
    // input_description=listeEcoles[input_id].description;
    input_id_batiment = listeFormations[i].input_id_batiment;
  }

  if(input_id_filiere == ""){
    // input_description=listeEcoles[input_id].description;
    input_id_filiere = listeFormations[i].input_id_filiere;
  }

  var ajax = new XMLHttpRequest();

  ajax.open('GET', 'serveur.php/?request=changeFormation&&id=' + input_id + '&&nom=' + input_nom + '&&niveau=' + input_niveau + '&&id_ecole=' + input_id_ecole + '&&id_batiment=' + input_id_batiment + '&&id_filiere=' + input_id_filiere );
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.addEventListener('load',  function () {
    var response = ajax.response;
    getListeFormation();
  });

  ajax.send('request=changeEcole&&id=' + input_id + '&&nom=' + input_nom + '&&niveau=' + input_niveau + '&&id_ecole=' + input_id_ecole + '&&id_batiment=' + input_id_batiment + '&&id_filiere=' + input_id_filiere );

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
  bouton_annuler.innerHTML  = "<button  id='supprimer-formation' class='supprimer_formation' > <img src='boutons/supprimer.png' alt='Oups'> </button>"
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
  bouton_annuler.innerHTML  = "<button  id='supprimer-formation' class='supprimer_formation'> <img src='boutons/supprimer.png' alt='Oups'> </button>"
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
  });

  ajax.send('request=deleteEcole&&id=' + input_id );
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
