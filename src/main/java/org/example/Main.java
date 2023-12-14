package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

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
                case "swc":
                {
                    System.out.println("Show all classes");
                    String jpql = "SELECT e FROM ClassTeacher e";
                    Query query = em.createQuery(jpql);
                    List<ClassTeacher> list = query.getResultList();
                    for(ClassTeacher c: list)
                    {
                        c.printing();
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
                case "max":
                {
                    System.out.println("Max salary in class(class id)");
                    ClassTeacher.max(scanner,em);
                    break;
                }
                case "newr":
                {
                    System.out.println("Add new rate (rate, classid, date, desc)");
                    Rate.add(scanner,em);
                    break;
                }
                case "swr":
                {
                    System.out.println("Show all rates");
                    String jpql = "SELECT e FROM Rate e";
                    Query query = em.createQuery(jpql);
                    List<Rate> list = query.getResultList();
                    for(Rate r: list)
                    {
                        r.printing();
                    }
                    break;
                }
                case "rmvr":
                {
                    System.out.println("Remove rate(id)");
                    Rate.remove(scanner,em);
                    break;
                }
                case "rtsm":
                {
                    System.out.println("Rates summary");
                    ClassTeacher.rtsm(scanner,em);
                    break;
                }
                case "csv":
                {
                    System.out.println("Print to csv(file name, classes/teachers/rates)");
                    String csvFileName = scanner.nextLine();
                    String choice = scanner.nextLine();
                    try (CSVWriter writer = new CSVWriter(new FileWriter(csvFileName))) {
                        if(choice.equals("classes"))
                        {
                            String jpql = "SELECT e FROM ClassTeacher e";
                            Query query = em.createQuery(jpql);
                            List<ClassTeacher> list = query.getResultList();
                            for(ClassTeacher c: list)
                            {
                                String[] record = {c.printings()};
                                writer.writeNext(record);
                            }
                        }
                        else if(choice.equals("rates"))
                        {
                            String jpql = "SELECT e FROM Rate e";
                            Query query = em.createQuery(jpql);
                            List<Rate> list = query.getResultList();
                            for(Rate c: list)
                            {
                                String[] record = {c.printings()};
                                writer.writeNext(record);
                            }
                        }
                        else if(choice.equals("teachers"))
                        {
                            String jpql = "SELECT e FROM Teacher e";
                            Query query = em.createQuery(jpql);
                            List<Teacher> list = query.getResultList();
                            for(Teacher c: list)
                            {
                                String[] record = {c.printings()};
                                writer.writeNext(record);
                            }
                        }
                        else
                        {
                            System.out.println("Error while printing to CSV");
                        }
                        System.out.println("Printed to CSV");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }

            }
        }





        em.getTransaction().commit();
        em.close();
    }
}
