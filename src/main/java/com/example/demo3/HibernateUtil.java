package com.example.demo3;

import com.example.demo3.entity.Marks;
import com.example.demo3.entity.Subjects;
import com.example.demo3.entity.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static{
        try{
            sessionFactory = new Configuration().addAnnotatedClass(Marks.class)
                    .addAnnotatedClass(Subjects.class)
                    .configure().buildSessionFactory();

        }catch (Throwable ex) {
            System.err.println("Session Factory could not be created." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static <T> List<T> getAll(Class<T> ofClass){
        Session session = getSessionFactory().openSession();
        Query<T> query = session.createQuery("from " + ofClass.getSimpleName(), ofClass);
        List<T> answer = query.list();
        session.close();
        return answer;
    }

}
