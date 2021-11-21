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
public class HarvestList {

    protected Vector harvests = new Vector();

    public void AddNew(Harvest harvest)
    {
        harvests.add(harvest);
    }

    public void RemoveAt(int level)
    {
        harvests.remove(level);
    }

    public void SetAt(int level, Harvest harvest)
    {
        harvests.set(level, harvest);
    }

    public Harvest GetAt(int level)
    {
        return (Harvest)harvests.get(level);
    }

    public int GetSize()
    {
        return harvests.size();
    }
    
    public Harvest Clone(int level, String newName){
        Harvest source = GetAt(level);
        Harvest newSource = null;
        
        try{
            newSource = source.clone();
            newSource.HNAME = newName;
        }
        catch(Exception ex){
            
        }
        
        return newSource;
    }
}
