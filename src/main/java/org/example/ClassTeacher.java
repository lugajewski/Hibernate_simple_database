package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ClassTeacher {
    @Id
    private int id;
    private String name;
}
