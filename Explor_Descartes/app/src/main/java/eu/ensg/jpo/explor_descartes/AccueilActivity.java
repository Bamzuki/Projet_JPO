package eu.ensg.jpo.explor_descartes;

import android.content.Context;
import android.os.Bundle;
import android.widget.GridView;

import java.util.Arrays;

import eu.ensg.jpo.explor_descartes.Menu.CustomAdapter;

public class AccueilActivity extends template {
    private String ecoles;
    private String[] name;
    private int img[];
    private Context contt=this;
    private CustomAdapter cl1;
    private GridView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gv = (GridView) findViewById(R.id.main_grid);
        contentTemp();
    }

    @Override
    protected void contentTemp(){
        ecoles="ENSG,CFA Decartes,Compagnons du devoir,ESIEE,IUT,UPEM,ESO Paris";
        name=ecoles.split(",");
        Arrays.sort(name);
        img= new int[]{R.drawable.icone_ensg,R.drawable.icone_cfa,R.drawable.icone_compagnon,
                R.drawable.icone_esiee,R.drawable.icone_iut,R.drawable.icone_upem,
                R.drawable.icone_eso3};
        Arrays.sort(img);

        cl1=new CustomAdapter(name,contt,img);
        gv.setAdapter(cl1);
    }
}
