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
public class OrganicList {
    protected Vector organics = new Vector();

    public void AddNew(Organic organic)
    {
        organics.add(organic);
    }

    public void RemoveAt(int level)
    {
        organics.remove(level);
    }

    public void SetAt(int level, Organic organic)
    {
        organics.set(level, organic);
    }

    public Organic[] GetAll()
    {
        return (Organic[]) organics.toArray();
    }

    public Organic GetAt(int level)
    {
        return (Organic)organics.get(level);
    }

    public int GetSize()
    {
        return organics.size();
    }
    
    public Organic Clone(int level, String newName){
        Organic source = GetAt(level);
        Organic newSource = null;
        
        try{
            newSource = source.clone();
            newSource.RENAME = newName;
        }
        catch(Exception ex){
            
        }
        
        return newSource;
    }
}
