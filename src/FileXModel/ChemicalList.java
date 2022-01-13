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
public class ChemicalList {
    protected Vector chems = new Vector();

    public void AddNew(Chemical chem)
    {
        chems.add(chem);
    }

    public void RemoveAt(int level)
    {
        chems.remove(level);
    }

    public void SetAt(int level, Chemical chem)
    {
        chems.set(level, chem);
    }

    public Chemical[] GetAll()
    {
        return (Chemical[]) chems.toArray();
    }

    public Chemical GetAt(int level)
    {
        return (Chemical)chems.get(level);
    }

    public int GetSize()
    {
        return chems.size();
    }
    
    public Chemical Clone(int level, String newName){
        Chemical source = GetAt(level);
        Chemical newSource = null;
        
        try{
            newSource = source.clone();
            newSource.CHNAME = newName;
        }
        catch(Exception ex){
            
        }
        
        return newSource;
    }
}
