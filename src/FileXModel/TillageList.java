/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

/**
 *
 * @author Jazzy
 */
public class TillageList extends ManagementList {

    @Override
    public ModelXBase AddNew(String name) {
        Tillage model = new Tillage(name);
        modelList.add(model);
        return model;
    }
    
    @Override
    public ModelXBase Clone(int sourceIndex, String newName){
        Tillage source = (Tillage) modelList.get(sourceIndex);
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
