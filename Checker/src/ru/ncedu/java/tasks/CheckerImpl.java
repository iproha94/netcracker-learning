/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks;

import java.util.regex.*;
import java.util.*;

/**
 *
 * @author Ilya
 */
public class CheckerImpl implements Checker {

  @Override
  public Pattern getPLSQLNamesPattern() {
    return Pattern.compile("\\p{Alpha}[\\w$]{0,29}");
  }

  @Override
  public Pattern getHrefURLPattern() {
    String patternStr = "<\\s*a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s\">]*)\\s*>";
    return Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
  }

  @Override
  public Pattern getEMailPattern() {
    String patternStr = "\\p{Alnum}[\\w.-]{0,20}\\p{Alnum}@(\\p{Alnum}[\\p{Alnum}w-]*\\p{Alnum}.)*(ru|com|net|org)";
    return Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
  }

  @Override
  public boolean checkAccordance(String inputString, Pattern pattern) throws IllegalArgumentException {
    if (inputString == null || inputString.length() == 0) {
      throw new IllegalArgumentException("null or empty filename");
    } else if (pattern == null) {
      throw new IllegalArgumentException("null pattern");
    }
    return pattern.matcher(inputString).matches();
  }

  @Override
  public List<String> fetchAllTemplates(StringBuffer inputString, Pattern pattern) throws IllegalArgumentException {
    if (inputString == null || inputString.length() == 0) {
      throw new IllegalArgumentException("null or empty filename");
    } else if (pattern == null) {
      throw new IllegalArgumentException("null pattern");
    }
    List<String> list = new ArrayList<>();
    Matcher matcher = pattern.matcher(inputString);
    while (matcher.find()) {
      int start = matcher.start();
      int end = matcher.end(); 
      String match = inputString.substring(start, end);
      list.add(match);
    }
    return list;
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here
    Checker ch = new CheckerImpl();
    System.out.println(ch.checkAccordance("<a href=\"www.g\"oogle.com\">", ch.getHrefURLPattern()));
  }
  
}
