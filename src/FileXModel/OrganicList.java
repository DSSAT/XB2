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
public class OrganicList extends ManagementList {
    
    @Override
    public void AddNew(String name)
    {
        modelList.add(new Organic(name));
    }
    
    public IModelXBase Clone(String sourceName, String newName){
        Organic source = (Organic)GetAt(sourceName);
        Organic newSource = null;
        
        try {
            newSource = new Organic();
            newSource.RENAME = newName;
            
            for(OrganicApplication c : source.GetApps()) {
                OrganicApplication ca = (OrganicApplication) c.Clone();           
                newSource.AddApp(ca);
            }
        }
        catch(Exception ex){
            
        }
        
        return newSource;
    }
}
