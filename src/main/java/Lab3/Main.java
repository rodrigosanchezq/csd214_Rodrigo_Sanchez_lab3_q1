package Lab3;

import entities.Cd;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf=null;
        EntityManager em=null;

        try{
            emf= Persistence.createEntityManagerFactory("default");
            em=emf.createEntityManager();
            Logger.getLogger(Main.class.getName()).log(Level.INFO, "Entity Manager created ("+emf+")");
            em.getTransaction().begin();

            Cd c=new Cd();
            c.setCdName("Hysteria");
            c.setBandName("Def Leppard");


            em.persist(c);

            em.getTransaction().commit();

            int id=c.getId();

            // get all entities
            List<Cd> ListOfCds = em.createQuery("SELECT c FROM Cd c").getResultList();
            System.out.println("\n------------------\nList of Cds\n------------------");
            for(Cd Cd:ListOfCds){
                System.out.println(Cd.getCdName()+ " "+ Cd.getBandName());
            }

            // demonstrate editing of an entity
            //
            System.out.println("\n------------------\nFind and Edit a Cd\n------------------");
            em.getTransaction().begin(); // must wrap with begin and commit or it wont work
            Cd c2=em.find(Cd.class, id);
            c.setCdName("Edited Title");
            c.setBandName("Edited isbn1234");
            em.merge(c2);
            em.getTransaction().commit();

            ListOfCds = em.createQuery("SELECT c FROM Cd c").getResultList();
            System.out.println("\n------------------\nList of PublicationEntitys\n------------------");
            for(Cd Cd:ListOfCds){
                System.out.println(Cd.getCdName()+ " "+ Cd.getBandName());
            }

            // add a new Publication
            System.out.println("\n------------------\nAdd a Cd\n------------------");
            Cd c3=new Cd();
            c.setCdName("Title");
            c.setBandName("isbn1234");
            em.getTransaction().begin();
            em.persist(c3);
            em.getTransaction().commit();

            // confirm added
            ListOfCds = em.createQuery("SELECT c FROM Cd c").getResultList();
            System.out.println("\n------------------\nList of Cds\n------------------");
            for(Cd Cd:ListOfCds){
                System.out.println(Cd.getCdName()+ " "+ Cd.getBandName());
            }
            // delete Publication
            System.out.println("\n------------------\nDelete a Cd\n------------------");
            em.getTransaction().begin();
            em.remove(c3);
            em.getTransaction().commit();
            // confirm deleted
            ListOfCds = em.createQuery("SELECT c FROM Cd c").getResultList();
            System.out.println("\n------------------\nList of Cds\n------------------");
            for(Cd Cd:ListOfCds){
                System.out.println(Cd.getCdName()+ " "+ Cd.getBandName());
            }

        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            if(emf!=null)
                emf.close();
//            if(em!=null)
//                em.close();
        }
    }

}