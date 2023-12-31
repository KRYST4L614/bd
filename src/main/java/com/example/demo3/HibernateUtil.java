package com.example.demo3;

import com.example.demo3.entity.Groups;
import com.example.demo3.entity.Marks;
import com.example.demo3.entity.People;
import com.example.demo3.entity.Subjects;
import com.example.demo3.tableData.GroupsData;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory;
    static{
        try{
            sessionFactory = new Configuration().addAnnotatedClass(Marks.class)
                    .addAnnotatedClass(Subjects.class)
                    .addAnnotatedClass(Groups.class)
                    .addAnnotatedClass(People.class)
                    .configure().buildSessionFactory();

        }catch (Throwable ex) {
            System.err.println("Session Factory could not be created." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static <T> List<T> getAll(Class<T> ofClass) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<T> query = session.createQuery("from " + ofClass.getSimpleName(), ofClass);
            return query.list();
        }
    }
}
