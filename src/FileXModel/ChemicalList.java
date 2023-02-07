/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

/**
 *
 * @author Jazzy
 */
public class ChemicalList extends ManagementList {
    
    @Override
    public ModelXBase Clone(int sourceIndex, String newName){
        Chemical source = (Chemical) modelList.get(sourceIndex);
        Chemical newSource = null;
        
        try{            
            newSource = new Chemical();
            newSource.CHNAME = newName;
            
            for(ChemicalApplication c : source.GetApps()) {
                ChemicalApplication ca = (ChemicalApplication) c.Clone();           
                newSource.AddApp(ca);
            }
        }
        catch(Exception ex){
            
        }
        
        return newSource;
    }

    @Override
    public ModelXBase AddNew(String name) {
        Chemical model = new Chemical(name);
        modelList.add(model);
        return model;
    }
}
