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
    public void AddNew(String name) {
        modelList.add(new Cultivar(name));
    }

    @Override
    public IModelXBase Clone(String sourceName, String newName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
