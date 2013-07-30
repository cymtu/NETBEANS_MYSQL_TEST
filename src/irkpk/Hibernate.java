package irkpk;

import java.util.List;
import java.util.Observable;
import java.util.Vector;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class Hibernate extends Observable{
    private static SessionFactory sessions;
    private static Session session;
    private static Hibernate hibernate;
    
    private Hibernate(){  
    }
    
    public static Hibernate getInstance(){
        if(hibernate==null) hibernate = new Hibernate();
        return hibernate;    
    }
    
    public void save(Vector v){
        sessions = new Configuration().configure().buildSessionFactory();  
        session = sessions.openSession();
        Transaction tx = null;                
        try {
            tx = session.beginTransaction();
            for(Object tmp:v){
               session.save((Person)tmp);
            }
            tx.commit();
            setChanged();
            notifyObservers();
            tx = null;
        } 
        catch (HibernateException e) {
            if (tx != null) 
                tx.rollback();
        } 
        finally {
            if(session.isOpen()) session.close();
        }
    }
    
    public List getList(){
        sessions = new Configuration().configure().buildSessionFactory();  
        session = sessions.openSession();               
        List v = session.createQuery("from Person").list();
        session.close();      
        return v;
    }
}
