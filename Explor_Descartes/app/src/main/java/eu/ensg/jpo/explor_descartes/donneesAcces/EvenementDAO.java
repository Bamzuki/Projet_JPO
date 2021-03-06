package eu.ensg.jpo.explor_descartes.donneesAcces;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import eu.ensg.jpo.explor_descartes.EcoleActivity;
import eu.ensg.jpo.explor_descartes.ExpandableListAdapterEvenement;
import eu.ensg.jpo.explor_descartes.GridViewPlanning.GridViewAdapterPlanning;
import eu.ensg.jpo.explor_descartes.GridViewPlanning.ImageEvenement;
import eu.ensg.jpo.explor_descartes.ListeObjets;
import eu.ensg.jpo.explor_descartes.PlanningActivity;
import eu.ensg.jpo.explor_descartes.R;
import eu.ensg.jpo.explor_descartes.donnesObjet.Ecole;
import eu.ensg.jpo.explor_descartes.donnesObjet.Evenement;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class EvenementDAO extends BddEcolesDAO<Evenement>{

    /**
     * Classe permettant d'implementer un événement en java à partir des données de la base de donnée
     * @param urlServeur : url du serveur
     */



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

    public void afficherEvenement(final EcoleActivity activity) {

        /**
         * Fonction permettant d'afficher les événements d'une école
         * @param activity : page de l'école pour laquelle on recherche les événements
         */

        // Construction de la requete
        String url = this.urlServeur + "?request=listeEvenements";
        String donnees = "&&filtreEcole=" + activity.getEcole().getNom();
        url = url + donnees;
        final Request request = new Request.Builder().url(url).build();
        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("EvenementDAO afficherEvenement : Connexion etablie avec succes !");
                Type listType = new TypeToken<ArrayList<Evenement>>() {}.getType();
                ArrayList<Evenement> listeEvenement = new Gson().fromJson(response.body().string(), listType);

                final ExpandableListView listView = (ExpandableListView) activity.findViewById(R.id.evenements);

                final List<String> listDataHeader = new ArrayList<String>();
                final HashMap<String, List<Evenement>> listHashMap = new HashMap<>();

                listDataHeader.add("Evénements");

                listHashMap.put(listDataHeader.get(0), listeEvenement);

                final ExpandableListAdapterEvenement listAdapterEvenement = new ExpandableListAdapterEvenement(activity, listDataHeader, listHashMap);

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(listAdapterEvenement);

                        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                            @Override
                            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                                activity.setListViewHeightEvenement(parent, groupPosition);
                                return false;
                            }
                        });
                    }
                });
            }

            public void onFailure(Call call, IOException e) {
                System.out.println("EvenementDAO afficherEvenement : Echec de la connexion !");
            }
        });
    }

    public void chargerPlanning(final PlanningActivity activity) {

        /**
         * Fonction permettant de charger la liste de tout les événements
         * @param activity: page du planning
         */

        // Construction de la requete
        String url = this.urlServeur + "?request=listeEvenements";
        final Request request = new Request.Builder().url(url).build();
        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException { //Obtention de la réponse du serveur si la requête est effectué
                System.out.println("EvenementDAO chargerPlanning : Connexion etablie avec succes !");

                Type listType = new TypeToken<ArrayList<Evenement>>() {}.getType();
                final ArrayList<Evenement> listeEvenement = new Gson().fromJson(response.body().string(), listType);
                Collections.sort(listeEvenement);
                final ArrayList<ImageEvenement> listeImageEvenement = new ArrayList<>();

                int id = (int) activity.getResources().getIdentifier("wait","drawable", activity.getPackageName());
                Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), id);

                for (Evenement evenement : listeEvenement){
                    Ecole ecole = ListeObjets.getEcoleFromNom(evenement.getEcole());
                    if (ecole != null){
                        listeImageEvenement.add(new ImageEvenement(bitmap, evenement, ecole.getImage()));
                    }
                }
                activity.getGridAdapter().getData().addAll(listeImageEvenement);

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        activity.getGridAdapter().notifyDataSetChanged();
                        for (ImageEvenement imageEvenement : listeImageEvenement){
                            imageEvenement.addPicture(activity, activity.getGridAdapter(), activity.getString(R.string.url_serveur));
                        }
                    }
                });

            }

            public void onFailure(Call call, IOException e) { //Cas où la requete échoue
                System.out.println("EvenementDAO chargerPlanning : Echec de la connection !");
            }
        });

    }

}