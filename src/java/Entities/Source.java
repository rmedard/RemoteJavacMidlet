package Entities;


import java.io.File;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Medard
 */
public class Source {

    private String className; // fileName.java
    private String location; // root + className
    private File root; //Folder - fileName.java
    private Author author;
    private Class<?> objectClass;
    private String sourceBody;

    public Source() {
    }
             //for testing
    public Source(String className, String location, File root, String sourceBody) {
        this.className = className;
        this.location = location;
        this.root = root;
        this.sourceBody = sourceBody;
    }

        //Creating source on server side, before compilation
    public Source(String className, String location, File root, Author author, String sourceBody) {
        this.className = className;
        this.location = location;
        this.root = root;
        this.author = author;
        this.sourceBody = sourceBody;
    }
        //Creating source on server side, after compilation
    public Source(String className, String location, File root, Author author, Class<?> objectClass, String sourceBody) {
        this.className = className;
        this.location = location;
        this.root = root;
        this.author = author;
        this.objectClass = objectClass;
        this.sourceBody = sourceBody;
    }       
        //Create source on client side for temporary use
    public Source(String className, Author author, String sourceBody) {
        this.className = className;
        this.author = author;
        this.sourceBody = sourceBody;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public File getRoot() {
        return root;
    }

    public void setRoot(File root) {
        this.root = root;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Class<?> getObjectClass() {
        return objectClass;
    }

    public void setObjectClass(Class<?> objectClass) {
        this.objectClass = objectClass;
    }

    public String getSourceBody() {
        return sourceBody;
    }

    public void setSourceBody(String sourceBody) {
        this.sourceBody = sourceBody;
    }

}
