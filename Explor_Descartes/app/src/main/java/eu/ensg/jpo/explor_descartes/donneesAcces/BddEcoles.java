package eu.ensg.jpo.explor_descartes.donneesAcces;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import eu.ensg.jpo.explor_descartes.R;


public class BddEcoles {

    private static Connection connection = null;
    private static String url = "jdbc:postgresql://82.229.248.34:5432";
    private static final String user = "postgres";
    private static final String passwd = "postgres";

    public static Connection getInstance() throws SQLException {

        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(url, user, passwd);
                System.out.println("Connexion etablie avec succes !");
            }
            catch(Exception e){
                    e.printStackTrace(); // pour gerer les erreurs (pas de pilote, base inexistante, etc.)
            }
            finally{
                    if(connection != null){
                        connection.close(); // toujours fermer les differentes ressources quand il n’y en as plus besoin
                    }
            }
        }
        return connection;
    }


}
