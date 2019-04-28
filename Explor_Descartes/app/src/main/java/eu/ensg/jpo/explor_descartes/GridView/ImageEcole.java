package eu.ensg.jpo.explor_descartes.GridView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import eu.ensg.jpo.explor_descartes.R;
import eu.ensg.jpo.explor_descartes.donnesObjet.Ecole;

import static xdroid.core.Global.getResources;

public class ImageEcole {
    private Bitmap image;
    private String nom;
    private Ecole ecole;

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