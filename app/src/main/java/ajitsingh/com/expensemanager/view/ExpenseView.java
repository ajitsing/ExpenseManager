package ajitsingh.com.expensemanager.view;

import android.widget.ArrayAdapter;

import java.util.List;

public interface ExpenseView {
  String getAmount();
  String getType();
  ArrayAdapter<String> getExpenseTypeAdapter(List<String> expenseTypes);
  void renderExpenseTypes(ArrayAdapter<String> adapter);
  void displayError();
}
