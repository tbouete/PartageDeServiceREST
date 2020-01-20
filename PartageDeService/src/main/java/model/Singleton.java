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
        listUtilisateurs = new ArrayList<>();
        listServices = new ArrayList<>();
        listServicesRendus = new ArrayList<>();
        
        Utilisateur bob = new Utilisateur("Bob", "Patrick", "1");
        Utilisateur mCrab = new Utilisateur("Mister", "Crab", "2");
        Utilisateur pacman = new Utilisateur("Pacman", "NomNom", "3");
        Utilisateur oui = new Utilisateur("Oui", ".", "4");
        Utilisateur arno = new Utilisateur("Sibade", "Arnaud", "5");
        
        Service laveurDeCarreaux = new Service("Laveur de carreaux", "1", "3", "Lavage", bob);        
        Service kebab = new Service("Maître Kebabier", "2", "1000", "Chef", arno);
        
        ServiceRendus serv = new ServiceRendus("1", "2", laveurDeCarreaux, bob);
        ServiceRendus kebabed = new ServiceRendus("2", "0.2", kebab, arno);
        ServiceRendus kebabedLeRetour = new ServiceRendus("3", "9", kebab, arno);
        ServiceRendus kebabedLeDepart = new ServiceRendus("4", "0.5", kebab, arno);
        ServiceRendus kebabedLaSuite = new ServiceRendus("5", "2", kebab, arno);
        
        listUtilisateurs.add(bob);
        listUtilisateurs.add(mCrab);
        listUtilisateurs.add(pacman);
        listUtilisateurs.add(oui);
        listUtilisateurs.add(arno);
        
        listServices.add(laveurDeCarreaux);
        listServices.add(kebab);
        
        listServicesRendus.add(serv);
        listServicesRendus.add(kebabed);
        listServicesRendus.add(kebabedLeRetour);
        listServicesRendus.add(kebabedLeDepart);
        listServicesRendus.add(kebabedLaSuite);
    }
}
