/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;
/**
 *
 * @author Jazzy
 */
public class SimulationList extends ManagementList {   
    @Override
    public void AddNew(String name)
    {
        modelList.add(new Simulation(name));
    }

    
    @Override
    public IModelXBase Clone(String sourceName, String newName){
        Simulation source = (Simulation) GetAt(sourceName);
        Simulation newSim = null;
        
        try{
            newSim = source.clone();
            newSim.SNAME = newName;
        }
        catch(CloneNotSupportedException ex){
            
        }
        
        return newSim;
    }
}
