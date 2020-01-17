package com.mycompany.partagedeservice.resources;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import model.Service;
import model.Singleton;

/**
 *
 * @author 
 */

@Path("services")
public class ServicesResource {
    
   @Context
    public UriInfo context;
    
    @GET
    @Produces (MediaType.APPLICATION_XML)
    public String getServices(@DefaultValue("") @QueryParam("search") String type){
        StringBuilder builder = new StringBuilder();
        
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        builder.append("<services>\n");
        for(Service service : Singleton.listServices) {
            service.setContext(context.getBaseUri().toString());
            if(service.getType().contains(type)){
                builder.append(service.toXML());
            }
        }        
        builder.append("</services>\n");
        
        return builder.toString();
    }
    
    @GET
    @Path("/{serviceId}")
    @Produces (MediaType.APPLICATION_XML)
    public String getService(@PathParam("serviceId") String id) {
        StringBuilder builder = new StringBuilder();
        
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        builder.append("<services>\n");
        for(Service service : Singleton.listServices) {
            service.setContext(context.getBaseUri().toString());
            if(service.getId().equals(id)){
                builder.append(service.toXML());
            }
        }        
        builder.append("</services>\n");
        
        return builder.toString();
    }
}
