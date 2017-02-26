/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks;

/**
 * "Интерфейс любого объекта - елумента xml файла"
 * @author Ilya
 */
public abstract class TutorsAttribute {
  
  /**
   * устанавливает значение атрибута, данный в строковом виде
   * @param value 
   */
  public abstract void setValueAttribute(String value);
  
  /**
   * Возвращает длину строки атрибута. Нужно для вывода функцией printf 
   * @return
   */
  public static int getMaxLength() {
    throw new NullPointerException();
  }
  
  /**
   * Возвращает значение атрибута в строковом виде 
   * @return 
   */
  public abstract String getValueAttribute();
  
  /**
   * Возвращает значение атрибута в строковом виде в указанном формате</br>
   * Метод не абстрактный, так как не обязательно должен быть реализован
   * @param format 
   * @return
   */
  public String getValueAttribute(String format) {
    throw new NullPointerException();
  }
    
  /**
   * Возвращает имя атрибута, т.е. его назыание в тегах xml документа
   * @return
   */
  public static String getNameAttribute() {
    throw new NullPointerException();
  }
}
