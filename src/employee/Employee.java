/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee;

/**
 *
 * @author Yash Patel
 * Date Feb 5 2020
 * Description: This is a simple employee database system.
 * Please run the EmployeeUI.java 
 */
import java.util.Date;

public class Employee {
 private int employeeIdNumber;
    private String firstName;
    private String lastName;
    private int annualSalary;
    private Date startDate;

    public Employee(int id, String firstName, String lastName, int salary, Date startDate) {
        this.employeeIdNumber = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.annualSalary = salary;
        this.startDate = startDate;
    }

    public int getId() {
        return employeeIdNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getSalary() {
        return annualSalary;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getStartDate() {
        return startDate;
    }

}
