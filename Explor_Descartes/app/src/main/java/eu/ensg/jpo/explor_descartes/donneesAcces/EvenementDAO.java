package eu.ensg.jpo.explor_descartes.donneesAcces;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import eu.ensg.jpo.explor_descartes.ListeObjets;
import eu.ensg.jpo.explor_descartes.donnesObjet.Evenement;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class EvenementDAO extends BddEcolesDAO<Evenement> {

    public EvenementDAO(String urlServeur) {
        super(urlServeur);
    }

    @Override
    public void create(Evenement Evenement) {
        return;
    }

    @Override
    public void delete(Evenement Evenement) {
        return;
    }

    @Override
    public void update(Evenement Evenement) {
        return;
    }

    public void chargerEvenement() {
        // Construction de la requete
        String url = this.urlServeur + "?request=listeEvenements";
        Request request = new Request.Builder().url(url).build();
        // Envoi de la requete
        Call call = client.newCall(request);
        ArrayList<Evenement> listeEvenement = null;
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Connexion etablie avec succes !");
                System.out.println(response.body());
                Type listType = new TypeToken<ArrayList<Evenement>>(){}.getType();
                ArrayList<Evenement> listeEvenement = new Gson().fromJson(response.body().string(), listType);
                ListeObjets.listeEvenement = listeEvenement;
            }

            public void onFailure(Call call, IOException e) {
                System.out.println("Echec de la connection !");
            }
        });

    }
}
