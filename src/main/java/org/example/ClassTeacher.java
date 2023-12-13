package org.example;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Generated;

import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

@Entity
public class ClassTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int max;
    @OneToMany
    private List<Teacher> list = new ArrayList<>();

    public ClassTeacher() {
    }

    public List<Teacher> getList() {
        return list;
    }

    public void setList(List<Teacher> list) {
        this.list = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    public void printing()
    {
        System.out.println("id:"+id+" name:"+name +" max:"+max );
    }
    public void add_teacher(Teacher t, EntityManager em)
    {
        if (list.add(t))
        {
            System.out.println("Teacher added");
            em.persist(t);
            em.persist(this);
        }
        else {
            System.out.println("Teacher not added");
        }
    }
    public void add_salary(Teacher t, double salary, EntityManager em)
    {
        boolean find = false;
        for(Teacher d : list)
        {
            if(d==t)
            {
                em.remove(d);
                d.setSalary(d.getSalary()+salary);
                System.out.println("Salary raised");
                find = true;
                em.persist(d);
                em.persist(this);
            }
        }
        if(!find)
        {
            System.out.println("Salary not raised");
        }
    }
    public void remove_teacher(Teacher t, EntityManager em)
    {
        if(list.remove(t))
        {
            System.out.println("Teacher removed");
            em.remove(t);
            em.persist(this);

        }
        else
        {
            System.out.println("Teacher not removed");
        }
    }
    public void changeCondition(String state, Teacher t, EntityManager em)
    {
        boolean find = false;
        for(Teacher d: list)
        {
            if(d==t)
            {
                em.remove(d);
                d.setState(state);
                System.out.println("Condition changed");
                find = true;
                em.persist(d);
                em.persist(this);
            }
        }
        if(!find)
        {
            System.out.println("Condition not changed");
        }
    }
    static public void add(Scanner scanner, EntityManager em)
    {
        ClassTeacher c = new ClassTeacher();
        String tem = scanner.nextLine();
        c.setName(tem);
        int tempi = scanner.nextInt();
        c.setMax(tempi);
        //c.setId(Global.classId);
        //Global.classId++;
        em.persist(c);
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
    }
    static public void remove(Scanner scanner, EntityManager em)
    {
        int temp = scanner.nextInt();
        ClassTeacher c = em.find(ClassTeacher.class,temp);
        if(c==null)
        {
            System.out.println("No such class");
            return;
        }
        em.remove(c);
        System.out.println("Class removed");
    }
    static public void addt(Scanner scanner, EntityManager em)
    {
        int temp = scanner.nextInt();
        int temp2 = scanner.nextInt();
        ClassTeacher c = em.find(ClassTeacher.class,temp);
        if(c==null)
        {
            System.out.println("No such class");
            return;
        }
        Teacher t = em.find(Teacher.class,temp2);
        if(t==null)
        {
            System.out.println("No such teacher");
            return;
        }
        c.add_teacher(t,em);
        System.out.println("Teacher added");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
    }
    static public void adds(Scanner scanner, EntityManager em)
    {
        int temp = scanner.nextInt();
        int temp2 = scanner.nextInt();
        double temp3 = scanner.nextDouble();
        ClassTeacher c = em.find(ClassTeacher.class,temp);
        Teacher p = em.find(Teacher.class,temp2);
        if(c==null)
        {
            System.out.println("No such class");
            return;
        }
        if(p==null)
        {
            System.out.println("No such teacher");
            return;
        }
        boolean find = false;
        for(Teacher t: c.list)
        {
            if(t.equals(p))
            {
                c.add_salary(p, temp3, em);
                find = true;
            }
        }
        if(!find)
        {
            System.out.println("No such teacher in class");
        }
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
    }
    static public void chcn(Scanner scanner, EntityManager em)
    {
        int temp = scanner.nextInt();
        int temp2 = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        String temp3 = scanner.nextLine();
        ClassTeacher c = em.find(ClassTeacher.class,temp);
        Teacher t = em.find(Teacher.class,temp2);
        if(c==null)
        {
            System.out.println("No such class");
            return;
        }
        if(t==null)
        {
            System.out.println("No such teacher in class");
            return;
        }
        c.changeCondition(temp3,t,em);
        System.out.println("Condition changed");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
    }
    static public void srch(Scanner scanner, EntityManager em)
    {
        String temp = scanner.nextLine();
        Teacher p = new Teacher();
        p.setFirstName(temp);
        p.setSecondName(temp);
        String jpql = "SELECT e FROM Teacher e";
        Query query = em.createQuery(jpql);
        List<Teacher> list = query.getResultList();
        if(list == null)
        {
            System.out.println("No teachers to search for");
            return;
        }
        boolean find = false;
        for(Teacher t: list)
        {
            NameComparator n = new NameComparator();
            if(n.compare(t,p)==1)
            {
                System.out.println("Teacher found");
                find = true;
                t.printing();
            }
        }
        if(!find)
        {
            System.out.println("Teacher not found");
            return;
        }
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        return;
    }
    static public void srcp(Scanner scanner, EntityManager em)
    {
        String temp = scanner.nextLine();
        String jpql = "SELECT e FROM Teacher e";
        Query query = em.createQuery(jpql);
        List<Teacher> list = query.getResultList();
        boolean find = false;
        for(Teacher t: list)
        {
            if(t.getFirstName().contains(temp))
            {
                System.out.println("Teacher found");
                t.printing();
                find = true;
            }
        }
        if(!find)
        {
            System.out.println("Teacher not found");
            return;
        }
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        return;
    }
    static public void cnbc(Scanner scanner, EntityManager em)
    {
        String temp = scanner.nextLine();
        String jpql = "SELECT e FROM Teacher e";
        Query query = em.createQuery(jpql);
        List<Teacher> list = query.getResultList();
        int found =0;
        for(Teacher t:list)
        {
            if(t.getState().equals(temp))
            {
                found++;
            }
        }
        System.out.println("Found :"+found+" teachers with condition: "+temp);
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        return;
    }
    static public void summ(Scanner scanner, EntityManager em)
    {
        int temp = scanner.nextInt();
        ClassTeacher c = em.find(ClassTeacher.class,temp);
        if(c==null)
        {
            System.out.println("Class not found");
            return;
        }
        for(Teacher t:c.list)
        {
            t.printing();
        }
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        return;
    }

}
