package ajitsingh.com.expensemanager.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.List;
import java.util.Map;

import ajitsingh.com.expensemanager.R;
import ajitsingh.com.expensemanager.adapter.CurrentWeeksExpenseAdapter;
import ajitsingh.com.expensemanager.database.ExpenseDatabaseHelper;
import ajitsingh.com.expensemanager.model.Expense;
import ajitsingh.com.expensemanager.presenter.CurrentWeekExpensePresenter;
import ajitsingh.com.expensemanager.view.CurrentWeekExpenseView;

public class CurrentWeekExpenseFragment extends Fragment implements CurrentWeekExpenseView {
  private ExpenseDatabaseHelper expenseDatabaseHelper;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.current_week_expenses, container, false);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    expenseDatabaseHelper = new ExpenseDatabaseHelper(this.getActivity());
    CurrentWeekExpensePresenter presenter = new CurrentWeekExpensePresenter(expenseDatabaseHelper, this);
    presenter.renderCurrentWeeksExpenses();
  }

  @Override
  public void displayCurrentWeeksExpenses(Map<String, List<Expense>> expensesByDate) {
    ExpandableListView listView = (ExpandableListView) getActivity().findViewById(R.id.current_week_expenses_list);
    listView.setAdapter(new CurrentWeeksExpenseAdapter(getActivity(), expensesByDate));
  }
}
