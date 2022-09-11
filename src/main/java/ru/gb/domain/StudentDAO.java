package ru.gb.domain;


import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static ru.gb.domain.SessionFactoryUtil.constructSessionFactory;

public class StudentDAO {

    public Student findById(int id) {
        return constructSessionFactory().openSession().get(Student.class, id);
    }

    public void saveList(Student student) {
        Session session = constructSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(student);
        transaction.commit();
        session.close();
    }

    public void saveList(List<Student> students) {
        Session session = constructSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        students.forEach(session::persist);
        transaction.commit();
        session.close();
    }

    public void update(Student student) {
        Session session = constructSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(student);
        transaction.commit();
        session.close();
    }

    public void delete(Student student) {
        Session session = constructSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(student);
        transaction.commit();
        session.close();
    }

    public Student findAutoById(int id) {
        return constructSessionFactory().openSession().get(Student.class, id);
    }

    public List<Student> findAll() {
        return (List<Student>) constructSessionFactory().openSession().createSelectionQuery("From Student").list();
    }
}
