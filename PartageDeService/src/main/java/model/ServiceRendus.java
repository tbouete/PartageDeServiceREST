package model;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

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
        String userId = findAttributeOfUri(uri, "users");
        
        for(Utilisateur user : Singleton.listUtilisateurs) {
            if(user.getId().equals(userId)){
                this.client = user;
            }
        }
        
         //find Service
        uri = findAttribute(xml, "SERVICE");
        String serviceId = findAttributeOfUri(uri, "services");
        
        for(Service service : Singleton.listServices) {
            if(service.getId().equals(userId)){
                this.service = service;
            }
        }
        
        id = findAttribute(xml, "ID");
        if (id==null)
            id = getNewId();
        
    }
    
    public ServiceRendus(String id, String nbHeuresConsommees, Service service, Utilisateur client) {
        this.id = id;
        this.nbHeuresConsommees = nbHeuresConsommees;
        this.service = service;
        this.client = client;
    }
    
    public String toXML() {
        return "<SERVICE_RENDUS ID=\""+id+"\" NBHEURESCONSOMMEES=\""+nbHeuresConsommees+"\"  SERVICE=\""+getServiceUri()+ "\" UTILISATEUR=\""+getUserUri()+ "\">";
    }
        
    public static String getNewId()
    {
        return ""+(idCounter++);
    }
    
    private String getServiceUri() {
        String uri = context.toString();
        uri = uri.substring(0, uri.length()-14);
        uri = uri + "services/" + this.client.getId();
        return uri;
    }
    
    private String getUserUri() {
        String uri = context.toString();
        uri = uri.substring(0, uri.length()-14);
        uri = uri + "users/" + this.client.getId();
        return uri;
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
    
    public static String findAttributeOfUri(String uri, String idAttribute) {        
        int pos1=uri.indexOf(idAttribute+"/"); // On ne fait qu'avec "
        if (pos1<0) return null;
        int pos2=uri.indexOf("\"", pos1+idAttribute.length()+2);
        return uri.substring(pos1+idAttribute.length()+2, pos2);
    }
}
