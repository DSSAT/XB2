package xbuild.Components;

import FileXModel.FileX;
import java.util.Calendar;
import java.util.Date;

public class DefaultDateHelper {
    public static Date getDefaultDate() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if (FileX.general != null && FileX.general.Year != null) {
            try {
                year = Integer.parseInt(FileX.general.Year.trim());
            } catch (Exception e) {}
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
