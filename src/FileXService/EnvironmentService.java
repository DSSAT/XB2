package FileXService;

import DSSATModel.EnvironmentFactorList;
import Extensions.Utils;
import FileXModel.EnvironmentApplication;
import FileXModel.Environmental;
import static FileXModel.FileX.environmentals;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 *
 * @author Jazzy
 */
public class EnvironmentService {
    public static void Read(File fileName) {
        try {
            FileReader fReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fReader);
            String strRead = null;
            
            String environmentHeader = "";
            boolean bEnvironmentHeader = false;
            boolean bEnvironment = false;
            
            while ((strRead = br.readLine()) != null) {
                String tmp = strRead;
                
                if (tmp.trim().startsWith("*ENVIRONMENT MODIFICATIONS")) {
                    bEnvironment = true;

                } else if (bEnvironment && !bEnvironmentHeader && tmp.trim().startsWith("@")) {
                    environmentHeader = tmp.trim();
                    bEnvironmentHeader = true;
                } else if (bEnvironment && bEnvironmentHeader && !tmp.trim().startsWith("!")) {
                    if (tmp.trim().isEmpty() || tmp.trim().startsWith("*")) {
                        bEnvironment = false;
                        bEnvironmentHeader = false;
                        continue;
                    }
                    //@E ODATE EDAY  ERAD  EMAX  EMIN  ERAIN ECO2  EDEW  EWIND ENVNAME
                    Environmental env = null;
                    EnvironmentApplication envApp = new EnvironmentApplication();
                    Integer level = Integer.parseInt(tmp.substring(0, 2).trim());

                    if(level > environmentals.GetSize()) {
                        env = new Environmental();
                    } else {
                        env = (Environmental)environmentals.GetAt(level - 1);
                    }

                    envApp.ODATE = Utils.GetDate(environmentHeader, tmp, "ODATE", 5);
                    envApp.EDAY_Fact = EnvironmentFactorList.GetAt(0, Utils.GetString(environmentHeader, tmp, "EDAY", 1));
                    envApp.EDAY = Utils.GetInteger(environmentHeader, tmp, "EDAY ", 3);
                    envApp.ERAD_Fact = EnvironmentFactorList.GetAt(0, Utils.GetString(environmentHeader, tmp, "ERAD", 1));
                    envApp.ERAD = Utils.GetFloat(environmentHeader, tmp, "ERAD ", 3);
                    envApp.EMAX_Fact = EnvironmentFactorList.GetAt(0, Utils.GetString(environmentHeader, tmp, "EMAX", 1));
                    envApp.EMAX = Utils.GetFloat(environmentHeader, tmp, "EMAX ", 3);
                    envApp.EMIN_Fact = EnvironmentFactorList.GetAt(0, Utils.GetString(environmentHeader, tmp, "EMIN", 1));
                    envApp.EMIN = Utils.GetFloat(environmentHeader, tmp, "EMIN ", 3);
                    envApp.ERAIN_Fact = EnvironmentFactorList.GetAt(0, Utils.GetString(environmentHeader, tmp, "ERAIN", 1));
                    envApp.ERAIN = Utils.GetFloat(environmentHeader, tmp, "ERAIN", 3);
                    envApp.ECO2_Fact = EnvironmentFactorList.GetAt(0, Utils.GetString(environmentHeader, tmp, "ECO2", 1));
                    envApp.ECO2 = Utils.GetFloat(environmentHeader, tmp, "ECO2 ", 3);
                    envApp.EDEW_Fact = EnvironmentFactorList.GetAt(0, Utils.GetString(environmentHeader, tmp, "EDEW", 1));
                    envApp.EDEW = Utils.GetFloat(environmentHeader, tmp, "EDEW ", 3);
                    envApp.EWIND_Fact = EnvironmentFactorList.GetAt(0, Utils.GetString(environmentHeader, tmp, "EWIND", 1));
                    envApp.EWIND = Utils.GetFloat(environmentHeader, tmp, " EWIND", 3);
                    env.ENVNAME = Utils.GetString(environmentHeader, tmp, "ENVNAME", tmp.length() - environmentHeader.indexOf("ENVNAME"));
                    env.AddApp(envApp);

                    if(level > environmentals.GetSize()) {
                        environmentals.AddNew(env);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void Extract(PrintWriter pw) {
        // <editor-fold defaultstate="collapsed" desc="Environmental">
        if (environmentals.GetSize() > 0) {
            pw.println();
            pw.println();
            pw.println("*ENVIRONMENT MODIFICATIONS");
            pw.println("@E ODATE EDAY  ERAD  EMAX  EMIN  ERAIN ECO2  EDEW  EWIND ENVNAME");

            for (int i = 0; i < environmentals.GetSize(); i++) {
                Environmental env = (Environmental) environmentals.GetAt(i);
                for (int n = 0; n < env.GetSize(); n++) {
                    EnvironmentApplication envApp = env.GetApp(n);

                    Integer level = i + 1;
                    pw.print(Utils.PadLeft(level, 2, ' '));

                    try {
                        pw.print(" " + Utils.PadRight(Utils.JulianDate(envApp.ODATE), 5, ' '));
                    } catch (Exception e) {
                        pw.print(" " + Utils.PadLeft("-99", 5, ' '));
                    }

                    pw.print(" " + envApp.EDAY_Fact.Code);
                    pw.print(Utils.PadLeft(envApp.EDAY, 4, ' '));
                    pw.print(" " + envApp.ERAD_Fact.Code);
                    pw.print(Utils.PadLeft(envApp.ERAD, 4, ' '));
                    pw.print(" " + envApp.EMAX_Fact.Code);
                    pw.print(Utils.PadLeft(envApp.EMAX, 4, ' '));
                    pw.print(" " + envApp.EMIN_Fact.Code);
                    pw.print(Utils.PadLeft(envApp.EMIN, 4, ' '));
                    pw.print(" " + envApp.ERAIN_Fact.Code);
                    pw.print(Utils.PadLeft(envApp.ERAIN, 4, ' '));
                    pw.print(" " + envApp.ECO2_Fact.Code);
                    pw.print(Utils.PadLeft(envApp.ECO2, 4, ' '));
                    pw.print(" " + envApp.EDEW_Fact.Code);
                    pw.print(Utils.PadLeft(envApp.EDEW, 4, ' '));
                    pw.print(" " + envApp.EWIND_Fact.Code);
                    pw.print(Utils.PadLeft(envApp.EWIND, 4, ' '));
                    if (env.ENVNAME != null) {
                        pw.print(" " + env.ENVNAME);
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
