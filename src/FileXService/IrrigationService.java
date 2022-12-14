package FileXService;

import Extensions.Utils;
import static FileXModel.FileX.irrigations;
import FileXModel.Irrigation;
import FileXModel.IrrigationApplication;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 *
 * @author Jazz
 */
public class IrrigationService {
    public static void Read(File fileName) {
        try {
            FileReader fReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fReader);
            String strRead = null;
            
            String irrigHeader1 = "";
            String irrigHeader2 = "";
            boolean bIrrigHeader1 = false;
            boolean bIrrigHeader2 = false;
            boolean bIrrig = false;
            
            while ((strRead = br.readLine()) != null) {
                String tmp = strRead;
                
                if (tmp.trim().startsWith("*IRRIGATION AND WATER MANAGEMENT")) {
                    bIrrig = true;
                } else if (bIrrig && !bIrrigHeader1 && !bIrrigHeader2 && tmp.trim().startsWith("@")) {
                    irrigHeader1 = tmp.trim();
                    bIrrigHeader1 = true;
                } else if (bIrrig && bIrrigHeader1 && !bIrrigHeader2 && tmp.trim().startsWith("@")) {
                    irrigHeader2 = tmp.trim();
                    bIrrigHeader2 = true;
                } else if (bIrrig && bIrrigHeader1 && bIrrigHeader2 && tmp.trim().startsWith("@")) {
                    bIrrigHeader2 = false;
                }else if (bIrrig && bIrrigHeader1 && !bIrrigHeader2) {
                    if (tmp.trim().isEmpty()) {
                        bIrrig = false;
                        bIrrigHeader1 = false;
                        bIrrigHeader2 = false;
                        continue;
                    }
                    //@I  EFIR  IDEP  ITHR  IEPT  IOFF  IAME  IAMT IRNAME
                    Irrigation irrig = new Irrigation();
                    irrig.EFIR = Utils.GetFloat(irrigHeader1, tmp, "  EFIR", 5);

                    irrig.IRNAME = Utils.GetString(irrigHeader1, tmp, "IRNAME", tmp.length() - irrigHeader1.indexOf("IRNAME"));
                    irrigations.AddNew(irrig);
                } else if (bIrrig && bIrrigHeader1 && bIrrigHeader2) {
                    if (tmp.trim().isEmpty()) {
                        bIrrig = false;
                        bIrrigHeader1 = false;
                        bIrrigHeader2 = false;
                        continue;
                    }
                    //@I IDATE  IROP IRVAL
                    try {
                        Integer level = Integer.parseInt(tmp.substring(0, 2).trim()) - 1;
                        Irrigation irrig = (Irrigation)irrigations.GetAt(level);
                        IrrigationApplication irrigApp = new IrrigationApplication();
                        try{
                            irrigApp.IDATE = Utils.GetDate(irrigHeader2, tmp, "IDATE", 5);
                        }
                        catch(Exception ex)
                        {
                            irrigApp.IDAY = Utils.GetInteger(irrigHeader2, tmp, "IDATE", 5);
                        }
                        irrigApp.IROP = Utils.GetString(irrigHeader2, tmp, " IROP", 5);
                        irrigApp.IRVAL = Utils.GetFloat(irrigHeader2, tmp, "IRVAL", 5);
                        irrig.AddApp(irrigApp);

                    } catch (NumberFormatException numberFormatException) {
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void Extract(PrintWriter pw){
        // <editor-fold defaultstate="collapsed" desc="Irrigation">
        if (irrigations.GetSize() > 0) {
            pw.println();
            pw.println("*IRRIGATION AND WATER MANAGEMENT");

            for (int i = 0; i < irrigations.GetSize(); i++) {
                Integer level = i + 1;
                Irrigation irrig = (Irrigation)irrigations.GetAt(i);

                pw.println("@I  EFIR  IDEP  ITHR  IEPT  IOFF  IAME  IAMT IRNAME");
                pw.print(Utils.PadLeft(level, 2, ' '));
                pw.print(" " + Utils.PadLeft(irrig.EFIR, 5, ' '));
                pw.print("   -99   -99   -99   -99   -99     1");
                if (irrig.IRNAME != null) {
                    pw.print(" " + irrig.IRNAME);
                } else {
                    pw.print(" -99");
                }
                pw.println();
                if (irrig.GetSize() > 0) {
                    pw.println("@I IDATE  IROP IRVAL");
                    for (int n = 0; n < irrig.GetSize(); n++) {
                        IrrigationApplication irrigApp = irrig.GetApp(n);
                        pw.print(Utils.PadLeft(level, 2, ' '));

                        if(irrigApp.IDATE != null)
                            pw.print(" " + Utils.PadRight(Utils.JulianDate(irrigApp.IDATE), 5, ' '));
                        else
                            pw.print(" " + Utils.PadLeft(irrigApp.IDAY, 5, ' '));

                        pw.print(" " + Utils.PadRight(irrigApp.IROP, 5, ' '));
                        pw.print(" " + Utils.PadLeft(irrigApp.IRVAL, 5, ' '));
                        pw.println();
                    }
                }
            }
        }
        // </editor-fold>
    }
}
