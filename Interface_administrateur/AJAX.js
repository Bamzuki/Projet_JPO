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
}


// I - Chargement des listes des noms des différentes
getListeNomNiveaux("filtre-niveau");
getListeNomEcoles("filtre-ecole");
getListeNomBatiments("filtre-batiment");
getListeNomFilieres("filtre-filiere");
