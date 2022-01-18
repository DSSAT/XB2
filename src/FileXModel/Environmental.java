/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

import java.util.*;

/**
 *
 * @author Jazzy
 */
public class Environmental implements Cloneable {
    protected ArrayList<EnvironmentApplication>  envApps = new ArrayList<>();
    public String ENVNAME;

    public Environmental(String ENVNAME)
    {
        this.ENVNAME = ENVNAME;
    }

    public Environmental()
    {
    }

    public void AddApp(EnvironmentApplication env)
    {
        envApps.add(env);
    }

    public void RemoveAt(int level)
    {
        envApps.remove(level);
    }

    public void SetAt(int level, EnvironmentApplication env)
    {
        envApps.set(level, env);
    }

    public ArrayList<EnvironmentApplication> GetApps()
    {
        return envApps;
    }

    public EnvironmentApplication GetApp(int level)
    {
        return (EnvironmentApplication)envApps.get(level);
    }

    public int GetSize()
    {
        return envApps.size();
    }
    
    public Environmental clone() throws CloneNotSupportedException{
        return (Environmental) super.clone();
    }
}
