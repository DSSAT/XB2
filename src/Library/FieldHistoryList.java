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
public class FieldHistoryList {

    protected static Hashtable fieldHistory = new Hashtable();

    public static void AddNew(FieldHistory fHist)
    {
        fieldHistory.put(fHist.Code, fHist);
    }

    public static FieldHistory GetAt(String Code)
    {
        FieldHistory fHist = null;
        try{
            fHist = (FieldHistory) fieldHistory.get(Code);
        }
        catch(Exception ex) {}

        return fHist;
    }

    public static FieldHistory GetAt(int n)
    {
        FieldHistory fHist = null;
        try{
            Object[] object = fieldHistory.values().toArray();
            fHist = (FieldHistory) object[n];
        }
        catch(Exception ex) {}

        return fHist;
    }
    
    public static List<FieldHistory> GetAll()
    {
        List<FieldHistory> ferMethods = new ArrayList<>();
        
        for(Object object : fieldHistory.values().toArray()){
            ferMethods.add((FieldHistory) object);
        }
        
        Collections.sort(ferMethods, new Comparator<FieldHistory>() {
            @Override
            public int compare(FieldHistory f1, FieldHistory f2) {
                return f1.Description.compareTo(f2.Description);
            }
        });

        return ferMethods;
    }

    public static int size()
    {
        return fieldHistory.size();
    }
}
