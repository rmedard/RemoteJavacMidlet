/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entities;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Medard
 */
public class StringBufferOutputStream extends OutputStream{
   private StringBuffer textBuffer = new StringBuffer();

    /**
     *
     */
    public StringBufferOutputStream()
    {
        super();
    }

    /*
     * @see java.io.OutputStream#write(int)
     */
    public void write(int b) throws IOException
    {
        char a = (char)b;
        textBuffer.append(a);
    }

    @Override
    public String toString()
    {
        return textBuffer.toString();
    }

    public void clear()
    {
        textBuffer.delete(0, textBuffer.length());
    }
}
