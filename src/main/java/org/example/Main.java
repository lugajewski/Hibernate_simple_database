package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mpu");
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            Teacher p = new Teacher();
            p.setId(2);
            p.setFirstName("Adam");
            p.setSecondName("Warzywko");
            p.setSalary(1000.0);
            p.setYear(1999);
            p.setState("present");
            em.persist(p);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
    }
}