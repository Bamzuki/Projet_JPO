package eu.ensg.jpo.explor_descartes.Menu;

import android.content.Context;
import android.content.Intent;

import eu.ensg.jpo.explor_descartes.AccueilActivity;
import eu.ensg.jpo.explor_descartes.NavigationActivity;
import eu.ensg.jpo.explor_descartes.PlanningActivity;
import eu.ensg.jpo.explor_descartes.RegisterActivity;
import eu.ensg.jpo.explor_descartes.SettingsActivity;
import eu.ensg.jpo.explor_descartes.SignInActivity;

public class Menu {

    private Context con;

    public Menu(Context con){
        this.con = con;
    }

    public void openNavigationActivity(){
        // Create intent
        Intent intent = new Intent(con, NavigationActivity.class);
        // Start activity
        con.startActivity(intent);
    }
    public void openAccueilActivity(){
        // Create intent
        Intent intent = new Intent(con, AccueilActivity.class);
        // Start activity
        con.startActivity(intent);
    }
    public void openSettingsActivity(){
        // Create intent
        Intent intent = new Intent(con, SettingsActivity.class);
        // Start activity
        con.startActivity(intent);
    }
    public void openSignInActivity() {
        // Create intent
        Intent intent = new Intent(con, SignInActivity.class);
        // Start activity
        con.startActivity(intent);
    }
    public void openRegisterActivity() {
        // Create intent
        Intent intent = new Intent(con, RegisterActivity.class);
        // Start activity
        con.startActivity(intent);
    }
    public void openPlanningActivity() {
        // Create intent
        Intent intent = new Intent(con, PlanningActivity.class);
        // Start activity
        con.startActivity(intent);
    }
}
