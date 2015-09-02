package ajitsingh.com.expensemanager.presenter;

import java.util.List;

import ajitsingh.com.expensemanager.database.ExpenseDatabaseHelper;
import ajitsingh.com.expensemanager.model.Expense;
import ajitsingh.com.expensemanager.utils.ExpenseCollection;
import ajitsingh.com.expensemanager.view.CurrentWeekExpenseView;

public class CurrentWeekExpensePresenter {

  private final ExpenseDatabaseHelper databaseHelper;
  private final CurrentWeekExpenseView view;
  private ExpenseCollection expenseCollection;

  public CurrentWeekExpensePresenter(ExpenseDatabaseHelper databaseHelper, CurrentWeekExpenseView view) {
    this.databaseHelper = databaseHelper;
    this.view = view;
  }

  public void renderTotalExpenses() {
    List<Expense> expenses = databaseHelper.getCurrentWeeksExpenses();
    expenseCollection = new ExpenseCollection(expenses);
    view.displayTotalExpenses(expenseCollection.getTotalExpense());
  }

  public void renderCurrentWeeksExpenses() {
    view.displayCurrentWeeksExpenses(expenseCollection.groupByDate());
  }
}
