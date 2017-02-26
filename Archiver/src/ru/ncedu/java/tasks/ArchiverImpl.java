/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author Ilya
 */
public class ArchiverImpl implements Archiver{

  private File zfile = null;

  public ArchiverImpl(String path) {
    zfile = new File(path);
  }

  public ArchiverImpl() {
    zfile = null;
  }

  @Override
  public File getZFile() {
    return zfile;
  }

  @Override
  public void setZFile(String path) {
    zfile = new File(path);
  }

  @Override
  public void archiveFilesInArchive(String fileZipName, ArrayList<String> filenames) throws FileNotFoundException {
    this.setZFile(fileZipName);
    archiveFilesInArchive(filenames);
  }

  @Override
  public void archiveFilesInArchive(ArrayList<String> filenames) throws FileNotFoundException {
    if (this.getZFile() == null) {
      throw new FileNotFoundException();
    }

    try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(this.getZFile()));) {
      for (String fileName : filenames) {
        File file = new File(fileName);
        this.addingFileInArchive(zout, file);
      }
    } catch (IOException ex) {
      Logger.getLogger(Archiver.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Добавляет в зип архив файл
   * @param zout зип архив
   * @param file добавляесый файл
   */
  private void addingFileInArchive(ZipOutputStream zout, File file) {
    if (file.isDirectory()) {
      addingDirectoryInArchive(zout, file);
      return;
    }

    ZipEntry entry = new ZipEntry(file.getPath());
    try (InputStream fis = new FileInputStream(file);) {
      zout.putNextEntry(entry);

      byte[] buffer = new byte[fis.available()];
      fis.read(buffer);
      zout.write(buffer);

      zout.closeEntry();
    } catch (IOException ex) {
      Logger.getLogger(Archiver.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  /**
   * Добавляет директорию и файлы внутри неё в зип файл
   * @param zout зип файл
   * @param dirfile директория
   */
  private void addingDirectoryInArchive(ZipOutputStream zout, File dirfile) {
    try {
      ZipEntry entry = new ZipEntry(dirfile.getPath().concat("/"));
      zout.putNextEntry(entry);
      zout.closeEntry();
    } catch (IOException ex) {
      Logger.getLogger(Archiver.class.getName()).log(Level.SEVERE, null, ex);
    }

    for (File file : dirfile.listFiles()) {
      addingFileInArchive(zout, file);
    }
  }

  @Override
  public void unzipFilesOutArchive(String fileZipName) throws FileNotFoundException {
    this.setZFile(fileZipName);
    this.unzipFilesOutArchiveWithDir(System.getProperty("user.dir"));
  }

  @Override
  public void unzipFilesOutArchive() throws FileNotFoundException {
    this.unzipFilesOutArchiveWithDir(System.getProperty("user.dir"));
  }

  @Override
  public void unzipFilesOutArchiveWithDir(String fileZipName, String outdir) throws FileNotFoundException {
    this.setZFile(fileZipName);
    this.unzipFilesOutArchiveWithDir(outdir);
  }

  @Override
  public void unzipFilesOutArchiveWithDir(String outdir) throws FileNotFoundException {
    if (this.getZFile() == null) {
      throw new FileNotFoundException();
    }

    try (ZipInputStream zin = new ZipInputStream(new FileInputStream(this.getZFile()));) {
      this.unzipFiles(zin, outdir);
    } catch (IOException ex) {
      Logger.getLogger(Archiver.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Разархивирует зипфаайл
   * @param zin какой архив разархивируем
   * @param outdir куда архививровать
   */
  private void unzipFiles(ZipInputStream zin, String outdir) {
    try {
      byte[] Unzipbuffer = new byte[2048];
      ZipEntry entry = null;

      while ((entry = zin.getNextEntry()) != null) {
        File newfile = new File(outdir + entry.getName());
        if (entry.isDirectory()) {
          newfile.mkdirs();
          zin.closeEntry();
          continue;
        }

        try (FileOutputStream fout = new FileOutputStream(newfile);) {
          int Unziplength = 0;
          while ((Unziplength = zin.read(Unzipbuffer)) > 0) {
            fout.write(Unzipbuffer, 0, Unziplength);
          }
        } catch (IOException ex) {
          Logger.getLogger(Archiver.class.getName()).log(Level.SEVERE, null, ex);
        }

        zin.closeEntry();
      }
    } catch (IOException ex) {
      Logger.getLogger(Archiver.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
//    try {
      if (args.length < 1) {
        throw new IllegalArgumentException();
      }
      
//      //архивация данных. Параметры через аргументы команднйо строки
//      //args[0] - имя архива
//      //args[1..] - файлы и диркетории, которые запаковываем
//      Archiver archiver = new ArchiverImpl(args[0]);
//      ArrayList<String> filenames = new ArrayList<>(Arrays.asList(args));
//      filenames.remove(0);
//      archiver.archiveFilesInArchive(filenames);
      
//      //разархивация данных. Параметры через аргументы командной строки
//      //args[0] - имя архива
//      //args[1] - куда разархивировать
//      Archiver archiver = new ArchiverImpl(args[0]);
//      archiver.unzipFilesOutArchiveWithDir(args[1]);
      
      
      
//    } catch (FileNotFoundException ex) {
//      Logger.getLogger(Archiver.class.getName()).log(Level.SEVERE, null, ex);
//    }
  }
}
