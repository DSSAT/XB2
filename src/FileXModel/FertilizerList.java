/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

import java.util.ArrayList;

/**
 *
 * @author Jazzy
 */
public class FertilizerList extends ManagementList {
    
    @Override
    public void AddNew(String name){
        modelList.add(new Fertilizer(name));
    }
    
    @Override
    public IModelXBase Clone(String sourceName, String newName){
        Fertilizer source = (Fertilizer) GetAt(sourceName);
        Fertilizer newSource = null;
        
        try{            
            newSource = new Fertilizer();
            newSource.FERNAME = newName;
            
            for(FertilizerApplication f : source.GetApps()) {
                FertilizerApplication fa = (FertilizerApplication) f.Clone();
                newSource.AddApp(fa);
            }
        }
        catch(Exception ex){
            
        }
        
        return newSource;
    }
}
