package FileXService;

import Extensions.Utils;
import FileXModel.FileX;
import static FileXModel.FileX.treaments;
import FileXModel.Treatment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 *
 * @author PCMIWS16
 */
public class TreatmentService {
    public static void Read(File fileName) {
        try {
            FileReader fReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fReader);
            String strRead = null;
            
            String treatmentHeader = "";
            boolean bTreatmentHeader = false;
            boolean bTreatment = false;
            
            while ((strRead = br.readLine()) != null) {
                if (strRead.trim().startsWith("*TREATMENTS")) {
                    bTreatment = true;

                } else if (bTreatment && !bTreatmentHeader && strRead.trim().startsWith("@")) {
                    treatmentHeader = strRead.trim();
                    bTreatmentHeader = true;
                } else if (bTreatment && bTreatmentHeader) {
                    if (strRead.trim().isEmpty()) {
                        bTreatment = false;
                        bTreatmentHeader = false;
                        continue;
                    }
                    //TNAME.................... CU FL SA IC MP MI MF MR MC MT ME MH SM
                    
                    Treatment treatment = new Treatment();
                    treatment.N = Utils.GetInteger(treatmentHeader, strRead, "@N", 2);
                    if (FileX.general.FileType.equalsIgnoreCase("Sequential")) {
                        treatment.R = Utils.GetString(treatmentHeader, strRead, "R", 1);
                        treatment.O = Utils.GetString(treatmentHeader, strRead, "O", 1);
                        treatment.C = Utils.GetString(treatmentHeader, strRead, "C", 1);
                    }
                    
                    treatment.TNAME = Utils.GetString(treatmentHeader, strRead, "TNAME", 25);
                    treatment.CU = Utils.GetInteger(treatmentHeader, strRead, "CU", 2);
                    treatment.FL = Utils.GetInteger(treatmentHeader, strRead, "FL", 2);
                    treatment.SA = Utils.GetInteger(treatmentHeader, strRead, "SA", 2);
                    treatment.IC = Utils.GetInteger(treatmentHeader, strRead, "IC", 2);
                    treatment.MP = Utils.GetInteger(treatmentHeader, strRead, "MP", 2);
                    treatment.MI = Utils.GetInteger(treatmentHeader, strRead, "MI", 2);
                    treatment.MF = Utils.GetInteger(treatmentHeader, strRead, "MF", 2);
                    treatment.MR = Utils.GetInteger(treatmentHeader, strRead, "MR", 2);
                    treatment.MC = Utils.GetInteger(treatmentHeader, strRead, "MC", 2);
                    treatment.MT = Utils.GetInteger(treatmentHeader, strRead, "MT", 2);
                    treatment.ME = Utils.GetInteger(treatmentHeader, strRead, "ME", 2);
                    treatment.MH = Utils.GetInteger(treatmentHeader, strRead, "MH", 2);
                    treatment.SM = Utils.GetInteger(treatmentHeader, strRead, "SM", 2);
                    treaments.AddNew(treatment);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void Extract(PrintWriter pw){
        // <editor-fold defaultstate="collapsed" desc="Treatment">
        if (treaments.GetSize() > 0) {
            pw.println();
            pw.println();
            pw.println("*TREATMENTS                        -------------FACTOR LEVELS------------");
            pw.println("@N R O C TNAME.................... CU FL SA IC MP MI MF MR MC MT ME MH SM");
            for (int i = 0; i < treaments.GetSize(); i++) {
                Treatment treat = treaments.GetAt(i);
                pw.print(Utils.PadLeft(treat.N, 2, ' '));
                
                if(!FileX.general.FileType.equalsIgnoreCase("Sequential"))
                    pw.print(" 1 0 0");
                else{
                    try {
                        if (!treat.R.isEmpty()) {
                            pw.print(' ' + treat.R.substring(0, 1));
                        } else {
                            pw.print(' ' + "0");
                        }
                    } catch (Exception e) {
                        pw.print(' ' + "0");
                    }
                    try {
                        if (!treat.O.isEmpty()) {
                            pw.print(' ' + treat.O.substring(0, 1));
                        } else {
                            pw.print(' ' + "0");
                        }
                    } catch (Exception e) {
                        pw.print(' ' + "0");
                    }
                    try {
                        if (!treat.C.isEmpty()) {
                            pw.print(' ' + treat.C.substring(0, 1));
                        } else {
                            pw.print(' ' + "0");
                        }
                    } catch (Exception e) {
                        pw.print(' ' + "0");
                    }
                }               
                
                try {
                    if (!treat.TNAME.isEmpty()) {
                        pw.print(" " + Utils.PadRight(treat.TNAME, 25, ' '));
                    } else {
                        pw.print(" " + Utils.PadRight("", 25, ' '));
                    }
                } catch (Exception e) {
                    pw.print(" " + Utils.PadRight("", 25, ' '));
                }
                try {
                    String tmp = treat.CU.toString();
                    pw.print(" " + Utils.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.FL.toString();
                    pw.print(" " + Utils.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.SA.toString();
                    pw.print(" " + Utils.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.IC.toString();
                    pw.print(" " + Utils.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.MP.toString();
                    pw.print(" " + Utils.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.MI.toString();
                    pw.print(" " + Utils.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.MF.toString();
                    pw.print(" " + Utils.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.MR.toString();
                    pw.print(" " + Utils.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.MC.toString();
                    pw.print(" " + Utils.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.MT.toString();
                    pw.print(" " + Utils.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.ME.toString();
                    pw.print(" " + Utils.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.MH.toString();
                    pw.print(" " + Utils.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.SM.toString();
                    pw.print(" " + Utils.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                pw.println();
            }
        }
        // </editor-fold>
    }
}
