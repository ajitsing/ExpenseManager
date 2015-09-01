package ajitsingh.com.expensemanager.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DateUtil {
  public static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm";
  public static final String DATE_FORMAT = "dd-MM-yyyy";

  public static String getCurrentDateTime(){
    Calendar now = Calendar.getInstance();
    return getFormattedDate(now, DATE_TIME_FORMAT);
  }

  public static String getCurrentDate(){
    Calendar now = Calendar.getInstance();
    return getFormattedDate(now, DATE_FORMAT);
  }

  public static ArrayList<String> getCurrentWeeksDates(){
    ArrayList<String> dates = new ArrayList<>();

    Calendar cal = Calendar.getInstance();

    for (int day = Calendar.MONDAY; day <= Calendar.SATURDAY; day++){
      cal.set(Calendar.DAY_OF_WEEK, day);
      dates.add(getFormattedDate(cal, DATE_FORMAT));
    }

    cal.add(Calendar.DATE, 1);
    dates.add(getFormattedDate(cal, DATE_FORMAT));

    return dates;
  }

  private static String getFormattedDate(Calendar date, String format){
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
    return simpleDateFormat.format(date.getTime());
  }
}
