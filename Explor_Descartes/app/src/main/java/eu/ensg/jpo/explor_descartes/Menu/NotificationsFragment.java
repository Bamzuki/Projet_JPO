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


public class NotificationsFragment extends Fragment {
    private GridView gvNotif;
    private CustomAdapterNotif CAN;
    private Context contextNotif;
    private Activity activityNotif;
    private ImageView supprNotif;

    public NotificationsFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_notifications,container,false);
        View t = inflater.inflate(R.layout.gd_notif, null);
        gvNotif = (GridView) v.findViewById(R.id.gd_notif);
        contextNotif = getActivity();
        activityNotif = getActivity();



        return v;
    }
}