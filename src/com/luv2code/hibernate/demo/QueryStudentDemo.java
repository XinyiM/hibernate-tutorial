package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class QueryStudentDemo {
    public static void main(String[] args) {
        //  Create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        try{
            // Create the student object
            session.beginTransaction();

            // Query Student Object
            List<Student> theStudents = session.createQuery("from Student").getResultList();
            displayStudents(theStudents);

            //
            // Query Students: lastName = "Zhang"
            theStudents = session.createQuery("from Student s where s.lastName = 'Zhang'").getResultList();
            displayStudents(theStudents);

            // Query Student: lastName="Zhang" OR firstName="Daffy"
            theStudents = session.createQuery("from Student s where s.firstName='Daffy' OR s.lastName='Zhang'").getResultList();
            displayStudents(theStudents);

            // Query Student: email is like *@test.com
            theStudents = session.createQuery("from Student s where s.email like '%gmail.com'")
                    .getResultList();
            displayStudents(theStudents);

            // Commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }
}
