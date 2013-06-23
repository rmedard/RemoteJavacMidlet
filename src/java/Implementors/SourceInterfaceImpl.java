/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementors;

import Entities.Source;
import Interfaces.SourceInterface;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Medard
 */
public class SourceInterfaceImpl implements SourceInterface {

    String mainRoot = "C:/FinalProject/AllUsers/";

    public String getMainRoot() {
        return mainRoot;
    }

    public Source createSource(Source source) {
        String rootDirectory = mainRoot.concat(source.getAuthor().getAuthor_username());
        boolean created = false;
        File rootFolder = new File(rootDirectory);
        Source src = null;

        if (!rootFolder.exists()) {
            created = rootFolder.mkdirs();
        }
        if (created || rootFolder.exists()) {
            File sourceFile = new File(rootFolder, source.getClassName());
            try {
                Writer writer = new FileWriter(sourceFile);
                writer.write(source.getSourceBody());
                writer.close();
                src = new Source(source.getClassName(), rootDirectory.concat("/" + source.getClassName()), rootFolder, source.getAuthor(), source.getSourceBody());
            } catch (IOException ex) {
                Logger.getLogger(SourceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return src;
    }

    public boolean updateSource(Source source) {
        String rootDir = mainRoot.concat(source.getAuthor().getAuthor_username());
        Source src = getSourceByClassName(rootDir, source.getClassName());
        File currentFile = new File(src.getLocation());
        boolean updated = false;

        if (currentFile.exists()) {
            try {
                FileOutputStream fos = new FileOutputStream(currentFile, false);
                fos.write(source.getSourceBody().getBytes());
                fos.close();
                //currentFile.renameTo(new File(rootDir.concat(java.io.File.separator + source.getClassName())));
                updated = true;
            } catch (IOException ex) {
                Logger.getLogger(SourceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return updated;
    }

    public boolean deleteSource(String rootDirectory, String className) {
        Source src = getSourceByClassName(rootDirectory, className);
        boolean deleted = new File(src.getLocation()).delete();
        if (deleted) {
            File objectClass = new File(src.getRoot().getPath().concat(className.substring(0, className.indexOf("."))).concat(".class"));
            if (objectClass.exists()) {
                objectClass.delete();
            }
        }
        return deleted;
    }

    public Source getSourceByClassName(String rootDirectory, String className) {
        Source source = null;
        String currentDirectory = rootDirectory.concat(java.io.File.separator + className);
        //String currentDirectory = rootDirectory.concat(className);
        File root = new File(rootDirectory);
        File currentFile = new File(currentDirectory);
        if (currentFile.exists()) {
            FileInputStream fstream = null;
            try {
                fstream = new FileInputStream(currentFile);
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String ls = System.getProperty("line.separator");
                String stringLine;
                StringBuilder stringBuilder = new StringBuilder();
                while ((stringLine = br.readLine()) != null) {
                    stringBuilder.append(stringLine);
                    stringBuilder.append(ls);
                }
                in.close();
                source = new Source(className, currentDirectory, root, stringBuilder.toString());
            } catch (IOException ex) {
                Logger.getLogger(SourceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fstream.close();
                } catch (IOException ex) {
                    Logger.getLogger(SourceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return source;
    }

    public String getSourceClassNamesByAuthor(String authorUsername) {

        File currentDirectory = new File(mainRoot.concat(authorUsername));
        File[] listOfFiles = currentDirectory.listFiles();
        String classNames = "";
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                if (listOfFile.getName().endsWith(".java")) {
                    classNames += listOfFile.getName().concat(java.io.File.pathSeparator);
                }
            }
        }
        return classNames;
    }

//    public static void main(String[] args) {
//
//        if (new AuthorInterfaceImpl().getAuthorByUsername("reber") != null) {
//            Source sr = new SourceInterfaceImpl().getSourceByClassName("C:/FinalProject/AllUsers/reber", "Helloworld.java");
//            if (sr == null) {
//                System.out.println("nadaaaa");
//            } else {
//                System.out.println(sr.getSourceBody());
//            }
//        }
//    }
}
