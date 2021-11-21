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
public class EnvironmentalList {

    protected Vector environmental = new Vector();

    public void AddNew(Environmental env)
    {
        environmental.add(env);
    }

    public void RemoveAt(int level)
    {
        environmental.remove(level);
    }

    public void SetAt(int level, Environmental env)
    {
        environmental.set(level, env);
    }

    public Environmental[] GetAll()
    {
        return (Environmental[]) environmental.toArray();
    }

    public Environmental GetAt(int level)
    {
        return (Environmental)environmental.get(level);
    }

    public int GetSize()
    {
        return environmental.size();
    }
    
    public Environmental Clone(int level, String newName){
        Environmental source = GetAt(level);
        Environmental newSource = null;
        
        try{
            newSource = source.clone();
            newSource.ENVNAME = newName;
        }
        catch(Exception ex){
            
        }
        
        return newSource;
    }
}
