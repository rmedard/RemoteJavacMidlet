/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementors;

import Entities.CompilerReturn;
import Entities.ExecutionReturn;
import Entities.Source;
import Interfaces.ExecuteInterface;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Medard
 */
public class ObjectClassExecutor implements ExecuteInterface {

    private Method[] allMethods;
    private Object arg[] = new Object[1];
    private String out = "";
    private ByteArrayOutputStream baosSuccess = new ByteArrayOutputStream();
    private ByteArrayOutputStream baosError = new ByteArrayOutputStream();
    private PrintStream StringOutSuccess = new PrintStream(baosSuccess);
    private PrintStream StringOutError = new PrintStream(baosError);

    public ExecutionReturn executeFile(Source source) {
        System.setOut(StringOutSuccess);
        System.setErr(StringOutError);
        ExecutionReturn executionReturn = new ExecutionReturn(false);
        arg[0] = null;
        try {

          //  Object instance = objectClass.newInstance();
            CompilerReturn compilerReturn = new Implementors().getCompileInterface().compile(source);
           // Object instance = source.getObjectClass().newInstance();
            Class<?> objectClass = compilerReturn.getClassObject();
            Object instance = objectClass.newInstance();
            allMethods = objectClass.getMethods();
            for (Method method : allMethods) {
                if (method.getName().equals("main")) {
                    try {
                        method.invoke(instance, arg);
                        executionReturn.setExecutionSatus(true);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(ObjectClassExecutor.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        Logger.getLogger(ObjectClassExecutor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        } catch (InstantiationException ex) {
            Logger.getLogger(ObjectClassExecutor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ObjectClassExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out = baosSuccess.toString("UTF8").concat("\n-------\n" + baosError.toString("UTF8"));
            executionReturn.setReturnedString(out);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ObjectClassExecutor.class.getName()).log(Level.SEVERE, null, ex);
            executionReturn.setReturnedString(ex.getMessage());
        }
        return executionReturn;
    }

    public ExecutionReturn execute(Class<?> objClass) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

//    public static void main(String[] args){
//       Source src = new Implementors().getSourceInterface().getSourceByClassName("C:/FinalProject/AllUsers/reber", "Helloworld");
//       ObjectClassExecutor obj = new ObjectClassExecutor();
//       ExecutionReturn ex = obj.executeFile(src);
//       System.out.println(ex.getReturnedString()+" "+ex.isSucceeded());
//
//    }
}
