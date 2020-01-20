/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.ServiceRendus;
import model.Singleton;

/**
 *
 * @author Guilhem
 */
@Path("services_rendus")
public class ServicesRendusResource {
    @Context
    public UriInfo context;
    
    @GET
    @Produces (MediaType.APPLICATION_XML)
    public String getServiceRendu(@DefaultValue("") @QueryParam("user") String userId){
        StringBuilder builder = new StringBuilder();
        
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        builder.append("<services_rendus>\n");
        for(ServiceRendus serviceRendus : Singleton.listServicesRendus) {
            serviceRendus.setContext(context.getBaseUri().toString());
            
            if(userId.isEmpty()){
                builder.append(serviceRendus.toXML());
            }
            else if(serviceRendus.getClient().getId().equals(userId)){
                builder.append(serviceRendus.toXML());
            }
            
            
        }        
        builder.append("</services_rendus>\n");
        
        return builder.toString();
    }
    
    @GET
    @Path("/{services_rendusID}")
    @Produces (MediaType.APPLICATION_XML)
    public String getServiceRenduID(@PathParam("services_rendusID") String id) {
        StringBuilder builder = new StringBuilder();
        
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        builder.append("<services_rendus>\n");
        for(ServiceRendus serviceRendus : Singleton.listServicesRendus) {
            serviceRendus.setContext(context.getBaseUri().toString());
            if(serviceRendus.getId().equals(id)){
                builder.append(serviceRendus.toXML());
            }
        }        
        builder.append("</services_rendus>\n");
        
        return builder.toString();
    }
    
    @GET
    @Path("/{services_rendusID}/facture")
    @Produces (MediaType.APPLICATION_XML)
    public String getServiceRenduIDFacture(@PathParam("services_rendusID") String id) {
        StringBuilder builder = new StringBuilder();
        
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        builder.append("<services_rendus>\n");
        for(ServiceRendus serviceRendus : Singleton.listServicesRendus) {
            serviceRendus.setContext(context.getBaseUri().toString());
            if(serviceRendus.getId().equals(id)){
                builder.append(serviceRendus.toFacture());
            }
        }        
        builder.append("</services_rendus>\n");
        
        return builder.toString();
    }
}
