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
public class Chemical implements Cloneable {

    protected Vector  chems = new Vector();
    public String CHNAME;

    public Chemical(String CHNAME)
    {
        this.CHNAME = CHNAME;
    }

    public Chemical()
    {
    }

    public void AddApp(ChemicalApplication chem)
    {
        chems.add(chem);
    }

    public void RemoveAt(int level)
    {
        chems.remove(level);
    }

    public void SetAt(int level, ChemicalApplication chem)
    {
        chems.set(level, chem);
    }

    public ChemicalApplication[] GetApps()
    {
        return (ChemicalApplication[]) chems.toArray();
    }

    public ChemicalApplication GetApp(int level)
    {
        return (ChemicalApplication)chems.get(level);
    }

    public int GetSize()
    {
        return chems.size();
    }
    
    public Chemical clone() throws CloneNotSupportedException
    {
        return (Chemical) super.clone();
    }
}
