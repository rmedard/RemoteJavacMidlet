/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WebService;

import Entities.Author;
import Entities.CompilerReturn;
import Entities.ExecutionReturn;
import Entities.Source;
import Implementors.Implementors;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Medard
 */
@WebService()
public class FinalWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createAuthor")
    public Author createAuthor(@WebParam(name = "author")
    Author author) {
        //TODO write your implementation code here:
        return new Implementors().getAuthorInterface().creatAuthor(author);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteAuthor")
    public Boolean deleteAuthor(@WebParam(name = "id")
    int id) {
        //TODO write your implementation code here:
        return new Implementors().getAuthorInterface().deleteAuthor(id);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateAuthor")
    public Boolean updateAuthor(@WebParam(name = "author")
    Author author) {
        //TODO write your implementation code here:
        return new Implementors().getAuthorInterface().updateAuthor(author);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAuthorById")
    public Author getAuthorById(@WebParam(name = "id")
    int id) {
        //TODO write your implementation code here:
        return new Implementors().getAuthorInterface().getAuthorById(id);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAuthorByUsername")
    public Author getAuthorByUsername(@WebParam(name = "username")
    String username) {
        //TODO write your implementation code here:
        return new Implementors().getAuthorInterface().getAuthorByUsername(username);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "logAuthor")
    public Boolean logAuthor(@WebParam(name = "username")
    String username, @WebParam(name = "password")
    String password) {
        //TODO write your implementation code here:
        return new Implementors().getAuthorInterface().logAuthor(username, password);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createSource")
    public Source createSource(@WebParam(name = "source")
    Source source) {
        //TODO write your implementation code here:
        return new Implementors().getSourceInterface().createSource(source);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateSource")
    public Boolean updateSource(@WebParam(name = "source")
    Source source) {
        //TODO write your implementation code here:
        return new Implementors().getSourceInterface().updateSource(source);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteSource")
    public Boolean deleteSource(@WebParam(name = "rootDirectory")
    String rootDirectory, @WebParam(name = "className")
    String className) {
        //TODO write your implementation code here:
        return new Implementors().getSourceInterface().deleteSource(rootDirectory, className);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getSourceByClassName")
    public Source getSourceByClassName(@WebParam(name = "rootDirectory")
    String rootDirectory, @WebParam(name = "className")
    String className) {
        //TODO write your implementation code here:
        return new Implementors().getSourceInterface().getSourceByClassName(rootDirectory, className);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getSourceClassNamesByAuthor")
    public String getSourceClassNamesByAuthor(@WebParam(name = "username")
    String username) {
        //TODO write your implementation code here:
        return new Implementors().getSourceInterface().getSourceClassNamesByAuthor(username);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "compile")
    public CompilerReturn compile(@WebParam(name = "source")
    Source source) {
        //TODO write your implementation code here:
        return new Implementors().getCompileInterface().compile(source);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getMainRoot")
    public String getMainRoot() {
        //TODO write your implementation code here:
        return new Implementors().getSourceInterface().getMainRoot();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "executeFile")
    public ExecutionReturn executeFile(@WebParam(name = "source")
    Source source) {
        //TODO write your implementation code here:
        return new Implementors().getExecuteInterface().executeFile(source);
    }
}
