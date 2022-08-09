/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DSSATModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Jazzy
 */
public class CropModelList {
    protected static HashMap cropModels = new HashMap();

    public static void AddNew(CropModel cropModel)
    {
        cropModels.put(cropModel.ModelCode, cropModel);
    }
    
    public static void Clear(){
        cropModels.clear();
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
    
    public static List<CropModel> GetAll()
    {
        List<CropModel> cropModelList = new ArrayList<>();
        Object[] objects = cropModels.values().toArray();
        for (Object object : objects) {
            cropModelList.add((CropModel) object);
        }

        Collections.sort(cropModelList, (BaseModel c1, BaseModel c2) -> c1.Code.compareTo(c2.Description));
        
        return cropModelList;
    }

    public static int size()
    {
        return cropModels.size();
    }
}
