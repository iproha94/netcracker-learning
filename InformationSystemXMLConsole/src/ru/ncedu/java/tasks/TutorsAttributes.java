/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks;

/**
 * Набор внутренних классов, которые унаследованы от TutorsAttribute </br>
 * Каждый класс - элемент элемента <tutor>
 *
 * @author Ilya
 */
public class TutorsAttributes {

  /**
   * идентификатор
   */
  static public class TUTORID extends TutorsAttribute {

    private int tutorId;

    private int getTutorId() {
      return tutorId;
    }

    //Overload
    static public String getNameAttribute() {
      return "tutor_id";
    }

    @Override
    public String getValueAttribute() {
      return Integer.toString(this.getTutorId());
    }

    @Override
    public void setValueAttribute(String value) {
      this.tutorId = new Integer(value);
    }

    //Overload
    public static int getMaxLength() {
      return 8;
    }
  };

  /**
   * фамилия
   */
  static public class LASTNAME extends TutorsAttribute {

    private String lastName;

    private String getLastName() {
      return lastName;
    }

    //Overload
    static public String getNameAttribute() {
      return "last_name";
    }

    @Override
    public String getValueAttribute() {
      return this.getLastName();
    }

    @Override
    public void setValueAttribute(String lastName) {
      this.lastName = lastName;
    }

    //Overload
    public static int getMaxLength() {
      return 15;
    }
  };

  static public class FIRSTNAME extends TutorsAttribute {

    private String firstName;

    private String getFirstName() {
      return firstName;
    }

    //Overload
    static public String getNameAttribute() {
      return "first_name";
    }

    @Override
    public String getValueAttribute() {
      return this.getFirstName();
    }

    @Override
    public void setValueAttribute(String firstName) {
      this.firstName = firstName;
    }

    //Overload
    public static int getMaxLength() {
      return 15;
    }
  };

  /**
   * Дата регистрации
   */
  static public class REGISTRATIONDATE extends TutorsAttribute {

    private int registrationDate;

    private int getRegistrationDate() {
      return registrationDate;
    }

    //Overload
    static public String getNameAttribute() {
      return "registration_date";
    }

    @Override
    public String getValueAttribute() {
      return Integer.toString(registrationDate);
    }

    //Overload
    public String getValueAttribute(String format) {
      String dd = Integer.toString(this.getRegistrationDate() % 100);
      String mm = Integer.toString(this.getRegistrationDate() % 10000 / 100);
      String yyyy = Integer.toString(this.getRegistrationDate() / 10000);
      return dd + '.' + mm + '.' + yyyy;
    }

    @Override
    public void setValueAttribute(String value) {
      this.registrationDate = new Integer(value);
    }

    //Overload
    public static int getMaxLength() {
      return 17;
    }
  };

  /**
   * id Преподаваемого предмета
   */
  static public class SUBJECTID extends TutorsAttribute {

    private int subjectId;

    private int getSubjectId() {
      return subjectId;
    }

    //Overload
    static public String getNameAttribute() {
      return "subject_id";
    }

    @Override
    public String getValueAttribute() {
      return Integer.toString(this.getSubjectId());
    }

    @Override
    public void setValueAttribute(String value) {
      this.subjectId = new Integer(value);
    }

    //Overload
    public static int getMaxLength() {
      return 10;
    }
  };

  /**
   * Кол-во проведенных занятий
   */
  static public class NUMBERTRAINING extends TutorsAttribute {

    private int numberTraining;

    public int getNumberTraining() {
      return numberTraining;
    }

    //Overload
    public static String getNameAttribute() {
      return "number_training";
    }

    @Override
    public String getValueAttribute() {
      return Integer.toString(this.getNumberTraining());
    }

    @Override
    public void setValueAttribute(String value) {
      this.numberTraining = new Integer(value);
    }

    //Overload
    public static int getMaxLength() {
      return 15;
    }
  };
};
