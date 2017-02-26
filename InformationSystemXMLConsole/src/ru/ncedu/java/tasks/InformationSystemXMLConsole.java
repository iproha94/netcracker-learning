package ru.ncedu.java.tasks;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;
import org.w3c.dom.*;

/**
 * программа для общения с xml документом
 *
 * @author Ilya
 */
public class InformationSystemXMLConsole {

  private Document doc;
  private String path;
  private String lastFind;

  InformationSystemXMLConsole() {
    this.doc = null;
    this.path = null;
    this.lastFind = "";
  }

  public InformationSystemXMLConsole(String path) {
    this.path = path;
    this.lastFind = "";
    this.doc = null;
  }

  public String getLastFind() {
    return this.lastFind;
  }

  /**
   * запоминает последний запрос на вывод таблицы
   * @param lastFind
   */
  public void setLastFind(String lastFind) {
    this.lastFind = lastFind;
  }

  public Document getDoc() {
    return this.doc;
  }

  public String getPath() {
    return this.path;
  }

  private void loadFromHDD() {
    try {
      File xmlFile = new File(this.getPath());
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      this.doc = (Document) builder.parse(xmlFile);
    } catch (ParserConfigurationException | SAXException | IOException ex) {
      Logger.getLogger(InformationSystemXMLConsole.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void saveInHDD() {
    try {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();

      transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // beautify result xml
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // beautify result xml

      DOMSource domSource = new DOMSource(doc); // in memory
      File xmlFile = new File(this.getPath());
      StreamResult result = new StreamResult(xmlFile); // at hdd

      transformer.transform(domSource, result);
    } catch (TransformerConfigurationException ex) {
      Logger.getLogger(InformationSystemXMLConsole.class.getName()).log(Level.SEVERE, null, ex);
    } catch (TransformerException ex) {
      Logger.getLogger(InformationSystemXMLConsole.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Выводит на экран элементы, имя+фамилия которых содержит @param str
   * @param str
   */
  public void find(String str) {
    this.loadFromHDD();
    str = str.trim().toLowerCase();
    NodeList nList = this.getDoc().getElementsByTagName(Tutor.getNameAttribute());
    System.out.println(Tutor.getStringAttributes());

    for (int i = 0; i < nList.getLength(); i++) {
      Tutor tutor = new Tutor();
      tutor.fill((Element) nList.item(i));

      if (tutor.getFullName().toLowerCase().contains(str)) {
        System.out.println(tutor.printAttributes());
      }
    }
  }

  /**
   * удаляет элементы по @param id
   * @param id
   * @return кол-во удаленных элементов
   */
  public int delete(String id) {
    NodeList nList = this.getDoc().getElementsByTagName(Tutor.getNameAttribute());
    int n = 0;
    for (int i = 0; i < nList.getLength(); i++) {
      Tutor tutor = new Tutor();
      tutor.fill((Element) nList.item(i));

      if (tutor.getTutorId().getValueAttribute().equals(id)) {
        nList.item(i).getParentNode().removeChild(nList.item(i));
        ++n;
      }
    }
    this.saveInHDD();
    return n;
  }

  private void add(Tutor tutor) {
    Element tutorId = doc.createElement(TutorsAttributes.TUTORID.getNameAttribute());
    tutorId.setTextContent(tutor.getTutorId().getValueAttribute());

    Element lastName = doc.createElement(TutorsAttributes.LASTNAME.getNameAttribute());
    lastName.setTextContent(tutor.getLastName().getValueAttribute());

    Element firstName = doc.createElement(TutorsAttributes.FIRSTNAME.getNameAttribute());
    firstName.setTextContent(tutor.getFirstName().getValueAttribute());

    Element registrationDate = doc.createElement(TutorsAttributes.REGISTRATIONDATE.getNameAttribute());
    registrationDate.setTextContent(tutor.getRegistrationDate().getValueAttribute());

    Element subjectId = doc.createElement(TutorsAttributes.SUBJECTID.getNameAttribute());
    subjectId.setTextContent(tutor.getSubjectId().getValueAttribute());

    Element numberTraining = doc.createElement(TutorsAttributes.NUMBERTRAINING.getNameAttribute());
    numberTraining.setTextContent(tutor.getNumberTraining().getValueAttribute());

    Element tutorEl = doc.createElement(Tutor.getNameAttribute());
    tutorEl.appendChild(tutorId);
    tutorEl.appendChild(lastName);
    tutorEl.appendChild(firstName);
    tutorEl.appendChild(registrationDate);
    tutorEl.appendChild(subjectId);
    tutorEl.appendChild(numberTraining);

    doc.getDocumentElement().appendChild(tutorEl);
    doc.normalize();
    this.saveInHDD();
  }

  /**
   * Добавляет элемент в таблицу по строке
   * В данный момент не проверяет наличие элементов с таким же id </br>
   * Сделать это если что не проблема, пробежаться по всем элементам и если  </br>
   * и если с таким id уже есть элементы, то return
   * @param strTutor = <id> <last_name> <first_name> <registration_date> <subject_id> <number_training>
   */
  public void add(String strTutor) {
    Tutor tutor = new Tutor();
    tutor.setValueAttribute(strTutor);
    this.add(tutor);
  }

  /**
   * Простое изменение.</br>
   * Элемент с указаным id заменяетя на указанный элемент
   * если такого элемента нет, то добавится новый
   * @param edtTutor
   */
  public void simpleEdit(String edtTutor) {
    int n = 0;
    this.delete(edtTutor.substring(0, edtTutor.indexOf(' ')));
    this.add(edtTutor);
  }

  public void printCommands() {
    System.out.println("Commands:");
    System.out.println("  add <id> <last_name> <first_name> <registration_date> <subject_id> <number_training>");
    System.out.println("\texample: \n\t\"add 77 Ivanov Ivan 20150510 1 67\"");

    System.out.println("  delete <id> ");
    System.out.println("\texample: \n\t\"delete 54\"");

    System.out.println("  edit <id> <last_name> <first_name> <registration_date> <subject_id> <number_training>");
    System.out.println("\texample: \n\t\"edit 77 Ivanov Ivan 20150510 1 67\"");

    System.out.println("  find <строка_для_поиска>");
    System.out.println("\texample: \n\t\"find ilya\" \n\t\"find\"");

    System.out.println("  exit");
  }

  /**
   * Меню программы. наверно можно сделать только его публичным, если </br>
   * имеется ввиду, что только с помощью этого метода можно управлять xml документом
   */
  public void start() {
    Scanner scan = new Scanner(System.in);
    String request = null;

    do {
      this.find(this.getLastFind());
      this.printCommands();
      System.out.print(">>");
      request = scan.nextLine();

      if (request.contains("add")) {
        this.add(request.replaceAll("add ", ""));
      } else if (request.contains("delete")) {
        int n = 0;
        n = this.delete(request.replaceAll("delete ", ""));
        System.out.println("deleted " + n + " of elements");
      } else if (request.contains("edit")) {
        this.simpleEdit(request.replaceAll("edit ", ""));
      } else if (request.contains("find")) {
        this.setLastFind(request.replaceAll("find", ""));
      } else if (!request.contains("exit")) {
        System.out.println("Command not found");
      }
    } while (!request.contains("exit"));

  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    InformationSystemXMLConsole tutorsxml = new InformationSystemXMLConsole("tutors.xml");
    tutorsxml.start();
  }
}
