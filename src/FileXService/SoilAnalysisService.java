package FileXService;

import Extensions.Utils;
import static FileXModel.FileX.soilAnalysis;
import FileXModel.SoilAnalysis;
import FileXModel.SoilAnalysisLayer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 *
 * @author Jazzy
 */
public class SoilAnalysisService {
    public static void Read(File fileName) {
        try {
            FileReader fReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fReader);
            String strRead = null;
            
            String soilHeader1 = "";
            boolean bSoilHeader1 = false;
            String soilHeader2 = "";
            boolean bSoilHeader2 = false;
            boolean bSoil = false;
            
            while ((strRead = br.readLine()) != null) {
                String tmp = strRead;
                
                if (tmp.trim().startsWith("*SOIL ANALYSIS")) {
                    bSoil = true;
                } else if (bSoil && !bSoilHeader1 && !bSoilHeader2 && tmp.trim().startsWith("@")) {
                    soilHeader1 = tmp.trim();
                    bSoilHeader1 = true;
                } else if (bSoil && bSoilHeader1 && !bSoilHeader2 && tmp.trim().startsWith("@")) {
                    soilHeader2 = tmp.trim();
                    bSoilHeader2 = true;
                } else if (bSoil && bSoilHeader1 && bSoilHeader2 && tmp.trim().startsWith("@")) {
                    bSoilHeader2 = false;
                }else if (bSoil && bSoilHeader1 && !bSoilHeader2) {
                    if (tmp.trim().isEmpty()) {
                        bSoil = false;
                        bSoilHeader1 = false;
                        bSoilHeader2 = false;
                        continue;
                    }
                    //@A SADAT  SMHB  SMPX  SMKE  SANAME
                    SoilAnalysis soil = new SoilAnalysis();
                    soil.SADAT = Utils.GetDate(soilHeader1, tmp, "SADAT", 5);
                    soil.SMHB = Utils.GetString(soilHeader1, tmp, " SMHB", 5);
                    soil.SMPX = Utils.GetString(soilHeader1, tmp, " SMPX", 5);
                    soil.SMKE = Utils.GetString(soilHeader1, tmp, " SMKE", 5);
                    soil.SANAME = Utils.GetString(soilHeader1, tmp, "SANAME", tmp.length() - soilHeader1.indexOf("SANAME"));

                    soilAnalysis.AddNew(soil);
                } else if (bSoil && bSoilHeader1 && bSoilHeader2) {
                    if (tmp.trim().isEmpty()) {
                        bSoil = false;
                        bSoilHeader1 = false;
                        bSoilHeader2 = false;
                        continue;
                    }
                    //@A  SABL  SADM  SAOC  SANI SAPHW SAPHB  SAPX  SAKE  SASC

                    try {
                        Integer level = Integer.parseInt(tmp.substring(0, 2).trim()) - 1;
                        SoilAnalysis soil = (SoilAnalysis)soilAnalysis.GetAt(level);
                        SoilAnalysisLayer soilLayer = new SoilAnalysisLayer();

                        soilLayer.SABL = Utils.GetFloat(soilHeader2, tmp, "SABL", 5);
                        soilLayer.SADM = Utils.GetFloat(soilHeader2, tmp, "SADM", 5);
                        soilLayer.SAOC = Utils.GetFloat(soilHeader2, tmp, "SAOC", 5);
                        soilLayer.SANI = Utils.GetFloat(soilHeader2, tmp, "SANI", 5);
                        soilLayer.SAPHW = Utils.GetFloat(soilHeader2, tmp, "SAPHW", 5);
                        soilLayer.SAPHB = Utils.GetFloat(soilHeader2, tmp, "SAPHB", 5);
                        soilLayer.SAPX = Utils.GetFloat(soilHeader2, tmp, "SAPX", 5);
                        soilLayer.SAKE = Utils.GetFloat(soilHeader2, tmp, "SAKE", 5);
                        soilLayer.SASC = Utils.GetFloat(soilHeader2, tmp, "SASC", 5);
                        soil.AddLayer(soilLayer);

                    } catch (NumberFormatException numberFormatException) {
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void Extract(PrintWriter pw){
        // <editor-fold defaultstate="collapsed" desc="Soil Analysis">
        if (soilAnalysis.GetSize() > 0) {
            pw.println();
            pw.println();
            pw.println("*SOIL ANALYSIS");
            for (int i = 0; i < soilAnalysis.GetSize(); i++) {
                Integer level = i + 1;
                SoilAnalysis soil = (SoilAnalysis)soilAnalysis.GetAt(i);

                pw.println("@A SADAT  SMHB  SMPX  SMKE  SANAME");
                pw.print(Utils.PadLeft(level, 2, ' '));
                pw.print(" " + Utils.JulianDate(soil.SADAT));
                pw.print(" " + Utils.PadLeft(soil.SMHB, 5, ' '));
                pw.print(" " + Utils.PadLeft(soil.SMPX, 5, ' '));
                pw.print(" " + Utils.PadLeft(soil.SMKE, 5, ' '));
                if (soil.SANAME != null) {
                    pw.print("  " + soil.SANAME);
                } else {
                    pw.print("  -99");
                }
                pw.println();
                if (soil.GetSize() > 0) {
                    for (int n = 0; n < soil.GetSize(); n++) {
                        SoilAnalysisLayer soilLayer = soil.GetLayer(n);
                        pw.println("@A  SABL  SADM  SAOC  SANI SAPHW SAPHB  SAPX  SAKE  SASC");
                        pw.print(Utils.PadLeft(level, 2, ' '));
                        pw.print(" " + Utils.PadLeft(soilLayer.SABL, 5, ' '));
                        pw.print(" " + Utils.PadLeft(soilLayer.SADM, 5, ' '));
                        pw.print(" " + Utils.PadLeft(soilLayer.SAOC, 5, ' '));
                        pw.print(" " + Utils.PadLeft(soilLayer.SANI, 5, ' '));
                        pw.print(" " + Utils.PadLeft(soilLayer.SAPHW, 5, ' '));
                        pw.print(" " + Utils.PadLeft(soilLayer.SAPHB, 5, ' '));
                        pw.print(" " + Utils.PadLeft(soilLayer.SAPX, 5, ' '));
                        pw.print(" " + Utils.PadLeft(soilLayer.SAKE, 5, ' '));
                        pw.print(" " + Utils.PadLeft(soilLayer.SASC, 5, ' '));
                        pw.println();
                    }
                }
            }
        }
        // </editor-fold>
    }
}
