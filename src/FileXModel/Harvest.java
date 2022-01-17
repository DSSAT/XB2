/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

import DSSATModel.GrowthStage;
import static DSSATModel.GrowthStageList.growthStage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
    
    public List<HarvestApplication> GetAll(){
        List<HarvestApplication> list = new ArrayList<HarvestApplication>();

        Object[] object = harvestApps.toArray();

        for (int i = 0; i < object.length; i++) {
            list.add((HarvestApplication) object[i]);
        }

        Collections.sort(list, new Comparator<HarvestApplication>() {
            public int compare(GrowthStage c1, GrowthStage c2) {
                return c1.Code.compareTo(c2.Code);
            }

            @Override
            public int compare(HarvestApplication o1, HarvestApplication o2) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        return list;
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
