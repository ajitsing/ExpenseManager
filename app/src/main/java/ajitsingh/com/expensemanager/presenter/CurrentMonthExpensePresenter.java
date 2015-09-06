package ajitsingh.com.expensemanager.presenter;

import android.graphics.Color;

import com.echo.holographlibrary.Bar;

import java.util.ArrayList;
import java.util.List;

import ajitsingh.com.expensemanager.database.ExpenseDatabaseHelper;
import ajitsingh.com.expensemanager.model.Expense;
import ajitsingh.com.expensemanager.utils.ExpenseCollection;
import ajitsingh.com.expensemanager.view.CurrentMonthExpenseView;


public class CurrentMonthExpensePresenter {
  private final CurrentMonthExpenseView view;
  private final ExpenseDatabaseHelper database;

  public CurrentMonthExpensePresenter(CurrentMonthExpenseView view, ExpenseDatabaseHelper database) {
    this.view = view;
    this.database = database;
  }

  public void plotGraph() {
    List<Bar> points = new ArrayList<Bar>();

    List<Expense> expenses = database.getExpensesForCurrentMonthGroupByCategory();
    ExpenseCollection expenseCollection = new ExpenseCollection(expenses);

    for (Expense expense : expenseCollection.withoutMoneyTransfer()) {
      Bar bar = new Bar();
      bar.setColor(Color.parseColor("#B50012"));
      bar.setName(expense.getType());
      bar.setValue(expense.getAmount());
      points.add(bar);
    }

    view.renderGraph(points);
  }
}
