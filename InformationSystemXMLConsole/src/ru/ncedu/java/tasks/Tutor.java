/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks;

import org.w3c.dom.Element;

/**
 * Так же наследует TutorsAttribute, но включает в себя другие элементы,
 * унаследованные от TutorsAttribute и определенные в TutorsAttributes
 *
 * @author Ilya
 */
public class Tutor extends TutorsAttribute {

  private final TutorsAttribute tutorId;
  private final TutorsAttribute lastName;
  private final TutorsAttribute firstName;
  private final TutorsAttribute registrationDate;
  private final TutorsAttribute subjectId;
  private final TutorsAttribute numberTraining;

  public TutorsAttribute getTutorId() {
    return tutorId;
  }

  public TutorsAttribute getLastName() {
    return lastName;
  }

  public TutorsAttribute getFirstName() {
    return firstName;
  }

  public TutorsAttribute getRegistrationDate() {
    return registrationDate;
  }

  public TutorsAttribute getSubjectId() {
    return subjectId;
  }

  public TutorsAttribute getNumberTraining() {
    return numberTraining;
  }

  public Tutor() {
    this.tutorId = new TutorsAttributes.TUTORID();
    this.lastName = new TutorsAttributes.LASTNAME();
    this.firstName = new TutorsAttributes.FIRSTNAME();
    this.registrationDate = new TutorsAttributes.REGISTRATIONDATE();
    this.subjectId = new TutorsAttributes.SUBJECTID();
    this.numberTraining = new TutorsAttributes.NUMBERTRAINING();
  }

  //Overload
  static public String getNameAttribute() {
    return "tutor";
  }

  @Override
  public String getValueAttribute() {
    return TutorsAttributes.TUTORID.getNameAttribute() + " = " + this.tutorId.getValueAttribute() + " "
            + TutorsAttributes.LASTNAME.getNameAttribute() + " = " + this.lastName.getValueAttribute() + " "
            + TutorsAttributes.FIRSTNAME.getNameAttribute() + " = " + this.firstName.getValueAttribute() + " "
            + TutorsAttributes.REGISTRATIONDATE.getNameAttribute() + " = " + this.registrationDate.getValueAttribute() + " "
            + TutorsAttributes.SUBJECTID.getNameAttribute() + " = " + this.subjectId.getValueAttribute() + " "
            + TutorsAttributes.NUMBERTRAINING.getNameAttribute() + " = " + this.numberTraining.getValueAttribute();
  }

  @Override
  public String toString() {
    return Tutor.getNameAttribute() + "{"
            + this.getValueAttribute() + "}";
  }

  /**
   * @return форматная строка, для функции printf, для вывода всех атрибутов
   */
  public static String getFormatStringAttributes() {
    return "%" + TutorsAttributes.TUTORID.getMaxLength() + "s "
            + "%" + TutorsAttributes.LASTNAME.getMaxLength() + "s "
            + "%" + TutorsAttributes.FIRSTNAME.getMaxLength() + "s "
            + "%" + TutorsAttributes.REGISTRATIONDATE.getMaxLength() + "s "
            + "%" + TutorsAttributes.SUBJECTID.getMaxLength() + "s "
            + "%" + TutorsAttributes.NUMBERTRAINING.getMaxLength() + "s ";
  }

  /**
   * @return строку с названиями атрибутов в виде стандартной форматной строки
   */
  public static String getStringAttributes() {
    return String.format(Tutor.getFormatStringAttributes(),
            TutorsAttributes.TUTORID.getNameAttribute(),
            TutorsAttributes.LASTNAME.getNameAttribute(),
            TutorsAttributes.FIRSTNAME.getNameAttribute(),
            TutorsAttributes.REGISTRATIONDATE.getNameAttribute(),
            TutorsAttributes.SUBJECTID.getNameAttribute(),
            TutorsAttributes.NUMBERTRAINING.getNameAttribute());
  }

  /**
   * строку с значениями атрибутов в виде стандартной форматной строки
   *
   * @return
   */
  public String printAttributes() {
    return String.format(Tutor.getFormatStringAttributes(),
            this.getTutorId().getValueAttribute(),
            this.getLastName().getValueAttribute(),
            this.getFirstName().getValueAttribute(),
            this.getRegistrationDate().getValueAttribute(""),
            this.getSubjectId().getValueAttribute(),
            this.getNumberTraining().getValueAttribute());
  }

  public String getFullName() {
    return this.getFirstName().getValueAttribute() + " " + this.getLastName().getValueAttribute();
  }

  /**
   * Заполняет объект значениями из @param el
   * @param el полученый из xml файла
   */
  public void fill(Element el) {
    this.getTutorId().setValueAttribute(el.getElementsByTagName(TutorsAttributes.TUTORID.getNameAttribute()).item(0).getTextContent());
    this.getLastName().setValueAttribute(el.getElementsByTagName(TutorsAttributes.LASTNAME.getNameAttribute()).item(0).getTextContent());
    this.getFirstName().setValueAttribute(el.getElementsByTagName(TutorsAttributes.FIRSTNAME.getNameAttribute()).item(0).getTextContent());
    this.getRegistrationDate().setValueAttribute(el.getElementsByTagName(TutorsAttributes.REGISTRATIONDATE.getNameAttribute()).item(0).getTextContent());
    this.getSubjectId().setValueAttribute(el.getElementsByTagName(TutorsAttributes.SUBJECTID.getNameAttribute()).item(0).getTextContent());
    this.getNumberTraining().setValueAttribute(el.getElementsByTagName(TutorsAttributes.NUMBERTRAINING.getNameAttribute()).item(0).getTextContent());
  }

  @Override
  public void setValueAttribute(String str) {
    String[] atrArr = str.split(" ");
    this.getTutorId().setValueAttribute(atrArr[0]);
    this.getLastName().setValueAttribute(atrArr[1]);
    this.getFirstName().setValueAttribute(atrArr[2]);
    this.getRegistrationDate().setValueAttribute(atrArr[3]);
    this.getSubjectId().setValueAttribute(atrArr[4]);
    this.getNumberTraining().setValueAttribute(atrArr[5]);
  }
};
