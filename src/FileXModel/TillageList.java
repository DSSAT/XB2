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
public class TillageList {
    protected Vector tillages = new Vector();

    public void AddNew(Tillage tillage)
    {
        tillages.add(tillage);
    }

    public void RemoveAt(int level)
    {
        tillages.remove(level);
    }

    public void SetAt(int level, Tillage tillage)
    {
        tillages.set(level, tillage);
    }

    public Tillage[] GetAll()
    {
        return (Tillage[]) tillages.toArray();
    }

    public Tillage GetAt(int level)
    {
        return (Tillage)tillages.get(level);
    }

    public int GetSize()
    {
        return tillages.size();
    }
    
    public Tillage Clone(int level, String newName){
        Tillage source = GetAt(level);
        Tillage newSource = null;
        
        try{
            newSource = source.clone();
            newSource.TNAME = newName;
        }
        catch(Exception ex){
            
        }
        
        return newSource;
    }
}
