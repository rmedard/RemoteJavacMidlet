//GEN-BEGIN:Client
/**
 * This file is generated. Please do not change
 */
package Entities;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

/**
 *  This class is used as an external protocol utility. It is so we don't
 *  generate as much code.
 */

public class Utility {

    /** Marker for null. Null is a type and a value together. */
    private final static short NULL_TYPE = -1;

    /** Marker for void return types. */
    public final static Object VOID_VALUE = new Object();

    /**
     * Sends return values to the client output stream.
     *
     * @param output The output stream into which all the data should be written
     * @param  returnValue The value which we should write into the stream
     * @throws IOException If an error occured while writing the results
     */
    public static void writeResults( DataOutput output, Object returnValue, int[] paramIDs) throws IOException {
        for ( int i = 0; i < paramIDs.length; i++ ) {
            writeObject(output, returnValue, paramIDs[i]);
        }
    }

    /**
     *
     * @param output
     * @param o
     * @throws java.io.IOException
     */
    public static void writeObject(DataOutput output, Object o, int id) throws IOException {
        if( o == null ) {
            // write null type to the stream
            output.writeShort( NULL_TYPE );
            return;
        }
        switch( id ) {
            case 1:
                // Author
                output.writeShort(1);
                Entities.Author b_Entities_Author = (Entities.Author)o;
                output.writeInt(b_Entities_Author.getAuthor_id());
                writeObject(output, b_Entities_Author.getAuthor_username(), 9);
                writeObject(output, b_Entities_Author.getAuthor_password(), 9);

                break;
            case 3:
                // Source
                output.writeShort(3);
                Entities.Source b_Entities_Source = (Entities.Source)o;
                writeObject(output, b_Entities_Source.getClassName(), 9);
                writeObject(output, b_Entities_Source.getLocation(), 9);
                writeObject(output, b_Entities_Source.getAuthor(), 1);
                writeObject(output, b_Entities_Source.getSourceBody(), 9);

                break;
            case 4:
                // CompilerReturn
                output.writeShort(4);
                Entities.CompilerReturn b_Entities_CompilerReturn = (Entities.CompilerReturn)o;
                writeObject(output, b_Entities_CompilerReturn.getCompilationError(), 9);

                break;
            case 5:
                // int
                output.writeShort(5);
                output.writeInt(((Integer)o).intValue());
                break;
            case 6:
                // Boolean
                output.writeShort(6);
                output.writeBoolean(((Boolean)o).booleanValue());
                break;
            case 9:
                // String
                output.writeShort(9);
                output.writeUTF((String)o);
                break;
            default:
                // default if a data type is not supported
                throw new IllegalArgumentException("Unsupported parameter type: " + o.getClass());
        }
    }

    /**
     *
     * @param in
     * @return
     * @throws java.io.IOException
     */
    protected static Object readObject(DataInput in) throws IOException {
        short type = in.readShort();
        Object result;
        switch (type) {
            case 1:
                // Author
                Entities.Author b_Entities_Author = new Entities.Author();
                b_Entities_Author.setAuthor_id(in.readInt());
                b_Entities_Author.setAuthor_username((String) readObject(in));
                b_Entities_Author.setAuthor_password((String) readObject(in));
                result = b_Entities_Author;

                return result;
            case 3:
                // Source
                Entities.Source b_Entities_Source = new Entities.Source();
                b_Entities_Source.setClassName((String) readObject(in));
                b_Entities_Source.setLocation((String) readObject(in));
                b_Entities_Source.setAuthor((Entities.Author) readObject(in));
                b_Entities_Source.setSourceBody((String) readObject(in));
                result = b_Entities_Source;

                return result;
            case 5:
                // int
                return new Integer(in.readInt());
            case 9:
                // String
                result = in.readUTF();
                return result;
            case NULL_TYPE: /* null */
                return null;
            default:
                throw new IllegalArgumentException(
                        "Unsupported return type (" + type + ")");
        }
    }
}
//GEN-END:Client