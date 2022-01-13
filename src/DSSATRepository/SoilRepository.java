package DSSATRepository;

import java.io.BufferedReader;
import java.io.File;
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
public class SoilRepository extends DSSATRepositoryBase {
    
    public SoilRepository(String rootPath) {
        super(rootPath);
    }
    
    @Override
    public ArrayList<String> Parse() {        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.US);
        System.out.println("Start Read Soil.LST : " + df.format(new Date()));

        ArrayList<String> soilList = new ArrayList<String>();
        try {
            File soilFile = new File(rootPath + "\\Soil\\Soil.lst");
            FileReader soilFileRead = new FileReader(soilFile);

            String strSRead = "";
            try {
                BufferedReader soilBufferReader = new BufferedReader(soilFileRead);
                boolean isStartAdd = false;
                while ((strSRead = soilBufferReader.readLine()) != null) {
                    if (!isStartAdd && strSRead.startsWith("@#")) {
                        isStartAdd = true;
                    } else if (isStartAdd) {                        
                        soilList.add(strSRead);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(LoadingDataFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                soilFileRead.close();
            } catch (IOException ex) {
                Logger.getLogger(LoadingDataFrame.class.getName()).log(Level.SEVERE, null, ex);
            }


        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        
        return soilList;
    }
}
