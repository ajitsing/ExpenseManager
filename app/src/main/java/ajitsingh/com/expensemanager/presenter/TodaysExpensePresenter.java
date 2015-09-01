package ajitsingh.com.expensemanager.presenter;

import java.util.List;

import ajitsingh.com.expensemanager.database.ExpenseDatabaseHelper;
import ajitsingh.com.expensemanager.model.Expense;
import ajitsingh.com.expensemanager.view.TodaysExpenseView;

public class TodaysExpensePresenter {

  private TodaysExpenseView view;
  private final List<Expense> expenses;

  public TodaysExpensePresenter(TodaysExpenseView view, ExpenseDatabaseHelper expenseDatabaseHelper) {
    this.view = view;
    expenses = expenseDatabaseHelper.getTodaysExpenses();
  }

  public void renderTotalExpense() {
    Long totalExpense = 0l;
    for (Expense expense : expenses)
      totalExpense += expense.getAmount();

    view.displayTotalExpense(totalExpense);
  }

  public void renderTodaysExpenses() {
    view.displayTodaysExpenses(expenses);
  }
}
