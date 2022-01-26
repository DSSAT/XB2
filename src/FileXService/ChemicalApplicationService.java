package FileXService;

import Extensions.Utils;
import FileXModel.Chemical;
import FileXModel.ChemicalApplication;
import static FileXModel.FileX.chemicalList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 *
 * @author Jazzy
 */
public class ChemicalApplicationService {
    public static void Read(File fileName) {
        try {
            FileReader fReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fReader);
            String strRead = null;

            String chemicalHeader = "";
            boolean bChemicalHeader = false;
            boolean bChemical = false;

            while ((strRead = br.readLine()) != null) {
                String tmp = strRead;

                if (tmp.trim().startsWith("*CHEMICAL APPLICATIONS")) {
                    bChemical = true;

                } else if (bChemical && !bChemicalHeader && tmp.trim().startsWith("@")) {
                    chemicalHeader = tmp.trim();
                    bChemicalHeader = true;
                } else if (bChemical && bChemicalHeader && !tmp.trim().startsWith("!")) {
                    if (tmp.trim().isEmpty() || tmp.trim().startsWith("*")) {
                        bChemical = false;
                        bChemicalHeader = false;
                        continue;
                    }
                    //@C CDATE CHCOD CHAMT  CHME CHDEP   CHT..CHNAME
                    Chemical chem = null;
                    ChemicalApplication chemApp = new ChemicalApplication();
                    Integer level = Integer.parseInt(tmp.substring(0, 2).trim());

                    if (level > chemicalList.GetSize()) {
                        chem = new Chemical();
                    } else {
                        chem = (Chemical)chemicalList.GetAt(level - 1);
                    }

                    chemApp.CDATE = Utils.GetDate(chemicalHeader, tmp, "CDATE", 5);
                    chemApp.CHCOD = Utils.GetString(chemicalHeader, tmp, "CHCOD", 5);
                    chemApp.CHAMT = Utils.GetFloat(chemicalHeader, tmp, "CHAMT", 5);
                    chemApp.CHME = Utils.GetString(chemicalHeader, tmp, " CHME", 5);
                    chemApp.CHDEP = Utils.GetInteger(chemicalHeader, tmp, "CHDEP", 5);
                    chemApp.CHT = Utils.GetString(chemicalHeader, tmp, "  CHT", 5);
                    chem.CHNAME = Utils.GetString(chemicalHeader, tmp, "CHNAME", tmp.length() - chemicalHeader.indexOf("CHNAME"));
                    chem.AddApp(chemApp);

                    if (level > chemicalList.GetSize()) {
                        chemicalList.AddNew(chem);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void Extract(PrintWriter pw) {
        // <editor-fold defaultstate="collapsed" desc="Chemicals">
        if (chemicalList.GetSize() > 0) {
            pw.println();
            pw.println();
            pw.println("*CHEMICAL APPLICATIONS");
            pw.println("@C CDATE CHCOD CHAMT  CHME CHDEP   CHT..CHNAME");
            for (int i = 0; i < chemicalList.GetSize(); i++) {
                Chemical chem = (Chemical)chemicalList.GetAt(i);
                for (int n = 0; n < chem.GetSize(); n++) {
                    ChemicalApplication chemApp = chem.GetApp(n);

                    Integer level = i + 1;
                    pw.print(Utils.PadLeft(level, 2, ' '));

                    try {
                        pw.print(" " + Utils.PadRight(Utils.JulianDate(chemApp.CDATE), 5, ' '));
                    } catch (Exception e) {
                        pw.print(" " + Utils.PadLeft("-99", 5, ' '));
                    }

                    pw.print(" " + Utils.PadRight(chemApp.CHCOD, 5, ' '));
                    pw.print(" " + Utils.PadLeft(chemApp.CHAMT, 5, ' '));
                    pw.print(" " + Utils.PadRight(chemApp.CHME, 5, ' '));
                    pw.print(" " + Utils.PadLeft(chemApp.CHDEP, 5, ' '));
                    pw.print(" " + Utils.PadLeft(chemApp.CHT, 5, ' '));
                    if (chem.CHNAME != null) {
                        pw.print("  " + chem.CHNAME);
                    } else {
                        pw.print("  -99");
                    }
                    pw.println();
                }
            }
        }
        // </editor-fold>
    }
}
