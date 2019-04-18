package eu.ensg.jpo.explor_descartes.donneesAcces;

import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import eu.ensg.jpo.explor_descartes.ListeObjets;
import eu.ensg.jpo.explor_descartes.RegisterActivity;
import eu.ensg.jpo.explor_descartes.SignInActivity;
import eu.ensg.jpo.explor_descartes.donnesObjet.Visiteur;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class VisiteurDAO extends BddEcolesDAO<Visiteur> {

    public VisiteurDAO(String urlServeur) {
        super(urlServeur);
    }

    @Override
    public void create(Visiteur newVisiteur) {
        return;
    }

    @Override
    public void update(Visiteur visiteur) {
        // Construction de la requete
        String url = this.urlServeur + "?request=changeUtilisateur";
        String donnees = "&&id=" + visiteur.getId() + "&&prenom=" + visiteur.getPrenom() + "&&nom=" + visiteur.getNom() + "&&pseudo=" + visiteur.getPseudo() + "&&email=" + visiteur.getEmail() + "&&mdp=" + visiteur.getMdp() + "&&admin=" + visiteur.getAdmin();
        url = url + donnees;
        Request request = new Request.Builder().url(url).build();
        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Connexion etablie avec succes !");
                //Toast.makeText(getActivity(), response.body().string() , Toast.LENGTH_LONG).show();
            }

            public void onFailure(Call call, IOException e) {
                System.out.println("Echec de la connection !");
            }
        });
        return;
    }

    @Override
    public void delete(final Visiteur visiteur) {
        // Construction de la requete
        String url = this.urlServeur + "?request=deleteUtilisateur";
        String donnees = "&&id=" + visiteur.getId();
        url = url + donnees;
        Request request = new Request.Builder().url(url).build();
        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Connexion etablie avec succes !");
                //Toast.makeText(getActivity(), response.body().string() , Toast.LENGTH_LONG).show();
            }

            public void onFailure(Call call, IOException e) {
                System.out.println("Echec de la connection !");
            }
        });
        return;
    }

    public void connexionBdd(final SignInActivity activity, String mailOrPseudo, String mdp) {

        // Construction de la requete
        String url = this.urlServeur + "?request=connexion";
        String donnees = "&&mail=" + mailOrPseudo + "&&mdp=" + mdp;
        url = url + donnees;
        Request request = new Request.Builder().url(url).build();

        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Connexion etablie avec succes !");
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
                            ListeObjets.visiteur = visiteur;
                            Toast.makeText(activity, "Bonjour " + visiteur.getPseudo() + " !", Toast.LENGTH_LONG).show();
                            activity.openAccueilActivity();
                        }
                    }
                });
            }

            public void onFailure(Call call, IOException e) {
                System.out.println("Echec de la connection !");
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

        // Construction de la requete
        String url = this.urlServeur + "?request=connexion";
        String donnees = "&&mail=" + newVisteur.getEmail() + "&&mdp=" + newVisteur.getMdp();
        url = url + donnees;
        Request request = new Request.Builder().url(url).build();

        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Connexion etablie avec succes !");
                final Visiteur visiteur = new Gson().fromJson(response.body().string(), Visiteur.class);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ListeObjets.visiteur = visiteur;
                        Toast.makeText(activity, "Inscription réussie !", Toast.LENGTH_LONG).show();
                        activity.openAccueilActivity();
                    }
                });
            }

            public void onFailure(Call call, IOException e) {
                System.out.println("Echec de la connection !");
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

        // Construction de la requete
        String url = this.urlServeur + "?request=saveUtilisateur";
        String donnees = "&&prenom=" + newVisiteur.getPrenom() + "&&nom=" + newVisiteur.getNom() + "&&pseudo=" + newVisiteur.getPseudo() + "&&email=" + newVisiteur.getEmail() + "&&mdp=" + newVisiteur.getMdp() + "&&admin=" + newVisiteur.getAdmin();
        url = url + donnees;
        Request request = new Request.Builder().url(url).build();

        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Connexion etablie avec succes !");
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

            public void onFailure(Call call, IOException e) {
                System.out.println("Echec de la connection !");
            }
        });
    }

}
