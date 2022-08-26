/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Jazzy
 */
public class Irrigation implements Cloneable, IModelXBase {

    protected ArrayList<IrrigationApplication>  irrigApps = new ArrayList<>();
    public Float EFIR;
    //IDEP
    //ITHR
    //IEPT
    //IOFF  
    //IAME
    //int IAMT  = 1
    public String IRNAME;

    public Irrigation(String IRNAME)
    {
        this.IRNAME = IRNAME;
    }

    public Irrigation()
    {
    }

    public void AddApp(IrrigationApplication irrig)
    {
        irrigApps.add(irrig);
        Collections.sort(irrigApps, Comparator.comparing(IrrigationApplication::getOrder));
    }

    public void RemoveAt(int level)
    {
        irrigApps.remove(level);
    }

    public void SetAt(int level, IrrigationApplication irrig)
    {
        irrigApps.set(level, irrig);
    }

    public ArrayList<IrrigationApplication> GetApps()
    {
        return irrigApps;
    }

    public IrrigationApplication GetApp(int level)
    {
        return (IrrigationApplication)irrigApps.get(level);
    }

    public int GetSize()
    {
        return irrigApps.size();
    }
    
    @Override
    public Irrigation clone() throws CloneNotSupportedException{
        return (Irrigation) super.clone();
    }

    @Override
    public String GetName() {
        return this.IRNAME == null ? "" : this.IRNAME;
    }
    
    @Override
    public void SetName(String name) {
        IRNAME = name;
    }
}
