package ajitsingh.com.expensemanager.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

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
    return DateTime.now().toString(DATE_FORMAT);
  }

  public static ArrayList<String> getCurrentWeeksDates(){
    ArrayList<String> dates = new ArrayList<>();

    LocalDate now = new LocalDate();
    for (int day = DateTimeConstants.MONDAY; day <= DateTimeConstants.SUNDAY; day++){
      LocalDate localDate = now.withDayOfWeek(day);
      dates.add(getFormattedDate(localDate, DATE_FORMAT));
    }

    return dates;
  }

  public static String currentMonthOfYear() {
    String date = DateTime.now().toString(DATE_FORMAT);
    String[] split = date.split("-");
    return split[1] + "-" + split[2];
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

  private static String getFormattedDate(LocalDate date, String format){
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
    return simpleDateFormat.format(date.toDate());
  }
}
