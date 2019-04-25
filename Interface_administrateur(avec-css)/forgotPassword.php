<?php

$link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
$mailAppli = "explor.descartes@gmail.com";

function newMail($mail){
  //Cette fonction renvoie true si le mail n'est pas déjà présent dans la base de données et false dans le cas contraire
  global $link;
  $requete = "SELECT id FROM utilisateurs WHERE email LIKE '" . $mail . "'";
  $result = pg_query($link, $requete);
  if ($result) {
    while ($row = pg_fetch_row($result)) {
      return false;
    }
    return true;
  }
}

function generateNewPassword(){
  $pwd = "";
  for ($i = 0; $i < 8; $i++){
    $n = rand(33, 125);
    while ($n == 34 || $n == 39 || $n == 44 || $n == 47 || $n == 92 || $n == 94 || $n == 96){
        $n = rand(33, 125);
    }
    $pwd .= chr($n);
  }
  return $pwd;
}

function sendMailForgotPassword($mail, $newPwd) {

  global $mailAppli;
  $subject = "Nouveau mot de passe";
  $boundary = "-----=".md5(rand());

  $message_txt = "En réponse à votre demande de réinitialisation de mot de passe, vous trouverez ci-dessous votre nouveau mot de passe : " . $newPwd;
  $message_html = "<html><head></head><body><p>En réponse à votre demande de réinitialisation de mot de passe, vous trouverez ci-dessous votre nouveau mot de passe :</p><p align='center'><b>" . $newPwd ."</b></p></body></html>";

  // I - Création de l'en-tête :

  // Adaptation de la norme en fonction du serveur
  if (!preg_match("#^[a-z0-9._-]+@(hotmail|live|msn).[a-z]{2,4}$#", $mail))
  {
  	$passage_ligne = "\r\n";
  }
  else
  {
  	$passage_ligne = "\n";
  }

  // Remplissage de l'en-tête
  $header = "From: \"Explor Descartes\"<" . $mailAppli .">".$passage_ligne;
  $header .= "Reply-to: \"Explor Descartes\" <" . $mailAppli .">".$passage_ligne;
  $header .= "MIME-Version: 1.0".$passage_ligne;
  $header .= "Content-Type: multipart/alternative;".$passage_ligne." boundary=\"$boundary\"".$passage_ligne;

  // II - Création du message

  $message = $passage_ligne."--".$boundary.$passage_ligne;
  // Ajout du message au format texte.
  $message.= "Content-Type: text/plain; charset=\"utf-8\"".$passage_ligne;
  $message.= "Content-Transfer-Encoding: 8bit".$passage_ligne;
  $message.= $passage_ligne.$message_txt.$passage_ligne;

  $message.= $passage_ligne."--".$boundary.$passage_ligne;
  // Ajout du message au format HTML
  $message.= "Content-Type: text/html; charset=\"utf-8\"".$passage_ligne;
  $message.= "Content-Transfer-Encoding: 8bit".$passage_ligne;
  $message.= $passage_ligne.$message_html.$passage_ligne;

  $message.= $passage_ligne."--".$boundary."--".$passage_ligne;
  $message.= $passage_ligne."--".$boundary."--".$passage_ligne;

  // III - Envoi du mail
  mail($mail, $subject, $message, $header);
}

function changePwd ($mail, $newPwd){
  //Cette fonction modifie le mot de passe d'un utilisateur à partir de son email
  global $link;
  $encodedPwd = hash("sha256", $newPwd);
  $requete = "UPDATE utilisateurs SET mdp = '" . $encodedPwd . "' WHERE email LIKE '" . $mail . "'";
  $result = pg_query($link, $requete);
  if ($result){
    return "Modification réussie !";
  }else{
    return "La modification a échouée";
  }
}

$mail = $_GET['mail'];
//$mail = "hugo.de-paulis@ensg.eu";
$newPwd = generateNewPassword();
changePwd ($mail, $newPwd);
sendMailForgotPassword($mail, $newPwd);

 ?>
