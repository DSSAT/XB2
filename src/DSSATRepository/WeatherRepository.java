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
public class WeatherRepository extends DSSATRepositoryBase {
    
    public WeatherRepository(String rootPath) {
        super(rootPath);
    }
        
    @Override
    public ArrayList<String> Parse() throws Exception
    {
        ArrayList<String> weatherList = new ArrayList<>();
        String wed = null;
        try {
            wed = DssatProfile.GetAt("WED");
        } catch (Exception e) {
            throw e;
        }

        File w = new File(wed);
        File fList[] = w.listFiles(new ExtendFilter(".wth"));

        for (File file : fList) {
            FileReader fileRead = null;
            try {
                String Code = file.getName().substring(0, 4);
                
                fileRead = new FileReader(file);
                BufferedReader wReader = new BufferedReader(fileRead);

                String strWRead = "";

                while ((strWRead = wReader.readLine()) != null) {
                    if (strWRead.startsWith("*WEATHER") || strWRead.startsWith("$WEATHER")) {
                        String WSTAName = strWRead.substring(strWRead.indexOf(":") + 1, strWRead.length()).trim();
                        weatherList.add(Code + ":" + WSTAName);
                        break;
                    }
                }
                
                fileRead.close();
                wReader.close();

            } catch (FileNotFoundException ex) {
            }
        }
        return weatherList;
    }
}
