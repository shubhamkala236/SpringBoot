package org.shubh;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        Student s1 = new Student();
        s1.setsName("Shubham");
        s1.setsAge(21);
        s1.setRollNo(126);

        //saveData(s1);
        fetchData();
        //saveUpdateData(s1);
    }

    public static void saveUpdateData(Student s1)
    {
        SessionFactory sf = new Configuration()
                .configure()
                .addAnnotatedClass(org.shubh.Student.class)
                .buildSessionFactory();

        Session session = sf.openSession();

        Transaction transaction = session.beginTransaction();
        session.merge(s1);
        transaction.commit();

        session.close();
        sf.close();

        System.out.println(s1);
    }

    public static void fetchData()
    {
        Student s1 = null;
        SessionFactory sf = new Configuration()
                .configure()
                .addAnnotatedClass(org.shubh.Student.class)
                .buildSessionFactory();

        Session session = sf.openSession();

        s1 = session.find(Student.class, 126);

        session.close();
        sf.close();

        System.out.println(s1);
    }

    public static void saveData(Student s1)
    {
        SessionFactory sf = new Configuration()
                .configure()
                .addAnnotatedClass(org.shubh.Student.class)
                .buildSessionFactory();

        Session session = sf.openSession();

        Transaction transaction = session.beginTransaction();
        session.persist(s1);
        transaction.commit();

        session.close();
        sf.close();

        System.out.println(s1);
    }
}
