/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entities;

/**
 *
 * @author Medard
 */
public class ExecutionReturn {
    
    private String returnedString;
    private boolean succeeded;

    public ExecutionReturn() {
    }

    public ExecutionReturn(boolean succeeded) {
        this.succeeded = succeeded;
    }

    
    public ExecutionReturn(String returnedString, boolean executionSatus) {
        this.returnedString = returnedString;
        this.succeeded = executionSatus;
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setExecutionSatus(boolean executionSatus) {
        this.succeeded = executionSatus;
    }

    public String getReturnedString() {
        return returnedString;
    }

    public void setReturnedString(String returnedString) {
        this.returnedString = returnedString;
    }
}
