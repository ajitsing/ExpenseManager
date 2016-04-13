package ajitsingh.com.expensemanager.activity;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ajitsingh.com.expensemanager.R;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
  @Rule
  public ActivityTestRule activityTestRule = new ActivityTestRule(MainActivity.class);

  @Test
  public void shouldDisplayExpenseButton() throws Exception {
    activityTestRule.launchActivity(new Intent());
    onView(withId(R.id.add_expense)).check(matches(withText("Add Expense")));
  }

  @Test
  public void shouldAddExpenseWithNewExpenseType() throws Exception {
    activityTestRule.launchActivity(new Intent());
    openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
    onView(withText(R.string.action_add_category)).perform(click());

    final String expenseType = "Hello11";
    onView(withId(R.id.category)).perform(typeText(expenseType));
    onView(withId(R.id.add_category)).perform(click());
    pressBack();

    onView(withId(R.id.amount)).perform(typeText("100"));
    onView(withId(R.id.expense_type)).perform(click());
    onData(allOf(is(instanceOf(String.class)), is(expenseType))).perform(click());

    onView(withId(R.id.add_expense)).perform(click());

    onView(withText(expenseType + " - 100")).check(matches(isDisplayed()));
  }
}