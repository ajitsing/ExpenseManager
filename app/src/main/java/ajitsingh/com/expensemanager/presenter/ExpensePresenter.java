package ajitsingh.com.expensemanager.presenter;

import android.widget.ArrayAdapter;

import java.util.Date;
import java.util.List;

import ajitsingh.com.expensemanager.database.ExpenseDatabaseHelper;
import ajitsingh.com.expensemanager.model.Expense;
import ajitsingh.com.expensemanager.view.ExpenseView;

public class ExpensePresenter {

  private ExpenseDatabaseHelper database;
  private ExpenseView view;

  public ExpensePresenter(ExpenseDatabaseHelper expenseDatabaseHelper, ExpenseView view) {
    this.database = expenseDatabaseHelper;
    this.view = view;
  }

  public void addExpense() {
    Expense expense = new Expense(Long.valueOf(view.getAmount()), view.getType(), new Date());
    database.addExpense(expense);
  }

  public void setExpenseTypes() {
    List<String> expenseTypes = database.getExpenseTypes();
    ArrayAdapter<String> adapter = view.getExpenseTypeAdapter(expenseTypes);
    view.renderExpenseTypes(adapter);
  }
}
