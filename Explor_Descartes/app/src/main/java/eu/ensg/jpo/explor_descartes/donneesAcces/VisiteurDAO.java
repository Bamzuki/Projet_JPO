package eu.ensg.jpo.explor_descartes.donneesAcces;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import eu.ensg.jpo.explor_descartes.ListeObjets;
import eu.ensg.jpo.explor_descartes.MainActivity;
import eu.ensg.jpo.explor_descartes.ModifierPerso;
import eu.ensg.jpo.explor_descartes.RegisterActivity;
import eu.ensg.jpo.explor_descartes.SignInActivity;
import eu.ensg.jpo.explor_descartes.donnesObjet.Evenement;
import eu.ensg.jpo.explor_descartes.donnesObjet.Visiteur;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class VisiteurDAO extends BddEcolesDAO<Visiteur> {

    /*
    Classe permettant d'implementer un utilisateur enregistré en java à partir des données de la base de donnée
     */

    //Constructeur
    public VisiteurDAO(String urlServeur) {
        super(urlServeur);
    }

    //Fonctions permettant de mettre à jour des informations sur unr école dans la base données
    @Override
    public void create(Visiteur newVisiteur) {

    } //création

    @Override
    public void update(Visiteur visiteur) {

        /*
        Fonction permettant de mettre à jour les informations associé à un compte dans la base de données
        visiteur : compte pour lequel on souhaite réaliser les modifications
         */

        // Construction de la requete
        String url = this.urlServeur + "?request=changeUtilisateur";
        String donnees = "&&id=" + visiteur.getId() + "&&prenom=" + visiteur.getPrenom() + "&&nom=" + visiteur.getNom() + "&&pseudo=" + visiteur.getPseudo() + "&&email=" + visiteur.getEmail() + "&&mdp=" + visiteur.getMdp() + "&&admin=" + visiteur.getAdmin();
        url = url + donnees;
        System.out.println(url);
        Request request = new Request.Builder().url(url).build();

        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException { //Obtention de la réponse du serveur si la requête est effectué
                System.out.println("VisiteurDAO update: Connexion etablie avec succes !");
            }
            // Pseudo et email corrects
            public void onFailure(Call call, IOException e) { //Cas où la requete échoue
                System.out.println("VisiteurDAO update: Echec de la connection !");
            }
        });

    } //mise à jour

    @Override
    public void delete(Visiteur visiteur) {

        /*
        Fonction permettant de supprimier les données d'un compte dans la base de données
        visiteur: compte que l'on souhaite supprimer
         */

        // Construction de la requete
        String url = this.urlServeur + "?request=deleteUtilisateur";
        String donnees = "&&id=" + visiteur.getId();
        url = url + donnees;
        Request request = new Request.Builder().url(url).build();

        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException { //Obtention de la réponse du serveur si la requête est effectué
                System.out.println("VisiteurDAO delete : Connexion etablie avec succes !");
            }
            // Pseudo et email corrects
            public void onFailure(Call call, IOException e) { //Cas où la requete échoue
                System.out.println("VisiteurDAO delete: Echec de la connection !");
            }
        });

    } //suppresion

    public void updatePerso(final ModifierPerso activity, Visiteur visiteur) {

        /*
        Fonction permettant de mettre à jour les informations associé à un compte
        activity: page de modification des données personnelles
        visiteur : compte pour lequel on souhaite réaliser les modifications
         */

        // Construction de la requete
        String url = this.urlServeur + "?request=changeUtilisateur";
        String donnees = "&&id="+visiteur.getId()+"&&prenom=" + visiteur.getPrenom() + "&&nom=" + visiteur.getNom() + "&&pseudo=" + visiteur.getPseudo() + "&&email=" + visiteur.getEmail() + "&&mdp=" + visiteur.getMdp() + "&&admin=" + visiteur.getAdmin();
        url = url + donnees;
        System.out.println(url);
        Request request = new Request.Builder().url(url).build();

        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException { //Obtention de la réponse du serveur si la requête est effectué
                System.out.println("VisiteurDAO updatePerso : Connexion etablie avec succes !");
                String reponseBdd = response.body().string();
                // Pseudo déjà utilisé
                if (reponseBdd.equals("Pseudo déjà utilisé !")){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity, "Pseudo déjà utilisé !" , Toast.LENGTH_LONG).show();
                            activity.getPseudoET().getText().clear();
                        }
                    });
                }
                // Email déjà utilisé
                else if (reponseBdd.equals("Email déjà utilisé !")){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity, "Email déjà utilisé !" , Toast.LENGTH_LONG).show();
                            activity.getMailET().getText().clear();
                        }
                    });
                }

            }

            public void onFailure(Call call, IOException e) { //Cas où la requete échoue
                System.out.println("VisiteurDAO updatePerso : Echec de la connection !");
            }
        });
        return;
    }

    public void connexionAuto(final MainActivity activity, String mailOrPseudo, String mdp) {

        /*
        Fonction permettant de se reconnecter automatiquement à chaque réouverture de l'application
        activity : page principale
        mailOrPseudo : adresse e-mail ou pseudo utilisé pour la connection
        mdp : mot de passe associé au compte aulequel on souhaite se connecter
         */

        // Construction de la requete
        String url = this.urlServeur + "?request=connexion";
        String donnees = "&&mail=" + mailOrPseudo + "&&mdp=" + mdp;
        url = url + donnees;
        Request request = new Request.Builder().url(url).build();

        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException { //Obtention de la réponse du serveur si la requête est effectué
                System.out.println("VisiteurDAO connexionAuto: Connexion etablie avec succes !");
                final Visiteur visiteur = new Gson().fromJson(response.body().string(), Visiteur.class);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //Identifiants incorrects
                        if (visiteur == null){
                            Toast.makeText(activity, "Votre compte semble avoir été modifié ! Reconnectez-vous avec vos nouveaux identifiants." , Toast.LENGTH_LONG).show();
                        }

                        //Identifiants corrects
                        else{
                            // Instanciation du visiteur
                            ListeObjets.visiteur = visiteur;
                            Toast.makeText(activity, "Bonjour " + visiteur.getPseudo() + " !", Toast.LENGTH_LONG).show();
                            activity.getMenu().openAccueilActivity();

                        }
                    }
                });
            }

            public void onFailure(Call call, IOException e) { //Cas où la requete échoue
                System.out.println("VisiteurDAO connexionAuto: Echec de la connection !");
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "Problème de connexion au serveur..." , Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    public void connexionBdd(final SignInActivity activity, String mailOrPseudo, String mdp) {

        /*
        Fonction permettant de se connecter à l'aide de la méthode classique
        activity : page de connextion
        mailOrPseudo : adresse e-mail ou pseudo utilisé pour la connection
        mdp : mot de passe associé au compte aulequel on souhaite se connecter
         */

        // Construction de la requete
        String url = this.urlServeur + "?request=connexion";
        String donnees = "&&mail=" + mailOrPseudo + "&&mdp=" + mdp;
        url = url + donnees;
        Request request = new Request.Builder().url(url).build();

        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException { //Obtention de la réponse du serveur si la requête est effectué
                System.out.println("VisiteurDAO connexionBdd : Connexion etablie avec succes !");
                final Visiteur visiteur = new Gson().fromJson(response.body().string(), Visiteur.class);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //Identifiants incorrects
                        if (visiteur == null){
                            Toast.makeText(activity, "Identifiants incorrects" , Toast.LENGTH_LONG).show();
                            activity.getMdpET().getText().clear();
                        }

                        //Identifiants corrects
                        else{
                            // Instanciation du visiteur
                            ListeObjets.visiteur = visiteur;
                            // Enregistrement dans les données de l'application
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.remove("pseudo");
                            editor.remove("mdp");
                            editor.putString("pseudo", visiteur.getPseudo());
                            editor.putString("mdp", visiteur.getMdp());
                            editor.commit();
                            Toast.makeText(activity, "Bonjour " + visiteur.getPseudo() + " !", Toast.LENGTH_LONG).show();
                            activity.openAccueilActivity();



                        }
                    }
                });
            }

            public void onFailure(Call call, IOException e) { //Cas où la requete échoue
                System.out.println("VisiteurDAO connexionBdd : Echec de la connection !");
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "Problème de connexion au serveur..." , Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    public void firstConnexionBdd(final RegisterActivity activity, Visiteur newVisteur) {

        /*
        Fonction permettant de se connecter pour la première fois, une fois que l'inscription est validé
        activity : page d'inscription
        newVisiteur : compte crée lors de l'inscription du visiteur
        */

        // Construction de la requete
        String url = this.urlServeur + "?request=connexion";
        String donnees = "&&mail=" + newVisteur.getEmail() + "&&mdp=" + newVisteur.getMdp();
        url = url + donnees;
        Request request = new Request.Builder().url(url).build();

        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException { //Obtention de la réponse du serveur si la requête est effectué
                System.out.println("VisiteurDAO firstConnexionBdd : Connexion etablie avec succes !");
                final Visiteur visiteur = new Gson().fromJson(response.body().string(), Visiteur.class);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ListeObjets.visiteur = visiteur;
                        // Enregistrement dans les données de l'application
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.remove("pseudo");
                        editor.remove("mdp");
                        editor.putString("pseudo", visiteur.getPseudo());
                        editor.putString("mdp", visiteur.getMdp());
                        editor.commit();
                        Toast.makeText(activity, "Inscription réussie !", Toast.LENGTH_LONG).show();
                        activity.openAccueilActivity();
                    }
                });
            }

            public void onFailure(Call call, IOException e) { //Cas où la requete échoue
                System.out.println("VisiteurDAO firstConnexionBdd : Echec de la connection !");
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "Problème de connexion au serveur..." , Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    public void inscriptionBdd(final RegisterActivity activity, final Visiteur newVisiteur){

        /*
        Fonction permettant de s'inscrire à l'aide de la méthode classique
        activity : page d'inscription
        newVisiteur : nouveau compte crée à inserer dans la base de donnée
         */

        // Construction de la requete
        String url = this.urlServeur + "?request=saveUtilisateur";
        String donnees = "&&prenom=" + newVisiteur.getPrenom() + "&&nom=" + newVisiteur.getNom() + "&&pseudo=" + newVisiteur.getPseudo() + "&&email=" + newVisiteur.getEmail() + "&&mdp=" + newVisiteur.getMdp() + "&&admin=" + newVisiteur.getAdmin();
        url = url + donnees;
        Request request = new Request.Builder().url(url).build();

        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException { //Obtention de la réponse du serveur si la requête est effectué
                System.out.println("VisiteurDAO inscriptionBdd : Connexion etablie avec succes !");
                String reponseBdd = response.body().string();
                // Pseudo déjà utilisé
                if (reponseBdd.equals("Pseudo déjà utilisé !")){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity, "Pseudo déjà utilisé !" , Toast.LENGTH_LONG).show();
                            activity.getPseudoET().getText().clear();
                        }
                    });
                }
                // Email déjà utilisé
                else if (reponseBdd.equals("Email déjà utilisé !")){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity, "Email déjà utilisé !" , Toast.LENGTH_LONG).show();
                            activity.getMailET().getText().clear();
                        }
                    });
                }
                // Pseudo et email corrects
                else{
                    firstConnexionBdd(activity, newVisiteur);
                }

            }

            public void onFailure(Call call, IOException e) { //Cas où la requete échoue
                System.out.println(" VisiteurDAO inscriptionBdd : Echec de la connection !");
            }
        });
    }

    public void ajouterFavori(final Activity activity, Evenement favori){

        /*
        Fonction permettant de mettre un événement en favori
        activity : page depuis laquelle on réalise l'ajout
        favori: événement que l'on souhaite mettre en favori
         */

        ListeObjets.listeFavoris.add(favori);
        ListeObjets.visiteur.getListeFavoris().add(favori.getId());

        // Construction de la requete
        String url = this.urlServeur + "?request=ajouterFavori";
        String donnees = "&&idUtilisateur=" + ListeObjets.visiteur.getId() + "&&idFavoris=" + favori.getId();
        url = url + donnees;
        Request request = new Request.Builder().url(url).build();
        System.out.println(url);

        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException { //Obtention de la réponse du serveur si la requête est effectué
                System.out.println("VisiteurDAO ajouterFavori : Connexion etablie avec succes !");
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "Favori ajouté !" , Toast.LENGTH_SHORT).show();
                    }
                });
            }

            public void onFailure(Call call, IOException e) { //Cas où la requete échoue
                System.out.println("VisiteurDAO ajouterFavori : Echec de la connection !");
            }
        });
    }

    public void supprimerFavori(final Activity activity, Evenement favori){

        /*
        Fonction permettant de retirer un événement en favori
        activity : page depuis laquelle on réalise la suppressio,
        favori: événement que l'on souhaite supprimer des favori
         */


        ListeObjets.listeFavoris.remove(favori);
        ListeObjets.visiteur.getListeFavoris().remove(ListeObjets.visiteur.getListeFavoris().indexOf(favori.getId()));

        // Construction de la requete
        String url = this.urlServeur + "?request=supprimerFavori";
        String donnees = "&&idUtilisateur=" + ListeObjets.visiteur.getId() + "&&idFavoris=" + favori.getId();
        url = url + donnees;
        Request request = new Request.Builder().url(url).build();

        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException { //Obtention de la réponse du serveur si la requête est effectué
                System.out.println("VisiteurDAO supprimerFavori : Connexion etablie avec succes !");
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "Favori supprimé !" , Toast.LENGTH_SHORT).show();
                    }
                });
            }

            public void onFailure(Call call, IOException e) { //Cas où la requete échoue
                System.out.println("VisiteurDAO supprimerFavori : Echec de la connection !");
            }
        });


    }


    public void ajouterFavori(final Activity activity, Visiteur visiteur, int idFavoris){

        /*
        Fonction permettant de mettre un événement en favori
        activity : page depuis laquelle on réalise l'ajout
        visiteur: compte pour lequel on souhaite mettre en favori
        idFavoris : id de l'événement que l'on souhaite mettre en favori
         */

        ListeObjets.visiteur.getListeFavoris().add(idFavoris);

        // Construction de la requete
        String url = this.urlServeur + "?request=ajouterFavori";
        String donnees = "&&idUtilisateur=" + visiteur.getId() + "&&idFavoris=" + idFavoris;
        url = url + donnees;
        Request request = new Request.Builder().url(url).build();

        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException { //Obtention de la réponse du serveur si la requête est effectué
                System.out.println("VisiteurDAO ajouterFavori : Connexion etablie avec succes !");
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "Favori ajouté !" , Toast.LENGTH_SHORT).show();
                    }
                });
            }

            public void onFailure(Call call, IOException e) { //Cas où la requete échoue
                System.out.println("VisiteurDAO ajouterFavori : Echec de la connection !");
            }
        });
    }

    public void supprimerFavori(final Activity activity, Visiteur visiteur, int idFavoris){

        /*
        Fonction permettant de retirer un événement en favori
        activity : page depuis laquelle on réalise la suppression
        visiteur: compte pour lequel on souhaite retirer des favoris
        idFavoris : id de l'événement que l'on souhaite retirer des favoris
         */

        ListeObjets.visiteur.getListeFavoris().remove(ListeObjets.visiteur.getListeFavoris().indexOf(idFavoris));

        // Construction de la requete
        String url = this.urlServeur + "?request=supprimerFavori";
        String donnees = "&&idUtilisateur=" + visiteur.getId() + "&&idFavoris=" + idFavoris;
        url = url + donnees;
        Request request = new Request.Builder().url(url).build();

        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException { //Obtention de la réponse du serveur si la requête est effectué
                System.out.println("VisiteurDAO supprimerFavori : Connexion etablie avec succes !");
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "Favori supprimé !" , Toast.LENGTH_SHORT).show();
                    }
                });
            }

            public void onFailure(Call call, IOException e) { //Cas où la requete échoue
                System.out.println("VisiteurDAO supprimerFavori : Echec de la connection !");
            }
        });


    }
}