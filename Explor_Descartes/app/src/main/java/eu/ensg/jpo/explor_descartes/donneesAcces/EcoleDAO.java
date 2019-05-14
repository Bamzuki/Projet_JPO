package eu.ensg.jpo.explor_descartes.donneesAcces;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

import eu.ensg.jpo.explor_descartes.ListeObjets;
import eu.ensg.jpo.explor_descartes.R;
import eu.ensg.jpo.explor_descartes.donnesObjet.Ecole;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

import static xdroid.core.Global.getResources;

public class EcoleDAO extends BddEcolesDAO<Ecole>{

    /*
    Classe permettant d'implementer une école en java à partir des données de la base de donnée
     */

    //Constructeur
    public EcoleDAO(String urlServeur) {
        super(urlServeur);
    }


    //Fonctions permettant de mettre à jour des informations sur unr école dans la base données
    @Override
    public void create(Ecole Ecole) {
        return;
    } //création

    @Override
    public void delete(Ecole Ecole) {
        return;
    } //suppression

    @Override
    public void update(Ecole Ecole) {
        return;
    }  //mise à jour



    public void chargerEcole() {

        /*
        Fonction permettant de charger une école depuis la base de données
         */

        //Construction de la requete
        String url = this.urlServeur + "?request=listeEcoles";
        Request request = new Request.Builder().url(url).build();

        //Envoi de la requete
        Call call = client.newCall(request);
        ArrayList<Ecole> listeEcole = null;
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException { //Obtention de la réponse du serveur si la requête est effectué

                System.out.println("EcoleDAO chargerEcole: Connexion etablie avec succes !");

                Type listType = new TypeToken<ArrayList<Ecole>>(){}.getType();
                ArrayList<Ecole> listeEcole = new Gson().fromJson(response.body().string(), listType);
                ListeObjets.listeEcole = listeEcole;
            }

            public void onFailure(Call call, IOException e) { //Cas où la requête échoue
                System.out.println("EcoleDAO chargerEcole: Echec de la connection !");
            }
        });

    }


    public void addPicture (final Activity activity, final ImageView imageView, Ecole ecole) {

        /* Fonction permettant de récuperer l'icone d'une école depuis la  base de données et l'joute dans une ImageView
        activity : activité dans laquelle on se trouve
        imageView : ImageView dans laquelle on souhaite charger l'image
        ecole : Ecole pour laquelle on recherche l'icone */

        //Construction de la requete
        String urlImage = this.urlServeur + "image_ecole/" + ecole.getImage();
        Request request = new Request.Builder().url(urlImage).build();

        //Envoi de la requete
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {   //Obtention de la réponse du serveur si la requête est effectué

                System.out.println("EcoleDAO addPicture: Image chargée avec succès !");

                InputStream inputStream = response.body().byteStream();
                final Bitmap imageLoaded = BitmapFactory.decodeStream(inputStream);
                inputStream.close();

                if (imageLoaded != null){
                    //On affiche l'image correspondante
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(imageLoaded);
                        }
                    });


                }

                else{
                    //On affiche l'image par défaut
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.error));
                        }
                    });
                }

            }

            public void onFailure(Call call, IOException e) { //Cas où la requête échoue
                System.out.println("EcoleDAO addPicture: Echec de la connexion !");
                //On affiche l'image par défaut
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.error));
                    }
                });
            }
        });
    }

}