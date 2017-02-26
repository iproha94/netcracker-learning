/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Класс - обертка для файла, позволяющий создавать на его основе зип файл
 * и разархивировать файл</br>
 * Не поддерживает русские символы
 * @author Ilya
 */
public interface Archiver {
  
  /**
   * Устанавливает файл по имени
   * @param path  имя файла
   */
  public void setZFile(String path);
  
  /**
   * 
   * @return файл, являющийся полем класса
   */
  public File getZFile();

  /**
   * Добавляет файлы и директории в указываемый файл(создаст при необходимости)
   * @param fileZipName имя будущего архива
   * @param filenames массив строк имен файлов(директорий), которые нужно запоковать
   * @throws FileNotFoundException вызывается это исключение, если поле класса(файл)
   * является null 
   */
  public void archiveFilesInArchive(String fileZipName, ArrayList<String> filenames) throws FileNotFoundException;

  /**
   * Добавляет файлы и директории в файл, являющийся полем класса
   * @param filenames тоже, что и в  {@link #archiveFilesInArchive(String , ArrayList<String>)}
   * @throws FileNotFoundException тоже, что и в {@link #archiveFilesInArchive(String , ArrayList<String>)}
   */
  public void archiveFilesInArchive(ArrayList<String> filenames) throws FileNotFoundException;

  /**
   * Разархивирует файл
   * @param fileZipName имя зип файла
   * @throws FileNotFoundException если файл не найден
   */
  public void unzipFilesOutArchive(String fileZipName) throws FileNotFoundException;

  /**
   * Разархивирует файл, являющийся полем класса
   * @throws FileNotFoundException тоже, что и в {@link #unzipFilesOutArchive(String)}
   */
  public void unzipFilesOutArchive() throws FileNotFoundException;

  /**
   * Разархивирует указаный файл в указанную директорию
   * @param fileZipName имя зип архива
   * @param outdir имя директории
   * @throws FileNotFoundException  тоже, что и в {@link #unzipFilesOutArchive(String)}
   */
  public void unzipFilesOutArchiveWithDir(String fileZipName, String outdir) throws FileNotFoundException;

  /**
   * Разархивирует файл, являющийся полем класса, в указаную директорию
   * @param outdir  тоже, что и в {@link #unzipFilesOutArchiveWithDir(String , String )}
   * @throws FileNotFoundException   тоже, что и в {@link #unzipFilesOutArchive(String)}
   */
  public void unzipFilesOutArchiveWithDir(String outdir) throws FileNotFoundException;

}
