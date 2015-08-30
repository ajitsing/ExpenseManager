package ajitsingh.com.expensemanager.table;

import android.provider.BaseColumns;

public class ExpenseTypeTable implements BaseColumns {
  private final static String TABLE_NAME = "expense_types";
  private String TYPE = "type";

  public final String CREATE_TABLE_QUERY = "create table " + TABLE_NAME + " ("+ _ID +" INTEGER, "+ TYPE +" TEXT)";
}
