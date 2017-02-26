/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks;

import java.util.List;
import java.util.regex.Pattern;

/**
 * ЦЕЛЬ ЗАДАЧИ - разобраться с регулярными выражениями и классами пакета java.util.regex.<br/>
 * <br/>
 * ЗАДАНИЕ<br/>
 * Реализовать проверку корректности имен, допустимых в языке PL/SQL, 
 * проверку корректности URLов и e-mail-ов,
 * нахождение соответствий (matches) в строке.<br/>
 * 
 * @author Alexey Vasiliev 
 */
public interface Checker {
	
  /**
   * Имена переменных в PL/SQL должны начинаться буквой латинского алфавита.<br/> 
   * Промежуточными символами могут быть буквы латинского алфавита, цифры, 
   *  знак подчеркивания ("_"), или знак доллара ("$").<br/>
   * Длина имени не должна превышать 30 символов.  
   * @return шаблон для имен в PL/SQL.
  */
  public Pattern getPLSQLNamesPattern();

  /**
   * Содержащиеся на web-странице URLы описываются тегом &lt;a href = ...&gt; (или &lt;a href=.../&gt;).<br/>
   *  Ремарка для начинающих: в HTML &gt; - это > (больше), &lt; - это < (меньше), а комментарии пишутся в таком
   *  "странном" виде, чтобы они корректно отображались в HTML, который из них генерируется через javadoc).<br/>
   *  То есть, следует читать: URLы описываются тегом <a href = ...> (или <a href=.../>).<br/>
   * Будем условно называть URLом закрытый или незакрытый тег a с обязательным атрибутом href,
   *  значение которого не должно содержать пробельных символов (см.ниже). <br/>
   * Заключать значение атрибута href в кавычки необязательно, но если использованы двойные кавычки,
   *  то в значении МОГУТ быть пробельные символы.<br/>
   * Имена тега A и атрибута HREF (как и другие имена в HTML) не чувствительны к регистру.<br/> 
   * Между символом меньше, именем тега, названием атрибута, знаком равно и символом больше 
   * могут быть следующие пробельные символы: 
   * табуляция, перевод строки, возврат каретки, перевод формата, пробел.<br/> 
   * @return шаблон для выделения содержащихся на web-странице URL-ов.
   * */
  public Pattern getHrefURLPattern();


  /**
   * e-mail имеет формат: <аккаунт>@<домен>.<домен_первого_уровня><br/>
   * <Аккаунт> должен быть длиной не более 22 символов и состоять из символов:
   *  латинские буквы, цифры, знак подчеркивания ("_"), точка ("."), дефис ("-").<br/> 
   * Аккаунт не может начинаться с символов дефис ("-"), точка (".") или знак подчеркивания ("_").<br/>
   * Аккаунт не может заканчиваться символом дефис ("-"), точка (".") или знак подчеркивания ("_"). <br/>
   * <Домен> может быть доменом любого уровня, каждый уровень отделяется от другого символом точка (".").
   * Название домена каждого уровня должно состоять более чем из одного символа, 
   * начинаться и заканчиваться буквой латинского алфавита или цифрой. <br/>
   * Промежуточными символами могут быть буквы латинского алфавита, цифры или дефис.<br/> 
   * <Домен первого уровня> - допустим один из следующих: .ru, .com, .net, .org.
   * @return шаблон для e-mail адресов.
   * */
  public Pattern getEMailPattern();
  /**
   * Метод проверяет соответствие inputString шаблону pattern.
   * @param inputString строка, подлежащая проверке.
   * @param pattern шаблон.
   * @throws IllegalArgumentException если только один из аргументов равен null.
   * @return true, если inputString соответствует шаблону pattern ИЛИ inputString и pattern являются null-ами.
   * */
  public boolean checkAccordance(String inputString, Pattern pattern) throws IllegalArgumentException;

  /**
   * Метод возвращает список всех соответствий в inputString шаблону pattern.
   * @param inputString строка для поиска
   * @param pattern шаблон поиска.
   * @throws IllegalArgumentException если хотя бы один из аргументов равен null.
   * @return Список всех соответствий, или пустой список, если соответствий нет. 
   * */
  public List<String> fetchAllTemplates(StringBuffer inputString, Pattern pattern) throws IllegalArgumentException;
}