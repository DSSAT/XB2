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
public class FertilizerMaterialList {
    /*
    public static FertilizerMethod fertilizer[];

    public static void setSize(int size)
    {
        fertilizer = new FertilizerMethod[size];
    }
    */

    protected static Hashtable fertilizer = new Hashtable();

    public static void AddNew(FertilizerMaterial fertil)
    {
        fertilizer.put(fertil.Code, fertil);
    }

    public static FertilizerMaterial GetAt(String Code)
    {
        FertilizerMaterial fertil = null;
        try{
            fertil = (FertilizerMaterial) fertilizer.get(Code);
        }
        catch(Exception ex) {}

        return fertil;
    }

    public static FertilizerMaterial GetAt(int n)
    {
        FertilizerMaterial fertil = null;
        try{
            Object[] object = fertilizer.values().toArray();
            fertil = (FertilizerMaterial) object[n];
        }
        catch(Exception ex) {}

        return fertil;
    }
    
    public static List<BaseModel> GetAll()
    {
        List<BaseModel> fertils = new ArrayList<>();
        
        for(Object object : fertilizer.values().toArray()){
            fertils.add((FertilizerMaterial) object);
        }
        
        Collections.sort(fertils, new Comparator<BaseModel>() {
            @Override
            public int compare(BaseModel f1, BaseModel f2) {
                return f1.Description.compareTo(f2.Description);
            }
        });

        return fertils;
    }

    public static int size()
    {
        return fertilizer.size();
    }
}
