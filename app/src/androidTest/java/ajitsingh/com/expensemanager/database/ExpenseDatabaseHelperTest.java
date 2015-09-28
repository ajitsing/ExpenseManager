package ajitsingh.com.expensemanager.database;


import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import ajitsingh.com.expensemanager.model.ExpenseType;
import ajitsingh.com.expensemanager.table.ExpenseTypeTable;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.hamcrest.core.Is.is;
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
  }

  @After
  public void tearDown() throws Exception {
    database.close();
  }

  @Test
  public void shouldAddExpenseType() throws Exception {
    database.addExpenseType(new ExpenseType("Food"));

    List<String> expenseTypes = database.getExpenseTypes();
    assertThat(expenseTypes.size(), is(1));
    assertTrue(expenseTypes.get(0).equals("Food"));
  }
}