package com.nhlstenden.person;

public class Teacher extends Person
{
    private String subject;

    public Teacher(String name, String teaching, int number)
    {
        super(name, number);
        this.subject = teaching;
    }

    public String getTeaching()
    {
        return subject;
    }

    public void setTeaching(String teaching)
    {
        this.subject = teaching;
    }
}
