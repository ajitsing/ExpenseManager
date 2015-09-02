package ajitsingh.com.expensemanager.utils;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DateUtilTest {

  @Test
  public void shouldReturnDayForDate() throws Exception {
    String dayName = DateUtil.getDayName("01-09-2015");
    assertThat(dayName, is("Tue"));
  }
}