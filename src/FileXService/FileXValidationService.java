package FileXService;

import DSSATModel.ExperimentType;
import Extensions.Utils;
import FileXModel.FieldDetail;
import FileXModel.FileX;

/**
 *
 * @author Jazzy
 */
public class FileXValidationService {
    public static boolean IsGeneralValid(){
    boolean isValid = true;
        
        if (FileX.general != null && (Utils.IsEmpty(FileX.general.SiteCode) || Utils.IsEmpty(FileX.general.InstituteCode) || Utils.IsEmpty(FileX.general.Year))) {
            isValid = false;
        }
        if (FileX.general != null && FileX.general.FileType == ExperimentType.Experimental && (FileX.general.crop == null || Utils.IsEmpty(FileX.general.crop.CropCode))) {
            isValid = false;
        }
        return isValid;
    }
    
    public static boolean IsMinimumRequired(){
        boolean isValid = IsGeneralValid();
        
        isValid = FileX.fieldList != null && FileX.fieldList.GetSize() > 0
                && !Utils.IsEmpty(((FieldDetail) FileX.fieldList.GetAt(0)).WSTA)
                && !Utils.IsEmpty(((FieldDetail) FileX.fieldList.GetAt(0)).ID_SOIL)
                && FileX.cultivars != null && FileX.cultivars.GetSize() > 0
                && FileX.plantings != null && FileX.plantings.GetSize() > 0
                && FileX.simulationList != null && FileX.simulationList.GetSize() > 0;
        
        return isValid;
    }
}
