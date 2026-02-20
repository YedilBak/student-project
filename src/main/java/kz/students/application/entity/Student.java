package kz.students.application.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {

    private int id;
    private String fullName;
    private String city;
    private double gpa;
    private int age;


}
