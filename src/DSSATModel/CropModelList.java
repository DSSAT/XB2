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
public class CropModelList {
    /*
    public static CropModel cropModels[];

    public static void setSize(int size)
    {
        CropModel cropModelList[] = new CropModel[size];

        CropModelList.cropModels = cropModelList;
    }
     * 
     */

    protected static Hashtable cropModels = new Hashtable();

    public static void AddNew(CropModel cropModel)
    {
        cropModels.put(cropModel.ModelCode, cropModel);
    }

    public static CropModel GetAt(String ModelCode)
    {
        CropModel cropModel = null;
        try{
            cropModel = (CropModel) cropModels.get(ModelCode);
        }
        catch(Exception ex) {}

        return cropModel;
    }

    public static CropModel GetAt(int n)
    {
        CropModel cropModel = null;
        try{
            Object[] object = cropModels.values().toArray();
            cropModel = (CropModel) object[n];
        }
        catch(Exception ex) {}

        return cropModel;
    }
    
    public static List<BaseModel> GetAll()
    {
        List<BaseModel> cropModelList = new ArrayList<>();
        Object[] object = cropModels.values().toArray();
        for (int i = 0; i < object.length; i++) {
            cropModelList.add((CropModel) object[i]);
        }

        Collections.sort(cropModelList, new Comparator<BaseModel>() {
            @Override
            public int compare(BaseModel c1, BaseModel c2) {
                return c1.Code.compareTo(c2.Description);
            }
        });
        
        return cropModelList;
    }

    public static int size()
    {
        return cropModels.size();
    }
}
