package eu.ensg.jpo.explor_descartes.GridViewPlanning;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;

import eu.ensg.jpo.explor_descartes.GridView.GridViewAdapter;
import eu.ensg.jpo.explor_descartes.R;
import eu.ensg.jpo.explor_descartes.donnesObjet.Evenement;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static xdroid.core.Global.getResources;

public class ImageEvenement implements Comparable<ImageEvenement>{

    private Bitmap iconEcole;
    private Evenement evenement;
    private String nomIcon;
    private final OkHttpClient client = new OkHttpClient();

    public ImageEvenement(Bitmap iconEcole, Evenement evenement, String nomIcon) {
        this.iconEcole = iconEcole;
        this.evenement = evenement;
        this.nomIcon = nomIcon;
    }

    public void addPicture (final Activity activity, final GridViewAdapterPlanning adapter, String url) {

        // Construction de la requete
        String urlImage = url + "image_ecole/" + this.nomIcon;
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
                    iconEcole = imageLoaded;
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });


                }else{
                    // On affiche l'image par défaut
                    iconEcole = BitmapFactory.decodeResource(getResources(), R.drawable.error);
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
                iconEcole = BitmapFactory.decodeResource(getResources(), R.drawable.error);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    public Bitmap getIconEcole() {
        return iconEcole;
    }

    public void setIconEcole(Bitmap iconEcole) {
        this.iconEcole = iconEcole;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public String getNomIcon() {
        return nomIcon;
    }

    public void setNomIcon(String nomIcon) {
        this.nomIcon = nomIcon;
    }

    @Override
    public int compareTo(ImageEvenement o) {
        return this.getEvenement().getDebut().compareTo(o.getEvenement().getDebut());
    }
}
