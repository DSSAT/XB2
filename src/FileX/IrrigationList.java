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
public class IrrigationList {
    protected Vector irrigs = new Vector();

    public void AddNew(Irrigation irrig)
    {
        irrigs.add(irrig);
    }

    public void RemoveAt(int level)
    {
        irrigs.remove(level);
    }

    public void SetAt(int level, Irrigation irrig)
    {
        irrigs.set(level, irrig);
    }

    public Irrigation[] GetAll()
    {
        return (Irrigation[]) irrigs.toArray();
    }

    public Irrigation GetAt(int level)
    {
        return (Irrigation)irrigs.get(level);
    }

    public int GetSize()
    {
        return irrigs.size();
    }
    
    public Irrigation Clone(int level, String newName){
        Irrigation source = GetAt(level);
        Irrigation newSource = null;
        
        try{
            newSource = source.clone();
            newSource.IRNAME = newName;
        }
        catch(Exception ex){
            
        }
        
        return newSource;
    }
}
