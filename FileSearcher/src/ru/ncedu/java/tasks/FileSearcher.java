/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ilya
 */
public class FileSearcher {

  //File information will be displayed in the same order 
  //as the constants in enum, if used treeSet<KeysCL>
  enum KeysCL {
    TYPE {
              private String getFormatString(String str) {
                return String.format(" %5s", str);
              }

              @Override
              public String getTruncation() {
                return "-tp ";
              }

              private String getFileTypeInfo(File file) {
                return file.isDirectory() ? "dir" : "file";
              }
              
              @Override
              public String getInfoFile(File file) {
                return getFormatString(getFileTypeInfo(file));
              }
            },
    TIME_LAST_MODIFIED {
              private String getFormatString(String str) {
                return String.format(" %10s", str);
              }

              @Override
              public String getTruncation() {
                return "-tm ";
              }

              private String getFileTimeLastModifiedInfo(File file) {
                Locale local = new Locale("ru", "RU");
                DateFormat tf = DateFormat.getTimeInstance(DateFormat.DEFAULT, local);
                return tf.format(new Date(file.lastModified()));
              }
              
              @Override
              public String getInfoFile(File file) {
                return getFormatString(getFileTimeLastModifiedInfo(file));
              }
            },
    DATA_LAST_MODIFIED {
              private String getFormatString(String str) {
                return String.format(" %10s", str);
              }

              @Override
              public String getTruncation() {
                return "-d ";
              }

              private String getFileDataLastModifiedInfo(File file) {
                Locale local = new Locale("ru", "RU");
                DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT, local);
                return df.format(new Date(file.lastModified()));
              }
              
              @Override
              public String getInfoFile(File file) {
                return getFormatString(getFileDataLastModifiedInfo(file));
              }
            },
    LENGTH {
              private String getFormatString(String str) {
                return String.format(" %5s", str);
              }

              @Override
              public String getTruncation() {
                return "-l ";
              }

              private String getFileLengthInfo(File file) {
                return Long.toString(file.length());
              }
              
              @Override
              public String getInfoFile(File file) {
                return getFormatString(getFileLengthInfo(file));
              }
            },
    NAME {
              private String getFormatString(String str) {
                return String.format(" %15s", str);
              }

              @Override
              public String getTruncation() {
                return "-n ";
              }

              private String getFileNameInfo(File file) {
                return file.getName();
              }
              
              @Override
              public String getInfoFile(File file) {
                return getFormatString(getFileNameInfo(file));
              }
            },
    PARENT {
              private String getFormatString(String str) {
                return String.format(" %s", str);
              }

              @Override
              public String getTruncation() {
                return "-p ";
              }

              private String getFileParentInfo(File file) {
                return file.getParent();
              }
              
              @Override
              public String getInfoFile(File file) {
                return getFormatString(getFileParentInfo(file));
              }
            };

    public abstract String getInfoFile(File file);
    public abstract String getTruncation();
  };

  public void searchFile(String dirName, String fileName, String strKeys) {
    Set<KeysCL> arrKeys = stringToSetKeysCL(strKeys);
    Pattern pattern = Pattern.compile(fileName);
    goSearchFile(dirName, pattern, arrKeys);
  }

  private Set<KeysCL> stringToSetKeysCL(String strKeys) {
    Set<KeysCL> arrKeys = new TreeSet();
    for (KeysCL keys : KeysCL.values()) {
      if (strKeys.contains(keys.getTruncation())) {
        arrKeys.add(keys);
      }
    }
    return arrKeys;
  }

  private void goSearchFile(String dirName, Pattern patternFileName, Set<KeysCL> arrKeys) {
    Matcher matcher;

    for (File file : new File(dirName).listFiles()) {
      matcher = patternFileName.matcher(file.getName());

      if (matcher.matches()) {
        System.out.println(getFileInfo(file, arrKeys));
      }

      if (file.isDirectory()) {
        goSearchFile(file.getAbsolutePath(), patternFileName, arrKeys);
      }
    }
  }

  private String getFileInfo(File file, Set<KeysCL> arrKeys) {
    String strInfoFile = "";
    for (KeysCL key : arrKeys) {
      strInfoFile += key.getInfoFile(file);
    }
    return strInfoFile;
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here
    if (args.length < 1) {
      System.out.println("Too few arguments");
      return;
    }

    String fileName = args[0];
    String startDir = System.getProperty("user.dir");

    //standart parametrs(type, name, dir)
    String strKeys = "-tp -n -p ";
    //array strings to string.
    for (int i = 1; i < args.length; ++i) {
      strKeys = strKeys + args[i].concat(" ");
    }

    FileSearcher fs = new FileSearcher();
    fs.searchFile(startDir, fileName, strKeys);
  }
}
