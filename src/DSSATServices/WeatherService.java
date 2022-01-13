package DSSATServices;

import DSSATRepository.WeatherRepository;
import DSSATModel.WeatherStation;
import DSSATModel.WeatherStationList;
import java.util.ArrayList;

/**
 *
 * @author Jazzy
 */
public class WeatherService extends DSSATServiceBase {
    private final WeatherRepository weatherRepository;
    
    public WeatherService(String rootPath) {
        super(rootPath);
        
        this.weatherRepository = new WeatherRepository(rootPath);
    }
    
    @Override
    public void Parse() throws Exception{
        boolean isValid = true;
        
        try {
            ArrayList<String> weatherList = this.weatherRepository.Parse();
            
            for(int i = 0;i < weatherList.size();i++)
            {
                String tmp = weatherList.get(i);
                
                WeatherStation weather = new WeatherStation();
                weather.StationName = tmp.substring(18, 45).trim();
                weather.Code = tmp.substring(5, 9).trim();
                if (!WeatherStationList.Exists(weather)) {
                    WeatherStationList.AddNew(weather);
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
        
        if(!isValid){
            throw new Exception("Weather parse failed");
        }
    }
    
    @Override
    public String getName() {
        return "Weather";
    }
}
