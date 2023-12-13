package org.example;

import jakarta.persistence.*;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

@Entity
public class Teacher implements Comparable<Teacher> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String secondName;
    private double salary;
    private int year;
    private String state;
//    @ManyToOne
//    @JoinColumn(name = "classid")
//    private ClassTeacher Tclass;
//    public ClassTeacher getTclass() {
//        return Tclass;
//    }
//
//    public void setTclass(ClassTeacher classid) {
//        this.Tclass = classid;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public void printing()
    {
        System.out.println("ID:"+id+" First name:"+firstName+" Second name:"+secondName+" Salary:"+salary+" Year of birth:"+year+" State:"+state);
//        System.out.println("First name:"+firstName);
//        System.out.println("Second name:"+secondName);
//        System.out.println("Salary:"+salary);
//        System.out.println("Year of birth:"+year);
//        System.out.println("State:"+state);
    }
    @Override
    public int compareTo(Teacher t) {
        if(this.firstName.equals(t.firstName))
        {
            if (this.secondName.equals(t.secondName))
            {
                return 0;
            }
        }
        return -1;
    }
    static public void add(Scanner scanner, EntityManager em)
    {
        Teacher t = new Teacher();
        //t.setId(Global.teacherId);
        //Global.teacherId++;
        String temp = scanner.nextLine();
        t.setFirstName(temp);
        temp = scanner.nextLine();
        t.setSecondName(temp);
        int tempi;
        tempi=scanner.nextInt();
        t.setYear(tempi);
        double tempd;
        tempd = scanner.nextDouble();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        t.setSalary(tempd);
        String temp3 = scanner.nextLine();
        t.setState(temp3);
        em.persist(t);
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
    }
    static public void remove(Scanner scanner, EntityManager em)
    {
        int temp = scanner.nextInt();
        Teacher t = em.find(Teacher.class,temp);
        if(t==null)
        {
            System.out.println("No such teacher");
            return;
        }
        em.remove(t);
        System.out.println("Teacher removed");
    }
}
