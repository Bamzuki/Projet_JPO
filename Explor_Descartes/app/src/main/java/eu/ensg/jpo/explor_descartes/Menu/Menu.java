package eu.ensg.jpo.explor_descartes.Menu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import eu.ensg.jpo.explor_descartes.AccueilActivity;
import eu.ensg.jpo.explor_descartes.GestionCompte;
import eu.ensg.jpo.explor_descartes.NavigationActivity;
import eu.ensg.jpo.explor_descartes.PlanningActivity;
import eu.ensg.jpo.explor_descartes.R;
import eu.ensg.jpo.explor_descartes.RegisterActivity;
import eu.ensg.jpo.explor_descartes.SatisfactionActivity;
import eu.ensg.jpo.explor_descartes.SettingsActivity;
import eu.ensg.jpo.explor_descartes.SignInActivity;

import static android.provider.Settings.System.getString;

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
    public void openSatisfactionActivity() {
        // Create intent
        Intent intent = new Intent(con, SatisfactionActivity.class);
        // Start activity
        con.startActivity(intent);
    }
    public void openGestionCompte() {
        // Create intent
        Intent intent = new Intent(con, GestionCompte.class);
        // Start activity
        con.startActivity(intent);
    }

    public void openpageFAQ() {
        String url = String.valueOf(R.string.url_serveur) + "FAQ.html";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        con.startActivity(i);
    }

    public void openpageCGU() {
        String url = String.valueOf(R.string.url_serveur) + "Conditions générales d'utilisation";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        con.startActivity(i);
    }
}