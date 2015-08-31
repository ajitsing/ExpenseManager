package ajitsingh.com.expensemanager.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import ajitsingh.com.expensemanager.model.Expense;

public class TodaysExpenseListViewAdapter extends ArrayAdapter{
  private final List<Expense> expenses;

  public TodaysExpenseListViewAdapter(List<Expense> expenses, Context context, int resource) {
    super(context, resource);
    this.expenses = expenses;
  }

  @Override
  public int getCount() {
    return expenses.size();
  }

  @Override
  public Object getItem(int position) {
    Expense expense = expenses.get(position);
    return expense.getType() + " - " + expense.getAmount();
  }

  @Override
  public long getItemId(int position) {
    return expenses.get(position).getId();
  }
}
