/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xbuild;

import java.util.*;

/**
 *
 * @author Jazzy
 */
public class MyEvent extends EventObject {
    String n;
    MyEvent(Object o, String n)
    {
        super(o);
        this.n = n;
    }

    public String getN()
    {
        return n;
    }
}
