package FileXService;

import Extensions.Utils;
import static FileXModel.FileX.organicList;
import FileXModel.Organic;
import FileXModel.OrganicApplication;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 *
 * @author Jazzy
 */
public class ResidueService {
    public static void Read(File fileName) {
        try {
            FileReader fReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fReader);
            String strRead = null;
            
            String organicHeader = "";
            boolean bOrganicHeader = false;
            boolean bOrganic = false;
            
            while ((strRead = br.readLine()) != null) {
                String tmp = strRead;
                
                if (tmp.trim().startsWith("*RESIDUES AND ORGANIC FERTILIZER")) {
                    bOrganic = true;

                } else if (bOrganic && !bOrganicHeader && tmp.trim().startsWith("@")) {
                    organicHeader = tmp.trim();
                    bOrganicHeader = true;
                } else if (bOrganic && bOrganicHeader && !tmp.trim().startsWith("!")) {
                    if (tmp.trim().isEmpty() || tmp.trim().startsWith("*")) {
                        bOrganic = false;
                        bOrganicHeader = false;
                        continue;
                    }
                    //@R RDATE  RCOD  RAMT  RESN  RESP  RESK  RINP  RDEP  RMET RENAME
                    Organic organic = null;
                    OrganicApplication organicApp = new OrganicApplication();
                    Integer level = Integer.parseInt(tmp.substring(0, 2).trim());

                    if(level > organicList.GetSize()) {
                        organic = new Organic();
                    } else {
                        organic = organicList.GetAt(level - 1);
                    }

                    try {
                        organicApp.RDATE = Utils.GetDate(organicHeader, tmp, "RDATE", 5);
                    } catch (Exception e) {
                        organicApp.RDAY = Utils.GetInteger(organicHeader, tmp, "RDATE", 5);
                    }

                    organicApp.RCOD = Utils.GetString(organicHeader, tmp, " RCOD", 5);
                    organicApp.RAMT = Utils.GetInteger(organicHeader, tmp, "RAMT", 5);
                    organicApp.RESN = Utils.GetFloat(organicHeader, tmp, "RESN", 5);
                    organicApp.RESP = Utils.GetFloat(organicHeader, tmp, "RESP", 5);
                    organicApp.RESK = Utils.GetFloat(organicHeader, tmp, "RESK", 5);
                    organicApp.RINP = Utils.GetInteger(organicHeader, tmp, "RINP", 5);
                    organicApp.RDEP = Utils.GetInteger(organicHeader, tmp, "RDEP", 5);
                    organicApp.RMET = Utils.GetString(organicHeader, tmp, " RMET", 5);
                    organic.RENAME = Utils.GetString(organicHeader, tmp, "RENAME", tmp.length() - organicHeader.indexOf("RENAME"));
                    organic.AddApp(organicApp);

                    if(level > organicList.GetSize()) {
                        organicList.AddNew(organic);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void Extract(PrintWriter pw){
        // <editor-fold defaultstate="collapsed" desc="Organic Amendment">
        if (organicList.GetSize() > 0) {
            pw.println();
            pw.println();
            pw.println("*RESIDUES AND ORGANIC FERTILIZER");
            pw.println("@R RDATE  RCOD  RAMT  RESN  RESP  RESK  RINP  RDEP  RMET RENAME");
            for (int i = 0; i < organicList.GetSize(); i++) {
                Organic organ = organicList.GetAt(i);
                for (int n = 0; n < organ.GetSize(); n++) {
                    OrganicApplication organApp = organ.GetApp(n);

                    Integer level = i + 1;
                    pw.print(Utils.PadLeft(level, 2, ' '));

                    if(organApp.RDATE != null)
                        pw.print(" " + Utils.PadRight(Utils.JulianDate(organApp.RDATE), 5, ' '));
                    else
                        pw.print(" " + Utils.PadLeft(organApp.RDAY, 5, ' '));

                    pw.print(" " + Utils.PadLeft(organApp.RCOD, 5, ' '));
                    pw.print(" " + Utils.PadLeft(organApp.RAMT, 5, ' '));
                    pw.print(" " + Utils.PadLeft(organApp.RESN, 5, ' '));
                    pw.print(" " + Utils.PadLeft(organApp.RESP, 5, ' '));
                    pw.print(" " + Utils.PadLeft(organApp.RESK, 5, ' '));
                    pw.print(" " + Utils.PadLeft(organApp.RINP, 5, ' '));
                    pw.print(" " + Utils.PadLeft(organApp.RDEP, 5, ' '));
                    pw.print(" " + Utils.PadLeft(organApp.RMET, 5, ' '));
                    if (organ.RENAME != null) {
                        pw.print(" " + organ.RENAME);
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
