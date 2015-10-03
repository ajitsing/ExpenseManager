package ajitsingh.com.expensemanager.database;


import android.support.test.runner.AndroidJUnit4;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import ajitsingh.com.expensemanager.model.Expense;
import ajitsingh.com.expensemanager.model.ExpenseType;
import ajitsingh.com.expensemanager.table.ExpenseTable;
import ajitsingh.com.expensemanager.table.ExpenseTypeTable;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ExpenseDatabaseHelperTest {

  private ExpenseDatabaseHelper database;

  @Before
  public void setUp() throws Exception {
    getTargetContext().deleteDatabase(ExpenseDatabaseHelper.EXPENSE_DB);
    database = new ExpenseDatabaseHelper(getTargetContext());
    database.truncate(ExpenseTypeTable.TABLE_NAME);
    database.truncate(ExpenseTable.TABLE_NAME);

    freezeDate("2015-10-02");
  }

  @After
  public void tearDown() throws Exception {
    database.close();
    DateTimeUtils.setCurrentMillisSystem();
  }

  @Test
  public void shouldAddExpenseType() throws Exception {
    database.addExpenseType(new ExpenseType("Food"));

    List<String> expenseTypes = database.getExpenseTypes();
    assertThat(expenseTypes.size(), is(1));
    assertTrue(expenseTypes.get(0).equals("Food"));
  }

  @Test
  public void shouldReturnCurrentMonthsExpenses() throws Exception {
    database.addExpense(new Expense(100l, "Food", "31-09-2015"));
    database.addExpense(new Expense(200l, "Food", "02-10-2015"));

    List<Expense> expenses = database.getExpensesForCurrentMonthGroupByCategory();

    assertThat(expenses.size(), is(1));
    assertThat(expenses.get(0).getAmount(), is(200l));
  }

  @Test
  public void shouldReturnCurrentMonthsExpensesForMultipleExpenseTypes() throws Exception {
    database.addExpense(new Expense(100l, "Food", "31-09-2015"));
    database.addExpense(new Expense(200l, "Food", "02-10-2015"));

    database.addExpense(new Expense(300l, "Travel", "10-10-2015"));
    database.addExpense(new Expense(200l, "Travel", "02-10-2015"));

    database.addExpense(new Expense(500l, "Movie", "02-09-2015"));

    List<Expense> expenses = database.getExpensesForCurrentMonthGroupByCategory();

    assertThat(expenses.size(), is(2));
  }

  @Test
  public void shouldReturnTodaysExpenses() throws Exception {
    database.addExpense(new Expense(200l, "Food", "02-10-2015"));

    List<Expense> expenses = database.getTodaysExpenses();

    assertThat(expenses.size(), is(1));
    assertThat(expenses.get(0).getAmount(), is(200l));
    assertThat(expenses.get(0).getType(), is("Food"));
    assertThat(expenses.get(0).getDate(), is("02-10-2015"));
  }

  @Test
  public void shouldReturnTodaysExpensesForMultipleExpenseTypes() throws Exception {
    database.addExpense(new Expense(200l, "Food", "02-10-2015"));
    database.addExpense(new Expense(300l, "Travel", "02-10-2015"));

    List<Expense> expenses = database.getTodaysExpenses();

    assertThat(expenses.size(), is(2));
  }

  @Test
  public void shouldReturnCurrentWeeksExpenses() throws Exception {
    database.addExpense(new Expense(200l, "Food", "02-10-2015"));
    database.addExpense(new Expense(100l, "Food", "02-10-2015"));
    database.addExpense(new Expense(300l, "Travel", "03-10-2015"));
    database.addExpense(new Expense(400l, "Movie", "04-10-2015"));

    List<Expense> expenses = database.getCurrentWeeksExpenses();
    ArrayList<String> expenseTypes = new ArrayList<>();
    for (Expense expense : expenses) {
      expenseTypes.add(expense.getType());
    }

    assertThat(expenses.size(), is(3));
    assertThat(expenseTypes, hasItems("Food", "Travel", "Movie"));
  }

  private void freezeDate(String date) {
    DateTimeUtils.setCurrentMillisFixed(new DateTime(date).getMillis());
  }
}