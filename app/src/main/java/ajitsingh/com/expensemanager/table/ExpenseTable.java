package ajitsingh.com.expensemanager.table;

import android.provider.BaseColumns;

public class ExpenseTable implements BaseColumns {
  public static final String TABLE_NAME = "expenses";
  public static final String AMOUNT = "amount";
  public static final String TYPE = "type";
  public static final String DATE = "date";

  public static final String CREATE_TABLE_QUERY = "create table " + TABLE_NAME + " ("+
                                                      _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                                      AMOUNT +" INTEGER,"+
                                                      TYPE +" TEXT, "+
                                                      DATE +" TEXT )";

  public static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + _ID + " DESC";

  public static String getExpensesForDate(String date){
    return "SELECT * FROM " + TABLE_NAME + " WHERE date like '"+date+"%' ORDER BY " + _ID + " DESC";
  }
}
