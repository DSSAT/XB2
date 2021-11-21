/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Jazzy
 */
public class WeatherStationList {
    //protected static Hashtable wStation = new Hashtable();
    protected static Vector wStation = new Vector();

    public static void AddNew(WeatherStation weather)
    {
        //wStation.put(weather.Code, weather);
        wStation.addElement(weather);
    }

    public static WeatherStation GetAt(String Code)
    {
        WeatherStation weather = null;
        /*
        try{
            weather = (WeatherStation) wStation.get(Code);
        }
        catch(Exception ex) {}
         *
         */
        for(int i = 0;i < wStation.size();i++)
        {
            if(((WeatherStation)wStation.get(i)).Code.equals(Code))
            {
                try{
                    weather = (WeatherStation) wStation.get(i);
                }
                catch(Exception ex) {}
            }
        }
        return weather;
    }

    public static WeatherStation GetAt(int n)
    {
        WeatherStation weather = null;
        try{
            //Object[] object = wStation.values().toArray();
            weather = (WeatherStation) wStation.get(n);
        }
        catch(Exception ex) {}

        return weather;
    }
    
    public static List<WeatherStation> GetAll()
    {
        List<WeatherStation> weatherList = new ArrayList<>();
        
        for(Object object : wStation.toArray()){
            weatherList.add((WeatherStation) object);
        }
        
        Collections.sort(weatherList, new Comparator<WeatherStation>() {
            @Override
            public int compare(WeatherStation w1, WeatherStation w2) {
                return w1.StationName.compareTo(w2.StationName);
            }
        });

        return weatherList;
    }
    
    public static boolean Exists(WeatherStation weather){
        for(Object o : wStation.toArray()){
            WeatherStation w = (WeatherStation)o;
            if(w.Code.equalsIgnoreCase(weather.Code) && w.StationName.equalsIgnoreCase(weather.StationName))
                return true;
        }
        
        return false;
    }

    public static int size()
    {
        return wStation.size();
    }
}
