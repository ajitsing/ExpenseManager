package ajitsingh.com.expensemanager.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import ajitsingh.com.expensemanager.R;
import ajitsingh.com.expensemanager.model.Expense;
import ajitsingh.com.expensemanager.utils.ExpenseCollection;

import static ajitsingh.com.expensemanager.utils.DateUtil.getDayName;

public class CurrentWeeksExpenseAdapter implements ExpandableListAdapter {
  private Context context;
  private final Map<String, List<Expense>> expenses;

  public CurrentWeeksExpenseAdapter(Context context, Map<String, List<Expense>> expenses) {
    this.context = context;
    this.expenses = expenses;
  }

  @Override
  public void registerDataSetObserver(DataSetObserver dataSetObserver) {

  }

  @Override
  public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

  }

  @Override
  public int getGroupCount() {
    return expenses.keySet().size();
  }

  @Override
  public int getChildrenCount(int position) {
    return expenses.get(expenses.keySet().toArray()[position]).size();
  }

  @Override
  public Object getGroup(int position) {
    String date = (String) this.expenses.keySet().toArray()[position];
    Long totalExpense = new ExpenseCollection(this.expenses.get(date)).getTotalExpense();

    return date + " (" + getDayName(date) + ") - " + context.getString(R.string.rupee_sym) + totalExpense;
  }

  @Override
  public Object getChild(int parent, int child) {
    Expense expense = expenses.get(expenses.keySet().toArray()[parent]).get(child);
    return expense.getType() + " - " + expense.getAmount();
  }

  @Override
  public long getGroupId(int position) {
    return position;
  }

  @Override
  public long getChildId(int parent, int child) {
    return child;
  }

  @Override
  public boolean hasStableIds() {
    return false;
  }

  @Override
  public View getGroupView(int parent, boolean isExpanded, View converterView, ViewGroup viewGroup) {
    String parentText = (String) getGroup(parent);

    if(converterView == null){
      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      converterView = inflater.inflate(R.layout.expense_header_text_box, viewGroup, false);
    }

    TextView textBox = (TextView) converterView.findViewById(R.id.expense_header_text_box);
    textBox.setText(parentText);

    return converterView;
  }

  @Override
  public View getChildView(int parent, int child, boolean lastChild, View converterView, ViewGroup viewGroup) {
    String childText = (String) getChild(parent, child);

    if(converterView == null){
      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      converterView = inflater.inflate(R.layout.expense_text_box, viewGroup, false);
    }

    TextView textBox = (TextView) converterView.findViewById(R.id.expense_text_box);
    textBox.setText(childText);

    return converterView;
  }

  @Override
  public boolean isChildSelectable(int i, int i1) {
    return false;
  }

  @Override
  public boolean areAllItemsEnabled() {
    return false;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public void onGroupExpanded(int i) {

  }

  @Override
  public void onGroupCollapsed(int i) {

  }

  @Override
  public long getCombinedChildId(long l, long l1) {
    return 0;
  }

  @Override
  public long getCombinedGroupId(long l) {
    return 0;
  }
}
