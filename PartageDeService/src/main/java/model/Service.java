/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Titouan
 */
public class Service {
    
    private String context;
    
    static int idCounter = 0;
    String nom, id, prix, type;
    Utilisateur artisan;

    public Service(String xml) {
        nom = findAttribute(xml, "NOM");
        prix = findAttribute(xml, "PRIX");
        type = findAttribute(xml, "TYPE");
        
        //find Utilisateur
        String uri = findAttribute(xml, "UTILISATEUR");
        String userId = findIdOfUserFromUri(uri);
        
        for(Utilisateur user : Singleton.listUtilisateurs) {
            if(user.getId().equals(userId)){
                this.artisan = user;
            }
        }

        id = getNewId();
    }
    
    public Service(String nom, String id, String prix, String type, Utilisateur artisan){
        this.nom = nom;
        this.id = id;
        this.prix = prix;
        this.type = type;
        this.artisan = artisan;
    }
    
    public String toXML() {
        return "<SERVICE ID=\""+id+"\" NOM=\""+nom+"\"  PRIX=\""+prix+"\" TYPE=\""+type+"\" UTILISATEUR=\""+ getUserUri()+ "\"/>";
    }
    
    private String getUserUri() {
        String uri = context.toString();
        uri += "users/" + this.getId();
        return uri;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Utilisateur getArtisan() {
        return artisan;
    }

    public void setArtisan(Utilisateur artisan) {
        this.artisan = artisan;
    }    

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
    
    public static String findAttribute(String xml, String idAttribute)
    {
        int pos1=xml.indexOf(idAttribute+"=\""); // On ne fait qu'avec "
        if (pos1<0) return null;
        int pos2=xml.indexOf("\"", pos1+idAttribute.length()+2);
        return xml.substring(pos1+idAttribute.length()+2, pos2);
    }
    
    public static String findIdOfUserFromUri(String uri) {
        String regex = "^.*\\/PartageDeService\\/api\\/users\\/(.*)$";
        
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(uri);
        
        if(m.find()) {
            return m.group(1);
        }
        
        return null;
    }
            
    
}
