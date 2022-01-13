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
public class FieldList {
    protected Vector fields = new Vector();

    public void AddNew(FieldDetail field)
    {
        fields.add(field);
    }

    public void RemoveAt(int level)
    {
        fields.remove(level);
    }

    public void SetAt(int level, FieldDetail field)
    {
        fields.set(level, field);
    }

    public FieldDetail[] GetAll()
    {
        return (FieldDetail[]) fields.toArray();
    }

    public FieldDetail GetAt(int level)
    {
        return (FieldDetail)fields.get(level);
    }

    public int GetSize()
    {
        return fields.size();
    }
    
    public FieldDetail Clone(int level, String newName){
        FieldDetail source = GetAt(level);
        FieldDetail newfield = null;
        
        try{
            newfield = source.clone();
            newfield.FLNAME = newName;
        }
        catch(Exception ex){
            
        }
        
        return newfield;
    }
}
