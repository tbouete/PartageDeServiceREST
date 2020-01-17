/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Titouan
 */
public class Singleton {
    public static List<Utilisateur> listUtilisateurs;
    public static List<Service> listServices;
    public static List<ServiceRendus> listServicesRendus;
    
    static {
        listUtilisateurs = new ArrayList<Utilisateur>();
        listServices = new ArrayList<Service>();
        listServicesRendus = new ArrayList<ServiceRendus>();
        
        Utilisateur bob = new Utilisateur("Bob", "Patrick", "1");
        
        Service laveurDeCarreaux = new Service("Laveur de carreaux", "1", "3€", "Lavage", bob);
        
        listUtilisateurs.add(bob);
        listServices.add(laveurDeCarreaux);
    }
}
