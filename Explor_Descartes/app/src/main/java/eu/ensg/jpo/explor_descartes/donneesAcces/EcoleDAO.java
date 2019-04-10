package eu.ensg.jpo.explor_descartes.donneesAcces;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import eu.ensg.jpo.explor_descartes.ListeObjets;
import eu.ensg.jpo.explor_descartes.donnesObjet.Ecole;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class EcoleDAO extends BddEcolesDAO<Ecole>{

    public EcoleDAO(String urlServeur) {
        super(urlServeur);
    }

    @Override
    public Ecole create(Ecole Ecole) {
        return null;
    }

    @Override
    public boolean delete(Ecole Ecole) {
        return false;
    }

    @Override
    public boolean update(Ecole Ecole) {
        return false;
    }

    public void chargerEcole() {
        // Construction de la requete
        String url = this.urlServeur + "?request=listeEcoles";
        Request request = new Request.Builder().url(url).build();
        // Envoi de la requete
        Call call = client.newCall(request);
        ArrayList<Ecole> listeEcole = null;
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Connexion etablie avec succes !");
                System.out.println(response.body());
                Type listType = new TypeToken<ArrayList<Ecole>>(){}.getType();
                ArrayList<Ecole> listeEcole = new Gson().fromJson(response.body().string(), listType);
                ListeObjets.listeEcole = listeEcole;
            }

            public void onFailure(Call call, IOException e) {
                System.out.println("Echec de la connection !");
            }
        });

    }

}