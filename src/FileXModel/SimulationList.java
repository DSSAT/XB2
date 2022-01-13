/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Jazzy
 */
public class SimulationList {
    protected Vector sims = new Vector();

    public void AddNew(Simulation sim)
    {
        sims.add(sim);
    }

    public void RemoveAt(int level)
    {
        sims.remove(level);
    }
    
    public void RemoveAt(String name)
    {
        int level = -1;
        for(int i = 0; i < sims.size();i++){
            if(GetAt(i).SNAME.equals(name)){
                level = i;
                break;
            }
        }
        if(level >= 0)
            sims.remove(level);
    }
    
    public String GetCopyName(String name){
        String copyName = "";
        int max = 0;
        for(int i = 0; i < sims.size();i++){
            if(GetAt(i).SNAME.startsWith(name)){
                max = i;
            }
        }
        
        return name + " (" + (max + 1) + ")";
    }

    public void SetAt(int level, Simulation sim)
    {
        sims.set(level, sim);
    }

    public List<Simulation> GetAll()
    {
        List<Simulation> simList = new ArrayList<>();
        for(int i = 0; i < sims.size();i++){
            simList.add((Simulation) sims.get(i));
        }
        
        return simList;
    }

    public Simulation GetAt(int level)
    {
        return (Simulation)sims.get(level);
    }
    
    public Simulation GetAt(String name)
    {
        for(int i = 0; i < sims.size();i++){
            if(GetAt(i).SNAME.equals(name)){
                return GetAt(i);
            }
        }
        return null;
    }
    
    public Simulation Copy(String sourceName, String newName){
        Simulation source = GetAt(sourceName);
        Simulation newSim = null;
        
        try{
            newSim = source.clone();
            newSim.SNAME = newName;
        }
        catch(Exception ex){
            
        }
        
        return newSim;
    }

    public int GetSize()
    {
        return sims.size();
    }
}
