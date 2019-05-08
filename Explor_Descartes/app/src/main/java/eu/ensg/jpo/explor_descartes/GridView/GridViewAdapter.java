package eu.ensg.jpo.explor_descartes.GridView;

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

import eu.ensg.jpo.explor_descartes.R;
import eu.ensg.jpo.explor_descartes.donneesAcces.PictureDAO;
import eu.ensg.jpo.explor_descartes.donnesObjet.Ecole;

import static android.provider.Settings.System.getString;
import static xdroid.core.Global.getResources;

public class GridViewAdapter extends ArrayAdapter {
    private Context context;
    private Activity activity;
    private int layoutResourceId;
    private ArrayList<ImageEcole> data = new ArrayList<>();

    public GridViewAdapter(Context context, Activity activity, int layoutResourceId, ArrayList data) {
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
            holder.imageTitle = (TextView) row.findViewById(R.id.text);
            holder.image = (ImageView) row.findViewById(R.id.image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        ImageEcole item = data.get(position);
        holder.imageTitle.setText(item.getNom());
        holder.image.setImageBitmap(item.getImage());

        return row;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }

    public ArrayList<ImageEcole> getData() {
        return data;
    }
}