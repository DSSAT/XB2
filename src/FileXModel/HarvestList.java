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
    public ModelXBase Clone(int sourceIndex, String newName){
        Harvest source = (Harvest) modelList.get(sourceIndex);
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
    public ModelXBase AddNew(String name, int newLevel, int currentLevel) {
        Harvest model = new Harvest(name);
        modelList.add(model);
        model.SetLevel(newLevel);
        return model;
    }
}
