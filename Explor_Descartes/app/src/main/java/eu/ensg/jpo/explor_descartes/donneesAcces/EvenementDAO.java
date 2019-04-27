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
import eu.ensg.jpo.explor_descartes.ExpandableListAdapterEvenement;
import eu.ensg.jpo.explor_descartes.ListeObjets;
import eu.ensg.jpo.explor_descartes.R;
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

    public void afficherEvenement(final EcoleActivity activity) {
        // Construction de la requete
        String url = this.urlServeur + "?request=listeEvenements";
        String donnees = "&&filtreEcole=" + activity.getEcole().getNom();
        url = url + donnees;
        final Request request = new Request.Builder().url(url).build();
        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("afficherEvenement : Connexion etablie avec succes !");
                Type listType = new TypeToken<ArrayList<Evenement>>() {}.getType();
                ArrayList<Evenement> listeEvenement = new Gson().fromJson(response.body().string(), listType);

                final ExpandableListView listView = (ExpandableListView) activity.findViewById(R.id.evenements);

                final List<String> listDataHeader = new ArrayList<String>();
                final HashMap<String, List<String>> listHashMap = new HashMap<>();

                listDataHeader.add("Evénements");

                ArrayList<String> evenements = new ArrayList<>();

                for (Evenement evenement : listeEvenement) {
                    evenements.add(evenement.getNom());
                }

                listHashMap.put(listDataHeader.get(0), evenements);

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
                System.out.println("Echec de la connexion !");
            }
        });
    }
}