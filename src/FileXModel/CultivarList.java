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
public class CultivarList {
    protected Vector cultivars = new Vector();

    public void AddNew(Cultivar cul)
    {
        cultivars.add(cul);
    }

    public void RemoveAt(int level)
    {
        cultivars.remove(level);
    }

    public void SetAt(int level, Cultivar cul)
    {
        cultivars.set(level, cul);
    }

    public Cultivar[] GetAll()
    {
        return (Cultivar[]) cultivars.toArray();
    }

    public Cultivar GetAt(int level)
    {
        return (Cultivar)cultivars.get(level);
    }

    public int GetSize()
    {
        return cultivars.size();
    }
}
