/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DSSATModel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 *
 * @author Jazzy
 */
public class Setup {

    private static String DSSATPath;
    private static String DSSATVersion;
    private static String XB2Path;

    public static void SetDSSATPath(String path) {
        DSSATPath = path;
    }

    public String GetDSSATPath() {
        if (DSSATPath == null) {
            String defaultDssatPath;
              
            // Try to resolve XB2Path based on the JAR location first
            try {
                java.net.URL jarUrl = Setup.class.getProtectionDomain().getCodeSource().getLocation();
                if (jarUrl != null) {
                    File jarFile = new File(jarUrl.toURI());
                    if (jarFile.isFile()) {
                        XB2Path = jarFile.getParentFile().getAbsolutePath();
                    }
                }
            } catch (Exception e) {
                // Ignore and fall back
            }

            if (XB2Path == null || !(new File(XB2Path, "XBuild.fle").exists())) {
                if(Paths.get("Tools").toAbsolutePath().toFile().exists()){
                    defaultDssatPath = Paths.get("").toAbsolutePath().toString();
                    XB2Path = Paths.get("Tools\\XB2").toAbsolutePath().toString();
                }
                else if(Files.exists(Paths.get("").toAbsolutePath().resolveSibling("Tools"))) {
                    defaultDssatPath = Paths.get("").toAbsolutePath().getParent().toString();
                    XB2Path = Paths.get("").toAbsolutePath().resolveSibling("Tools").toString();
                }
                else{
                    defaultDssatPath = Paths.get("").toAbsolutePath().getParent().getParent().toString();
                    XB2Path = Paths.get("").toAbsolutePath().toString();
                }
            } else {
                defaultDssatPath = new File(XB2Path).getParentFile().getParentFile().getAbsolutePath();
            }
                        
            for(int i = 47;i <= 49;i++){
               File file = new File(defaultDssatPath + "\\DSSATPRO.v" + i);
               File fileConfig = new File(XB2Path + "\\XBuild.fle");
                if (file.exists() && !fileConfig.exists()) {
                    DSSATVersion = "v" + i;
                    SaveFile(defaultDssatPath);
                    return defaultDssatPath;
                }
            }
            
            GetFile();
        }
        return DSSATPath;
    }

    public String GetDSSATVersion() {
        if (DSSATVersion == null) {
            GetFile();
        }
        return DSSATVersion;
    }

    protected void GetFile() {
        String xBuildCfg = XB2Path + "\\XBuild.fle";
        File file = new File(xBuildCfg);
        if (file.exists()) {
            FileReader fileRead = null;
            try {
                fileRead = new FileReader(xBuildCfg);
            } catch (FileNotFoundException ex) {
            }

            BufferedReader br = new BufferedReader(fileRead);
            String buffer;
            try {
                while ((buffer = br.readLine()) != null) {
                    String tmp[] = buffer.split("=");
                    try {
                        switch (tmp[0].trim()) {
                            case "DSSAT":
                                DSSATPath = tmp[1].trim();
                                break;
                            case "VERSION":
                                DSSATVersion = tmp[1].trim();
                                break;
                            default:
                                break;
                        }
                    } catch (Exception ex1) {
                        System.out.println(ex1.getMessage());
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                br.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                fileRead.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void SaveFile(String path) {
        String xBuildCfg = XB2Path + "\\XBuild.fle";
        FileWriter writer = null;
        try {
            writer = new FileWriter(xBuildCfg);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        try (PrintWriter pw = new PrintWriter(writer)) {
            pw.println("DSSAT=" + path);
            pw.println("VERSION=" + DSSATVersion);
            
            DSSATPath = path;
        }
        try {
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
