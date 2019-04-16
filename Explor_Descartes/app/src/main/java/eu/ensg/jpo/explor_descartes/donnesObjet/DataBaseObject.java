package eu.ensg.jpo.explor_descartes.donnesObjet;

public abstract class DataBaseObject {

    protected int id;

    DataBaseObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
