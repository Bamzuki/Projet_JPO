package eu.ensg.jpo.explor_descartes.Menu;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import eu.ensg.jpo.explor_descartes.R;

public class CustomAdapter1 extends BaseAdapter {
    Context context;
    LayoutInflater il;
    String txt_notifs[];
    String titres[];
    private static LayoutInflater inflater1=null;

    public CustomAdapter1(String[] txt_notifs, String titres[], LayoutInflater il, Context context){
        this.il=il;
        this.txt_notifs=txt_notifs;
        this.titres = titres;
        this.context = context;
        inflater1= il;
    }

    @Override
    public int getCount() {
        return txt_notifs.length;
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
    public View getView(int position, View convertView, ViewGroup parent){
        final holder hld1 = new holder();
        View rowview1;
        rowview1=inflater1.inflate(R.layout.gd_notif,null);
        hld1.title_notif = (TextView) rowview1.findViewById(R.id.title_notif);
        hld1.text_notif  = (TextView) rowview1.findViewById(R.id.txt_notif);
        hld1.title_notif.setText(titres[position]);
        hld1.text_notif.setText(txt_notifs[position]);
        hld1.title_notif.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        hld1.text_notif.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        return rowview1;
    }

    public class holder{
        TextView title_notif;
        TextView text_notif;
    }
}