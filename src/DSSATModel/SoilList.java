/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DSSATModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Jazzy
 */
public class SoilList {
    protected static HashMap soils = new HashMap();

    public static void AddNew(Soil soil)
    {
        soils.put(soil.Code, soil);
    }
    
    public static void Clear(){
        soils.clear();
    }

    public static Soil GetAt(String Code)
    {
        Soil soil = null;
        try{
            soil = (Soil) soils.get(Code);
        }
        catch(Exception ex) {}

        return soil;
    }

    public static Soil GetAt(int n)
    {
        Soil soil = null;
        try{
            Object[] object = soils.values().toArray();
            soil = (Soil) object[n];
        }
        catch(Exception ex) {}

        return soil;
    }

    public static int size()
    {
        return soils.size();
    }
    
    public static List<Soil> GetAll()
    {
        List<Soil> soilList = new ArrayList<>();
        for(int i = 0;i < soils.size();i++){
            soilList.add(GetAt(i));
        }
        
        return soilList;
    }
}
