/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Ilya
 */
public class BusinessCardImpl implements BusinessCard {
  private String firstName;
  private String lastName;
  private String department;
  private Calendar birthDate;
  private String gender;
  private int salary;
  private String phoneNumber;

  private void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  private void setLastName(String lastName) {
    this.lastName = lastName;
  }

  private void setDepartment(String department) {
    this.department = department;
  }

  private void setDateOfBirth(String birthDate) {
    if (!birthDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
      throw new InputMismatchException("Date of birth is incorrect.");
    }
    String[] tokens = birthDate.split("-");
    this.birthDate = new GregorianCalendar(Integer.parseInt(tokens[2]), 
            Integer.parseInt(tokens[1]) - 1,
            Integer.parseInt(tokens[0]));
  }

  private void setGender(String gender) {
    if (gender.equals("M")) {
        this.gender = "Male";
    } else if (gender.equals("F")) {
        this.gender = "Female";
    } else {
      throw new InputMismatchException("Gender is incorrect.");
    }
  }

  private void setSalary(String salary) {
    try {
      int sal = new Integer(salary);
      if ((sal >= 100) && (sal <= 100000)) {
        this.salary = sal;
      } else {
        throw new InputMismatchException("Salary not between 100 .. 100000");
      }
    } catch (NumberFormatException e) {
      throw new InputMismatchException("Incorrect salary");
    }
  }

  private void setPhoneNumber(String phoneNumber) {
    if (phoneNumber.length() != 10) {
      throw new InputMismatchException("Incorrect phone Number");
    }
    for (int i = 0; i < 10; ++i) {
      if (!Character.isDigit(phoneNumber.charAt(i))) {
        throw new InputMismatchException("Symbol in phone Number not digital");
      }
    }
    this.phoneNumber = "+7 " + 
            phoneNumber.substring(0, 3) + "-" + 
            phoneNumber.substring(3, 6) + "-" + 
            phoneNumber.substring(6, 8) + "-" + 
            phoneNumber.substring(8, 10);
  }
  
  @Override
  public BusinessCard getBusinessCard(Scanner scanner) {
    String[] data = scanner.nextLine().split(";");
    if (data.length != 7) {
      throw new NoSuchElementException();
    }
    setFirstName(data[0]);
    setLastName(data[1]);
    setDepartment(data[2]);
    setDateOfBirth(data[3]);
    setGender(data[4]);
    setSalary(data[5]);
    setPhoneNumber(data[6]);
    return this;
  }

  @Override
  public String getEmployee() {
    return this.getFirstName() + " " + this.getLastName();
  }

  @Override
  public String getDepartment() {
    return this.department;
  }

  @Override
  public int getSalary() {
    return this.salary;
  }

  @Override
  public int getAge() {
    Calendar c2 = Calendar.getInstance();
    Calendar c1 = (Calendar)this.getBirthDate().clone();
    
    int count = 0;
    for (c1.add(Calendar.YEAR, 1); c1.compareTo(c2) <= 0; c1.add(Calendar.YEAR, 1)) {
      count++;
    }
    return count;    
  }

  @Override
  public String getGender() {
    return this.gender;
  }

  @Override
  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  
  
  private String getFirstName() {
    return this.firstName;
  }

  private String getLastName() {
    return this.lastName;
  }
  
  private Calendar getBirthDate() {
    return this.birthDate;
  }
  
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here
    BusinessCard bc = new BusinessCardImpl();
    bc.getBusinessCard(new Scanner(System.in));
    //Ilya;Petukhov;IBM;29-07-1993;M;3300;9251481942
    System.out.println(bc.getAge());
    System.out.println(bc.getGender());
    System.out.println(bc.getPhoneNumber());
  }
}
