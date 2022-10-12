package FileXService;

import DSSATModel.ExperimentType;
import Extensions.Utils;
import FileXModel.Cultivar;
import FileXModel.FieldDetail;
import FileXModel.FileX;
import FileXModel.IModelXBase;
import FileXModel.Planting;
import FileXModel.Simulation;

/**
 *
 * @author Jazzy
 */
public class FileXValidationService {

    private static String getNodeName(String node) {
        if (node == null || !node.contains(":")) {
            return node;
        }
        
        String[] names = node.split(":");
        
        return names.length > 1 ? node.split(":")[1].trim() : "";
    }

    public static boolean IsGeneralValid() {
        boolean isValid = true;

        if (FileX.general != null && (Utils.IsEmpty(FileX.general.SiteCode) || Utils.IsEmpty(FileX.general.InstituteCode) || Utils.IsEmpty(FileX.general.Year))) {
            isValid = false;
        }
        if (FileX.general != null && FileX.general.FileType == ExperimentType.Experimental && (FileX.general.crop == null || Utils.IsEmpty(FileX.general.crop.CropCode))) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean IsMinimumRequired() {
        boolean isValid = IsGeneralValid();

        isValid = FileX.fieldList != null && FileX.fieldList.GetSize() > 0 && IsFieldValid(FileX.fieldList.GetAt(0).GetName())
                && FileX.cultivars != null && FileX.cultivars.GetSize() > 0
                && FileX.plantings != null && FileX.plantings.GetSize() > 0 && IsPlantingValid(FileX.plantings.GetAt(0).GetName())
                && FileX.simulationList != null && FileX.simulationList.GetSize() > 0 && IsSimulationControlValid(FileX.simulationList.GetAt(0).GetName());

        return isValid;
    }

    public static boolean IsFieldsValid() {
        boolean isValid = true;

        if (FileX.fieldList == null || FileX.fieldList.GetSize() == 0) {
            isValid = false;
        } else {
            for (IModelXBase field : FileX.fieldList.GetAll()) {
                FieldDetail f = (FieldDetail) field;
                if (f.WSTA == null || f.WSTA.isEmpty()) {
                    isValid = false;
                } else if (f.ID_SOIL == null || f.ID_SOIL.isEmpty()) {
                    isValid = false;
                }
            }
        }
        return isValid;
    }

    public static boolean IsFieldValid(String node) {
        boolean isValid = true;

        for (IModelXBase field : FileX.fieldList.GetAll()) {
            FieldDetail f = (FieldDetail) field;
            if (f.FLNAME == null ? getNodeName(node) == null : f.FLNAME.equals(getNodeName(node))) {
                if (f.WSTA == null || f.WSTA.isEmpty()) {
                    isValid = false;
                } else if (f.ID_SOIL == null || f.ID_SOIL.isEmpty()) {
                    isValid = false;
                }
            }
        }

        return isValid;
    }

    public static boolean IsCultivarsValid() {
        boolean isValid = true;

        if (FileX.cultivars == null || FileX.cultivars.GetSize() == 0) {
            isValid = false;
        } else {
            for (IModelXBase cul : FileX.cultivars.GetAll()) {
                Cultivar c = (Cultivar) cul;
                if (c.CR == null || c.CR.isEmpty()) {
                    isValid = false;
                }
            }
        }
        return isValid;
    }

    public static boolean IsPlantingsValid() {
        boolean isValid = true;

        if (FileX.plantings == null || FileX.plantings.GetSize() == 0) {
            isValid = false;
        } else {
            for (IModelXBase planting : FileX.plantings.GetAll()) {
                Planting p = (Planting) planting;
                if (p.PDATE == null) {
                    isValid = false;
                } else if (p.PLME == null || p.PLME.isEmpty()) {
                    isValid = false;
                } else if (p.PLDS == null || p.PLDS.isEmpty()) {
                    isValid = false;
                } else if (p.PLRS == null) {
                    isValid = false;
                } else if (p.PLRD == null) {
                    isValid = false;
                } else if (p.PLDP == null) {
                    isValid = false;
                } else if (p.PPOP == null) {
                    isValid = false;
                }

            }
        }
        return isValid;
    }

    public static boolean IsPlantingValid(String node) {
        boolean isValid = true;

        for (IModelXBase planting : FileX.plantings.GetAll()) {
            Planting p = (Planting) planting;
            if (p.PLNAME.equals(getNodeName(node))) {
                if (p.PDATE == null) {
                    isValid = false;
                } else if (p.PLME == null || p.PLME.isEmpty()) {
                    isValid = false;
                } else if (p.PLDS == null || p.PLDS.isEmpty()) {
                    isValid = false;
                } else if (p.PLRS == null) {
                    isValid = false;
                } else if (p.PLRD == null) {
                    isValid = false;
                } else if (p.PLDP == null) {
                    isValid = false;
                } else if (p.PPOP == null) {
                    isValid = false;
                }
            }
        }
        return isValid;
    }

    public static boolean IsSimulationControlsValid() {
        boolean isValid = true;
        if (FileX.simulationList == null || FileX.simulationList.GetSize() == 0) {
            isValid = false;
        } else {
            for (IModelXBase simulation : FileX.simulationList.GetAll()) {
                Simulation s = (Simulation) simulation;
                if (s.SDATE == null) {
                    isValid = false;
                }
            }
        }
        return isValid;
    }

    public static boolean IsSimulationControlValid(String node) {
        boolean isValid = true;

        for (IModelXBase simulation : FileX.simulationList.GetAll()) {
            Simulation s = (Simulation) simulation;
            if (s.SNAME.equals(getNodeName(node))) {
                if (s.SDATE == null) {
                    isValid = false;
                }
            }
        }
        return isValid;
    }
}
