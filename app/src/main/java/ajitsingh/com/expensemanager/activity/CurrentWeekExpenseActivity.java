package ajitsingh.com.expensemanager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import ajitsingh.com.expensemanager.R;
import ajitsingh.com.expensemanager.adapter.CurrentWeeksExpenseAdapter;
import ajitsingh.com.expensemanager.database.ExpenseDatabaseHelper;
import ajitsingh.com.expensemanager.model.Expense;
import ajitsingh.com.expensemanager.presenter.CurrentWeekExpensePresenter;
import ajitsingh.com.expensemanager.view.CurrentWeekExpenseView;

public class CurrentWeekExpenseActivity extends Activity implements CurrentWeekExpenseView {
  private ExpenseDatabaseHelper expenseDatabaseHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.current_week_expenses);

    expenseDatabaseHelper = new ExpenseDatabaseHelper(this);
    CurrentWeekExpensePresenter presenter = new CurrentWeekExpensePresenter(expenseDatabaseHelper, this);
    presenter.renderTotalExpenses();
    presenter.renderCurrentWeeksExpenses();
  }

  @Override
  public void displayCurrentWeeksExpenses(Map<String, List<Expense>> expensesByDate) {
    ExpandableListView listView = (ExpandableListView) findViewById(R.id.current_week_expenses_list);
    listView.setAdapter(new CurrentWeeksExpenseAdapter(this, expensesByDate));
  }

  @Override
  public void displayTotalExpenses(Long totalExpense) {
    TextView totalExpenseTextBox = (TextView) findViewById(R.id.current_week_expense);
    totalExpenseTextBox.setText(getString(R.string.total_expense) + " " + getString(R.string.rupee_sym) + totalExpense);
  }
}
