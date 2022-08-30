/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

import Extensions.Utils;

/**
 *
 * @author Jazzy
 */
public class FieldList extends ManagementList {
    
    @Override
    public void AddNew(String name)
    {
        FieldDetail f = new FieldDetail(name);
        int expNo = GetSize() + Utils.ParseInteger(FileX.general.ExperimentNumber);
        f.ID_FIELD = FileX.general.InstituteCode + FileX.general.SiteCode + FileX.general.Year.substring(2) + Utils.PadLeft(expNo, 2, '0');
        modelList.add(f);
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
