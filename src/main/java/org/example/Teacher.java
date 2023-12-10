package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Teacher implements Comparable<Teacher> {
    @Id
    private int id;
    private String firstName;
    private String secondName;
    private double salary;
    private int year;
    private String state;

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
        System.out.println("ID:"+id);
        System.out.println("First name:"+firstName);
        System.out.println("Second name:"+secondName);
        System.out.println("Salary:"+salary);
        System.out.println("Year of birth:"+year);
        System.out.println("State:"+state);
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

}
