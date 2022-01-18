/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DSSATModel;

import static FileXModel.FileX.cultivars;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Jazzy
 */
public class GrowthStageList {
    public static ArrayList<GrowthStage> growthStage = new ArrayList<>();

    public static void AddNew(GrowthStage gStage)
    {
        growthStage.add(gStage);
    }

    public static int size()
    {
        return growthStage.size();
    }

    public static GrowthStage GetAt(String Code, Crop crop)
    {
        GrowthStage gStage = null;
        for(int i = 0;i < growthStage.size();i++)
        {
            if(((GrowthStage)growthStage.get(i)).Code.equals(Code) && ((GrowthStage)growthStage.get(i)).crop.CropCode.equals(crop.CropCode))
                gStage = (GrowthStage)growthStage.get(i);
        }

        return gStage;
    }
    
    public static GrowthStage GetAt(String Code)
    {
        GrowthStage gStage = null;
        for(int i = 0;i < growthStage.size();i++)
        {
            if(((GrowthStage)growthStage.get(i)).Code.equals(Code))
                gStage = (GrowthStage)growthStage.get(i);
        }

        return gStage;
    }

    public static List<GrowthStage>GetAt(Crop crop)
    {
        List<GrowthStage> gList = new ArrayList<>();
        
        
        growthStage.forEach(growth -> {
            cultivars.GetAll().forEach(cul -> {
                if(growth.crop.CropCode.equalsIgnoreCase(cul.CR)){
                    gList.add(growth);
                }
            });
        });
        
        Collections.sort(gList, new Comparator<GrowthStage>() {
            @Override
            public int compare(GrowthStage g1, GrowthStage g2) {
                return g1.Description.compareTo(g2.Description);
            }
        });

        return gList;
    }
    
    public static List<GrowthStage> GetAll(){
        List<GrowthStage> growthStageList = new ArrayList<GrowthStage>();

        Object[] object = growthStage.toArray();

        for (int i = 0; i < object.length; i++) {
            growthStageList.add((GrowthStage) object[i]);
        }

        Collections.sort(growthStageList, new Comparator<GrowthStage>() {
            @Override
            public int compare(GrowthStage c1, GrowthStage c2) {
                return c1.Code.compareTo(c2.Code);
            }
        });
        
        return growthStageList;
    }

    public static void Clear()
    {
        growthStage.clear();
    }
}
