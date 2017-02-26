/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks;

import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 *
 * @author Ilya
 */
public class WordProcessorImpl implements WordProcessor {
  private String text;
  
  public WordProcessorImpl() {
    this.text = null;
  }
  
  @Override
  public String getText() {
    return this.text;
  }

  @Override
  public void setSource(String src) {
    if (src == null) {
      throw new IllegalArgumentException();
    }
    this.text = src;
  }

  @Override
  public void setSourceFile(String srcFile) throws IOException {
    if (srcFile == null) {
      throw new IllegalArgumentException();
    }
    FileInputStream fis = new FileInputStream(srcFile);
    this.setSource(fis);
    fis.close();
  }

  @Override
  public void setSource(FileInputStream fis) throws IOException {
    if (fis == null) {
      throw new IllegalArgumentException();
    }
    Scanner in = new Scanner(fis);
    String str = in.nextLine();
    while (in.hasNext()) {
      str += (in.nextLine() + "\n");
    }
    this.setSource(str);
    in.close();
  }

  @Override
  public Set<String> wordsStartWith(String begin) {
    if (this.getText() == null) {
      throw new IllegalStateException();
    }
//    if (begin == null) {
//      begin = "";
//    }
    Set<String> set = new TreeSet<>();
    Pattern pattern = Pattern.compile("\\p{Space}+", Pattern.CASE_INSENSITIVE);
    String[] tokens = pattern.split(this.getText().trim());
    Pattern pattern2 = Pattern.compile(begin + "[^\\p{Space}]+", Pattern.CASE_INSENSITIVE);
    for (String str: tokens) {
      if (pattern2.matcher(str).matches()) {
        set.add(str.toLowerCase());
      }
    }
    return set;
  }
  
  public static void main (String[] args){
    WordProcessor wp = new WordProcessorImpl();
    wp.setSource("hello Help\thead");
    System.out.println(wp.wordsStartWith(null));
  }
}

