package DSSATServices;

import DSSATRepository.WeatherRepository;
import DSSATModel.WeatherStation;
import DSSATModel.WeatherStationList;
import DSSATModel.WstaType;
import java.util.ArrayList;

/**
 *
 * @author Jazzy
 */
public class WeatherService extends DSSATServiceBase {

    private final WeatherRepository weatherRepository;
    private final String[] wstaTypes = new String[]{"", "/Gen", "/Climate"};
    private final WstaType[] wstaExtensions = new WstaType[]{WstaType.WTH, WstaType.WTG, WstaType.CLI};

    public WeatherService(String rootPath) {
        super(rootPath);

        this.weatherRepository = new WeatherRepository(rootPath);
    }

    @Override
    public void Parse() throws Exception {
        boolean isValid = true;
        String invalidResult = "";

        try {
            WeatherStationList.Clear();
            for (int i = 0; i < 3; i++) {
                WstaType type = wstaExtensions[i];
                ArrayList<String> weatherList = this.weatherRepository.Parse(wstaTypes[i], type.toString());

                for (String w : weatherList) {
                    try {
                        WeatherStation wsta = new WeatherStation();
                        String tmp[] = w.split("\\^")[0].split(":");
                        if (tmp.length == 5) {
                            wsta.Code = tmp[0];
                            wsta.StationName = tmp[1];
                            wsta.Begin = Integer.parseInt(tmp[2]);
                            wsta.Number = Integer.parseInt(tmp[3]);
                            wsta.Type = type;

                            if (wsta.Begin >= 50 && wsta.Begin <= 99) {
                                wsta.Begin += 1900;
                            } else if (wsta.Begin < 50) {
                                wsta.Begin += 2000;
                            }

                            WeatherStation wstaExist = WeatherStationList.GetAt(wsta.Code, type);
                            if (wstaExist == null) {
                                wsta.FullCode.add(wsta.Code);
                                wsta.FullCode.add(tmp[4]);
                                WeatherStationList.AddNew(wsta);
                            } else {
                                wstaExist.FullCode.add(tmp[4]);
                                wstaExist.Begin = Math.min(wsta.Begin, wstaExist.Begin);
                                wstaExist.Number = Math.max(wstaExist.Begin + wstaExist.Number - wstaExist.Begin, wsta.Begin + wsta.Number - wstaExist.Begin);
                            }
                        }
                    }
                    catch(Exception ex){
                        isValid = false;
                        invalidResult += "\n" + w.split("\\^")[1];
                    }
                }
            }
        } catch (Exception ex) {
            isValid = false;
        }

        if (!isValid) {
            throw new Exception(invalidResult);
        }
    }

    @Override
    public String getName() {
        return "Weather";
    }
}
