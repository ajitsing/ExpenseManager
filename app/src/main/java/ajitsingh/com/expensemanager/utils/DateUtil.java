package ajitsingh.com.expensemanager.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
  public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm";

  public static String getCurrentDateTime(){
    Calendar now = Calendar.getInstance();
    SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
    return format.format(now.getTime());
  }
}
