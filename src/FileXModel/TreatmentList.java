/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

import DSSATModel.ExperimentType;
import Extensions.Utils;
import java.util.ArrayList;

/**
 *
 * @author Jazzy
 */
public class TreatmentList extends ManagementList {
    protected ArrayList<Treatment> treatments = new ArrayList<>();

    @Override
    public ModelXBase AddNew(String name, int newLevel, int currentLevel) {
        Treatment model;//new Treatment(name);
        
        if(currentLevel >= 0){
            try {
                model = ((Treatment)FileX.treatments.GetAtIndex(currentLevel)).Clone();
            } catch (CloneNotSupportedException ex) {
                model = new Treatment(name);
                model.SetLevel(FileX.general.FileType == ExperimentType.Sequential ? 1 : newLevel);
            }
            
            if(FileX.general.FileType != ExperimentType.Sequential){
                model.SetLevel(FileX.treatments.GetSize() + 1);
            }
            else{
                Integer r = Utils.ParseInteger(((Treatment)FileX.treatments.GetAtIndex(FileX.treatments.GetSize() - 1)).R);
                model.R = r.toString();
            }
        }
        else{
            model = new Treatment(name);
            model.SetLevel(FileX.general.FileType == ExperimentType.Sequential ? 1 : newLevel);
        }
        
        
        
        if(FileX.treatments.GetSize() == 0){
            model.CU = FileX.cultivars.GetAtIndex(0).GetLevel();
            model.FL = FileX.fieldList.GetAtIndex(0).GetLevel();
            model.MP = FileX.plantings.GetAtIndex(0).GetLevel();
            model.SM = FileX.simulationList.GetAtIndex(0).GetLevel();
        }
        model.SetLevel(newLevel);
        modelList.add(model);
        return model;
    }

    @Override
    public ModelXBase Clone(int sourceIndex, String newName){
        Treatment source = (Treatment) modelList.get(sourceIndex);
        Treatment newSource = null;
        
        try{            
            newSource = source.Clone();
            newSource.TNAME = newName;
        }
        catch(Exception ex){
            
        }
        
        return newSource;
    }

    @Override
    public boolean IsUseInTreatment(int level) {
        return false;
    }
}
