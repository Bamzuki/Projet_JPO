package eu.ensg.jpo.explor_descartes.donneesAcces;


import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.lang.String;



public class sha
{


    public static void main(String[] args)throws Exception
    {

        String nom="LTacito";
        String pseudo="Tacito";
        String adresseIP="/host/Documents/JPO/Projet_JPO/fichier_php_connexion/connexion.php";


        decodage(pseudo);
        System.out.println(decodage(pseudo));


        lectureInfosProfil(nom, pseudo, adresseIP );
    }




    public static StringBuffer decodage(String pseudo)throws Exception {


        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(pseudo.getBytes());

        byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        //System.out.println("En format hexa : " + sb.toString());
        return sb;


    }






    public static String lectureInfosProfil(String nom, String pseudo, String adresseIP)
    {
        nom = nom;
        pseudo = pseudo;
        String adresseIp = adresseIP;
        String test = null;

        try
        {
            URL url = new URL( adresseIp + "?nom=" + nom +"&pseudo=" + pseudo);
            Object modifier = url.getContent();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());

            byte[] bytes = new byte[1024];
            int tmp ;
            while( (tmp = bis.read(bytes) ) != -1 )
            {
                String chaine = new String(bytes,0,tmp);
                test = chaine;
            }

            conn.disconnect();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }


        return test;


    }













}