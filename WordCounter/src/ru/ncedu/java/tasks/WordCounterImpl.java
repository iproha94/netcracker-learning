/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.Comparator;
import java.util.Collections;
import java.util.regex.Matcher;
 
/**
 *
 * @author Ilya
 */
public class WordCounterImpl implements WordCounter {
  private String text;
  
  public WordCounterImpl() {
    text = null;
  }
  
  @Override
  public void setText (String text) {
    this.text = text;
  }
  
  @Override
  public String getText () {
    return text;
  }
  
  private String deleteComments(String str) {
    Pattern pattern = Pattern.compile("<[^><]*>");
    Matcher matcher = pattern.matcher(str);
    String output = matcher.replaceAll("");
    if (!str.equals(output)) {
      return deleteComments(output);
    }else{
      return output;
    }
  }
  
  @Override
  public Map<String, Long> getWordCounts() {
    if (this.getText() == null || this.getText().equals("")) {
      throw new IllegalStateException();
    }
    String output = deleteComments(this.getText().trim());
    Pattern pattern = Pattern.compile("\\p{Space}+");
    String[] tokens = pattern.split(output);

    Map<String, Long> map = new TreeMap<>();
    for (String str: tokens) {
      String strLower = str.toLowerCase();
      Long count = map.get(strLower);
      if (count != null && count != 0) {
        map.remove(strLower);
        map.put(strLower, ++count);
      } else {
        map.put(strLower, new Long(1));
      }
    }
    return map;
  }
  
  @Override
  public List<Map.Entry<String, Long>> getWordCountsSorted () {
    if (this.getText() == null || this.getText().equals("")) {
      throw new IllegalStateException();
    }
    return this.sortWordCounts(this.getWordCounts());
  }
  
  @Override
  public List<Map.Entry<String, Long>> sortWordCounts (Map<String, Long> orig) {
    if (orig == null) {
      return null;
    }
    List<Map.Entry<String, Long>> list = new ArrayList<>(orig.entrySet());
    Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {
      @Override
      public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
        return o2.getValue().compareTo(o1.getValue());
      }
    });
    return list;
  }
  
  @Override
  public void printWordCounts (PrintStream ps) {
    Map<String, Long> map = this.getWordCounts();
    if (map == null) {
      return;
    }
    for(Map.Entry<String, Long> item : map.entrySet()){
      ps.println(item.getValue() + " " + item.getKey());
    }
  }
  
  @Override
  public void printWordCountsSorted (PrintStream ps) {
    List<Map.Entry<String, Long>> list = this.getWordCountsSorted();
    if (list == null) {
      return;
    }
    for(Map.Entry<String, Long> item : list){
      ps.println(item.getValue() + " " + item.getKey());
    }
  }
  
  public static void main(String[] args) {
    //String str = "  Hello < speak <   hel <l > o > I > <w > hy  ";
    String str = null;

    WordCounter text = new WordCounterImpl();
    text.setText(str);
    text.printWordCounts(System.out);
  }
}
