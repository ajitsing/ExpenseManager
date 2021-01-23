package ajitsingh.com.expensemanager.activity;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ajitsingh.com.expensemanager.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule activityTestRule = new ActivityScenarioRule(MainActivity.class);

    @Test
    public void shouldDisplayExpenseButton() throws Exception {
        onView(withId(R.id.add_expense)).check(matches(withText("Add Expense")));
    }
}