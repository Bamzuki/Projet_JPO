package eu.ensg.jpo.explor_descartes;

import java.security.MessageDigest;

public final class SHA {

    public static String encode(String mdp) throws Exception{

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp.getBytes());

        byte byteData[] = md.digest();

        // Convertir le tableau de bits en une format hexadécimal :
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        //System.out.println("En format hexa : " + sb.toString());
        return sb.toString();

    }
}
