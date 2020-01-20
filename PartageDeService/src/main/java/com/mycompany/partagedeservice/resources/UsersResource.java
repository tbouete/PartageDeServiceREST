
package com.mycompany.partagedeservice.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import model.Singleton;
import model.Utilisateur;
import model.Service;

/**
 *
 * @author Titouan
 */
@Path("users")
public class UsersResource {
    
    @Context
    public UriInfo context;
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getUsers() {
        StringBuilder builder = new StringBuilder();
        
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        builder.append("<users>\n");
        
        for(Utilisateur user : Singleton.listUtilisateurs) {
            builder.append(user.toXML());
        }    
        
        builder.append("</users>\n");        
        return builder.toString();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public String createUser(String xml) throws ServerErrorException {
        StringBuilder builder = new StringBuilder();
        
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        builder.append("<users>\n");
        
        Utilisateur user = new Utilisateur(xml);
        if(user.getNom() == null || user.getPrenom() == null){
            throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
        }
        
        Singleton.listUtilisateurs.add(user);
        builder.append(user.toXML());
        builder.append("</users>\n");  
        
        return builder.toString();
    }
    
    
    
    @GET
    @Path("/{userId}")
    @Produces (MediaType.APPLICATION_XML)
    public String getUser(@PathParam("userId") String id) {
        StringBuilder builder = new StringBuilder();
        
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        builder.append("<users>\n");
        for(Utilisateur user : Singleton.listUtilisateurs) {
            if(user.getId().equals(id)){
                builder.append(user.toXML());
            }
        }        
        builder.append("</users>\n");
        
        return builder.toString();
    }
    
    @DELETE
    @Path("/{userId}")
    @Produces (MediaType.APPLICATION_XML)
    public String deleteUser(@PathParam("userId") String id) throws ServerErrorException {
        StringBuilder builder = new StringBuilder();
        
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        builder.append("<users>\n");
        
        try {
        Utilisateur user = Singleton.listUtilisateurs.get(Integer.parseInt(id) -1);
            Singleton.listUtilisateurs.remove(user);
            builder.append(user.toXML());
        }
        catch(IndexOutOfBoundsException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        
        builder.append("</users>\n");
        
        return builder.toString();
    }
    
    @PUT
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces (MediaType.APPLICATION_XML)
    public String updateUser(@PathParam("userId") String id, String xml) {
        StringBuilder builder = new StringBuilder();
        
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        builder.append("<users>\n");
        
        
        try{
            Utilisateur user = Singleton.listUtilisateurs.get(Integer.parseInt(id) -1);
            if(user.findAttribute(xml, "NOM") != null){
                user.setNom(user.findAttribute(xml, "NOM"));
            }
            if(user.findAttribute(xml, "PRENOM") != null) {
                user.setPrenom(user.findAttribute(xml, "PRENOM"));
            }
            builder.append(user.toXML());
        } 
        catch(IndexOutOfBoundsException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        
        builder.append("</users>\n");
        
        return builder.toString();
    }
    
    @GET
    @Path("/{userId}/services")
    @Produces (MediaType.APPLICATION_XML)
    public String getServicesOfUser(@PathParam("userId") String id) throws ServerErrorException {
        StringBuilder builder = new StringBuilder();
        
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        builder.append("<users>\n");
        
        try{
            Utilisateur user = Singleton.listUtilisateurs.get(Integer.parseInt(id) -1);
            
            for(Service service : Singleton.listServices){
                service.setContext(context.getBaseUri().toString());
                if(service.getArtisan().equals(user)){
                    builder.append(service.toXML());
                }
            }
        } 
        catch(IndexOutOfBoundsException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        
        builder.append("</users>\n");
        
        return builder.toString();
    }
    
    @POST
    @Path("/{userId}/services")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces (MediaType.APPLICATION_XML)
    public String createServiceForUser(@PathParam("userId") String id, String xml) throws ServerErrorException {
        StringBuilder builder = new StringBuilder();
        
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        builder.append("<users>\n");
        
        try{
            Utilisateur user = Singleton.listUtilisateurs.get(Integer.parseInt(id) -1);
           
            Service service = new Service(xml);            
            service.setContext(context.getBaseUri().toString());
            
            if (service.getNom() == null || service.getPrix() == null || service.getType() == null || service.getArtisan() != user){
                throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
            }
            Singleton.listServices.add(service);
            builder.append(service.toXML());
            
        } 
        catch(IndexOutOfBoundsException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        
        builder.append("</users>\n");
        
        return builder.toString();
    }
    
    
    
}
