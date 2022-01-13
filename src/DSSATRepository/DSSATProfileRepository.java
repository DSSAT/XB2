package DSSATRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import xbuild.LoadingDataFrame;

/**
 *
 * @author Jazzy
 */
public class DSSATProfileRepository {
    private String version;
    private String rootPath;
    
    public DSSATProfileRepository(String version, String rootPath){
        this.version = version;
        this.rootPath = rootPath;
    }
    
    public ArrayList<String> Parse() throws Exception{
        ArrayList<String> dssatList = new ArrayList<String>() {};
        
        // Set File to Read
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.US);
                
        FileReader file = null;
        System.out.println("Start Read File DSSATPRO." + version + " : " + df.format(new Date()));
            
        // <editor-fold defaultstate="collapsed" desc="DSSATPRO.vxx">
        try {
            file = new FileReader(rootPath + "\\DSSATPRO." + version);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        
        BufferedReader br = new BufferedReader(file);
        try {
            String strRead = null;
            while (( strRead = br.readLine()) != null) {
                if (!strRead.isEmpty() && !strRead.startsWith("*") && !strRead.startsWith("!")) {
                    dssatList.add(strRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        }
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(LoadingDataFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(LoadingDataFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        // </editor-fold>
        
        return dssatList;
    }
}
