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
    public ModelXBase AddNew(String name) {
        Simulation model = new Simulation(name);
        modelList.add(model);
        return model;
    }
    
    @Override
    public ModelXBase Clone(int sourceIndex, String newName){
        Simulation source = (Simulation) modelList.get(sourceIndex);
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
