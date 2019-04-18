package eu.ensg.jpo.explor_descartes.donneesAcces;

import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import eu.ensg.jpo.explor_descartes.EcoleActivity;
import eu.ensg.jpo.explor_descartes.ExpandableListAdapter;
import eu.ensg.jpo.explor_descartes.ListeObjets;
import eu.ensg.jpo.explor_descartes.R;
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

    public void afficherFormation(final EcoleActivity activity){
        // Construction de la requete
        String url = this.urlServeur + "?request=listeFormations";
        String donnees = "&&filtreEcole=" + activity.getEcole().getNom();
        url = url + donnees;
        final Request request = new Request.Builder().url(url).build();
        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("afficherFormation : Connexion etablie avec succes !");
                Type listType = new TypeToken<ArrayList<Formation>>(){}.getType();
                ArrayList<Formation> listeFormation = new Gson().fromJson(response.body().string(), listType);

                final ExpandableListView listView = (ExpandableListView)activity.findViewById(R.id.liste_extensions);

                List<String> listDataHeader = new ArrayList<>();
                HashMap<String, List<String>> listHashMap = new HashMap<>();
                ArrayList<String> formations = new ArrayList<>();

                listDataHeader.add("Formations proposées");

                for (Formation formation : listeFormation) {
                    formations.add(formation.getNom());
                }

                listHashMap.put(listDataHeader.get(0), formations);

                final ExpandableListAdapter listAdapter = new ExpandableListAdapter(activity, listDataHeader, listHashMap);
                System.out.print("listAdapter: " + listAdapter);

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(listAdapter);                    }
                });

            }

            public void onFailure(Call call, IOException e) {
                System.out.println("Echec de la connexion !");
            }
        });
    }
}