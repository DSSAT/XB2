/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author Jazzy
 */
public class HarvestSizeList {

    protected static Hashtable harvestSize = new Hashtable();

    public static void AddNew(HarvestSize harvest)
    {
        harvestSize.put(harvest.Code, harvest);
    }

    public static HarvestSize GetAt(String Code)
    {
        HarvestSize harvest = null;
        try{
            harvest = (HarvestSize) harvestSize.get(Code);
        }
        catch(Exception ex) {}

        return harvest;
    }

    public static HarvestSize GetAt(int n)
    {
        HarvestSize harvest = null;
        try{
            Object[] object = harvestSize.values().toArray();
            harvest = (HarvestSize) object[n];
        }
        catch(Exception ex) {}

        return harvest;
    }
    
    public static List<HarvestSize> GetAll()
    {
        List<HarvestSize> havestSizeList = new ArrayList<>();
        
        for(Object object : harvestSize.values().toArray()){
            havestSizeList.add((HarvestSize) object);
        }
        
        Collections.sort(havestSizeList, new Comparator<HarvestSize>() {
            @Override
            public int compare(HarvestSize h1, HarvestSize h2) {
                return h1.Description.compareTo(h2.Description);
            }
        });

        return havestSizeList;
    }

    public static int size()
    {
        return harvestSize.size();
    }
}
