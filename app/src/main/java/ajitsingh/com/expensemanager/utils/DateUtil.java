package ajitsingh.com.expensemanager.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
  public static final String DATE_FORMAT = "dd-MM-yyyy";

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

  public static String getDayName(String dateString) {
    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    Date date = null;
    try { date = formatter.parse(dateString); }
    catch (ParseException e) {}

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US);
  }

  private static String getFormattedDate(Calendar date, String format){
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
    return simpleDateFormat.format(date.getTime());
  }
}
