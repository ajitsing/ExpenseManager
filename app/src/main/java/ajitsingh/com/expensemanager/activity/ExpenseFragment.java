package ajitsingh.com.expensemanager.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import ajitsingh.com.expensemanager.R;
import ajitsingh.com.expensemanager.database.ExpenseDatabaseHelper;
import ajitsingh.com.expensemanager.presenter.ExpensePresenter;
import ajitsingh.com.expensemanager.view.ExpenseView;

public class ExpenseFragment extends Fragment implements ExpenseView{
  private ExpenseDatabaseHelper expenseDatabaseHelper;
  private ExpensePresenter expensePresenter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.new_expense, container, false);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    expenseDatabaseHelper = new ExpenseDatabaseHelper(this.getActivity());
    expensePresenter = new ExpensePresenter(expenseDatabaseHelper, this);
    expensePresenter.setExpenseTypes();

    Button addExpenseButton = (Button) getActivity().findViewById(R.id.add_expense);
    addExpenseButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        expensePresenter.addExpense();
      }
    });
  }

  @Override
  public String getAmount() {
    TextView view = (TextView) getActivity().findViewById(R.id.amount);
    return view.getText().toString();
  }

  @Override
  public String getType() {
    Spinner spinner = (Spinner) getActivity().findViewById(R.id.expense_type);
    return (String) spinner.getSelectedItem();
  }

  @Override
  public ArrayAdapter<String> getExpenseTypeAdapter(List<String> expenseTypes) {
    return new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, expenseTypes);
  }

  @Override
  public void renderExpenseTypes(ArrayAdapter<String> adapter) {
    Spinner spinner = (Spinner) getActivity().findViewById(R.id.expense_type);
    spinner.setAdapter(adapter);
  }
}
