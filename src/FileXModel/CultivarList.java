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
public class CultivarList extends ManagementList {
    protected ArrayList<Cultivar> cultivars = new ArrayList<>();

//    public void AddNew(Cultivar cul)
//    {
//        cultivars.add(cul);
//    }
//
//    public void RemoveAt(int level)
//    {
//        cultivars.remove(level);
//    }
//
//    public void SetAt(int level, Cultivar cul)
//    {
//        cultivars.set(level, cul);
//    }

    @Override
    public ModelXBase AddNew(String name, int newLevel, int currentLevel) {
        Cultivar model = new Cultivar(name);
        modelList.add(model);
        model.SetLevel(newLevel);
        return model;
    }

    @Override
    public ModelXBase Clone(int sourceIndex, String newName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean IsUseInTreatment(int level) {
        boolean isUsed = false;
        
        for (ModelXBase treatment : FileX.treatments.GetAll()) {
            Treatment treat = (Treatment) treatment;
            if (treat.CU != null && treat.CU == level) {
                isUsed = true;
                break;
            }
        }
        return isUsed;
    }
}
