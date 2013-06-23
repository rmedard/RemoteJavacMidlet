package Implementors;

import Entities.CompilerReturn;
import Entities.Source;
import Entities.StringBufferOutputStream;
import Interfaces.CompileInterface;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Medard
 */
public class SourceCompiler implements CompileInterface {

    private URLClassLoader urlClassLoader;
    private Class<?> objectClass;
    private CompilerReturn compilerReturn;

    public CompilerReturn compile(Source source) {
        StringBufferOutputStream errorString = new StringBufferOutputStream();
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, errorString, source.getLocation());
        if (result == 0) {
            try {
                urlClassLoader = URLClassLoader.newInstance(new URL[]{source.getRoot().toURI().toURL()});
                objectClass = Class.forName(source.getClassName(), true, urlClassLoader);
                source.setObjectClass(objectClass);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SourceCompiler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(SourceCompiler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            objectClass = null;
        }
        compilerReturn = new CompilerReturn(objectClass, errorString.toString());
        return compilerReturn;
    }
}
