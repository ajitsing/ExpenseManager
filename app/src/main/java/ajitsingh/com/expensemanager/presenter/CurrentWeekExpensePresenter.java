package ajitsingh.com.expensemanager.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ajitsingh.com.expensemanager.database.ExpenseDatabaseHelper;
import ajitsingh.com.expensemanager.model.Expense;
import ajitsingh.com.expensemanager.view.CurrentWeekExpenseView;

public class CurrentWeekExpensePresenter {

  private final ExpenseDatabaseHelper databaseHelper;
  private final CurrentWeekExpenseView view;

  public CurrentWeekExpensePresenter(ExpenseDatabaseHelper databaseHelper, CurrentWeekExpenseView view) {
    this.databaseHelper = databaseHelper;
    this.view = view;
  }

  public void renderCurrentWeeksExpenses() {
    List<Expense> expenses = databaseHelper.getCurrentWeeksExpenses();
    view.displayCurrentWeeksExpenses(expensesByDate(expenses));
  }

  private Map<String, List<Expense>> expensesByDate(List<Expense> expenses) {
    Map<String, List<Expense>> expensesByDate = new HashMap<>();
    for (Expense expense : expenses) {
      if(expensesByDate.get(expense.getDate()) == null){
        List<Expense> expensesList = new ArrayList<>();
        expensesList.add(expense);
        expensesByDate.put(expense.getDate(), expensesList);

      } else {
        expensesByDate.get(expense.getDate()).add(expense);
      }
    }

    return expensesByDate;
  }
}
