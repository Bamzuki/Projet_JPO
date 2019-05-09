package eu.ensg.jpo.explor_descartes.GridViewPlanning;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import eu.ensg.jpo.explor_descartes.ListeObjets;
import eu.ensg.jpo.explor_descartes.R;

import static xdroid.core.Global.getResources;

public class GridViewAdapterPlanning extends ArrayAdapter {
    private Context context;
    private Activity activity;
    private int layoutResourceId;
    private ArrayList<ImageEvenement> data = new ArrayList<>();

    public GridViewAdapterPlanning(Context context, Activity activity, int layoutResourceId, ArrayList data) {
        super(context, layoutResourceId, data);
        this.activity = activity;
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.iconEcole = (ImageView) row.findViewById(R.id.iconEcole);
            holder.nom = (TextView) row.findViewById(R.id.nom);
            holder.ecole = (TextView) row.findViewById(R.id.nomEcole);
            holder.batiment = (TextView) row.findViewById(R.id.nomBatiment);
            holder.horaires = (TextView) row.findViewById(R.id.horaires);
            holder.favourite = (ImageView) row.findViewById(R.id.favorite);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        ImageEvenement item = data.get(position);
        holder.iconEcole.setImageBitmap(item.getIconEcole());
        holder.nom.setText(item.getEvenement().getNom());
        holder.ecole.setText(item.getEvenement().getEcole());
        holder.batiment.setText(item.getEvenement().getBatiment());
        holder.horaires.setText(item.getEvenement().getHoraires());
        if (ListeObjets.listeFavoris.contains(item.getEvenement())){
            int id = (int) this.context.getResources().getIdentifier("star","drawable", this.context.getPackageName());
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
            holder.favourite.setImageBitmap(bitmap);
        }else {
            int id = (int) this.context.getResources().getIdentifier("star_stroked","drawable", this.context.getPackageName());
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
            holder.favourite.setImageBitmap(bitmap);
        }

        return row;
    }

    static class ViewHolder {
        ImageView iconEcole;
        TextView nom;
        TextView ecole;
        TextView batiment;
        TextView horaires;
        ImageView favourite;
    }

    public ArrayList<ImageEvenement> getData() {
        return data;
    }

}