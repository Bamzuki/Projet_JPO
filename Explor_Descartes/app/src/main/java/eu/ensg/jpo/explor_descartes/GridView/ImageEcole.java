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
    private Bitmap image;
    private String nom;
    private Ecole ecole;
    private final OkHttpClient client = new OkHttpClient();

    public ImageEcole(Bitmap image, String nom) {
        super();
        this.image = image;
        this.nom = nom;
    }

    public ImageEcole(String nom, Ecole ecole) {
        super();
        this.image = BitmapFactory.decodeResource(getResources(), R.drawable.wait);
        this.nom = nom;
        this.ecole = ecole;
    }

    public void addPicture (final Activity activity, final GridViewAdapter adapter, String url) {

        // Construction de la requete
        String urlImage = url + "image_ecole/" + this.ecole.getImage();
        //String url = this.url + "image_ecole/eavt-icone.jpg";
        System.out.println(url);
        Request request = new Request.Builder().url(urlImage).build();

        // Envoi de la requete
        Call call = client.newCall(request);

        // Instanciation de l'image
        final Drawable[] drawable = {null};

        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {

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

            public void onFailure(Call call, IOException e) {
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