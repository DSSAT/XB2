/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

/**
 *
 * @author Jazzy
 */
public class EnvironmentalList extends ManagementList {
    
    @Override
    public void AddNew(String name) {
        modelList.add(new Environmental(name));
    }
    
    @Override
    public IModelXBase Clone(String sourceName, String newName){
        Environmental source = (Environmental) GetAt(sourceName);
        Environmental newSource = null;
        
        try {            
            newSource = new Environmental();
            newSource.ENVNAME = newName;
            
            for(EnvironmentApplication c : source.GetApps()) {
                EnvironmentApplication ca = (EnvironmentApplication) c.Clone();           
                newSource.AddApp(ca);
            }
        }
        catch(Exception ex){
            
        }
        
        return newSource;
    }
}
