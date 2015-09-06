package ajitsingh.com.expensemanager.presenter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Map;

import ajitsingh.com.expensemanager.database.ExpenseDatabaseHelper;
import ajitsingh.com.expensemanager.model.Expense;
import ajitsingh.com.expensemanager.view.CurrentWeekExpenseView;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CurrentWeekExpensePresenterTest {

  private ExpenseDatabaseHelper database;
  private CurrentWeekExpenseView view;
  private CurrentWeekExpensePresenter presenter;

  @Before
  public void setUp() throws Exception {
    database = mock(ExpenseDatabaseHelper.class);
    view = mock(CurrentWeekExpenseView.class);

    DateTimeUtils.setCurrentMillisFixed(new DateTime("2015-09-06").getMillis());
    Expense expense1 = new Expense(90l, "Food", "03-09-2015");
    Expense expense2 = new Expense(100l, "Travel", "31-08-2015");

    when(database.getCurrentWeeksExpenses()).thenReturn(asList(expense1, expense2));

    presenter = new CurrentWeekExpensePresenter(database, view);
  }

  @After
  public void tearDown() throws Exception {
    DateTimeUtils.setCurrentMillisSystem();
  }

  @Test
  public void shouldRenderExpensesOfCurrentWeek() throws Exception {
    presenter.renderCurrentWeeksExpenses();

    ArgumentCaptor<Map> expenseCaptor = ArgumentCaptor.forClass(Map.class);

    verify(view).displayCurrentWeeksExpenses(expenseCaptor.capture());

    Map<String, List<Expense>> expenseGroupByDate = expenseCaptor.getValue();
    assertThat(expenseGroupByDate.keySet().size(), is(2));

    assertThat(expenseGroupByDate.get("31-08-2015").size(), is(1));
    assertThat(expenseGroupByDate.get("31-08-2015").get(0).getAmount(), is(100l));
    assertThat(expenseGroupByDate.get("31-08-2015").get(0).getType(), is("Travel"));

    assertThat(expenseGroupByDate.get("03-09-2015").size(), is(1));
    assertThat(expenseGroupByDate.get("03-09-2015").get(0).getAmount(), is(90l));
    assertThat(expenseGroupByDate.get("03-09-2015").get(0).getType(), is("Food"));
  }

  @Test
  public void shouldRenderTotalExpensesOfCurrentWeek() throws Exception {
    presenter.renderTotalExpenses();

    verify(view).displayTotalExpenses(190l);
  }
}