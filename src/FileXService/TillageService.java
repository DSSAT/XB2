package FileXService;

import Extensions.Utils;
import static FileXModel.FileX.tillageList;
import FileXModel.Tillage;
import FileXModel.TillageApplication;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 *
 * @author Jazz
 */
public class TillageService {
    public static void Read(File fileName) {
        try {
            FileReader fReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fReader);
            String strRead = null;
            
            String tillageHeader = "";
            boolean bTillageHeader = false;
            boolean bTillage = false;
            
            while ((strRead = br.readLine()) != null) {
                String tmp = strRead;
                
                if (tmp.trim().startsWith("*TILLAGE AND ROTATIONS")) {
                    bTillage = true;

                } else if (bTillage && !bTillageHeader && tmp.trim().startsWith("@")) {
                    tillageHeader = tmp.trim();
                    bTillageHeader = true;
                } else if (bTillage && bTillageHeader && !tmp.trim().startsWith("!")) {
                    if (tmp.trim().isEmpty() || tmp.trim().startsWith("*")) {
                        bTillage = false;
                        bTillageHeader = false;
                        continue;
                    }
                    //@T TDATE TIMPL  TDEP TNAME
                    Tillage tillage = null;
                    TillageApplication tillageApp = new TillageApplication();
                    Integer level = Integer.parseInt(tmp.substring(0, 2).trim());

                    if(level > tillageList.GetSize()) {
                        tillage = new Tillage();
                    } else {
                        tillage = (Tillage)tillageList.GetAt(level - 1);
                    }

                    tillageApp.TDATE = Utils.GetDate(tillageHeader, tmp, "TDATE", 5);
                    tillageApp.TIMPL = Utils.GetString(tillageHeader, tmp, "TIMPL", 5);
                    tillageApp.TDEP = Utils.GetInteger(tillageHeader, tmp, "TDEP", 5);
                    tillage.TNAME = Utils.GetString(tillageHeader, tmp, "TNAME", tmp.length() - tillageHeader.indexOf("TNAME"));
                    tillage.AddApp(tillageApp);

                    if(level > tillageList.GetSize()) {
                        tillageList.AddNew(tillage);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void Extract(PrintWriter pw) {
        // <editor-fold defaultstate="collapsed" desc="Tillage">
        if (tillageList.GetSize() > 0) {
            pw.println();
            pw.println("*TILLAGE AND ROTATIONS");
            pw.println("@T TDATE TIMPL  TDEP TNAME");
            for (int i = 0; i < tillageList.GetSize(); i++) {
                Tillage tillage = (Tillage)tillageList.GetAt(i);
                for (int n = 0; n < tillage.GetSize(); n++) {
                    TillageApplication tilApp = tillage.GetApp(n);

                    Integer level = i + 1;
                    pw.print(Utils.PadLeft(level, 2, ' '));

                    try {
                        pw.print(" " + Utils.PadRight(Utils.JulianDate(tilApp.TDATE), 5, ' '));
                    } catch (Exception e) {
                        pw.print(" " + Utils.PadLeft("-99", 5, ' '));
                    }

                    pw.print(" " + Utils.PadRight(tilApp.TIMPL, 5, ' '));
                    pw.print(" " + Utils.PadLeft(tilApp.TDEP, 5, ' '));
                    if (tillage.TNAME != null) {
                        pw.print(" " + tillage.TNAME);
                    } else {
                        pw.print(" -99");
                    }
                    pw.println();
                }
            }
        }
        // </editor-fold>
    }
}
