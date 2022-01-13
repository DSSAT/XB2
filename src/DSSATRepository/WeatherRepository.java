package DSSATRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Jazzy
 */
public class WeatherRepository extends DSSATRepositoryBase {
    
    public WeatherRepository(String rootPath) {
        super(rootPath);
    }
    @Override
    public ArrayList<String> Parse() throws Exception {
        ArrayList<String> weatherList = new ArrayList<String>();
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.US);
        
        System.out.println("Start Read Weather : " + df.format(new Date()));

        File w = new File(rootPath + "\\Weather\\WTH.LST");
        System.out.println("Start Read Weather File : " + w.getName() + " : " + df.format(new Date()) + "\n");
        FileReader fileRead = new FileReader(w);
        
        String strWRead = "";
        try {
            BufferedReader wReader = new BufferedReader(fileRead);
            boolean isStartAdd = false;
            while ((strWRead = wReader.readLine()) != null) {
                if (!isStartAdd && strWRead.startsWith("@#")) {
                    isStartAdd = true;
                } else if (isStartAdd) {
                    weatherList.add(strWRead);
                }
            }

            wReader.close();
            fileRead.close();

        } catch (IOException ex) {
            throw ex;
        }

        return weatherList;
    }
}
