package eu.ensg.jpo.explor_descartes.donneesAcces;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import eu.ensg.jpo.explor_descartes.ListeObjets;
import eu.ensg.jpo.explor_descartes.donnesObjet.Formation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class FormationDAO extends BddEcolesDAO<Formation> {

    public FormationDAO(String urlServeur) {
        super(urlServeur);
    }

    @Override
    public void create(Formation Formation) {
        return;
    }

    @Override
    public void delete(Formation Formation) {
        return;
    }

    @Override
    public void update(Formation Formation) {
        return;
    }

    public void chargerFormation() {
        // Construction de la requete
        String url = this.urlServeur + "?request=listeFormations";
        Request request = new Request.Builder().url(url).build();
        // Envoi de la requete
        Call call = client.newCall(request);
        ArrayList<Formation> listeFormation = null;
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Connexion etablie avec succes !");
                System.out.println(response.body());
                Type listType = new TypeToken<ArrayList<Formation>>(){}.getType();
                ArrayList<Formation> listeFormation = new Gson().fromJson(response.body().string(), listType);
                ListeObjets.listeFormation = listeFormation;
            }

            public void onFailure(Call call, IOException e) {
                System.out.println("Echec de la connection !");
            }
        });

    }
}
