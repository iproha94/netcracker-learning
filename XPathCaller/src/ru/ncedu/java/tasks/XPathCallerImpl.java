/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Ilya
 */
public class XPathCallerImpl implements XPathCaller{

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    try {
      // TODO code application logic here
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();   
      Document doc = builder.parse("emp.xml");
      XPathCaller xpathcaller = new XPathCallerImpl();
      //Element[] masel = xpathcaller.getEmployees(doc, "30",  "emp-hier");
      //Element[] masel = xpathcaller.getOrdinaryEmployees(doc,  "emp-hier");
      Element[] masel = xpathcaller.getCoworkers(doc, "7521",  "emp");
      //String str = xpathcaller.getHighestPayed(doc, "10", "emp-hier");
      //System.out.println(str);
      for (int i = 0; i < masel.length; ++i) {
        System.out.println(masel[i].getElementsByTagName("ename").item(0).getTextContent());
      }
    } catch (ParserConfigurationException ex) {
      Logger.getLogger(XPathCallerImpl.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SAXException ex) {
      Logger.getLogger(XPathCallerImpl.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(XPathCallerImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  private Element[] getEmployeesUsingCompileString(Document src, String docType, String compile) {
      try {
      XPathFactory factory = XPathFactory.newInstance();
      XPath xpath = factory.newXPath();
      XPathExpression expr = xpath.compile(compile);
      NodeList nodes = (NodeList) expr.evaluate(src, XPathConstants.NODESET);
      Element[] masel = new Element[nodes.getLength()];
      for (int i = 0; i < nodes.getLength(); i++) {
          masel[i] = (Element) nodes.item(i); 
      }
      return masel;
    } catch (XPathExpressionException ex) {
      Logger.getLogger(XPathCallerImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null; 
  } 
  
  public Element[] getEmployees(Document src, String docType) {
    return getEmployeesUsingCompileString(src, docType, "//employee");
  }

  @Override
  public Element[] getEmployees(Document src, String deptno, String docType) {
    return getEmployeesUsingCompileString(src, docType, "//employee[@deptno=" + deptno + "]");
  }

  @Override
  public String getHighestPayed(Document src, String docType) {
    Element[] masel = getEmployees(src, docType);
    double maxsal = 0;
    String nameEmpMaxSal = "";
    for (int i = 0; i < masel.length; ++i) {
      double cursal = Double.parseDouble(masel[i].getElementsByTagName("sal").item(0).getTextContent());
      if (maxsal < cursal) {
        maxsal = cursal;
        nameEmpMaxSal = masel[i].getElementsByTagName("ename").item(0).getTextContent();
      }
    }
    return nameEmpMaxSal;
  }

  @Override
  public String getHighestPayed(Document src, String deptno, String docType) {
    Element[] masel = getEmployees(src, deptno, docType);
    double maxsal = 0;
    String nameEmpMaxSal = "";
    for (int i = 0; i < masel.length; ++i) {
      double cursal = Double.parseDouble(masel[i].getElementsByTagName("sal").item(0).getTextContent());
      if (maxsal < cursal) {
        maxsal = cursal;
        nameEmpMaxSal = masel[i].getElementsByTagName("ename").item(0).getTextContent();
      }
    }
    return nameEmpMaxSal;
  }

  @Override
  public Element[] getTopManagement(Document src, String docType) {
    return getEmployeesUsingCompileString(src, docType, "//employee[not (@mgr)]");
  }

  @Override
  public Element[] getOrdinaryEmployees(Document src, String docType) {
    Element[] masel = getEmployees(src, docType);
    ArrayList<Element> ordinaryEmployees = new ArrayList<Element>();
    for (Element ordinary: masel) {
      boolean flag = true;
      for (Element employ: masel) {
        if (employ != ordinary && employ.getAttribute("mgr").equals(ordinary.getAttribute("empno"))) {
          flag = false;
          break;
        }
      }
      if (flag) {
        ordinaryEmployees.add(ordinary);
      }
    }
    Element[] ordinaryEmployeesArr = new Element[ordinaryEmployees.size()];
    return ordinaryEmployees.toArray(ordinaryEmployeesArr);
  }

  @Override
  public Element[] getCoworkers(Document src, String empno, String docType) {
    Element specifiedEmployee = getEmployeesUsingCompileString(src, docType, "//employee[@empno=" + empno + "]")[0];
    String managerEmpno = specifiedEmployee.getAttribute("mgr");
    return getEmployeesUsingCompileString(src, docType, "//employee[@empno!=" + empno + "][@mgr=" + managerEmpno + "]");
  }
}
