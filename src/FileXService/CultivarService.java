package FileXService;

import Extensions.Utils;
import FileXModel.Cultivar;
import static FileXModel.FileX.cultivars;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 *
 * @author Jazzy
 */
public class CultivarService {
    public static void Read(File fileName) {
        try {
            FileReader fReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fReader);
            String strRead = null;
            
            String cultivarHeader = "";
            boolean bCultivarHeader = false;
            boolean bCultivar = false;
            
            while ((strRead = br.readLine()) != null) {
                if (strRead.trim().startsWith("*CULTIVARS")) {
                    bCultivar = true;

                } else if (bCultivar && !bCultivarHeader && strRead.trim().startsWith("@")) {
                    cultivarHeader = strRead.trim();
                    bCultivarHeader = true;
                } else if (bCultivar && bCultivarHeader) {
                    if (strRead.trim().isEmpty()) {
                        bCultivar = false;
                        bCultivarHeader = false;
                        continue;
                    }
                    //@C CR INGENO CNAME
                    Cultivar cul = new Cultivar();
                    cul.CR = Utils.GetString(cultivarHeader, strRead, "CR", 2);
                    cul.INGENO = Utils.GetString(cultivarHeader, strRead, "INGENO", 6);
                    cul.CNAME = Utils.GetString(cultivarHeader, strRead, "CNAME", strRead.length() - 13);
                    cultivars.AddNew(cul);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void Extract(PrintWriter pw){
        // <editor-fold defaultstate="collapsed" desc="Cultivars">
        if (cultivars.GetSize() > 0) {
            pw.println();
            pw.println("*CULTIVARS");
            pw.println("@C CR INGENO CNAME");
            for (int i = 0; i < cultivars.GetSize(); i++) {
                Cultivar cul = (Cultivar) cultivars.GetAt(i);
                Integer level = i + 1;
                pw.print(Utils.PadLeft(level, 2, ' '));
                pw.print(" " + Utils.PadLeft(cul.CR, 2, ' '));
                pw.print(" " + Utils.PadLeft(cul.INGENO, 6, ' '));
                pw.print(" " + Utils.PadLeft(cul.CNAME, 0, ' '));
                pw.println();
            }
        }

        // </editor-fold>
    }
}
