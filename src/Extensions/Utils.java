package Extensions;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Jazzy
 */
public class Utils {
    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }
    
    public static Float GetFloat(String Header, String value, String field, int fieldLength) {
        int start = Header.indexOf(field) + field.length() - fieldLength - 1;
        Float val = null;

        if(start >= 0) {
            int stop = start + fieldLength + 1;

            String tmp = value.substring(start,stop).trim();

            if(!tmp.equals("-99")){
                try {
                    val = Float.parseFloat(tmp);
                } catch (NumberFormatException numberFormatException) {
                }
            }
        }
        return val;
    }

    public static Date GetDate(String Header, String value, String field, int fieldLength) {
        int start = Header.indexOf(field) + field.length() - fieldLength - 1;
        Date val = null;

        if(start >= 0) {
            int stop = start + fieldLength + 1;

            String tmp = value.substring(start,stop).trim();

            if(!tmp.equals("-99")){
                try {
                    Integer year = Integer.parseInt(tmp.substring(0, 2));
                    if (year >= 60) {
                        year += 1900;
                    } else {
                        year += 2000;
                    }
                    int day = Integer.parseInt(tmp.substring(2, 5));

                    int month[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                    month[1] += ((year % 4) == 0) ? 1 : 0;

                    int dayCount = 0;
                    int nDay = 0;
                    int nMonth = 0;
                    for(int i = 0;i < 12;i++) {
                        if(day <= (dayCount + month[i]))
                        {
                            nDay = day - dayCount;
                            nMonth = i;
                            break;
                        }
                        dayCount += month[i];
                    }

                    //val = new Date(year, nMonth, nDay);
                    Calendar ca = Calendar.getInstance(Locale.US);
                    ca.set(year, nMonth, nDay);
                    val = ca.getTime();
                } catch (NumberFormatException numberFormatException) {
                    throw numberFormatException;
                }
            }
        }
        return val;
    }
    
    public static Integer GetInteger(String Header, String value, String field, int fieldLength) {
        int start = Header.indexOf(field.startsWith("@") ? field : " " + field) + field.length() - fieldLength;
        Integer val = null;
        
        if(start >= 0) {
            int stop = start + fieldLength + 1;

            String tmp = value.substring(start,stop).trim();

            if(!tmp.equals("-99")){
                try {
                    val = Integer.parseInt(tmp);
                } catch (NumberFormatException numberFormatException) {
                }
            }
        }
        return val;
    }
    
    public static Integer ParseInteger(Object value) {
        Integer val = null;

        try {
            if(value == null)
                return 0;
            val = Integer.parseInt(value.toString());
        } catch (NumberFormatException numberFormatException) {

        }
        return val;
    }

    public static String GetString(String Header, String value, String field, int fieldLength) {
        int start = Header.indexOf(field);
        String val = null;
        
        if(start >= 0) {
            int stop = start + fieldLength;

            String tmp = value.substring(start,stop).trim();
            if(tmp.isEmpty())
                val = "-99";
            else
                val = tmp;

            //if(!tmp.equals("-99")) val = tmp;
        }
        return val;
    }
    
    public static String FloatToString(Float value)
    {
        DecimalFormat df = new DecimalFormat("##.##");
        String val = df.format(value);

        if(val.length() > 3)
        {
            if(val.substring(val.length() - 2).equals("00"))
                val = val.substring(0, val.length()-2);
        }
        return val;
    }

    public static String PadLeft(String value, int count, char character)
    {
        if(value == null) value = "-99";
        if(value.isEmpty()) value = "-99";
        
        if(value.endsWith(".0")) value = value.replace(".0", "");
        if(value.endsWith(".00")) value = value.replace(".00", "");
        
        for(int i = value.length();i < count;i++)
            value = character + value;

        return value;
    }

    public static String PadLeft(Integer value, int count, char character)
    {
        if(value == null) value = -99;
        return PadLeft(value.toString(), count, character);
    }  

    public static String PadLeft(Float value, int count, char character)
    {
        if(value == null) value = -99F;
        return PadLeft(value.toString(), count, character);
    }

    public static String PadRight(Integer value, int count, char character)
    {
        if(value == null) value = -99;
        return PadLeft(value.toString(), count, character);
    }

    public static String PadRight(Float value, int count, char character)
    {
        if(value == null) value = -99F;
        return PadLeft(value.toString(), count, character);
    }

    public static String PadRight(String value, int count, char character)
    {
        if(value == null) value = "-99";
        if(value.isEmpty()) value = "-99";

        if(value.endsWith(".0")) value = value.replace(".0", "");
        if(value.endsWith(".00")) value = value.replace(".00", "");

        for(int i = value.length();i < count;i++)
            value += character;

        return value;
    }

    public static String JulianDate(Date date)
    {
        String d = "";

        try {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);

            Locale l = new Locale("en", "US");
            SimpleDateFormat df = new SimpleDateFormat("yy", l);

            d = df.format(date) + PadLeft(((Integer) ca.get(Calendar.DAY_OF_YEAR)).toString(), 3, '0');
        } catch (Exception e) {
            d = "-99";
        }

        return d;
    }
    
    public static boolean IsEmpty(String text){
        return text == null || text.isEmpty();
    }
}
