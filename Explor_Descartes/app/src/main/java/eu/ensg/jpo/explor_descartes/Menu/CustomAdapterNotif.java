package eu.ensg.jpo.explor_descartes.Menu;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import eu.ensg.jpo.explor_descartes.ListeObjets;
import eu.ensg.jpo.explor_descartes.PlanningActivity;
import eu.ensg.jpo.explor_descartes.R;
import eu.ensg.jpo.explor_descartes.donnesObjet.Evenement;

import okhttp3.OkHttpClient;

public class CustomAdapterNotif extends BaseAdapter {
    Context context;
    LayoutInflater li;
    String url;
    ArrayList<Evenement> listeNotif;
    ArrayList<Integer> listeNotif_prev;
    ArrayList<Integer> listeFavoris;
    ArrayList<Evenement> liste;
    private static LayoutInflater inflaterNotif=null;
    ImageView supprNotif;
    protected static final OkHttpClient client = new OkHttpClient();


    /**
     * Classe adapter pour la gridView du fragment notification
     * @param li
     * @param context
     */
    //Constructeur de la classe
    public CustomAdapterNotif(LayoutInflater li, Context context){
        if(ListeObjets.visiteur != null) {
            this.listeFavoris = ListeObjets.visiteur.getListeFavoris();
            this.listeNotif = ListeObjets.listeNotif;
            this.listeNotif_prev = ListeObjets.listeNotif_prev;
            this.liste = new ArrayList<Evenement>();
            this.li = li;
            this.context = context;
            inflaterNotif = li;
        }
/*
        for(int i=0; i<listeNotif.size(); i++){
            if(!(ListeObjets.listeNotif_prev.contains(listeNotif.get(i).getId()))){
                liste.add(listeNotif.get(i));
                ListeObjets.listeNotif_prev.add(listeNotif.get(i).getId());
            }
        }
        */
    }

    @Override
    public int getCount() {
        if(listeNotif != null){
            return listeNotif.size();
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

    // Fonction héritée permettant de remplir le fragment NotifFragment
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        final holderNotif hldNot = new holderNotif();
        final View rowviewNotif;
        final Evenement evnt = ListeObjets.listeNotif.get(position);
        rowviewNotif = inflaterNotif.inflate(R.layout.gd_notif, null);

        //Association des variables avec le layout
        hldNot.titreNotif = (TextView) rowviewNotif.findViewById(R.id.titreNotif);
        hldNot.textNotif = (TextView) rowviewNotif.findViewById(R.id.textNotif);
        hldNot.date = (TextView) rowviewNotif.findViewById(R.id.horairesNotif);
        hldNot.suppr = (ImageView) rowviewNotif.findViewById(R.id.supprNotif);

        //Action réalisée suite au click sur le favoris (ouverture de l'activité planning)
        hldNot.titreNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlanningActivity.class);
                context.startActivity(intent);
            }
        });

        //Suppression du favoris en cliquant sur une image
        hldNot.suppr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListeObjets.listeNotif.remove(position);
                notifyDataSetChanged();
            }
        });

        //Récupération des variables textes
        hldNot.titreNotif.setText(evnt.getNom());
        hldNot.textNotif.setText(evnt.getEcole());
        hldNot.date.setText("Evénement dans " + evnt.getInterval()/(60*1000)+" minutes");
        hldNot.titreNotif.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        hldNot.textNotif.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        hldNot.date.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));

        return rowviewNotif;
    }

    public class holderNotif{
        TextView titreNotif;
        TextView textNotif;
        TextView date;
        ImageView suppr;
    }
}
