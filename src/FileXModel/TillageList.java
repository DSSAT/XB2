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
public class TillageList extends ManagementList {

    @Override
    public void AddNew(String name)
    {
        modelList.add(new Tillage(name));
    }
    
    @Override
    public IModelXBase Clone(String sourceName, String newName){
        Tillage source = (Tillage)GetAt(sourceName);
        Tillage newSource = null;
        
        try{            
            newSource = new Tillage();
            newSource.TNAME = newName;
            
            for(TillageApplication c : source.GetApps()) {
                TillageApplication ca = (TillageApplication) c.Clone();           
                newSource.AddApp(ca);
            }
        }
        catch(Exception ex){
            
        }
        
        return newSource;
    }
}
