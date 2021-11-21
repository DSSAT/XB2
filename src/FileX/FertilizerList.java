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
public class FertilizerList {
    protected Vector fertils = new Vector();

    public void AddNew(Fertilizer fertil)
    {
        fertils.add(fertil);
    }

    public void RemoveAt(int level)
    {
        fertils.remove(level);
    }

    public void SetAt(int level, Fertilizer fertil)
    {
        fertils.set(level, fertil);
    }

    public Fertilizer[] GetAll()
    {
        return (Fertilizer[]) fertils.toArray();
    }

    public Fertilizer GetAt(int level)
    {
        return (Fertilizer)fertils.get(level);
    }

    public int GetSize()
    {
        return fertils.size();
    }
    
    public Fertilizer Clone(int level, String newName){
        Fertilizer source = GetAt(level);
        Fertilizer newSource = null;
        
        try{
            newSource = source.clone();
            newSource.FERNAME = newName;
        }
        catch(Exception ex){
            
        }
        
        return newSource;
    }
}
