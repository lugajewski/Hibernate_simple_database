package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String option;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mpu");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        boolean exit = false;
        while (!exit)
        {
//            System.out.println("Teachers :");
//            for(Teacher t: Global.teachers)
//            {
//                t.printing();
//            }
//            System.out.println("Classes");
//            for(ClassTeacher c: Global.classTeachers)
//            {
//                c.printing();
//            }

            option = scanner.nextLine();
            switch (option)
            {
                case "exit": {
                    exit = true;
                    break;
                }
                case "newt":
                {
                    System.out.println("Add new teacher (fname, sname, year, salary, condidtion)");
                    Teacher.add(scanner,em);
                    break;
                }
                case "rmvt":
                {
                    System.out.println("Remove teacher (teacher id)");
                    Teacher.remove(scanner,em);
                    break;
                }
                case "newc":
                {
                    System.out.println("Add new class (name, max)");
                    ClassTeacher.add(scanner,em);
                    break;
                }
                case "rmvc":
                {
                    System.out.println("Remove class (class id)");
                    ClassTeacher.remove(scanner,em);
                    break;
                }
                case "add":
                {
                    System.out.println("Add teacher to class(class id, teacher id)");
                    ClassTeacher.addt(scanner,em);
                    break;
                }
                case "ads":
                {
                    System.out.println("Add salary to teacher(class id, teacher id, salary)");
                    ClassTeacher.adds(scanner,em);
                    break;
                }
                case "swt":
                {
                    System.out.println("Show all teachers");
                    String jpql = "SELECT e FROM Teacher e";
                    Query query = em.createQuery(jpql);
                    List<Teacher> list = query.getResultList();
                    for(Teacher t: list)
                    {
                        t.printing();
                    }
                    break;
                }
                case "chc":
                {
                    System.out.println("Change condition (Class id, teacher id, condiditon)");
                    ClassTeacher.chcn(scanner,em);
                    break;
                }
                case "src":
                {
                    System.out.println("Search for teacher (name)");
                    ClassTeacher.srch(scanner,em);
                    break;
                }
                case "srp":
                {
                    System.out.println("Partial search for teacher(name)");
                    ClassTeacher.srcp(scanner,em);
                    break;
                }
                case "cbc":
                {
                    System.out.println("Count by condition(condition)");
                    ClassTeacher.cnbc(scanner,em);
                    break;
                }
                case "sum":
                {
                    System.out.println("Class summary(class id)");
                    ClassTeacher.summ(scanner,em);
                    break;
                }


            }
        }





        em.getTransaction().commit();
        em.close();
    }
}
