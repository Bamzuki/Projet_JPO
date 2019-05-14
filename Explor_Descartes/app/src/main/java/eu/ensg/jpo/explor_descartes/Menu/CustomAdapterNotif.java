package eu.ensg.jpo.explor_descartes.Menu;

import android.content.Context;
import android.content.Intent;
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
import eu.ensg.jpo.explor_descartes.donnesObjet.Evenement;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class CustomAdapterNotif extends BaseAdapter {
    Context context;
    LayoutInflater li;
    String url;
    ArrayList<Evenement> listeEventFav;
    ArrayList<Integer> listeFavoris;
    private static LayoutInflater inflaterNotif=null;
    ImageView supprNotif;
    protected static final OkHttpClient client = new OkHttpClient();


    public CustomAdapterNotif(LayoutInflater li, Context context){
        this.listeFavoris = ListeObjets.visiteur.getListeFavoris();
        this.li = li;
        this.context = context;
        inflaterNotif = li;

        // Construction de la requete
        this.url = context.getString(R.string.url_serveur) + "?request=listeEvenements";
        final Request request = new Request.Builder().url(this.url).build();
        // Envoi de la requete
        Call call = client.newCall(request);
        this.listeEventFav = null;
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Connexion etablie avec succes !");
                System.out.println(response.body());
                Type listType = new TypeToken<ArrayList<Evenement>>(){}.getType();
                ArrayList<Evenement> listeEvenement = new Gson().fromJson(response.body().string(), listType);
                ListeObjets.listeEvenement = listeEvenement;
            }
            public void onFailure(Call call, IOException e){
                System.out.println("Echec de la connexion");
            }
        });

        ArrayList<Evenement> listEvent = ListeObjets.listeEvenement;
        for(int r=0; r<listEvent.size(); r++){
            if(listeFavoris.contains(listEvent.get(r).getId())){
                this.listeEventFav.add(listEvent.get(r));
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
    public View getView(int position, final View convertView, ViewGroup parent) {
        final holderNotif hldNot = new holderNotif();
        final View rowviewNotif;
        rowviewNotif = inflaterNotif.inflate(R.layout.gd_notif, null);

        hldNot.titreNotif = (TextView) rowviewNotif.findViewById(R.id.titreNotif);
        hldNot.textNotif = (TextView) rowviewNotif.findViewById(R.id.textNotif);
        hldNot.date = (TextView) rowviewNotif.findViewById(R.id.horairesNotif);
        hldNot.suppr = (ImageView) rowviewNotif.findViewById(R.id.supprNotif);
        hldNot.titreNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlanningActivity.class);
                context.startActivity(intent);
            }
        });
        hldNot.suppr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                notifyDataSetChanged();
            }
        });

        return rowviewNotif;
    }

    public class holderNotif{
        TextView titreNotif;
        TextView textNotif;
        TextView date;
        ImageView suppr;
    }
}
