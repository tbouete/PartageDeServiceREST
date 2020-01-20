
package com.mycompany.partagedeservice.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
        Utilisateur user = new Utilisateur(xml);
        if(user.getNom() == null || user.getPrenom() == null){
            throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
        }
        
        Singleton.listUtilisateurs.add(user);
        return user.toXML();
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
    
}
