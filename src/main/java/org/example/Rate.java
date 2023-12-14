package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Entity
@Table(name = "rate")
public class Rate
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    private int rate;
    @ManyToOne
    private ClassTeacher group_id;
    private String date;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public ClassTeacher getGroup_id() {
        return group_id;
    }

    public void setGroup_id(ClassTeacher group_name) {
        this.group_id = group_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void printing()
    {
        System.out.println("ID:"+id+" group:"+group_id.getName()+" rate:"+rate + " date:"+date+" description:"+description);
    }
    public String printings()
    {
        return "ID:"+id+" group:"+group_id.getName()+" rate:"+rate + " date:"+date+" description:"+description;
    }
    static public void add(Scanner scanner, EntityManager em)
    {
        int temp = scanner.nextInt();
        int temp2 = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        String temp3 = scanner.nextLine();
        String temp4 = scanner.nextLine();
        Rate r = new Rate();
        r.rate = temp;
        ClassTeacher c = em.find(ClassTeacher.class,temp2);
        if(c==null)
        {
            System.out.println("No such class");
            return;
        }
        r.group_id = c;
        c.addRate(r,em);
        r.date = temp3;
        r.description = temp4;
        em.persist(r);
        System.out.println("Rate added");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        return;
    }
    static public void remove(Scanner scanner, EntityManager em)
    {
        int temp = scanner.nextInt();
        Rate r = em.find(Rate.class,temp);
        if(r==null)
        {
            System.out.println("No such rate");
            return;
        }
        String jpql = "SELECT e FROM ClassTeacher e";
        Query query = em.createQuery(jpql);
        List<ClassTeacher> list = query.getResultList();
        for(ClassTeacher c: list)
        {
            List<Rate> listr = c.getRates();
            if(listr.contains(r))
            {
                c.removeRate(r,em);
            }
        }
        em.remove(r);
        System.out.println("Rate removed");
    }
}
