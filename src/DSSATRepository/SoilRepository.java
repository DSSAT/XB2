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
import xbuild.ExtendFilter;

/**
 *
 * @author Jazzy
 */
public class SoilRepository extends DSSATRepositoryBase {
    
    public SoilRepository(String rootPath) {
        super(rootPath);
    }
    
    @Override
    public ArrayList<String> Parse() throws IOException {        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.US);
        System.out.println("Start Read Soil.LST : " + df.format(new Date()));

        ArrayList<String> soilList = new ArrayList<>();
        try {
            File sPath = new File(rootPath + "\\Soil");
            File soilFileList[] = sPath.listFiles(new ExtendFilter(".sol"));
            
            for (File sFile : soilFileList) {
                FileReader fileRead = new FileReader(sFile);
                BufferedReader sReader = new BufferedReader(fileRead);

                String strRead = "";
                Boolean isProfile = false;
                while ((strRead = sReader.readLine()) != null) {
                    if(!strRead.isBlank() && strRead.startsWith("*") && !strRead.toLowerCase().startsWith("*soils") && strRead.length() > 36){
                        String soilCode = strRead.substring(1, 11).trim();
                        String soilDescription = strRead.length() <= 36 ? soilCode : strRead.substring(37, strRead.length()).trim();
                        soilList.add(soilCode + ":" + soilDescription);
                        isProfile = false;
                    }
                    else if(strRead.startsWith("@  SLB") && !isProfile){
                        isProfile = true;
                    }
                    else if(strRead.startsWith("@  SLB") && isProfile){
                        isProfile = false;
                    }
                    else if(!strRead.isBlank() && !strRead.startsWith("!") && strRead.length() >= 100 && isProfile){
                        int index = soilList.size() - 1;
                        String tmp = soilList.get(index);
                        tmp += "|" + strRead;
                        soilList.set(index, tmp);
                    }
                    else if(strRead.isBlank()){
                        isProfile = false;
                    }
                        
                }
                
                fileRead.close();
                sReader.close();
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        
        return soilList;
    }
}
