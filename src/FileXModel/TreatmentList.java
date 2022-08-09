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
    public void AddNew(String name) {
        modelList.add(new Treatment(name));
    }

    @Override
    public IModelXBase Clone(String sourceName, String newName) {
        Treatment source = (Treatment) GetAt(sourceName);
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
