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
    public ModelXBase AddNew(String name) {
        Environmental model = new Environmental(name);
        modelList.add(model);
        return model;
    }
    
    @Override
    public ModelXBase Clone(int sourceIndex, String newName){
        Environmental source = (Environmental) modelList.get(sourceIndex);
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
