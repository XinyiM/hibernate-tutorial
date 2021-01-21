package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ReadStudentDemo {
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
            Student tempStudent = new Student("Daffy", "Duck", "daffy@test.com");

            //  Create the student object
            session.beginTransaction();

            //  save the student object
            System.out.println("Saving a student...");
            System.out.println(tempStudent);
            session.save(tempStudent);

            //  commit transaction
            session.getTransaction().commit();

            // NEW CODE
            System.out.println("Saved student, generated Id: " + tempStudent.getId());

            // get a new session and start a transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve a student based on the PK
            System.out.println("\nGetting Student with id: "+ tempStudent.getId());
            Student myStudent = session.get(Student.class, tempStudent.getId());
            System.out.println("Get Completed:" + myStudent);

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            factory.close();
        }
    }
}
