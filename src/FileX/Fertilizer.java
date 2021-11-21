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
public class Fertilizer implements Cloneable {

    protected Vector  ferApps = new Vector();
    public String FERNAME;

    public Fertilizer(String FERNAME)
    {
        this.FERNAME = FERNAME;
    }

    public Fertilizer()
    {
    }

    public void AddApp(FertilizerApplication fer)
    {
        ferApps.add(fer);
    }

    public void RemoveAt(int level)
    {
        ferApps.remove(level);
    }

    public void SetAt(int level, FertilizerApplication fer)
    {
        ferApps.set(level, fer);
    }

    public FertilizerApplication[] GetApps()
    {
        return (FertilizerApplication[]) ferApps.toArray();
    }

    public FertilizerApplication GetApp(int level)
    {
        return (FertilizerApplication)ferApps.get(level);
    }

    public int GetSize()
    {
        return ferApps.size();
    }
    
    public Fertilizer clone() throws CloneNotSupportedException{
        return (Fertilizer) super.clone();
    }
}
