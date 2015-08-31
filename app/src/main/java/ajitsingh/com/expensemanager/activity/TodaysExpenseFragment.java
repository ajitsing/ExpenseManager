package ajitsingh.com.expensemanager.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import ajitsingh.com.expensemanager.R;
import ajitsingh.com.expensemanager.adapter.TodaysExpenseListViewAdapter;
import ajitsingh.com.expensemanager.database.ExpenseDatabaseHelper;
import ajitsingh.com.expensemanager.model.Expense;

public class TodaysExpenseFragment extends Fragment {
  private ExpenseDatabaseHelper expenseDatabaseHelper;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.todays_expenses, container, false);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    expenseDatabaseHelper = new ExpenseDatabaseHelper(this.getActivity());
    ListView listView = (ListView) getActivity().findViewById(R.id.todays_expenses_list);

    List<Expense> expenses = expenseDatabaseHelper.getExpenses();
    listView.setAdapter(new TodaysExpenseListViewAdapter(expenses, getActivity(), android.R.layout.simple_list_item_1));
  }
}
