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
public class FertilizerMethodList {

    protected static Hashtable fertilizer = new Hashtable();

    public static void AddNew(FertilizerMethod fertil)
    {
        fertilizer.put(fertil.Code, fertil);
    }

    public static FertilizerMethod GetAt(String Code)
    {
        FertilizerMethod fertil = null;
        try{
            fertil = (FertilizerMethod) fertilizer.get(Code);
        }
        catch(Exception ex) {}

        return fertil;
    }

    public static FertilizerMethod GetAt(int n)
    {
        FertilizerMethod fertil = null;
        try{
            Object[] object = fertilizer.values().toArray();
            fertil = (FertilizerMethod) object[n];
        }
        catch(Exception ex) {}

        return fertil;
    }

    public static List<FertilizerMethod> GetAll()
    {
        List<FertilizerMethod> ferMethods = new ArrayList<>();
        
        for(Object object : fertilizer.values().toArray()){
            ferMethods.add((FertilizerMethod) object);
        }
        
        Collections.sort(ferMethods, new Comparator<FertilizerMethod>() {
            @Override
            public int compare(FertilizerMethod f1, FertilizerMethod f2) {
                return f1.Description.compareTo(f2.Description);
            }
        });

        return ferMethods;
    }
    
    public static int size()
    {
        return fertilizer.size();
    }
}
