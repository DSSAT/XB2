package Extensions;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
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
        if (start >= 0 && start < Header.indexOf(field)) {
            int stop = Math.min(start + fieldLength + 1, value.length());

            if(stop > start){
                String tmp = value.substring(start, stop).trim();

                if (tmp != null && !"".equals(tmp)) {
                    try {
                        val = Float.valueOf(tmp);
                    } catch (NumberFormatException ex) {
                        
                    }
                }
            }
        }
        return val;
    }
    
    public static Double GetDouble(String Header, String value, String field, int fieldLength) {
        int start = Header.indexOf(field) + field.length() - fieldLength - 1;
        Double val = null;

        if (start >= 0) {
            int stop = Math.min(start + fieldLength + 1, value.length());

            String tmp = value.substring(start, stop).trim();

            if (tmp != null && !"".equals(tmp)) {
                val = Double.valueOf(tmp);
            }
        }
        return val;
    }

    public static Date GetDate(String Header, String value, String field, int fieldLength) {
        int start = Header.indexOf(field) + field.length() - fieldLength - 1;
        Date val = null;

        if (start >= 0) {
            int stop = start + fieldLength + 1;

            String tmp = value.substring(start, stop).trim();

             if (!tmp.equals("-99") && !"".equals(tmp)) {
                try {
                    int yearDigits = tmp.length() == 7 ? 2 : 0;
                    Integer year = Integer.valueOf(tmp.substring(0, 2 + yearDigits));
                    
                    if(tmp.length() == 5){
                        if (year >= 60) {
                            year += 1900;
                        } else {
                            year += 2000;
                        }
                    }
                    
                    int day = Integer.parseInt(tmp.substring(2 + yearDigits, 5 + yearDigits));

                    int month[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                    month[1] += ((year % 4) == 0) ? 1 : 0;

                    int dayCount = 0;
                    int nDay = 0;
                    int nMonth = 0;
                    for (int i = 0; i < 12; i++) {
                        if (day <= (dayCount + month[i])) {
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
                } 
                catch (NumberFormatException numberFormatException) {
                    throw numberFormatException;
                }
                catch(Exception ex){
                    //LocalDate localDate = LocalDate.of(1900, 1, 1);
                    //return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    throw ex;
                }
            }
        }
        return val;
    }

    public static Integer GetInteger(String Header, String value, String field, int fieldLength) {        
        String tmp = GetString(Header, value, field, fieldLength);
        
        Integer val = null;
        if (tmp != null && !"".equals(tmp) && !tmp.equals("-99")) {
            val = Integer.valueOf(tmp);
        }

        return val;
    }

    public static Integer ParseInteger(Object value) {
        Integer val;
        if (value == null) {
            return 0;
        }
        val = Integer.valueOf(value.toString());

        return val;
    }
    
    public static Integer TryParseInteger(Object value) {
        Integer val;
        if (value == null) {
            return 0;
        }
        
        try{
            val = Integer.valueOf(value.toString());
        }
        catch(NumberFormatException e){
            val = 0;
        }
        return val;
    }

    public static Float ParseFloat(Object value) {
        Float val = null;

        try {
            if (value == null) {
                return 0.0f;
            }
            val = Float.valueOf(value.toString());
        } catch (NumberFormatException numberFormatException) {

        }
        return val;
    }
    
    public static Double ParseDouble(Object value) {
        Double val = null;

        try {
            if (value == null) {
                return 0.00d;
            }
            val = Double.valueOf(value.toString());
        } catch (NumberFormatException numberFormatException) {

        }
        return val;
    }

    public static String GetString(String Header, String value, String field, int fieldLength) {
        int start = Header.indexOf(field);
        String val = null;

        if (start >= 0 && start <= value.length()) {
            int stop = start + fieldLength;
            if (stop > value.length()) {
                stop = value.length();
            }

            String tmp = value.substring(start, stop).trim();
            if (tmp == null || "".equals(tmp.trim())) {
                val = "-99";
            } else {
                val = tmp;
            }

            //if(!tmp.equals("-99")) val = tmp;
        }
        return val;
    }

    public static String FloatToString(Float value) {
        DecimalFormat df = new DecimalFormat("##.##");
        String val = df.format(value);

        if (val.length() > 3) {
            if (val.substring(val.length() - 2).equals("00")) {
                val = val.substring(0, val.length() - 2);
            }
        }
        return val;
    }
    
    public static String DoubleToString(Double value) {
        DecimalFormat df = new DecimalFormat("##.##");
        String val = df.format(value);

        if (val.length() > 3) {
            if (val.substring(val.length() - 2).equals("00")) {
                val = val.substring(0, val.length() - 2);
            }
        }
        return val;
    }

    public static String PadLeft(String value, int count, char character) {
        if (value == null || "".equals(value.trim()) || "-99.0".equals(value) || "-99.00".equals(value) || "-99.000".equals(value)) {
            value = "-99";
        }

        for (int i = value.length(); i < count; i++) {
            value = character + value;
        }

        return value;
    }
    
    public static String PadLeft(String value, int count, char character, boolean ignoreRemoveDigit) {
        if (value == null) {
            value = "-99";
        }
        if ("".equals(value.trim())) {
            value = "-99";
        }

        if(!ignoreRemoveDigit){
            if (value.endsWith(".0")) {
                value = value.replace(".0", "");
            }
            if (value.endsWith(".00")) {
                value = value.replace(".00", "");
            }
            if (value.endsWith(".000")) {
                value = value.replace(".000", "");
            }
        }

        for (int i = value.length(); i < count; i++) {
            value = character + value;
        }

        return value;
    }

    public static String PadLeft(Integer value, int count, char character) {
        if (value == null) {
            value = -99;
        }
        return PadLeft(value.toString(), count, character);
    }

    public static String PadLeft(Float value, int count, char character) {
        if (value == null) {
            value = -99F;
        }
        return PadLeft(value.toString(), count, character, false);
    }
    
    public static String PadLeft(Double value, int count, char character) {
        if (value == null) {
            value = -99d;
        }
        return PadLeft(value.toString(), count, character);
    }

    public static String PadRight(Integer value, int count, char character) {
        if (value == null) {
            value = -99;
        }
        return PadLeft(value.toString(), count, character);
    }

    public static String PadRight(Float value, int count, char character) {
        if (value == null) {
            value = -99F;
        }
        return PadLeft(value.toString(), count, character);
    }

    public static String PadRight(String value, int count, char character) {
        if (value == null || "".equals(value.trim()) || "-99.0".equals(value) || "-99.00".equals(value) || "-99.000".equals(value)) {
            value = "-99";
        }

        for (int i = value.length(); i < count; i++) {
            value += character;
        }
        
        if(value.length() > count)
            value = value.substring(0, count);

        return value;
    }

    public static String JulianDate(Date date) {
        return JulianDate(date, "yy");
    }
    
    public static String JulianDate(Date date, String yearFormat) {
        String d;

        try {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);

            Locale l = new Locale("en", "US");
            SimpleDateFormat df = new SimpleDateFormat(yearFormat, l);

            d = df.format(date) + PadLeft(((Integer) ca.get(Calendar.DAY_OF_YEAR)).toString(), 3, '0');
        } catch (Exception e) {
            d = "-99";
        }

        return d;
    }

    public static boolean IsEmpty(String text) {
        return text == null || "".equals(text.trim());
    }

    /**
     * Formats the @N / @L row-number column, which DSSAT lays out in a fixed
     * {@code width}-column zone (3 for FileX). Values that fit are right-justified
     * in the leading {@code width-1} columns followed by a separating space
     * (" 1 ", "10 "); values that fill the whole zone consume that separator
     * ("100"), so the columns after the number stay aligned past 99 rows.
     */
    public static String formatLevelField(int level, int width) {
        String value = String.valueOf(level);
        if (value.length() >= width) {
            return value;
        }
        return PadLeft(value, width - 1, ' ') + " ";
    }

    public static String formatSequenceNR(int n, int r) {
        return String.valueOf(n) + PadLeft(String.valueOf(r), 2, '0');
    }

    /**
     * Sequence files use two treatment line formats:
     * - R 1-9:  separate columns, e.g. " 1 2 1 0 Cotton12"
     * - R 10-99: combined NR field, e.g. " 110 1 0 Fallow13F"
     */
    public static int[] parseSequenceNR(String line) {
        String[] parts = line.trim().split("\\s+");
        if (parts.length == 0) {
            return new int[]{1, 1};
        }

        if (parts[0].length() == 1) {
            int n = TryParseInteger(parts[0]);
            int r = parts.length > 1 ? TryParseInteger(parts[1]) : 1;
            return new int[]{n, r};
        }

        int n = Character.getNumericValue(parts[0].charAt(0));
        int r = parts[0].length() > 1 ? TryParseInteger(parts[0].substring(1)) : 1;
        return new int[]{n, r};
    }

    public static String parseSequenceO(String line) {
        String[] parts = line.trim().split("\\s+");
        if (parts.length == 0) {
            return "0";
        }
        if (parts[0].length() == 1) {
            return parts.length > 2 ? parts[2] : "0";
        }
        return parts.length > 1 ? parts[1] : "0";
    }

    public static String parseSequenceC(String line) {
        String[] parts = line.trim().split("\\s+");
        if (parts.length == 0) {
            return "0";
        }
        if (parts[0].length() == 1) {
            return parts.length > 3 ? parts[3] : "0";
        }
        return parts.length > 2 ? parts[2] : "0";
    }

    public static String formatTreatmentDigit(String value, String defaultValue) {
        if (value == null || "".equals(value.trim()) || "-99".equals(value.trim())) {
            return defaultValue;
        }
        return value.trim().substring(0, 1);
    }
}
