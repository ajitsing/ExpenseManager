package ajitsingh.com.expensemanager.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeUtils;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DateUtilTest {

  @Before
  public void setUp() throws Exception {
    DateTimeUtils.setCurrentMillisFixed(new DateTime("2015-09-06").getMillis());
  }

  @After
  public void tearDown() throws Exception {
    DateTimeUtils.setCurrentMillisSystem();
  }

  @Test
  public void shouldReturnDayForDate() throws Exception {
    String dayName = DateUtil.getDayName("01-09-2015");
    assertThat(dayName, is("Tue"));
  }

  @Test
  public void shouldReturnCurrentMonthOfYear() throws Exception {
    String currentMonthOfYear = DateUtil.currentMonthOfYear();
    assertThat(currentMonthOfYear, is("09-2015"));
  }

  @Test
  public void shouldReturnCurrentDate() throws Exception {
    String date = DateUtil.getCurrentDate();
    assertThat(date, is("06-09-2015"));
  }

  @Test
  public void shouldReturnCurrentWeeksDatesFromMonToSun() throws Exception {
    LocalDate now = new LocalDate();
    assertThat(now.withDayOfWeek(DateTimeConstants.MONDAY).toString(), is("2015-08-31"));
    assertThat(now.withDayOfWeek(DateTimeConstants.SUNDAY).toString(), is("2015-09-06"));
    ArrayList<String> currentWeeksDates = DateUtil.getCurrentWeeksDates();

    assertThat(currentWeeksDates.size(), is(7));
    assertThat(currentWeeksDates, hasItems(
        "31-08-2015",
        "01-09-2015",
        "02-09-2015",
        "03-09-2015",
        "04-09-2015",
        "05-09-2015",
        "06-09-2015")
    );
  }
}