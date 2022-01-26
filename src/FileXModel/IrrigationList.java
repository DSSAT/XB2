/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

/**
 *
 * @author Jazzy
 */
public class IrrigationList extends ManagementList {
    
    @Override
    public void AddNew(String name)
    {
        modelList.add(new Irrigation(name));
    }
    
    public IModelXBase Clone(String sourceName, String newName){
        Irrigation source = (Irrigation) GetAt(sourceName);
        Irrigation newSource = null;
        
        try{
            newSource = new Irrigation();
            newSource.IRNAME = newName;
            newSource.EFIR = source.EFIR;
            
            for(IrrigationApplication ir : source.GetApps()) {
                IrrigationApplication ia = (IrrigationApplication) ir.Clone();                
                newSource.AddApp(ia);
            }
        }
        catch(Exception ex){
            
        }
        
        return newSource;
    }
}
