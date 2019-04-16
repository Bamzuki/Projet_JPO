package eu.ensg.jpo.explor_descartes.donneesAcces;

import java.io.IOException;

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
    public void create(Visiteur visiteur) {
        // Construction de la requete
        String url = this.urlServeur + "?request=saveUtilisateur";
        String donnees = "&&prenom=" + visiteur.getPrenom() + "&&nom=" + visiteur.getNom() + "&&pseudo=" + visiteur.getPseudo() + "&&email=" + visiteur.getEmail() + "&&mdp=" + visiteur.getMdp() + "&&admin=" + visiteur.getAdmin();
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
    public void delete(Visiteur visiteur) {
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

    public void getVisiteurByPseudoAndMdp(String pseudo, String mdp) {
        return;
    }

    public void getVisiteurByEmailAndMdp(String pseudo, String mdp) {
        return;
    }
}

