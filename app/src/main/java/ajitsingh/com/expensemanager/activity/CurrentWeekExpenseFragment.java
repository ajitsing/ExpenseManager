package ajitsingh.com.expensemanager.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ajitsingh.com.expensemanager.R;
import ajitsingh.com.expensemanager.database.ExpenseDatabaseHelper;

public class CurrentWeekExpenseFragment extends Fragment {
  private ExpenseDatabaseHelper expenseDatabaseHelper;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.current_week_expenses, container, false);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    expenseDatabaseHelper = new ExpenseDatabaseHelper(this.getActivity());
    expenseDatabaseHelper.getCurrentWeeksExpenses();
  }
}
