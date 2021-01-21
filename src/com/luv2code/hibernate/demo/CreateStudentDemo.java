package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateStudentDemo {
    public static void main(String[] args) {
        //  Create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        try{
            //  use the session objection to save Java Object
            System.out.println("Creating a new student object...");
            Student tempStudent = new Student("Paul", "Wall", "Paul@test.com");

            //  Create the student object
            session.beginTransaction();

            //  save the student object
            System.out.println("Saving a student...");
            session.save(tempStudent);

            //  commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
