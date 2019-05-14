package eu.ensg.jpo.explor_descartes.GridView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;

import eu.ensg.jpo.explor_descartes.R;
import eu.ensg.jpo.explor_descartes.donnesObjet.Ecole;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static xdroid.core.Global.getResources;

public class ImageEcole {

    /**
     * Classse représentant l'icone d'une école
     *
     * @param nom : nom de l'école auquelle est associé l'image
     * @param ecole : école à laquelle est associé l'image
     */


    private Bitmap image;
    private String nom;
    private Ecole ecole;
    private final OkHttpClient client = new OkHttpClient();


    //Constructeur
    public ImageEcole(String nom, Ecole ecole) {
        super();
        this.image = BitmapFactory.decodeResource(getResources(), R.drawable.wait);
        this.nom = nom;
        this.ecole = ecole;
    }


    public void addPicture (final Activity activity, final GridViewAdapter adapter, String url) {

        /**
         * Fonction permettant d'ajouter l'icone d'une école d'une image à une page
         *
         * @param activity : page dans laquelle on souhaite inserer l'image
         * @param adapter : adaptateur
         * @param url : url du serveur
         */

        // Construction de la requete
        String urlImage = url + "image_ecole/" + this.ecole.getImage();
        Request request = new Request.Builder().url(urlImage).build();

        // Envoi de la requete
        Call call = client.newCall(request);

        // Instanciation de l'image
        final Drawable[] drawable = {null};

        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException { //Obtention de la réponse du serveur si la requête est effectué

                System.out.println("Image chargée avec succès !");

                InputStream inputStream = response.body().byteStream();
                final Bitmap imageLoaded = BitmapFactory.decodeStream(inputStream);
                inputStream.close();

                if (imageLoaded != null){
                    // On affiche l'image correspondante
                    image = imageLoaded;
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });


                }else{
                    // On affiche l'image par défaut
                    image = BitmapFactory.decodeResource(getResources(), R.drawable.error);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                }

            }

            public void onFailure(Call call, IOException e) { //Cas où la requête échoue
                System.out.println("Echec de la connexion !");
                // On affiche l'image par défaut
                image = BitmapFactory.decodeResource(getResources(), R.drawable.error);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }


    /*
    Getter et Setter
     */

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public void setNome(String nom) {
        this.nom = nom;
    }

    public Ecole getEcole() {
        return ecole;
    }
}