package ajitsingh.com.expensemanager.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ajitsingh.com.expensemanager.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
  @Rule
  public ActivityTestRule activityTestRule = new ActivityTestRule(MainActivity.class);

  @Test
  public void shouldDisplayExpenseButton() throws Exception {
    onView(withId(R.id.add_expense)).check(matches(withText("Add Expense")));
  }
}