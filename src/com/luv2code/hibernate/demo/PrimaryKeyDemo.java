package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
    public static void main(String[] args) {
        //  Create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        try{
            //  use the session objection to save Java Object
            System.out.println("Creating 3 new students object...");
            Student tempStudent1 = new Student("Bob", "Wall", "bob@test.com");
            Student tempStudent2 = new Student("John", "Wall", "john@test.com");
            Student tempStudent3 = new Student("Dylan", "Zhang", "dylan@test.com");

            //  Create the student object
            session.beginTransaction();

            //  save the student object
            System.out.println("Saving the students...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

            //  commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
