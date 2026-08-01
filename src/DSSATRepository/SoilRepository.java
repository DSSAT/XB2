package DSSATRepository;

import DSSATModel.DssatProfile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
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
    public ArrayList<String> Parse() throws Exception {

        ArrayList<StringBuilder> soilBuilders = new ArrayList<>();
        
        try {
            String soilPath = DssatProfile.GetAt("SLD");
            File sPath = new File(soilPath);
            
            if (!sPath.exists()) {
                throw new Exception("Please check your weather folder: " + soilPath + "!!");
            }
            
            File soilFileList[] = sPath.listFiles(new ExtendFilter(".sol"));
            
            for (File sFile : soilFileList) {
                BufferedReader sReader;
                try (FileReader fileRead = new FileReader(sFile)) {
                    sReader = new BufferedReader(fileRead);
                    String strRead;
                    Boolean isProfile = false;
                    int line = 1;
                    while ((strRead = sReader.readLine()) != null) {
                        if(strRead != null && !"".equals(strRead)){
                            if(strRead.startsWith("*") && !strRead.toLowerCase().startsWith("*soils") && strRead.length() > 36){
                                String soilCode = strRead.substring(1, 11).trim();
                                String soilDescription = strRead.length() <= 36 ? soilCode : strRead.substring(37, strRead.length()).trim();
                                soilBuilders.add(new StringBuilder(soilCode + ":" + soilDescription));
                                isProfile = false;
                            }
                            else if(strRead.startsWith("@  SLB") && !isProfile){
                                isProfile = true;
                            }
                            else if(strRead.startsWith("@  SLB") && isProfile){
                                isProfile = false;
                            }
                            else if(!strRead.startsWith("!") && strRead.length() >= 90 && isProfile){
                                int index = soilBuilders.size() - 1;
                                if(index >= 0){
                                    StringBuilder sb = soilBuilders.get(index);
                                    sb.append("|").append(strRead).append("^File: ").append(sFile.getName()).append(", Line: ").append(line);
                                }
                            }
                        }
                        else{
                            isProfile = false;
                        }
                        
                        line++;
                    }
                }
                sReader.close();
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            throw ex;
        }
        
        ArrayList<String> soilList = new ArrayList<>(soilBuilders.size());
        for (StringBuilder sb : soilBuilders) {
            soilList.add(sb.toString());
        }
        
        return soilList;
    }
}
