package eu.ensg.jpo.explor_descartes.Menu;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import eu.ensg.jpo.explor_descartes.PlanningActivity;
import eu.ensg.jpo.explor_descartes.R;

public class CustomAdapter1 extends BaseAdapter {
    Context context;
    LayoutInflater li;
    ArrayList<String> txt_notification;
    ArrayList<String> titres_notif;
    private static LayoutInflater inflater1=null;
    ImageView suppr;

    public CustomAdapter1(ArrayList<String> txt_notification, ArrayList<String> titres_notif, LayoutInflater li, Context context){
        this.li=li;
        this.txt_notification=txt_notification;
        this.titres_notif = titres_notif;
        this.context = context;
        inflater1= li;
    }

    @Override
    public int getCount() {
        if(txt_notification != null){
            return txt_notification.size();
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
    public View getView(final int position, View convertView, final ViewGroup parent){
        final holder hld1 = new holder();
        final View rowview1;
        rowview1=inflater1.inflate(R.layout.gd_notif,null);
        hld1.nomEvent = (TextView) rowview1.findViewById(R.id.titreNotif);
        hld1.nomEcole  = (TextView) rowview1.findViewById(R.id.textNotif);
        hld1.suppr = (ImageView) rowview1.findViewById(R.id.supprFav);
        hld1.nomEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlanningActivity.class);
                context.startActivity(intent);
            }
        });
        hld1.suppr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titres_notif.remove(position);
                txt_notification.remove(position);
                notifyDataSetChanged();
            }
        });
        hld1.nomEvent.setText(titres_notif.get(position));
        hld1.nomEcole.setText(txt_notification.get(position));
        hld1.nomEvent.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        hld1.nomEcole.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));

        return rowview1;
    }

    public class holder{
        TextView nomEvent;
        TextView nomEcole;
        ImageView suppr;
    }
}
