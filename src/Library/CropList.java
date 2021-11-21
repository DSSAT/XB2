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
public class CropList {
    /*public static Crop crops[];

    public static void setSize(int size)
    {
        Crop crops[] = new Crop[size];

        CropList.crops = crops;
    }*/

    protected static Hashtable crops = new Hashtable();

    public static void AddNew(Crop crop)
    {
        crops.put(crop.CropCode, crop);
    }

    public static Crop GetAt(String Code)
    {
        Crop crop = null;
        try{
            crop = (Crop) crops.get(Code);
        }
        catch(Exception ex) {}

        return crop;
    }

    public static Crop GetAt(int n)
    {
        Crop crop = null;
        try{
            Object[] object = crops.values().toArray();
            crop = (Crop) object[n];
        }
        catch(Exception ex) {}

        return crop;
    }

    public static int size()
    {
        return crops.size();
    }
    
    public static List<Crop> GetAll(){
        List<Crop> cropList = new ArrayList<Crop>();

        Object[] object = crops.values().toArray();

        for (int i = 0; i < object.length; i++) {
            cropList.add((Crop) object[i]);
        }

        Collections.sort(cropList, new Comparator<Crop>() {
            @Override
            public int compare(Crop c1, Crop c2) {
                return c1.CropCode.compareTo(c2.CropCode);
            }
        });
        
        return cropList;
    }

    public static Crop GetAtName(String CropName) {
        Crop crop = null;
        try{
            Object[] object = crops.values().toArray();
            for(int i = 0;i < object.length;i++)
            {

                if(((Crop)object[i]).CropName.equals(CropName))
                {
                    crop = (Crop) object[i];
                    break;
                }
            }
        }
        catch(Exception ex) {}

        return crop;
    }
}
