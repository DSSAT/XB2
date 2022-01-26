/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

/**
 *
 * @author Jazzy
 */
public class FieldList extends ManagementList {
    
    @Override
    public void AddNew(String name)
    {
        modelList.add(new FieldDetail(name));
    }
    
    @Override
    public IModelXBase Clone(String sourceName, String newName){
        FieldDetail source = (FieldDetail) GetAt(sourceName);
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
