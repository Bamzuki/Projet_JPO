package eu.ensg.jpo.explor_descartes.donneesAcces;

import java.io.IOException;

import eu.ensg.jpo.explor_descartes.ListeObjets;
import eu.ensg.jpo.explor_descartes.SatisfactionActivity;
import eu.ensg.jpo.explor_descartes.donnesObjet.Satisfaction;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class SatisfactionDAO extends BddEcolesDAO<Satisfaction> {

    /*
    Classe permettant d'implementer un formulaire de réponse au questionnaire de satisfaction en java à partir des données de la base de donnée
     */


    //Constructeur
    public SatisfactionDAO(String urlServeur) {
        super(urlServeur);
    }

    //Fonctions permettant de mettre à jour des informations sur unr école dans la base données
    @Override
    public void create(Satisfaction obj) {
        return;
    }  //création

    @Override
    public void update(Satisfaction obj) {
        return;
    } //mise à jour

    @Override
    public void delete(Satisfaction obj) {
        return;
    } //suppression

    public void saveSatisfaction(final SatisfactionActivity activity, final Satisfaction satisfaction) {

        /*
        Fonction permettant d'enregistrer les réponses au questionnaire de satisfaction
        activity : activité dans laquelle on se trouve
        satisfaction : formulaire de réponse
         */

        // Construction de la requete
        String url = this.urlServeur + "?request=saveSatisfaction";
        String donnees = "&&question_1=" + satisfaction.getQuestion_1()+"&&question_2=" + satisfaction.getQuestion_2()+"&&question_3=" + satisfaction.getQuestion_3()+"&&question_4=" + satisfaction.getQuestion_4()+"&&question_5=" + satisfaction.getQuestion_5()+"&&question_6=" + satisfaction.getQuestion_6()+"&&question_7=" + satisfaction.getQuestion_7()+"&&question_8=" + satisfaction.getQuestion_8()+"&&question_9=" + satisfaction.getQuestion_9()+"&&question_10=" + satisfaction.getQuestion_10()+"&&question_11=" + satisfaction.getQuestion_11()+"&&question_12=" + satisfaction.getQuestion_12()+"&&question_13=" + satisfaction.getQuestion_13()+"&&question_14=" + satisfaction.getQuestion_14()+"&&question_15=" + satisfaction.getQuestion_15()+"&&question_16=" + satisfaction.getQuestion_16()+"&&question_17=" + satisfaction.getQuestion_17()+"&&question_18=" + satisfaction.getQuestion_18()+"&&question_19=" + satisfaction.getQuestion_19()+"&&question_20=" + satisfaction.getQuestion_20()+"&&question_21=" + satisfaction.getQuestion_21()+"&&question_22=" + satisfaction.getQuestion_22()+"&&question_23=" + satisfaction.getQuestion_23();
        url = url + donnees;
        System.out.println(url);
        Request request = new Request.Builder().url(url).build();

        // Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException { //Obtention de la réponse du serveur si la requête est effectué
                System.out.println("SatisfactionDAO saveSatisfaction: Connexion etablie avec succes !");
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ListeObjets.satisfaction = satisfaction;
                    }
                });
            }

            public void onFailure(Call call, IOException e) { //Cas où la requête échoue
                System.out.println("SatisfactionDAO saveSatisfaction: Echec de la connection !");
            }
        });
        return;
    }
}
