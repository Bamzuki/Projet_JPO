//Fonction permettant de générer un nouveau mot de passe
function resetPassword(){
  var mail = document.getElementById("mail").value;
  var error = document.getElementById("error");
  var regex = /^[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*@[a-z0-9]+([_|\.|-]­{1}[a-z0-9]+)*[\.]{1}[a-z]{2,6}$/;

  // Adresse mail invalide
  if (regex.exec(mail) == null) {
    error.innerHTML = "Cette adresse mail n'est pas valide.";
  }
  else{
    var ajax = new XMLHttpRequest();
    ajax.open('GET', 'serveur.php/?request=newMail&&mail=' + mail);
    ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    ajax.addEventListener('load',  function () {
      var response = ajax.response;
      // Adresse mail inconnue
      if (response == true){
        error.innerHTML = "Cette adresse mail n'est pas connue.";
      }else{
        sendNewPwd(mail);
      }
    });
    ajax.send('request=newMail&&mail=' + mail);
  }
}
//Fonction permettant de recevoir un nouveau mot de passe par mail
function sendNewPwd(mail){
  var ajax = new XMLHttpRequest();
  ajax.open('GET', 'forgotPassword.php/?mail=' + mail);
  ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  ajax.addEventListener('load',  function () {
    window.location.href="sentMail.html";
  });
  ajax.send('mail=' + mail);
}

var resetButton = document.getElementById("resetButton");
resetButton.addEventListener("click", resetPassword);
