package FileXService;

import DSSATModel.ExperimentType;
import Extensions.Utils;
import FileXModel.Comment;
import static FileXModel.FileX.comments;
import static FileXModel.FileX.general;
import FileXModel.Treatment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import static FileXModel.FileX.treatments;
import FileXModel.Section;

/**
 *
 * @author PCMIWS16
 */
public class TreatmentService {
    public static void Read(File fileName) {
        try {
            FileReader fReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fReader);
            String strRead;
            
            String treatmentHeader = "";
            boolean bTreatmentHeader = false;
            boolean bTreatment = false;
            
            while ((strRead = br.readLine()) != null) {
                if (strRead.trim().startsWith("*TREATMENTS")) {
                    bTreatment = true;

                } else if (bTreatment && !bTreatmentHeader && strRead.trim().startsWith("@")) {
                    treatmentHeader = strRead.trim();
                    bTreatmentHeader = true;
                } 
                else if(bTreatment && bTreatmentHeader && strRead.trim().startsWith("*")){
                    bTreatment = false;
                    bTreatmentHeader = false;
                }
                else if (bTreatment && bTreatmentHeader && !"".equals(strRead.trim())) {
                    if(strRead.trim().startsWith("!")){
                        int l = 1;
                        if(treatments.GetSize() > 0){
                            l = treatments.GetAtIndex(treatments.GetSize() - 1).GetLevel();
                        }
                        comments.addComment(l, Section.Treatment, strRead);
                        continue;
                    }
                    //TNAME.................... CU FL SA IC MP MI MF MR MC MT ME MH SM
                    Treatment treatment = new Treatment();
                    String treatmentLine = Utils.PadRight(strRead, treatmentHeader.length(), ' ');

                    if (general.FileType == ExperimentType.Sequential) {
                        int[] nr = Utils.parseSequenceNR(treatmentLine);
                        treatment.SetLevel(nr[0]);
                        treatment.R = String.valueOf(nr[1]);
                        treatment.O = Utils.parseSequenceO(treatmentLine);
                        treatment.C = Utils.parseSequenceC(treatmentLine);
                        treatment.TNAME = treatmentLine.substring(8, Math.min(33, treatmentLine.length())).trim();
                    } else {
                        treatment.SetLevel(Utils.GetInteger(treatmentHeader, treatmentLine, "@N", 3));
                        treatment.R = Utils.GetString(treatmentHeader, treatmentLine, " R", 2);
                        treatment.O = Utils.GetString(treatmentHeader, treatmentLine, " O", 2);
                        treatment.C = Utils.GetString(treatmentHeader, treatmentLine, " C", 2);
                        treatment.TNAME = Utils.GetString(treatmentHeader, treatmentLine, "TNAME", 25);
                    }
                    
                    treatment.CU = getTreatmentInteger(treatmentHeader, treatmentLine, " CU", 3);
                    treatment.FL = getTreatmentInteger(treatmentHeader, treatmentLine, " FL", 3);
                    treatment.SA = getTreatmentInteger(treatmentHeader, treatmentLine, " SA", 3);
                    treatment.IC = getTreatmentInteger(treatmentHeader, treatmentLine, " IC", 3);
                    treatment.MP = getTreatmentInteger(treatmentHeader, treatmentLine, " MP", 3);
                    treatment.MI = getTreatmentInteger(treatmentHeader, treatmentLine, " MI", 3);
                    treatment.MF = getTreatmentInteger(treatmentHeader, treatmentLine, " MF", 3);
                    treatment.MR = getTreatmentInteger(treatmentHeader, treatmentLine, " MR", 3);
                    treatment.MC = getTreatmentInteger(treatmentHeader, treatmentLine, " MC", 3);
                    treatment.MT = getTreatmentInteger(treatmentHeader, treatmentLine, " MT", 3);
                    treatment.ME = getTreatmentInteger(treatmentHeader, treatmentLine, " ME", 3);
                    treatment.MH = getTreatmentInteger(treatmentHeader, treatmentLine, " MH", 3);
                    treatment.SM = getTreatmentInteger(treatmentHeader, treatmentLine, " SM", 3);
                    treatments.AddNew(treatment);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void Extract(PrintWriter pw){
        // <editor-fold defaultstate="collapsed" desc="Treatment">
        if (treatments.GetSize() > 0) {
            pw.println();
            pw.println("*TREATMENTS                        -------------FACTOR LEVELS------------");
            pw.println("@N R O C TNAME.................... CU FL SA IC MP MI MF MR MC MT ME MH SM");
            for (int i = 0; i < treatments.GetSize(); i++) {
                Treatment treat = (Treatment) treatments.GetAtIndex(i);
                int level = treat.GetLevel();

                if (general.FileType == ExperimentType.Sequential) {
                    int r = Utils.ParseInteger(treat.R);
                    if (r < 10) {
                        pw.print(' ' + String.valueOf(level));
                        pw.print(' ' + String.valueOf(r));
                    } else {
                        pw.print(' ' + Utils.formatSequenceNR(level, r));
                    }
                    pw.print(' ' + Utils.formatTreatmentDigit(treat.O, "0"));
                    pw.print(' ' + Utils.formatTreatmentDigit(treat.C, "0"));
                } else {
                    pw.print(Utils.PadLeft(level, 3, ' '));
                    pw.print(" 1 0 0");
                }
                
                try {
                    if (!"".equals(treat.TNAME)) {
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
                
                for (Comment comment : comments.getAll(level, Section.Treatment)) {
                    pw.println(comment.description);
                }
            }
        }
        // </editor-fold>
    }

    private static Integer getTreatmentInteger(String header, String line, String field, int fieldLength) {
        int start = header.indexOf(field);
        if (start < 0) {
            return null;
        }

        int stop = Math.min(start + fieldLength, line.length());
        if (stop <= start) {
            return null;
        }

        String tmp = line.substring(start, stop).trim();
        if (tmp.isEmpty() || "-99".equals(tmp)) {
            return null;
        }

        return Integer.valueOf(tmp);
    }
}
