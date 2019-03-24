var listeNomNiveaux = [];
var listeNomEcoles = [];
var listeNomBatiments = [];
var listeNomFilieres = [];

function getListeNomNiveaux(){
  // Mise à jour de la liste des noms des niveaux depuis le serveur
  var ajax = new XMLHttpRequest();
  ajax.open('GET', 'serveur.php/?request=listeNomObjets&&nomTable=niveaux');
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     listeNomNiveaux = JSON.parse(ajax.response);
   });
  ajax.send('request=listeNomObjets&&nomTable=niveaux');
}

function getListeNomEcoles(){
  // Mise à jour de la liste des noms des écoles depuis le serveur
  var ajax = new XMLHttpRequest();
  ajax.open('GET', 'serveur.php/?request=listeNomObjets&&nomTable=ecoles');
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     listeNomEcoles = JSON.parse(ajax.response);
   });
  ajax.send('request=listeNomObjets&&nomTable=ecoles');
}

function getListeNomBatiments(){
  // Mise à jour de la liste des noms des filieres depuis le serveur
  var ajax = new XMLHttpRequest();
  ajax.open('GET', 'serveur.php/?request=listeNomObjets&&nomTable=batiments');
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     listeNomBatiments = JSON.parse(ajax.response);
   });
  ajax.send('request=listeNomObjets&&nomTable=batiments');
}

function getListeNomFilieres(){
  // Mise à jour de la liste des noms des filieres depuis le serveur
  var ajax = new XMLHttpRequest()
  ajax.open('GET', 'serveur.php/?request=listeNomObjets&&nomTable=filieres');
   ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
   ajax.addEventListener('load',  function () {
     listeNomFilieres = JSON.parse(ajax.response);
   });
  ajax.send('request=listeNomObjets&&nomTable=filieres');
}


// I - Chargement des listes des noms des différentes
getListeNomNiveaux();
getListeNomEcoles();
getListeNomBatiments();
getListeNomFilieres();
