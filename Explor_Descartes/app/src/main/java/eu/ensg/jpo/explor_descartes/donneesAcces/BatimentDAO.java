package eu.ensg.jpo.explor_descartes.donneesAcces;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eu.ensg.jpo.explor_descartes.donnesObjet.Batiment;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class BatimentDAO extends BddEcolesDAO<Batiment> {

    public BatimentDAO(String urlServeur) {
        super(urlServeur);
    }

    @Override
    public Batiment create(Batiment batiment) {
        return null;
    }

    @Override
    public boolean delete(Batiment batiment) {
        return false;
    }

    @Override
    public boolean update(Batiment batiment) {
        return false;
    }


    public Batiment findById(int id) {
        String url = this.urlServeur + "?request=batiment&&id="+id;
        Request request = new Request.Builder().url(url).build();
        // Envoi de la requete
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            System.out.println("Connexion etablie avec succes !");
            System.out.println(response.toString());
            Batiment Batiment = new Gson().fromJson(response.toString(), Batiment.class);
            return Batiment;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Echec de la connexion.");
            return null;
        }

    }

    /**

    public ArrayList<Batiment> getListeBatiment() {
        // Construction de la requete
        String url = this.urlServeur + "?request=listeBatiments";
        Request request = new Request.Builder().url(url).build();
        // Envoi de la requete
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            System.out.println("Connexion etablie avec succes !");
            System.out.println(response.toString());
            ArrayList<Batiment> listeBatiment = new Gson().fromJson(response.toString(), ArrayList<Batiment>.class);
            return listeBatiment;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Echec de la connexion.");
            return null;
        }
    }

     */

}
