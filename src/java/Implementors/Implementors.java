/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Implementors;

import Interfaces.AuthorInterface;
import Interfaces.CompileInterface;
import Interfaces.ExecuteInterface;
import Interfaces.SourceInterface;

/**
 *
 * @author Medard
 */
public final class Implementors {
   private AuthorInterface authorInterface;
   private CompileInterface compileInterface;
   private ExecuteInterface executeInterface;
   private SourceInterface sourceInterface;

    public Implementors() {
        setAuthorInterface(new AuthorInterfaceImpl());
        setCompileInterface(new SourceCompiler());
        setExecuteInterface(new ObjectClassExecutor());
        setSourceInterface(new SourceInterfaceImpl());
    }

    public AuthorInterface getAuthorInterface() {
        return authorInterface;
    }

    public void setAuthorInterface(AuthorInterface authorInterface) {
        this.authorInterface = authorInterface;
    }

    public CompileInterface getCompileInterface() {
        return compileInterface;
    }

    public void setCompileInterface(CompileInterface compileInterface) {
        this.compileInterface = compileInterface;
    }

    public ExecuteInterface getExecuteInterface() {
        return executeInterface;
    }

    public void setExecuteInterface(ExecuteInterface executeInterface) {
        this.executeInterface = executeInterface;
    }

    public SourceInterface getSourceInterface() {
        return sourceInterface;
    }

    public void setSourceInterface(SourceInterface sourceInterface) {
        this.sourceInterface = sourceInterface;
    }
}
