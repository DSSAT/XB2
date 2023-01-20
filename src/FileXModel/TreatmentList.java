/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

import java.util.ArrayList;

/**
 *
 * @author Jazzy
 */
public class TreatmentList extends ManagementList {
    protected ArrayList<Treatment> treatments = new ArrayList<>();

    @Override
    public ModelXBase AddNew(String name) {
        Treatment model = new Treatment(name);
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
}
