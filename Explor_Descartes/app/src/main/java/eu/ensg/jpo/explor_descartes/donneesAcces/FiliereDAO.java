package eu.ensg.jpo.explor_descartes.donneesAcces;

import eu.ensg.jpo.explor_descartes.donnesObjet.Filiere;

public class FiliereDAO extends BddEcolesDAO<Filiere> {

    public FiliereDAO(String urlServeur) {
        super(urlServeur);
    }

    @Override
    public void create(Filiere filiere) {
        return;
    }

    @Override
    public void delete(Filiere filiere) {
        return;
    }

    @Override
    public void update(Filiere filiere) {
        return;
    }

    /**
    public Filiere findById(int id) {
        Statement state = null;
        try {
            // Creation d’un objet Statement
            state = bd.getInstance().createStatement();
            // Execution d’une requete SQL
            ResultSet result = state.executeQuery("SELECT * FROM filieres WHERE id=" + id);
            result.next();
            String nom = result.getString("nom");
            result.close();
            state.close();
            return new Filiere(id, nom);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public  ArrayList<Filiere> getListeFiliere() {
        Statement state = null;
        try {
            // Creation d’un objet Statement
            state = bd.getInstance().createStatement();
            // Execution d’une requete SQL
            ResultSet result = state.executeQuery("SELECT * FROM filieres");
            // Initialisation de la liste des filieres
            ArrayList<Filiere> listeFiliere = new ArrayList<Filiere>();
            // Déplacement iteratif du curseur sur les differentes lignes du resultats
            while (result.next()) {
                int id = result.getInt("id");
                String nom = result.getString("nom");
                Filiere filiere = new Filiere(id, nom);
                listeFiliere.add(filiere);
            }
            result.close();
            state.close();
            return listeFiliere;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

     */

}
