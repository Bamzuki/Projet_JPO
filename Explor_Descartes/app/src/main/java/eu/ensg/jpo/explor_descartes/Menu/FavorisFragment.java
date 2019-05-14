package eu.ensg.jpo.explor_descartes.Menu;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import eu.ensg.jpo.explor_descartes.ListeObjets;
import eu.ensg.jpo.explor_descartes.R;

public class FavorisFragment extends Fragment {
    private GridView gdFav;
    private CustomAdapterFavoris CAF;
    private Context contextFav;
    private Activity activityFav;
    private ImageView supprFav;

    public FavorisFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_favoris,container,false);
        gdFav = (GridView) v.findViewById(R.id.gd_fav);
        contextFav = getActivity();
        activityFav = getActivity();

        if(ListeObjets.visiteur.getListeFavoris() != null){
            CAF = new CustomAdapterFavoris(inflater,contextFav,activityFav);
            gdFav.setAdapter(CAF);
        }

        return v;
    }

}