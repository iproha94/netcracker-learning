/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.logging.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Ilya
 */


public class SimpleXMLImpl implements SimpleXML {

  @Override
  public String createXML(String tagName, String textNode) {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setNamespaceAware(true);
      Document doc = factory.newDocumentBuilder().newDocument();
      
      Element root = doc.createElement(tagName);
      root.appendChild(doc.createTextNode(textNode));
      doc.appendChild(root);
      
      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
      StringWriter writer = new StringWriter();
      transformer.transform(new DOMSource(doc), new StreamResult(writer));
      
      return writer.toString();
    } catch (ParserConfigurationException ex) {
      Logger.getLogger(SimpleXMLImpl.class.getName()).log(Level.SEVERE, null, ex);
    } catch (TransformerException ex) {
      Logger.getLogger(SimpleXMLImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }
  
  @Override
  @SuppressWarnings("empty-statement")
  public String parseRootElement(InputStream xmlStream) throws SAXException {
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      
      factory.setValidating(true);
      factory.setNamespaceAware(false);
      SAXParser parser  = factory.newSAXParser();
      
      class MyHandler extends DefaultHandler {
        private String rootName;
        
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
          if (rootName == null)
            rootName = qName;
        }
        
        public String getRootName() {
          return rootName;
        }
      };
      
      MyHandler handler = new MyHandler();

      parser.parse(xmlStream, handler);
      return handler.getRootName();
    } catch (ParserConfigurationException ex) {
      Logger.getLogger(SimpleXMLImpl.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(SimpleXMLImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  /**
   * @param args the command line arguments
   * @throws java.io.FileNotFoundException
   * @throws org.xml.sax.SAXException
   */
  public static void main(String[] args) throws FileNotFoundException, SAXException {
    // TODO code application logic here
//    SimpleXML sxml = new SimpleXMLImpl();
//    String str = sxml.createXML("dota", "<R&D>");
//    System.out.println(str);
//    InputStream xmlData = new FileInputStream("books.xml");
//    System.out.println(sxml.parseRootElement(xmlData));
  }
}
