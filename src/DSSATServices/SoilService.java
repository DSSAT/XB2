package DSSATServices;

import DSSATRepository.SoilRepository;
import DSSATModel.Soil;
import DSSATModel.SoilList;
import java.util.ArrayList;

/**
 *
 * @author Jazzy
 */
public class SoilService extends DSSATServiceBase {
    private final SoilRepository soilRepository;
    public SoilService(String rootPath) {
        super(rootPath);
        
        this.soilRepository = new SoilRepository(rootPath);
    }
    
    @Override
    public void Parse() throws Exception{
        boolean isValid = true;
        
        try {
            ArrayList<String> soilList = this.soilRepository.Parse();
            
            for(int i = 0;i < soilList.size();i++)
            {
                String tmp = soilList.get(i);
                String soilFileCode = tmp.substring(5, 13).trim();
                String soilFileExt = tmp.substring(14, 17).trim();
                String soilCode = tmp.substring(18, 28).trim();
                String soilDescription = tmp.substring(44, tmp.length() - 1).trim();

                Soil soil = new Soil();
                soil.Code = soilCode;
                soil.Description = soilDescription;
                soil.Unknow1 = soilFileCode;
                soil.Unknow2 = soilFileExt;
                
                SoilList.AddNew(soil);
            }
        } catch (Exception ex) {
            throw ex;
        }
        
        if(!isValid){
            throw new Exception("Soil parse failed");
        }
    }  
    
    @Override
    public String getName() {
        return "Soil";
    }
}
