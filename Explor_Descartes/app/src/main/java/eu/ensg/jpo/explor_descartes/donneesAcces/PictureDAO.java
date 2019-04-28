package eu.ensg.jpo.explor_descartes.donneesAcces;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import eu.ensg.jpo.explor_descartes.GridView.ImageEcole;
import eu.ensg.jpo.explor_descartes.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static xdroid.core.Global.getResources;

public class PictureDAO {

    private String url;
    protected final OkHttpClient client = new OkHttpClient();

    public PictureDAO(String url) {
        this.url = url;
    }


    public Drawable getPicture (String nomImage) {
        // Le drawable à renvoyer
        Drawable drawable = null;
        try {
            // Récupération de l'URL à partir de sa représentation sous forme de String.
            String urlPath = this.url + "image_ecole/" + nomImage;
            URL URL = new URL(urlPath);
            // Ouverture de l'inputStream associé à cette URL pour sa lecture.
            InputStream is = (InputStream) URL.getContent();
            // Construction du Drawable à partir de ce flux entrant.
            drawable = Drawable.createFromStream(is, "src");
        } catch (IOException e) {
            // Si une exception se produit faire quelque chose d'intelligent.
            drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.error, null);
        }
        // Renvoyer le résultat.
        return drawable;
    }

    public void addPicture (final Activity activity, final ImageEcole imageEcole, String nomImage) {

        // Construction de la requete
        String url = this.url + "image_ecole/" + nomImage;
        //String url = this.url + "image_ecole/eavt-icone.jpg";
        System.out.println(url);
        Request request = new Request.Builder().url(url).build();

        // Envoi de la requete
        Call call = client.newCall(request);

        // Instanciation de l'image
        final Drawable[] drawable = {null};

        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {

                System.out.println("Image chargée avec succès !");

                InputStream inputStream = response.body().byteStream();
                final Bitmap image = BitmapFactory.decodeStream(inputStream);

                if (image != null){
                    // On affiche l'image correspondante
                    imageEcole.setImage(image);

                }else{
                    // On affiche l'image par défaut
                    imageEcole.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.error));
                }

            }

            public void onFailure(Call call, IOException e) {
                System.out.println("Echec de la connexion !");
                // On affiche l'image par défaut
                imageEcole.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.error));
            }
        });
    }

}
