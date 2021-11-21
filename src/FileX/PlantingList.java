/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileX;

import java.util.Vector;

/**
 *
 * @author Jazzy
 */
public class PlantingList {
    protected Vector planting = new Vector();

    public void AddNew(Planting p)
    {
        planting.add(p);
    }

    public void RemoveAt(int level)
    {
        planting.remove(level);
    }

    public void SetAt(int level, Planting p)
    {
        planting.set(level, p);
    }

    public Planting[] GetAll()
    {
        return (Planting[]) planting.toArray();
    }

    public Planting GetAt(int level)
    {
        return (Planting)planting.get(level);
    }

    public int GetSize()
    {
        return planting.size();
    }
    
    public Planting Clone(int level, String newName){
        Planting source = GetAt(level);
        Planting newSource = null;
        
        try{
            newSource = source.clone();
            newSource.PLNAME = newName;
        }
        catch(Exception ex){
            
        }
        
        return newSource;
    }
}
