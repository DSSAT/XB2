/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Jazzy
 */
public class InitialCondition implements Cloneable {
    public String PCR;
    public Date ICDAT;
    public Float ICRT;
    public Float ICND;
    public Float ICRN;
    public Float ICRE;
    public Float ICWD;
    public Float ICRES;
    public Float ICREN;
    public Float ICREP;
    public Float ICRIP;
    public Float ICRID;
    public String ICNAME;
    protected Vector  InitApps = new Vector();

    public InitialCondition(String ICNAME)
    {
        this.ICNAME = ICNAME;
    }

    public InitialCondition()
    {
        
    }

    public void AddApp(InitialConditionApplication initApp)
    {
        InitApps.add(initApp);
    }

    public void RemoveAt(int level)
    {
        InitApps.remove(level);
    }

    public void SetAt(int level, InitialConditionApplication initApp)
    {
        InitApps.set(level, initApp);
    }

    public InitialConditionApplication[] GetApps()
    {
        return (InitialConditionApplication[]) InitApps.toArray();
    }

    public InitialConditionApplication GetApp(int level)
    {
        return (InitialConditionApplication)InitApps.get(level);
    }

    public int GetSize()
    {
        return InitApps.size();
    }
    
    public InitialCondition clone() throws CloneNotSupportedException {
        return (InitialCondition)super.clone();
    } 
}
