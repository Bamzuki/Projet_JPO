package eu.ensg.jpo.explor_descartes.donneesAcces;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import eu.ensg.jpo.explor_descartes.ListeObjets;
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
    public Visiteur create(Visiteur Visiteur) {
        return null;
    }

    @Override
    public boolean update(Visiteur Visiteur) {
        return false;
    }

    @Override
    public boolean delete(Visiteur Visiteur) {
        return false;
    }

    public void chargerVisiteur() {
        // Construction de la requete
        String url = this.urlServeur + "?request=listeVisiteurs";
        Request request = new Request.Builder().url(url).build();
        // Envoi de la requete
        Call call = client.newCall(request);
        ArrayList<Visiteur> listeVisiteur = null;
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Connexion etablie avec succes !");
                System.out.println(response.body());
                Type listType = new TypeToken<ArrayList<Visiteur>>() {
                }.getType();
                ArrayList<Visiteur> listeVisiteur = new Gson().fromJson(response.body().string(), listType);
                ListeObjets.listeVisiteur = listeVisiteur;
            }

            public void onFailure(Call call, IOException e) {
                System.out.println("Echec de la connection !");
            }
        });


    }
}

