/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces;

import Entities.CompilerReturn;
import Entities.Source;

/**
 *
 * @author Medard
 */
public interface CompileInterface {
     public CompilerReturn compile(Source source);
}
