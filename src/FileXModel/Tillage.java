/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

import java.util.Vector;

/**
 *
 * @author Jazzy
 */
public class Tillage implements Cloneable {

    protected Vector  tillAps = new Vector();
    public String TNAME;

    public Tillage(String TNAME)
    {
        this.TNAME = TNAME;
    }

    public Tillage()
    {
    }

    public void AddApp(TillageApplication till)
    {
        tillAps.add(till);
    }

    public void RemoveAt(int level)
    {
        tillAps.remove(level);
    }

    public void SetAt(int level, TillageApplication till)
    {
        tillAps.set(level, till);
    }

    public TillageApplication[] GetApps()
    {
        return (TillageApplication[]) tillAps.toArray();
    }

    public TillageApplication GetApp(int level)
    {
        return (TillageApplication)tillAps.get(level);
    }

    public int GetSize()
    {
        return tillAps.size();
    }
    
    public Tillage clone() throws CloneNotSupportedException{
        return (Tillage) super.clone();
    }
}
