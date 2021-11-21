/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Library;

import java.io.*;

/**
 *
 * @author Jazzy
 */
public class Setup {
    private static String DSSATPath;
    private static String DSSATVersion;

    public String GetDSSATPath()
    {
        if(DSSATPath == null)
        {
            GetFile();
        }
        return DSSATPath;
    }
    
    public String GetDSSATVersion()
    {
        if(DSSATVersion == null)
        {
            GetFile();
        }
        return DSSATVersion;
    }

    protected void GetFile()
    {
        String xBuildCfg = "XBuild.fle";
        File file = new File(xBuildCfg);
        if(file.exists())
        {
            FileReader fileRead = null;
            try {
                fileRead = new FileReader(xBuildCfg);
            } catch (FileNotFoundException ex) { }

            BufferedReader br = new BufferedReader(fileRead);
            String buffer;
            try {
                while ((buffer = br.readLine()) != null) {
                    String tmp[] = buffer.split("=");
                    try{
                        if(tmp[0].trim().equals("DSSAT")) DSSATPath = tmp[1].trim();
                        if(tmp[0].trim().equals("VERSION")) DSSATVersion = tmp[1].trim();
                    }
                    catch(Exception ex1){
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

    public void SaveFile(String path, String version)
    {
        String xBuildCfg = "XBuild.fle";
        FileWriter writer = null;
        try {
            writer = new FileWriter(xBuildCfg);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        PrintWriter pw = new PrintWriter(writer);
        pw.println("DSSAT=" + path);
        pw.println("VERSION=" + version);

        DSSATPath = path;
        DSSATVersion = version;

        pw.close();
        try {
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
