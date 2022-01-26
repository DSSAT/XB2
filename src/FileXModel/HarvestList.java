/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

/**
 *
 * @author Jazzy
 */
public class HarvestList extends ManagementList {
    
    @Override
    public IModelXBase Clone(String sourceName, String newName){
        Harvest source = (Harvest) GetAt(sourceName);
        Harvest newSource = null;
        
        try {
            newSource = new Harvest();
            newSource.HNAME = newName;
            
            for(HarvestApplication c : source.GetAll()) {
                HarvestApplication ca = (HarvestApplication) c.Clone();           
                newSource.AddApp(ca);
            }
        }
        catch(Exception ex){
            
        }
        
        return newSource;
    }

    @Override
    public void AddNew(String name) {
        modelList.add(new Harvest(name));
    }
}
