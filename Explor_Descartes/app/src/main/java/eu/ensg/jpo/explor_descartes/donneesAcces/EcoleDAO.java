package eu.ensg.jpo.explor_descartes.donneesAcces;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

import eu.ensg.jpo.explor_descartes.GridView.GridViewAdapter;
import eu.ensg.jpo.explor_descartes.ListeObjets;
import eu.ensg.jpo.explor_descartes.R;
import eu.ensg.jpo.explor_descartes.donnesObjet.Ecole;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

import static xdroid.core.Global.getResources;

public class EcoleDAO extends BddEcolesDAO<Ecole>{

    public EcoleDAO(String urlServeur) {
        super(urlServeur);
    }

    @Override
    public void create(Ecole Ecole) {
        return;
    }

    @Override
    public void delete(Ecole Ecole) {
        return;
    }

    @Override
    public void update(Ecole Ecole) {
        return;
    }

    public void chargerEcole() {
        // Construction de la requete
        String url = this.urlServeur + "?request=listeEcoles";
        Request request = new Request.Builder().url(url).build();
        // Envoi de la requete
        Call call = client.newCall(request);
        ArrayList<Ecole> listeEcole = null;
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Connexion etablie avec succes !");
                System.out.println(response.body());
                Type listType = new TypeToken<ArrayList<Ecole>>(){}.getType();
                ArrayList<Ecole> listeEcole = new Gson().fromJson(response.body().string(), listType);
                ListeObjets.listeEcole = listeEcole;
            }

            public void onFailure(Call call, IOException e) {
                System.out.println("Echec de la connection !");
            }
        });

    }

    public void addPicture (final Activity activity, final ImageView imageView, Ecole ecole) {

        // Construction de la requete
        String urlImage = this.urlServeur + "image_ecole/" + ecole.getImage();

        Request request = new Request.Builder().url(urlImage).build();

        // Envoi de la requete
        Call call = client.newCall(request);

        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {

                System.out.println("Image chargée avec succès !");

                InputStream inputStream = response.body().byteStream();
                final Bitmap imageLoaded = BitmapFactory.decodeStream(inputStream);
                inputStream.close();

                if (imageLoaded != null){
                    // On affiche l'image correspondante
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(imageLoaded);
                        }
                    });


                }else{
                    // On affiche l'image par défaut
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.error));
                        }
                    });
                }

            }

            public void onFailure(Call call, IOException e) {
                System.out.println("Echec de la connexion !");
                // On affiche l'image par défaut
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