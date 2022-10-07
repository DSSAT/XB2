package Extensions;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author Jazz
 */
public class Variables {
    
    private static SimpleDateFormat dateFormatWithTime;
    private static SimpleDateFormat dateFormat;
    private static final String DATE_FORMAT = "MM/dd/yyyy";
    
    public static SimpleDateFormat getDateFormatWithTime()
    {
        if(dateFormatWithTime == null)
            dateFormatWithTime = new SimpleDateFormat(DATE_FORMAT + "hh:mm:ss", new Locale("en","US"));
        
        return dateFormatWithTime;
    }
    
    public static SimpleDateFormat getDateFormat()
    {
        if(dateFormat == null)
            dateFormat = new SimpleDateFormat(DATE_FORMAT, new Locale("en","US"));
        
        return dateFormat;
    }
    
    public static String getDateFormatString(){
        return DATE_FORMAT;
    }
}
