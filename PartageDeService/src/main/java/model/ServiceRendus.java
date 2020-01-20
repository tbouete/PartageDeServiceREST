package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author Titouan
 */
public class ServiceRendus {
    
    private String context;
    
    
    static int idCounter = 0;
    private String id, nbHeuresConsommees;
    private Service service;
    private Utilisateur client;
    
    public ServiceRendus(String xml) {        
        nbHeuresConsommees = findAttribute(xml, "NBHEURESCONSOMMEES");
        
        //find Client
        String uri = findAttribute(xml, "UTILISATEUR");
        String userId = findIdOfUserFromUri(uri);
        
        for(Utilisateur user : Singleton.listUtilisateurs) {
            if(user.getId().equals(userId)){
                this.client = user;
            }
        }
        
         //find Service
        uri = findAttribute(xml, "SERVICE");
        String serviceId = findIdOfServiceFromUri(uri);
        
        for(Service service : Singleton.listServices) {
            if(service.getId().equals(serviceId)){
                this.service = service;
            }
        }
        
        id = getNewId();
        
    }
    
    public ServiceRendus(String id, String nbHeuresConsommees, Service service, Utilisateur client) {
        this.id = id;
        this.nbHeuresConsommees = nbHeuresConsommees;
        this.service = service;
        this.client = client;
    }
    
    public String toXML() {
        return "<SERVICE_RENDUS ID=\""+id+"\" NBHEURESCONSOMMEES=\""+nbHeuresConsommees+"\"  SERVICE=\""+getServiceUri()+ "\" UTILISATEUR=\""+getUserUri()+ "\"/>";
    }
    
    public String toFacture() {
        return "Facture pour le SERVICE_RENDUS="+getServiceRenduUri() +" <br>"
                +"Somme payée : "+(Double.parseDouble(nbHeuresConsommees)*Double.parseDouble(this.service.prix) + "€");
    }
        
    public static String getNewId()
    {
        return ""+(idCounter++);
    }
    
    private String getServiceRenduUri() {
        return context + "services_rendus/" + this.id;
    }
    
    private String getServiceUri() {
        return context + "services/" + this.service.getId();
    }
    
    private String getUserUri() {
        return context + "users/" + this.client.getId();
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNbHeuresConsommees() {
        return nbHeuresConsommees;
    }

    public void setNbHeuresConsommees(String nbHeuresConsommees) {
        this.nbHeuresConsommees = nbHeuresConsommees;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Utilisateur getClient() {
        return client;
    }

    public void setClient(Utilisateur client) {
        this.client = client;
    }
    
    public static String findAttribute(String xml, String idAttribute) {
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
    
    public static String findIdOfServiceFromUri(String uri) {
        String regex = "^.*\\/PartageDeService\\/api\\/services\\/(.*)$";
        
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(uri);
        
        if(m.find()) {
            return m.group(1);
        }
        
        return null;
    }
}
