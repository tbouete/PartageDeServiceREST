
package com.mycompany.partagedeservice.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import model.Service;
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
}
