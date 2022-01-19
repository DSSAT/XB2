/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

import java.util.ArrayList;

/**
 *
 * @author Jazzy
 */
public class TreatmentList {
    protected ArrayList<Treatment> treatments = new ArrayList<>();

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

    public ArrayList<Treatment> GetAll()
    {
        return treatments;
    }

    public Treatment GetAt(int level)
    {
        return treatments.get(level);
    }

    public int GetSize()
    {
        return treatments.size();
    }
}
