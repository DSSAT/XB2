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
public class Irrigation extends ModelXBase implements Cloneable {

    protected ArrayList<IrrigationApplication>  irrigApps = new ArrayList<>();
    public Float EFIR;
    public Integer IDEP;
    public Integer ITHR;
    public Integer IEPT;
    public String IOFF;
    public String IAME;
    public Integer IAMT;
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
        Sort();
    }

    public void RemoveAt(int level)
    {
        irrigApps.remove(level);
        Sort();
    }

    public void SetAt(int level, IrrigationApplication irrig)
    {
        irrigApps.set(level, irrig);
        Sort();
    }

    public void Sort()
    {
        if (irrigApps != null && irrigApps.size() > 1) {
            Collections.sort(irrigApps, (a, b) -> {
                if (a.IDATE != null && b.IDATE != null) {
                    return a.IDATE.compareTo(b.IDATE);
                } else if (a.IDAY != null && b.IDAY != null) {
                    return a.IDAY.compareTo(b.IDAY);
                }
                return 0;
            });
        }
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
