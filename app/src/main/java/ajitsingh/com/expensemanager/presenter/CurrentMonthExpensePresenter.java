package ajitsingh.com.expensemanager.presenter;

import com.echo.holographlibrary.Bar;

import java.util.ArrayList;
import java.util.List;

import ajitsingh.com.expensemanager.database.ExpenseDatabaseHelper;
import ajitsingh.com.expensemanager.model.Expense;
import ajitsingh.com.expensemanager.utils.ExpenseCollection;
import ajitsingh.com.expensemanager.view.CurrentMonthExpenseView;


public class CurrentMonthExpensePresenter {
  private final CurrentMonthExpenseView view;
  private final ExpenseCollection expenseCollection;

  public CurrentMonthExpensePresenter(CurrentMonthExpenseView view, ExpenseDatabaseHelper database) {
    this.view = view;
    List<Expense> expenses = database.getExpensesForCurrentMonthGroupByCategory();
    expenseCollection = new ExpenseCollection(expenses);
  }

  public void plotGraph() {
    List<Bar> points = new ArrayList<Bar>();

    for (Expense expense : expenseCollection.withoutMoneyTransfer()) {
      Bar bar = new Bar();
      bar.setColor(view.getGraphColor());
      bar.setName(expense.getType());
      bar.setValue(expense.getAmount());
      points.add(bar);
    }

    view.displayGraph(points);
  }

  public void showTotalExpense() {
    view.displayTotalExpense(expenseCollection.getTotalExpense());
  }
}
