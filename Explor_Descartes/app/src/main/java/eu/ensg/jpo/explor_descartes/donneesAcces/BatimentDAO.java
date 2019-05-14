package eu.ensg.jpo.explor_descartes.donneesAcces;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import eu.ensg.jpo.explor_descartes.ListeObjets;
import eu.ensg.jpo.explor_descartes.donnesObjet.Batiment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class BatimentDAO extends BddEcolesDAO<Batiment>  {

    /**
     * Classe permettant d'implementer un batiment en java à partir des données de la base de donnée
     * @param urlServeur : url du serveur
     */

    //Constructeur
    public BatimentDAO(String urlServeur) {
        super(urlServeur);
    }


    //Fonctions permettant de mettre à jour des informations sur un batiments dans la base données
    @Override
    public void create(Batiment batiment) {
        return;
    }  //création

    @Override
    public void delete(Batiment batiment) {
        return;
    }  //suppression

    @Override
    public void update(Batiment batiment) {
        return;
    }  //mise à jour

    public void chargerBatiment() {

        /**
         * Fonction permettant de charger un batiment depuis la base de données
         */

        //Construction de la requete
        String url = this.urlServeur + "?request=listeBatiments";
        Request request = new Request.Builder().url(url).build();

        //Envoi de la requete
        Call call = client.newCall(request);
        ArrayList<Batiment> listeBatiment = null;
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException { //Obtention de la réponse du serveur si la requête est effectué
                System.out.println("BatimentDAO chargerBatiment: Connexion etablie avec succes !");
                Type listType = new TypeToken<ArrayList<Batiment>>(){}.getType();
                ArrayList<Batiment> listeBatiment = new Gson().fromJson(response.body().string(), listType);
                ListeObjets.listeBatiment = listeBatiment;
            }

            public void onFailure(Call call, IOException e) { //Cas où la requete échoue
                System.out.println("BatimentDAO chargerBatiment: Echec de la connection !");
            }
        });

    }
}