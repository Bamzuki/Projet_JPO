package eu.ensg.jpo.explor_descartes;

import android.widget.BaseAdapter;

import java.util.ArrayList;

import eu.ensg.jpo.explor_descartes.donnesObjet.Satisfaction;
import eu.ensg.jpo.explor_descartes.donnesObjet.Visiteur;
import eu.ensg.jpo.explor_descartes.donnesObjet.Batiment;
import eu.ensg.jpo.explor_descartes.donnesObjet.Ecole;
import eu.ensg.jpo.explor_descartes.donnesObjet.Evenement;
import eu.ensg.jpo.explor_descartes.donnesObjet.Formation;

public final class ListeObjets {

    /**
     * classe stockant des objets afin de permettre leur utilisation à travers les différentes activités
     */

    public static ArrayList<Batiment> listeBatiment = new ArrayList<Batiment>();
    public static ArrayList<Ecole> listeEcole = new ArrayList<Ecole>();
    public static ArrayList<Formation> listeFormation = new ArrayList<Formation>();
    public static ArrayList<Evenement> listeEvenement = new ArrayList<Evenement>();
    public static ArrayList<Evenement> listeFavoris = new ArrayList<>();
    public static ArrayList<Evenement> listeNotif = new ArrayList<Evenement>();
    public static ArrayList<Integer> listeNotif_prev = new ArrayList<Integer>();
    public static Ecole ecoleSelectionnee = null;
    public static Visiteur visiteur = null;
    public static String dateJPO = "26/04/2019";
    public static Satisfaction satisfaction = null;
    public static BaseAdapter adapterFavoris = null;
    public static BaseAdapter adapterPlanning = null;



    public static Ecole getEcoleById(int id){
        for (Ecole ecole : listeEcole){
            if (ecole.getId() == id){
                return ecole;
            }
        }
        return null;
    }

    public static Ecole getEcoleFromNom(String nom){

        /**
         * fonction permettant de récupérer une école à partir de son nom
         *
         * @param nom : nom de l'école que l'on recherche
         *
         * @return ecole recherché
         */

        for (Ecole ecole: listeEcole){
            if (ecole.getNom().equals(nom)){
                return ecole;
            }
        }
        return null;
    }

    public static Batiment getBatimentFromNom(String nom){

        /**
         * fonction permettant de récupérer une école à partir de son nom
         *
         * @param nom : nom du batiment que l'on recherche
         *
         * @return batiment recherché
         */

        for (Batiment batiment : listeBatiment){
            if (batiment.getNom().equals(nom)){
                return batiment;
            }
        }
        return null;
    }

}
