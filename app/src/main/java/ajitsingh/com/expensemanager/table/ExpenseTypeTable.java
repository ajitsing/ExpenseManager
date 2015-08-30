package ajitsingh.com.expensemanager.table;

import android.content.ContentValues;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import ajitsingh.com.expensemanager.model.ExpenseType;

public class ExpenseTypeTable implements BaseColumns {
  public final static String TABLE_NAME = "expense_types";
  public final static String TYPE = "type";

  public final static String CREATE_TABLE_QUERY = "create table " + TABLE_NAME + " ("+ _ID +" INTEGER, "+ TYPE +" TEXT)";

  public static List<ExpenseType> seedData(){
    ArrayList<ExpenseType> expenseTypes = new ArrayList<>();
    expenseTypes.add(new ExpenseType("Rent"));
    expenseTypes.add(new ExpenseType("Shopping"));
    expenseTypes.add(new ExpenseType("Food"));
    expenseTypes.add(new ExpenseType("Travel"));
    expenseTypes.add(new ExpenseType("Money-Transfer"));
    expenseTypes.add(new ExpenseType("Other"));

    return expenseTypes;
  }
}
