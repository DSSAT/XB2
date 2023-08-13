/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

import DSSATModel.CropModel;
import DSSATModel.CropModelList;
import DSSATModel.SimulationControlDefaults;

/**
 *
 * @author Jazzy
 */
public class SimulationList extends ManagementList {   
    @Override
    public ModelXBase AddNew(String name, int newLevel, int currentLevel) {
        Simulation model = SimulationControlDefaults.Get(FileX.general.FileType);
        model.SetName(name);
        
        CropModel cm = CropModelList.GetByCrop(FileX.general.crop.CropCode);
        if (cm != null) {
            model.SMODEL = cm.ModelCode;
        }
        if (FileX.plantings.GetSize() > 0 && newLevel <= FileX.plantings.GetSize()) {
            Planting pl = (Planting) FileX.plantings.GetAt(newLevel);
            model.SDATE = pl.PDATE;
        }
        
        modelList.add(model);
        model.SetLevel(newLevel);
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
    
    @Override
    public boolean IsUseInTreatment(int level) {
        boolean isUsed = false;
        
        for (ModelXBase treatment : FileX.treatments.GetAll()) {
            if (((Treatment) treatment).SM == level) {
                isUsed = true;
                break;
            }
        }
        return isUsed;
    }
}
