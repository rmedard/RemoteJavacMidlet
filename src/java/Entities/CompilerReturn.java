package Entities;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Medard
 */
public class CompilerReturn {

    private Class<?> classObject;
    private String compilationError;

    public CompilerReturn() {
    }

    public CompilerReturn(Class<?> classObject, String compilationError) {
        this.classObject = classObject;
        this.compilationError = compilationError;
    }

    public Class<?> getClassObject() {
        return classObject;
    }

    public void setClassObject(Class<?> classObject) {
        this.classObject = classObject;
    }

    public String getCompilationError() {
        return compilationError;
    }

    public void setCompilationError(String compilationError) {
        this.compilationError = compilationError;
    }
}
