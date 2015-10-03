package ajitsingh.com.expensemanager.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import ajitsingh.com.expensemanager.database.ExpenseDatabaseHelper;
import ajitsingh.com.expensemanager.model.ExpenseType;
import ajitsingh.com.expensemanager.view.AddCategoryView;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class CategoryPresenterTest {

  private ExpenseDatabaseHelper database;
  private AddCategoryView view;
  private CategoryPresenter presenter;

  @Before
  public void setUp() throws Exception {
    database = mock(ExpenseDatabaseHelper.class);
    view = mock(AddCategoryView.class);
    presenter = new CategoryPresenter(view, database);
  }

  @Test
  public void shouldAddCategoryToTheDatabaseAndNotify() throws Exception {
    when(view.getCategory()).thenReturn("Movie");

    ArgumentCaptor<ExpenseType> expenseTypeCaptor = ArgumentCaptor.forClass(ExpenseType.class);

    assertTrue(presenter.addCategory());
    verify(database).addExpenseType(expenseTypeCaptor.capture());

    ExpenseType expenseType = expenseTypeCaptor.getValue();
    assertThat(expenseType.getType(), is("Movie"));
  }

  @Test
  public void shouldNotAddCategoryAndNotifyViewWhenCategoryInputIsEmpty() throws Exception {
    when(view.getCategory()).thenReturn("");

    assertFalse(presenter.addCategory());
    verifyNoMoreInteractions(database);
    verify(view).displayError();
  }
}