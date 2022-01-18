/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Jazzy
 */
public class Tillage implements Cloneable {

    protected ArrayList<TillageApplication>  tillAps = new ArrayList<>();
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

    public ArrayList<TillageApplication>GetApps()
    {
        return tillAps;
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
