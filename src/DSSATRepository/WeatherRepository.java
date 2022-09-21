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
    public ArrayList<String> Parse(String wstaType, String extension) throws Exception {
        ArrayList<String> weatherList = new ArrayList<>();
        String wed = null;
        try {
            wed = DssatProfile.GetAt("WED");
        } catch (Exception e) {
            throw e;
        }

        File w = new File(wed + wstaType);
        File fList[] = w.listFiles(new ExtendFilter("." + extension));

        for (File file : fList) {

            String fullName = file.getName();
            String code = fullName.substring(0, 4);
            String number = "";
            if (fullName.length() > 4) {
                number = file.getName().substring(6, 8);
            }

            try ( FileReader fileRead = new FileReader(file);  BufferedReader wReader = new BufferedReader(fileRead)) {
                String strWRead;
                String wsta = "";

                boolean is2 = false;
                boolean is4 = false;
                boolean isCli = false;
                boolean isR = false;

                while ((strWRead = wReader.readLine()) != null) {
                    if (strWRead.startsWith("*WEATHER") || strWRead.startsWith("**WEATHER") || strWRead.startsWith("$WEATHER") || strWRead.startsWith("*CLIMATE")) {
                        String WSTAName = strWRead.substring(strWRead.indexOf(":") + 1, strWRead.length()).trim();
                        wsta = code + ":" + (!WSTAName.isEmpty() ? WSTAName : code);
                    } else if (strWRead.startsWith("@DATE")) {
                        is2 = true;
                    } else if (strWRead.startsWith("@  DATE")) {
                        is4 = true;
                    } else if (strWRead.startsWith("@START")) {
                        isCli = true;
                    } else if(strWRead.startsWith("@YRDAY")){
                        isR = true;
                    } else if (is2) {
                        wsta += ":" + strWRead.substring(0, 2) + ":" + number;
                        weatherList.add(wsta);
                        break;
                    } else if (is4) {
                        wsta += ":" + strWRead.substring(0, 4) + ":" + number;
                        weatherList.add(wsta);
                        break;
                    } else if (isCli) {
                        number = strWRead.substring(8, 13).trim();
                        wsta += ":" + strWRead.substring(0, 6).trim() + ":" + number;
                        weatherList.add(wsta);
                        break;
                    }
                    else if(isR){
                        number = fullName.substring(6, 8).trim();
                        wsta += ":" + fullName.substring(4, 6).trim() + ":" + number;
                        weatherList.add(wsta);
                        break;
                    }
                }

            }
        }
        return weatherList;
    }
}
