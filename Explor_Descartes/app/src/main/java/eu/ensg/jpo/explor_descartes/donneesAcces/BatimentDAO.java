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

    public BatimentDAO(String urlServeur) {
        super(urlServeur);
    }

    @Override
    public void create(Batiment batiment) {
        return;
    }

    @Override
    public void delete(Batiment batiment) {
        return;
    }

    @Override
    public void update(Batiment batiment) {
        return;
    }


    public Batiment findById(int id) {
        String url = this.urlServeur + "?request=batiment&&id="+id;
        Request request = new Request.Builder().url(url).build();
        // Envoi de la requete
        Call call = client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
            System.out.println("Connexion etablie avec succes !");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Echec de la connexion.");
        }
        System.out.println(response.toString());
        Batiment Batiment = new Gson().fromJson(response.toString(), Batiment.class);
        return Batiment;

    }



    public void chargerBatiment() {
        // Construction de la requete
        String url = this.urlServeur + "?request=listeBatiments";
        Request request = new Request.Builder().url(url).build();
        // Envoi de la requete
        Call call = client.newCall(request);
        ArrayList<Batiment> listeBatiment = null;
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Connexion etablie avec succes !");
                Type listType = new TypeToken<ArrayList<Batiment>>(){}.getType();
                ArrayList<Batiment> listeBatiment = new Gson().fromJson(response.body().string(), listType);
                ListeObjets.listeBatiment = listeBatiment;

            }

            public void onFailure(Call call, IOException e) {
                System.out.println("Echec de la connection !");
            }
        });

    }

    public ArrayList<Batiment> getListeBatiment() {
        // Construction de la requete
        String url = this.urlServeur + "?request=listeBatiments";
        Request request = new Request.Builder().url(url).build();
        // Envoi de la requete
        Call call = client.newCall(request);
        ArrayList<Batiment> listeBatiment = null;
        try (Response response = client.newCall(request).execute()) {
            System.out.println("Connexion etablie avec succes !");
            Type listType = new TypeToken<ArrayList<Batiment>>(){}.getType();
            listeBatiment = new Gson().fromJson(response.body().string(), listType);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Echec de la connection !");
        }
        return listeBatiment;

    }



}