package eu.ensg.jpo.explor_descartes;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import java.util.HashMap;
import java.util.List;

import eu.ensg.jpo.explor_descartes.donneesAcces.VisiteurDAO;

public class ExpandableListAdapterEvenement extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHashMap;

    public ExpandableListAdapterEvenement(Context context, List<String> listDataHeader, HashMap<String, List<String>> listHashMap) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }


    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String)getGroup(groupPosition);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.liste_entete,null);
        }
        TextView lblListHeader = (TextView)convertView.findViewById(R.id.titre_entete);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, final View convertView, ViewGroup parent) {
        final String childText = (String)getChild(groupPosition, childPosition);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.liste_evenement,null);
        }
        final TextView txtListChild = (TextView)convertView.findViewById(R.id.contenu_objets);
        txtListChild.setText(childText);
        txtListChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ListeObjets.visiteur == null){
                    ToggleButton etat = (ToggleButton) convertView.findViewById(R.id.etat);
                    if ( ! etat.isChecked()){
                        Drawable img = context.getDrawable(android.R.drawable.star_big_on);
                        txtListChild.setCompoundDrawablesWithIntrinsicBounds(null,null, img, null);
/*
                        VisiteurDAO visiteurDAO = new VisiteurDAO(getContext().getString(R.string.url_serveur));
                        visiteurDAO.ajouterFavori(new EcoleActivity(), ListeObjets.visiteur, childPosition);
*/
                    }
                    else {
                        Drawable img = context.getDrawable(android.R.drawable.star_big_off);
                        txtListChild.setCompoundDrawablesWithIntrinsicBounds(null,null, img, null);
/*
                        VisiteurDAO visiteurDAO = new VisiteurDAO(getContext().getString(R.string.url_serveur));
                        visiteurDAO.supprimerFavori(new EcoleActivity(), ListeObjets.visiteur, childPosition);
*/
                    }
                }
                else {
                    Toast.makeText(context, R.string.favori_impossible, Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public Context getContext() { return context; }

    public void setContext(Context context) { this.context = context; }

    public List<String> getListDataHeader() { return listDataHeader; }

    public void setListDataHeader(List<String> listDataHeader) { this.listDataHeader = listDataHeader; }

    public HashMap<String, List<String>> getListHashMap() { return listHashMap; }

    public void setListHashMap(HashMap<String, List<String>> listHashMap) { this.listHashMap = listHashMap; }
}
