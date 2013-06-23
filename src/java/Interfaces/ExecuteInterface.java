/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces;

import Entities.ExecutionReturn;
import Entities.Source;

/**
 *
 * @author Medard
 */
public interface ExecuteInterface {

    public ExecutionReturn execute(Class<?> objClass);
    public ExecutionReturn executeFile(Source source);
}
