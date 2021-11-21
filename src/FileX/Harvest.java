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
public class Harvest implements Cloneable {

    protected Vector  harvestApps = new Vector();
    public String HNAME;

    public Harvest(String HNAME)
    {
        this.HNAME = HNAME;
    }

    public Harvest()
    {
    }

    public void AddApp(HarvestApplication harvestApp)
    {
        harvestApps.add(harvestApp);
    }

    public void RemoveAt(int level)
    {
        harvestApps.remove(level);
    }

    public void SetAt(int level, HarvestApplication harvestApp)
    {
        harvestApps.set(level, harvestApp);
    }

    public HarvestApplication[] GetApps()
    {
        return (HarvestApplication[]) harvestApps.toArray();
    }

    public HarvestApplication GetApp(int level)
    {
        return (HarvestApplication)harvestApps.get(level);
    }

    public int GetSize()
    {
        return harvestApps.size();
    }
    
    public Harvest clone() throws CloneNotSupportedException
    {
        return (Harvest) super.clone();
    }
}
