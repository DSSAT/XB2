/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Library;

import java.text.*;
import java.util.*;

/**
 *
 * @author Jazzy
 */
public class Tools {

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
}
