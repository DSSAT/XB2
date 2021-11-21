/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileX;

import java.util.Vector;

/**
 *
 * @author Jazzy
 */
public class Organic implements Cloneable {

    protected Vector  organicApps = new Vector();
    public Float EFIR;

    public String RENAME;

    public Organic(String RENAME)
    {
        this.RENAME = RENAME;
    }

    public Organic()
    {
    }

    public void AddApp(OrganicApplication organic)
    {
        organicApps.add(organic);
    }

    public void RemoveAt(int level)
    {
        organicApps.remove(level);
    }

    public void SetAt(int level, OrganicApplication organic)
    {
        organicApps.set(level, organic);
    }

    public OrganicApplication[] GetApps()
    {
        return (OrganicApplication[]) organicApps.toArray();
    }

    public OrganicApplication GetApp(int level)
    {
        return (OrganicApplication)organicApps.get(level);
    }

    public int GetSize()
    {
        return organicApps.size();
    }
    
    public Organic clone() throws CloneNotSupportedException
    {
        return (Organic) super.clone();
    }
}
