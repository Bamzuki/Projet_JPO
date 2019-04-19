package eu.ensg.jpo.explor_descartes;

import android.os.Bundle;
import android.widget.TextView;


public class PlanningActivity extends template {
    private String text;
    private TextView tes;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        contentTemp();
    }

    @Override
    protected void contentTemp(){
        text = "Planning";
        tes = (TextView) findViewById(R.id.textPlanning);

    }

    @Override
    protected void llayout(){
        setLayout(R.layout.activity_planning);
    }
}