/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Source;

/**
 *
 * @author Medard
 */
public interface SourceInterface {

    public String getMainRoot();
    
    public Source createSource(Source source);

    public boolean updateSource(Source source);

    public boolean deleteSource(String rootDirectory, String className);

    public Source getSourceByClassName(String rootDirectory, String className);

    public String getSourceClassNamesByAuthor(String authorUsername);
}
