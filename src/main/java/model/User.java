package model;

public class User {
    //obiectele sunt private, se pot aloca valori doar in interiorul acestei clase
   private String name;
   private int age;
   private double salary;

    public User(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;

    }
    //metoda care poate fi folosita in afara clasei pentru a returna valori
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public double getSalary(){
        return salary;
    }
    public void setSalary(double salary){
        if (salary >= 0) {
            this.salary = salary;
        }
    }
    public void raiseSalary(double amount){
        if (amount > 0) {
            salary += amount;
        }
    }
}
