/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

/**
 *
 * @author Jazzy
 */
public class PlantingList extends ManagementList {
   
    public IModelXBase Clone(String sourceName, String newName){
        Planting source = (Planting)GetAt(sourceName);
        Planting newSource = null;
        
        try{
            newSource = source.clone();
            newSource.PLNAME = newName;
        }
        catch(Exception ex){
            
        }
        
        return newSource;
    }

    @Override
    public void AddNew(String name) {
        modelList.add(new Planting(name));
    }
}
