package com.mobiledoctors24.rxaffectsui.methodrefrence;

import java.time.LocalDate;
import java.util.Calendar;

public class Person {
    public enum Sex {
        MALE, FEMALE
    }

    String name;
    Calendar birthday;
    Sex gender;
    String emailAddress;

    public int getAge() {
        return 5;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public static int compareByAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);
    }

}
