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
            WeatherStationList.Clear();
            weatherList.forEach(w ->{
                WeatherStation wsta = new WeatherStation();
                String tmp[] = w.split(":");
                try
                {
                    wsta.Code = tmp[0];
                    wsta.StationName = tmp[1];
                    if(WeatherStationList.GetAt(wsta.Code) == null)
                        WeatherStationList.AddNew(wsta);
                }
                catch(Exception ex) {
                    System.out.print(ex.getMessage());
                }
            });
        }
        catch(Exception ex){
           isValid = false; 
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
