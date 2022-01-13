/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

import java.util.Vector;

/**
 *
 * @author Jazzy
 */
public class InitialConditionList {
    protected Vector inits = new Vector();

    public void AddNew(InitialCondition init)
    {
        inits.add(init);
    }

    public void RemoveAt(int level)
    {
        inits.remove(level);
    }

    public void SetAt(int level, InitialCondition init)
    {
        inits.set(level, init);
    }

    public InitialCondition[] GetAll()
    {
        return (InitialCondition[]) inits.toArray();
    }

    public InitialCondition GetAt(int level)
    {
        return (InitialCondition)inits.get(level);
    }

    public int GetSize()
    {
        return inits.size();
    }
    
    public InitialCondition Clone(int level, String newName){
        InitialCondition source = GetAt(level);
        InitialCondition newfield = null;
        
        try{
            newfield = source.clone();
            newfield.ICNAME = newName;
        }
        catch(Exception ex){
            
        }
        
        return newfield;
    }
}
