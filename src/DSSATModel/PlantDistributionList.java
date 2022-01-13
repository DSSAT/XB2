/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DSSATModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author Jazzy
 */
public class PlantDistributionList {

    /*public static PlantDistribution plantingDistribution[];

    public static void setSize(int size)
    {
        PlantDistribution plantingDistributionList[] = new PlantDistribution[size];

        PlantDistributionList.plantingDistribution = plantingDistributionList;
    }
     *
     */

    protected static Hashtable pDistribution = new Hashtable();

    public static void AddNew(PlantDistribution plant)
    {
        pDistribution.put(plant.Code, plant);
    }

    public static PlantDistribution GetAt(String Code)
    {
        PlantDistribution plant = null;
        try{
            plant = (PlantDistribution) pDistribution.get(Code);
        }
        catch(Exception ex) {}

        return plant;
    }

    public static PlantDistribution GetAt(int n)
    {
        PlantDistribution plant = null;
        try{
            Object[] object = pDistribution.values().toArray();
            plant = (PlantDistribution) object[n];
        }
        catch(Exception ex) {}

        return plant;
    }
    
    public static List<PlantDistribution> GetAll()
    {
        List<PlantDistribution> plantList = new ArrayList<>();
        
        for(Object object : pDistribution.values().toArray()){
            plantList.add((PlantDistribution) object);
        }
        
        Collections.sort(plantList, new Comparator<PlantDistribution>() {
            @Override
            public int compare(PlantDistribution p1, PlantDistribution p2) {
                return p1.Description.compareTo(p2.Description);
            }
        });

        return plantList;
    }

    public static int size()
    {
        return pDistribution.size();
    }
}
