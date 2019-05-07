package eu.ensg.jpo.explor_descartes;

import java.util.ArrayList;
import eu.ensg.jpo.explor_descartes.donnesObjet.Visiteur;
import eu.ensg.jpo.explor_descartes.donnesObjet.Batiment;
import eu.ensg.jpo.explor_descartes.donnesObjet.Ecole;
import eu.ensg.jpo.explor_descartes.donnesObjet.Evenement;
import eu.ensg.jpo.explor_descartes.donnesObjet.Formation;

public final class ListeObjets {

    public static ArrayList<Batiment> listeBatiment = new ArrayList<Batiment>();
    public static ArrayList<Ecole> listeEcole = new ArrayList<Ecole>();
    public static ArrayList<Formation> listeFormation = new ArrayList<Formation>();
    public static ArrayList<Evenement> listeEvenement = new ArrayList<Evenement>();
    public static Ecole ecoleSelectionnee = null;
    public static Visiteur visiteur = null;
    public static ArrayList<Evenement> listeFavoris = new ArrayList<>();
    public static String dateJPO = "26/04/2019";

    public static Ecole getEcoleById(int id){
        for (Ecole ecole : listeEcole){
            if (ecole.getId() == id){
                return ecole;
            }
        }
        return null;
    }

    public static Batiment getBatimentFromNom(String nom){
        for (Batiment batiment : listeBatiment){
            if (batiment.getNom().equals(nom)){
                return batiment;
            }
        }
        return null;
    }

}
