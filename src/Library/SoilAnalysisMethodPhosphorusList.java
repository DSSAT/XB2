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
public class SoilAnalysisMethodPhosphorusList {

    /*
    public static SoilAnalysisMethodPhosphorus methods[];

    public static void setSize(int size)
    {
        SoilAnalysisMethodPhosphorus methodList[] = new SoilAnalysisMethodPhosphorus[size];

        SoilAnalysisMethodPhosphorusList.methods = methodList;
    }
     *
     */

    protected static Hashtable methods = new Hashtable();

    public static void AddNew(SoilAnalysisMethodPhosphorus phos)
    {
        methods.put(phos.Code, phos);
    }

    public static SoilAnalysisMethodPhosphorus GetAt(String Code)
    {
        SoilAnalysisMethodPhosphorus ph = null;
        try{
            ph = (SoilAnalysisMethodPhosphorus) methods.get(Code);
        }
        catch(Exception ex) {}

        return ph;
    }

    public static SoilAnalysisMethodPhosphorus GetAt(int n)
    {
        SoilAnalysisMethodPhosphorus ph = null;
        try{
            Object[] object = methods.values().toArray();
            ph = (SoilAnalysisMethodPhosphorus) object[n];
        }
        catch(Exception ex) {}

        return ph;
    }
    
    public static List<SoilAnalysisMethodPhosphorus> GetAll()
    {
        List<SoilAnalysisMethodPhosphorus> soilAnalysisList = new ArrayList<>();
        
        for(Object object : methods.values().toArray()){
            soilAnalysisList.add((SoilAnalysisMethodPhosphorus) object);
        }
        
        Collections.sort(soilAnalysisList, new Comparator<SoilAnalysisMethodPhosphorus>() {
            @Override
            public int compare(SoilAnalysisMethodPhosphorus s1, SoilAnalysisMethodPhosphorus s2) {
                return s1.Description.compareTo(s2.Description);
            }
        });

        return soilAnalysisList;
    }

    public static int size()
    {
        return methods.size();
    }
}
