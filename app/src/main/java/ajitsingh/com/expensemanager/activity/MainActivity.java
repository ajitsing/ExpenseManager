package ajitsingh.com.expensemanager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import ajitsingh.com.expensemanager.R;
import ajitsingh.com.expensemanager.database.ExpenseDatabaseHelper;
import ajitsingh.com.expensemanager.model.ExpenseType;
import ajitsingh.com.expensemanager.presenter.ExpensePresenter;
import ajitsingh.com.expensemanager.view.ExpenseView;


public class MainActivity extends Activity implements ExpenseView {

  private ExpenseDatabaseHelper expenseDatabaseHelper;
  private ExpensePresenter expensePresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    expenseDatabaseHelper = new ExpenseDatabaseHelper(this);
    expensePresenter = new ExpensePresenter(expenseDatabaseHelper, this);
    expensePresenter.setExpenseTypes();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public void addExpense(View view) {
    expensePresenter.addExpense();
  }

  @Override
  public String getAmount() {
    TextView view = (TextView) this.findViewById(R.id.amount);
    return view.getText().toString();
  }

  @Override
  public String getType() {
    Spinner spinner = (Spinner) this.findViewById(R.id.expense_type);
    return (String) spinner.getSelectedItem();
  }

  @Override
  public ArrayAdapter<String> getExpenseTypeAdapter(List<String> expenseTypes) {
    return new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, expenseTypes);
  }

  @Override
  public void renderExpenseTypes(ArrayAdapter<String> adapter) {
    Spinner spinner = (Spinner) this.findViewById(R.id.expense_type);
    spinner.setAdapter(adapter);
  }
}
