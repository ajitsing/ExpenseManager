package ajitsingh.com.expensemanager.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
  public static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm";
  public static final String DATE_FORMAT = "dd-MM-yyyy";

  public static String getCurrentDateTime(){
    return getFormattedDate(DATE_TIME_FORMAT);
  }

  public static String getCurrentDate(){
    return getFormattedDate(DATE_FORMAT);
  }

  private static String getFormattedDate(String format){
    Calendar now = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
    return simpleDateFormat.format(now.getTime());
  }
}
