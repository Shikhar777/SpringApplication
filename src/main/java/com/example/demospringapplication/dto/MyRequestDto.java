package com.example.demospringapplication.dto;

public class MyRequestDto {

    private String firstName;
    private String lastName;
    private int age;
    private double experience;
    private double salary;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString()
    {
        return "MyRequestDto: "+"First Name: "+firstName+" "+"Last Name: "+lastName+" "+"Age: "+age+" "+"Experience: "+experience
                +" "+"Salary: "+salary;
    }
}
