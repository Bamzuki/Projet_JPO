package eu.ensg.jpo.explor_descartes.Menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import eu.ensg.jpo.explor_descartes.ListeObjets;
import eu.ensg.jpo.explor_descartes.PlanningActivity;
import eu.ensg.jpo.explor_descartes.R;
import eu.ensg.jpo.explor_descartes.donneesAcces.EvenementDAO;
import eu.ensg.jpo.explor_descartes.donneesAcces.VisiteurDAO;
import eu.ensg.jpo.explor_descartes.donnesObjet.Evenement;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class CustomAdapterFavoris extends BaseAdapter {
    Context context;
    LayoutInflater li;
    String url;
    ArrayList<Evenement> listeEventFav;
    ArrayList<Integer> listeFavoris;
    private static LayoutInflater inflaterFav=null;
    ImageView supprFav;
    protected static final OkHttpClient client = new OkHttpClient();
    Activity activity;


    public CustomAdapterFavoris(LayoutInflater li, Context context, Activity activity){
        this.listeFavoris = ListeObjets.visiteur.getListeFavoris();
        this.li = li;
        this.context = context;
        this.activity = activity;

        inflaterFav = li;

        // Construction de la requete
        this.url = context.getString(R.string.url_serveur) + "serveur.php/"+ "?request=listeEvenements";
        final Request request = new Request.Builder().url(this.url).build();
        // Envoi de la requete
        Call call = client.newCall(request);
        this.listeEventFav = new ArrayList<Evenement>();
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Connexion etablie avec succes !");
                System.out.println(response.body());
                Type listType = new TypeToken<ArrayList<Evenement>>(){}.getType();
                ArrayList<Evenement> listeEvenement = new Gson().fromJson(response.body().string(), listType);
                System.out.println("test prouti " + listeEvenement);
                ListeObjets.listeEvenement = listeEvenement;
            }
            public void onFailure(Call call, IOException e){
                System.out.println("Echec de la connexion");
            }
        });

        ArrayList<Evenement> listEvent = ListeObjets.listeEvenement;
        System.out.println("test prout"+listEvent.toString());
        for(int r=0; r<listEvent.size(); r++){
            if(listeFavoris.contains(ListeObjets.listeEvenement.get(r).getId())){
                this.listeEventFav.add(ListeObjets.listeEvenement.get(r));
            }
        }
    }

    @Override
    public int getCount() {
        if(listeEventFav != null){
            return listeEventFav.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final holderFav hldFav = new holderFav();
        final View rowviewFav;
        final Evenement evnt = listeEventFav.get(position);
        rowviewFav = inflaterFav.inflate(R.layout.gd_fav,null);

        hldFav.nomEvent = (TextView) rowviewFav.findViewById(R.id.nomEvent);
        hldFav.nomEcole = (TextView) rowviewFav.findViewById(R.id.nomEcole);
        hldFav.date = (TextView) rowviewFav.findViewById(R.id.horairesFav);
        hldFav.suppr = (ImageView) rowviewFav.findViewById(R.id.supprFav);
        hldFav.nomEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlanningActivity.class);
                context.startActivity(intent);
            }
        });
        hldFav.suppr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisiteurDAO visiteurDAO = new VisiteurDAO(context.getString(R.string.url_serveur));
                visiteurDAO.supprimerFavori(activity,evnt);
                listeEventFav.remove(position);
                notifyDataSetChanged();
            }
        });

        hldFav.nomEvent.setText(evnt.getNom());
        hldFav.nomEcole.setText(evnt.getEcole());
        hldFav.date.setText(evnt.getDebut());
        hldFav.nomEvent.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        hldFav.nomEcole.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        hldFav.date.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));

        return rowviewFav;
    }

    public class holderFav{
        TextView nomEvent;
        TextView nomEcole;
        TextView date;
        ImageView suppr;
    }
}
