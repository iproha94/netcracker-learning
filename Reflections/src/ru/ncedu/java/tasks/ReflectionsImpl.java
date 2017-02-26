/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks;

import java.lang.reflect.*;
import java.util.List;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ilya
 */
public class ReflectionsImpl implements Reflections{

  @Override
  public Object getFieldValueByName(Object object, String fieldName) throws NoSuchFieldException {
    try {
      Class<?> c = object.getClass();
      Field field = c.getDeclaredField(fieldName);
      field.setAccessible(true);
      return field.get(object);
    } catch (NoSuchFieldException e) {
      throw new NoSuchFieldException(); 
    } catch (IllegalAccessException e) {
      throw new IllegalStateException("Access error", e); 
    }
  }

  @Override
  public Set<String> getProtectedMethodNames(Class clazz) {
    Set<String> set = new TreeSet<>();
    Method[] methods = clazz.getDeclaredMethods();
    for (Method method : methods) {
      if (method.getModifiers() == Modifier.PROTECTED) {
        set.add(method.getName());
      }
    }
    return set;
  }

  @Override
  public Set<Method> getAllImplementedMethodsWithSupers(Class clazz) {
    if (clazz == null) {
      throw new NullPointerException();
    }
    Set<Method> set = new LinkedHashSet<>();
    while (clazz != null) {
      set.addAll(Arrays.asList(clazz.getDeclaredMethods()));
      clazz = clazz.getSuperclass();
    } 
    return set;
  }

  @Override
  public List<Class> getExtendsHierarchy(Class clazz) {
    List<Class> set = new ArrayList<>();
    clazz = clazz.getSuperclass();
    while (clazz != null) {
      set.add(clazz);
      clazz = clazz.getSuperclass();
    } 
    return set;
  }

  @Override
  public Set<Class> getImplementedInterfaces(Class clazz) {
    Set<Class> interfaces  = new HashSet<>();
    interfaces.addAll(Arrays.asList(clazz.getInterfaces()));
    return interfaces;
  }

  @Override
  public List<Class> getThrownExceptions(Method method) {
    List<Class> exceptions = new ArrayList<>();
    Collections.addAll(exceptions, method.getExceptionTypes());
    //exceptions.addAll(method.getExceptionTypes());
    return exceptions;
  }

  @Override
  public String getFooFunctionResultForDefaultConstructedClass() {
    try {
      Class<?> clazz = Class.forName("ru.ncedu.java.tasks.Reflections");
      clazz = clazz.getClasses()[0];
      Constructor constructor = clazz.getDeclaredConstructor(new Class<?>[0]);
      constructor.setAccessible(true);
      Object secretClass = constructor.newInstance(new Object[0]);
      Method method = clazz.getDeclaredMethod("foo", new Class<?>[0]);
      method.setAccessible(true);
      return (String)method.invoke(secretClass, new Object[0]);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(ReflectionsImpl.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(ReflectionsImpl.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(ReflectionsImpl.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NoSuchMethodException ex) {
      Logger.getLogger(ReflectionsImpl.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SecurityException ex) {
      Logger.getLogger(ReflectionsImpl.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalArgumentException ex) {
      Logger.getLogger(ReflectionsImpl.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvocationTargetException ex) {
      Logger.getLogger(ReflectionsImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  @Override
  public String getFooFunctionResultForClass(String constructorParameter, String string, Integer... integers) {
    try {
      Class<?> clazz = Class.forName("ru.ncedu.java.tasks.Reflections");
      clazz = clazz.getClasses()[0];
      Constructor constructor = clazz.getDeclaredConstructor(new Class<?>[]{constructorParameter.getClass()});
      constructor.setAccessible(true);
      Object secretClass = constructor.newInstance(new Object[]{constructorParameter});
      Method method = clazz.getDeclaredMethod("foo", new Class<?>[]{string.getClass(), integers.getClass()});
      method.setAccessible(true);
      return (String)method.invoke(secretClass, new Object[]{string, integers});
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(ReflectionsImpl.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NoSuchMethodException ex) {
      Logger.getLogger(ReflectionsImpl.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SecurityException ex) {
      Logger.getLogger(ReflectionsImpl.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(ReflectionsImpl.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(ReflectionsImpl.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalArgumentException ex) {
      Logger.getLogger(ReflectionsImpl.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvocationTargetException ex) {
      Logger.getLogger(ReflectionsImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here
    Reflections r = new ReflectionsImpl();
    System.out.println(r.getFooFunctionResultForClass("ConstStr", "Summa", new Integer[]{10,20,30,40}));
  }
}
