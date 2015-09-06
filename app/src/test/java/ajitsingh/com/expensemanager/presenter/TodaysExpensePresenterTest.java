package ajitsingh.com.expensemanager.presenter;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import ajitsingh.com.expensemanager.database.ExpenseDatabaseHelper;
import ajitsingh.com.expensemanager.model.Expense;
import ajitsingh.com.expensemanager.view.TodaysExpenseView;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TodaysExpensePresenterTest {

  private ExpenseDatabaseHelper database;
  private TodaysExpenseView view;
  private TodaysExpensePresenter presenter;
  private List<Expense> expenses;

  @Before
  public void setUp() throws Exception {
    database = mock(ExpenseDatabaseHelper.class);
    view = mock(TodaysExpenseView.class);

    Expense expense1 = new Expense(90l, "Food", "03-09-2015");
    Expense expense2 = new Expense(100l, "Travel", "31-08-2015");
    expenses = asList(expense1, expense2);
    when(database.getTodaysExpenses()).thenReturn(expenses);

    presenter = new TodaysExpensePresenter(view, database);
  }

  @Test
  public void shouldRenderTodaysExpenses() throws Exception {
    presenter.renderTodaysExpenses();
    verify(view).displayTodaysExpenses(expenses);
  }

  @Test
  public void shouldRenderTodaysTotalExpenses() throws Exception {
    presenter.renderTotalExpense();
    verify(view).displayTotalExpense(190l);
  }
}