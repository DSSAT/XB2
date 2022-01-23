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
            SoilList.Clear();
            for(String soilTemp : soilList)
            {
                String[] tmp = soilTemp.split(":");
                String soilCode = tmp[0];
                String soilDescription = tmp[1];

                Soil soil = new Soil();
                soil.Code = soilCode;
                soil.Description = soilDescription;
                
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
