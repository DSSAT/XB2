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
public class TreatmentList {
    protected Vector treatments = new Vector();

    public void AddNew(Treatment treat)
    {
        treatments.add(treat);
    }

    public void RemoveAt(int level)
    {
        treatments.remove(level);
    }

    public void SetAt(int level, Treatment treat)
    {
        treatments.set(level, treat);
    }

    public Treatment[] GetAll()
    {
        return (Treatment[]) treatments.toArray();
    }

    public Treatment GetAt(int level)
    {
        return (Treatment)treatments.get(level);
    }

    public int GetSize()
    {
        return treatments.size();
    }
}
