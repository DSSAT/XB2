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
public class SoilAnalysisMethodPotassiumList {

    /*
    public static SoilAnalysisMethodPotassium methods[];

    public static void setSize(int size)
    {
        SoilAnalysisMethodPotassium methodList[] = new SoilAnalysisMethodPotassium[size];

        SoilAnalysisMethodPotassiumList.methods = methodList;
    }
     *
     */

    protected static Hashtable methods = new Hashtable();

    public static void AddNew(SoilAnalysisMethodPotassium potass)
    {
        methods.put(potass.Code, potass);
    }

    public static SoilAnalysisMethodPotassium GetAt(String Code)
    {
        SoilAnalysisMethodPotassium potass = null;
        try{
            potass = (SoilAnalysisMethodPotassium) methods.get(Code);
        }
        catch(Exception ex) {}

        return potass;
    }

    public static SoilAnalysisMethodPotassium GetAt(int n)
    {
        SoilAnalysisMethodPotassium potass = null;
        try{
            Object[] object = methods.values().toArray();
            potass = (SoilAnalysisMethodPotassium) object[n];
        }
        catch(Exception ex) {}

        return potass;
    }
    
    public static List<SoilAnalysisMethodPotassium> GetAll()
    {
        List<SoilAnalysisMethodPotassium> spList = new ArrayList<>();
        
        for(Object object : methods.values().toArray()){
            spList.add((SoilAnalysisMethodPotassium) object);
        }
        
        Collections.sort(spList, new Comparator<SoilAnalysisMethodPotassium>() {
            @Override
            public int compare(SoilAnalysisMethodPotassium p1, SoilAnalysisMethodPotassium p2) {
                return p1.Description.compareTo(p2.Description);
            }
        });

        return spList;
    }

    public static int size()
    {
        return methods.size();
    }
}
