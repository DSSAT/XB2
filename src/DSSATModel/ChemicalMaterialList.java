/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DSSATModel;

import static DSSATModel.CropList.crops;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author Jazzy
 */
public class ChemicalMaterialList {

    //public static ChemicalMaterial chemicals[];
    protected static Hashtable chemicals = new Hashtable();

    public static void Clear()
    {
        chemicals.clear();
    }

    public static void AddNew(ChemicalMaterial chem)
    {
        chemicals.put(chem.Code, chem);
    }

    public static ChemicalMaterial GetAt(String Code)
    {
        ChemicalMaterial chem = null;
        try{
            chem = (ChemicalMaterial) chemicals.get(Code);
        }
        catch(Exception ex) {}

        return chem;
    }

    public static ChemicalMaterial GetAt(int n)
    {
        ChemicalMaterial chem = null;
        try{
            Object[] object = chemicals.values().toArray();
            chem = (ChemicalMaterial) object[n];
        }
        catch(Exception ex) {}

        return chem;
    }
    
    public static List<ChemicalMaterial> GetAll(){
        List<ChemicalMaterial> chemicalList = new ArrayList<>();

        Object[] object = chemicals.values().toArray();

        for (int i = 0; i < object.length; i++) {
            chemicalList.add((ChemicalMaterial) object[i]);
        }

        Collections.sort(chemicalList, new Comparator<ChemicalMaterial>() {
            @Override
            public int compare(ChemicalMaterial c1, ChemicalMaterial c2) {
                return c1.Description.compareTo(c2.Description);
            }
        });
        
        return chemicalList;
    }

    public static int size()
    {
        return chemicals.size();
    }
}
