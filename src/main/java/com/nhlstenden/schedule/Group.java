package com.nhlstenden.schedule;

import com.nhlstenden.person.Student;

import java.util.ArrayList;

public class Group
{
    private String name;
    private final ArrayList<Student> students;

    public Group(String name)
    {
        this.name = name;
        this.students = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<Student> getStudents()
    {
        return students;
    }

    public void addStudent(Student student)
    {
        this.students.add(student);
    }

    public void removeStudent(Student student)
    {
        this.students.remove(student);
    }
}
