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
public class ResiduesList {

    protected static Hashtable residues = new Hashtable();

    public static void AddNew(Residues residue)
    {
        residues.put(residue.Code, residue);
    }

    public static Residues GetAt(String Code)
    {
        Residues residue = null;
        try{
            residue = (Residues) residues.get(Code);
        }
        catch(Exception ex) {}

        return residue;
    }

    public static Residues GetAt(int n)
    {
        Residues residue = null;
        try{
            Object[] object = residues.values().toArray();
            residue = (Residues) object[n];
        }
        catch(Exception ex) {}

        return residue;
    }
    
    public static List<Residues> GetAll()
    {
        List<Residues> residuesList = new ArrayList<>();
        
        for(Object object : residues.values().toArray()){
            residuesList.add((Residues) object);
        }
        
        Collections.sort(residuesList, new Comparator<Residues>() {
            @Override
            public int compare(Residues p1, Residues p2) {
                return p1.Description.compareTo(p2.Description);
            }
        });

        return residuesList;
    }

    public static int size()
    {
        return residues.size();
    }
}
