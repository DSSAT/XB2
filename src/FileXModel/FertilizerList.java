/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

/**
 *
 * @author Jazzy
 */
public class FertilizerList extends ManagementList {
    
    @Override
    public ModelXBase AddNew(String name, int newLevel, int currentLevel) {
        Fertilizer model = new Fertilizer(name);
        modelList.add(model);
        model.SetLevel(newLevel);
        return model;
    }
    
    @Override
    public ModelXBase Clone(int sourceIndex, String newName){
        Fertilizer source = (Fertilizer) modelList.get(sourceIndex);
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
