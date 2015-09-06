package ajitsingh.com.expensemanager.presenter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import ajitsingh.com.expensemanager.database.ExpenseDatabaseHelper;
import ajitsingh.com.expensemanager.model.Expense;
import ajitsingh.com.expensemanager.view.ExpenseView;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class ExpensePresenterTest {

  private ExpenseDatabaseHelper database;
  private ExpenseView view;
  private ExpensePresenter presenter;

  @Before
  public void setUp() throws Exception {
    database = mock(ExpenseDatabaseHelper.class);
    view = mock(ExpenseView.class);
    presenter = new ExpensePresenter(database, view);
  }

  @Test
  public void shouldAddExpenseAndNotify() throws Exception {
    DateTimeUtils.setCurrentMillisFixed(new DateTime("2015-09-06").getMillis());
    when(view.getAmount()).thenReturn("123");
    when(view.getType()).thenReturn("Food");

    ArgumentCaptor<Expense> expenseCaptor = ArgumentCaptor.forClass(Expense.class);

    assertTrue(presenter.addExpense());
    verify(database).addExpense(expenseCaptor.capture());

    Expense actualExpense = expenseCaptor.getValue();
    assertThat(actualExpense.getAmount(), is(123l));
    assertThat(actualExpense.getType(), is("Food"));
    assertThat(actualExpense.getDate(), is("06-09-2015"));
    DateTimeUtils.setCurrentMillisSystem();
  }

  @Test
  public void shouldNotAddExpenseAndNotifyViewWhenAmountIsEmpty() throws Exception {
    when(view.getAmount()).thenReturn("");
    when(view.getType()).thenReturn("Food");

    assertFalse(presenter.addExpense());
    verify(view).displayError();
    verifyNoMoreInteractions(database);
  }

  @Test
  public void shouldRenderExpenseTypes() throws Exception {
    List<String> expenseTypes = asList("Food", "Travel");
    when(database.getExpenseTypes()).thenReturn(expenseTypes);

    presenter.setExpenseTypes();

    verify(view).renderExpenseTypes(expenseTypes);
  }
}