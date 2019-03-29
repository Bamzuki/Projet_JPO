function getListeNomNiveaux(idSelectNiveau){
  // Mise à jour de la liste des noms des niveaux depuis le serveur
  var ajax = new XMLHttpRequest();
  ajax.open('GET', 'serveur.php/?request=listeNomObjets&&nomTable=niveaux');
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     var listeNomNiveaux = JSON.parse(ajax.response);
     var select_niveau = document.getElementById(idSelectNiveau);
     for (var i = 0; i < listeNomNiveaux.length; i++){
       var newOption = document.createElement("option");
       newOption.value = listeNomNiveaux[i];
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
     for (var i = 0; i < listeNomEcoles.length; i++){
       var newOption = document.createElement("option");
       newOption.value = listeNomEcoles[i];
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
     for (var i = 0; i < listeNomBatiments.length; i++){
       var newOption = document.createElement("option");
       newOption.value = listeNomBatiments[i];
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
     for (var i = 0; i < listeNomFilieres.length; i++){
       var newOption = document.createElement("option");
       newOption.value = listeNomFilieres[i];
       select_filiere.appendChild(newOption);
     }
   });
  ajax.send('request=listeNomObjets&&nomTable=filieres');
}

function getListeFormation(){
  // Mise à jour de la liste des formations depuis le serveur en fonction des filtres choisis
  var filtre_niveau    = document.getElementById("filtre-niveau").value;
  var filtre_ecole     = document.getElementById("filtre-ecole").value;
  var filtre_batiment  = document.getElementById("filtre-batiment").value;
  var filtre_filiere   = document.getElementById("filtre-filiere").value;
  var request = "request=listeFormations"
  if (filtre_niveau != "Tous"){
    request += "&&filtreNiveau=" + filtre_niveau;
  }
  if (filtre_ecole != "Toutes"){
    request += "&&filtreEcole=" + filtre_ecole;
  }
  if (filtre_batiment != "Tous"){
    request += "&&filtreBatiment=" + filtre_batiment;
  }
  if (filtre_niveau != "Toutes"){
    request += "&&filtreFiliere=" + filtre_filiere;
  }
  var ajax = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?'+request);
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     var listeFormations = JSON.parse(ajax.response);
     }
   });
  ajax.send(request);
}

function saveEcole(){
  // Enregistrement d'une école dans la base de données

  var input_nom         = document.getElementById("input-nom").value;
  var input_site        = document.getElementById("input-site").value;
  var input_description = document.getElementById("input-description").value;
  var ajax = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?request=saveEcole&&nom=' + input_nom + '&&site=' + input_site + '&&description=' + input_description);
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     var response = JSON.parse(ajax.response);
     console.log(response);
     }
   });
  ajax.send('request=saveEcole&&nom=' + input_nom + '&&site=' + input_site + '&&description=' + input_description);
}

function saveBatiment(){
  // Enregistrement d'un batiment dans la base de données

  var input_nom      = document.getElementById("input-nom").value;
  var input_fonction = document.getElementById("input-fonction").value;
  var input_lat      = document.getElementById("input-lat").value;
  var input_lng      = document.getElementById("input-lng").value;
  var ajax = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?request=saveBatiment&&nom=' + input_nom + '&&fonction=' + input_fonction + '&&lat=' + input_lat + '&&lng=' + input_lng);
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     var response = JSON.parse(ajax.response);
     console.log(response);
     }
   });
  ajax.send('request=saveBatiment&&nom=' + input_nom + '&&fonction=' + input_fonction + '&&lat=' + input_lat + '&&lng=' + input_lng);
}

function saveFiliere(){
  // Enregistrement d'une filière dans la base de données

  var input_nom = document.getElementById("input-nom").value;
  var ajax = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?request=saveFiliere&&nom=' + input_nom);
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     var response = JSON.parse(ajax.response);
     console.log(response);
     }
   });
  ajax.send('request=saveFiliere&&nom=' + input_nom);
}

function saveFormations(){
  // Enregistrement d'une formations dans la base de données

  var input_nom      = document.getElementById("input-nom").value;
  var input_niveau   = document.getElementById("input-niveau").value;
  var input_ecole    = document.getElementById("input-ecole").value;
  var input_batiment = document.getElementById("input-batiment").value;
  var input_filiere  = document.getElementById("input-filiere").value;
  var ajax = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?request=saveFormation&&nom=' + input_nom + '&&niveau=' + input_niveau + '&&ecole=' + input_ecole + '&&batiment=' + input_batiment + '&&filiere=' + input_filiere);
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     var response = JSON.parse(ajax.response);
     console.log(response);
     }
   });
  ajax.send('request=saveFormation&&nom=' + input_nom + '&&niveau=' + input_niveau + '&&ecole=' + input_ecole + '&&batiment=' + input_batiment + '&&filiere=' + input_filiere);



// I - Chargement des listes des noms des différentes
getListeNomNiveaux("filtre-niveau");
getListeNomEcoles("filtre-ecole");
getListeNomBatiments("filtre-batiment");
getListeNomFilieres("filtre-filiere");
