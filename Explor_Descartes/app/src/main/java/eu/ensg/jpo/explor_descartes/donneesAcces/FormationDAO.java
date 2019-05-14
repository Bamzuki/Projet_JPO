package eu.ensg.jpo.explor_descartes.donneesAcces;

import android.view.View;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import eu.ensg.jpo.explor_descartes.EcoleActivity;
import eu.ensg.jpo.explor_descartes.ExpandableListAdapterFormation;
import eu.ensg.jpo.explor_descartes.ListeObjets;
import eu.ensg.jpo.explor_descartes.R;
import eu.ensg.jpo.explor_descartes.donnesObjet.Formation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class FormationDAO extends BddEcolesDAO<Formation> {

    /*
    Classe permettant d'implementer une formation en java à partir des données de la base de donnée
     */

    //Constructeur
    public FormationDAO(String urlServeur) {
        super(urlServeur);
    }

    //Fonctions permettant de mettre à jour des informations sur unr école dans la base données
    @Override
    public void create(Formation Formation) {
        return;
    } //création

    @Override
    public void delete(Formation Formation) {
        return;
    } //suppression

    @Override
    public void update(Formation Formation) {
        return;
    } //mise à jour

    public void chargerFormation() {

        /*
        Fonction permettant de charger une formation depuis la base de données
         */

        // Construction de la requete
        String url = this.urlServeur + "?request=listeFormations";
        Request request = new Request.Builder().url(url).build();

        // Envoi de la requete
        Call call = client.newCall(request);
        ArrayList<Formation> listeFormation = null;
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException { //Obtention de la réponse du serveur si la requête est effectué
                System.out.println("FormationDAO chargerFormation : Connexion etablie avec succes !");
                Type listType = new TypeToken<ArrayList<Formation>>(){}.getType();
                ArrayList<Formation> listeFormation = new Gson().fromJson(response.body().string(), listType);
                ListeObjets.listeFormation = listeFormation;
            }

            public void onFailure(Call call, IOException e) { //Cas où la requête échoue
                System.out.println("FormationDAO chargerFormation : Echec de la connection !");
            }
        });

    }

    public void afficherFormation(final EcoleActivity activity){

        /*
        Foction permettant d'afficher la liste des formations proposé dans une école
        acticity : page de l'école dans laquelle on souhaite afficher la formation
         */

        // Construction de la requete
        String url = this.urlServeur + "?request=listeFormations";
        String donnees = "&&filtreEcole=" + activity.getEcole().getNom();
        url = url + donnees;
        final Request request = new Request.Builder().url(url).build();

        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException { //Obtention de la réponse du serveur si la requête est effectué
                System.out.println("FormationDAO afficherFormation : Connexion etablie avec succes !");
                Type listType = new TypeToken<ArrayList<Formation>>(){}.getType();
                ArrayList<Formation> listeFormation = new Gson().fromJson(response.body().string(), listType);

                final ExpandableListView listView = (ExpandableListView)activity.findViewById(R.id.formations);

                final List<String> listDataHeader = new ArrayList<String>();
                final HashMap<String, List<String>> listHashMap = new HashMap<>();

                listDataHeader.add("Formations");

                ArrayList<String> formations = new ArrayList<>();

                for (Formation formation : listeFormation) {
                    formations.add(formation.getNom());
                }

                 listHashMap.put(listDataHeader.get(0), formations);

                final ExpandableListAdapterFormation listAdapter = new ExpandableListAdapterFormation(activity, listDataHeader, listHashMap);

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        listView.setAdapter(listAdapter);

                        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                            @Override
                            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                                activity.setListViewHeightFormation(parent, groupPosition);
                                return false;
                            }
                        });
                    }
                });
            }

            public void onFailure(Call call, IOException e) { //Cas où la requête échoue
                System.out.println("FormationDAO afficherFormation: Echec de la connexion !");
            }
        });
    }
}