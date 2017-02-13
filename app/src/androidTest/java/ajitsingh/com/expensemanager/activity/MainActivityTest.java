package ajitsingh.com.expensemanager.activity;

import android.content.Intent;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ajitsingh.com.expensemanager.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by Ajit on 2/12/17.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
  @Rule
  public ActivityTestRule rule = new ActivityTestRule(MainActivity.class, true, false);

  @Test
  public void shouldRenderView() throws Exception {
    rule.launchActivity(new Intent());

    onView(withId(R.id.add_new_expense_id)).check(matches(withText(R.string.add_new_expense)));
    onView(withId(R.id.add_expense)).check(matches(isDisplayed()));
  }

  @Test
  public void secondTest() throws Exception {
    /*
    withSpinnerText
    isCompletelyDisplayed
    hasSibling
    hasDescendant
    isDescendantOfA
    isClickable
    withEffectiveVisibility
    allOf
    withHint
    * */

    rule.launchActivity(new Intent());

    onView(withId(R.id.expense_type)).check(matches(withSpinnerText("Food")));
    onView(withId(R.id.expense_type)).check(matches(isCompletelyDisplayed()));

    onView(withId(R.id.amount)).check(matches(hasSibling(withId(R.id.expense_type))));
    onView(withId(R.id.amount)).check(matches(hasSibling(withId(R.id.add_expense))));

    onView(withId(R.id.add_expense_container)).check(matches(hasDescendant(withId(R.id.amount))));
    onView(withId(R.id.amount)).check(matches(isDescendantOfA(withId(R.id.add_expense_container))));

    onView(withId(R.id.add_expense)).check(matches(isClickable()));
    onView(withId(R.id.add_expense)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    onView(allOf(withHint(R.id.amount), isDescendantOfA(withId(R.id.add_expense_container))))
        .check(matches(isDisplayed()));
  }
}