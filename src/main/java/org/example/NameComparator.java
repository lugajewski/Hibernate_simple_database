package org.example;

import java.util.Comparator;

public class NameComparator implements Comparator<Teacher> {
    @Override
    public int compare(Teacher t1, Teacher t2) {
        if (t1.getFirstName().equals(t2.getFirstName()))
        {
            return 1;
        }
        if(t1.getSecondName().equals(t2.getSecondName()))
        {
            return 1;
        }
        return 0;
    }
}
