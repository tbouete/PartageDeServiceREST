package model;

/**
 *
 * @author jcs
 */
// <UTILISATEUR ID="xxx" NOM="xxx" PRENOM="xxx/>"

public class Utilisateur {
    static int idCounter=0;
    String nom, prenom, id;
    
    public Utilisateur(String xml)
    {
        nom = findAttribute(xml, "NOM");
        prenom = findAttribute(xml, "PRENOM");
        
        id = findAttribute(xml, "ID");
        if (id==null)
            id = getNewId();
    }
    
    public Utilisateur(String nom, String prenom, String id){
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
    }
    
    public String toXML() {
        return "<UTILISATEUR ID=\""+id+"\" NOM=\""+nom+"\"  PRENOM=\""+prenom+"\" />";
    }
    
    public static String getNewId()
    {
        return ""+(idCounter++);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
            
    
    
    public static String findAttribute(String xml, String idAttribute)
    {
            int pos1=xml.indexOf(idAttribute+"=\""); // On ne fait qu'avec "
            if (pos1<0) return null;
            int pos2=xml.indexOf("\"", pos1+idAttribute.length()+2);
            return xml.substring(pos1+idAttribute.length()+2, pos2);
    }
        
}








