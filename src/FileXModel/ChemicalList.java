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
    public IModelXBase Clone(String sourceName, String newName){
        Chemical source = (Chemical) GetAt(sourceName);
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
    public void AddNew(String name) {
        modelList.add(new Chemical(name));
    }
}
