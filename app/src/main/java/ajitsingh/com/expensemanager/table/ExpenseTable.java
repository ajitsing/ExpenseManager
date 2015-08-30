package ajitsingh.com.expensemanager.table;

import android.provider.BaseColumns;

public class ExpenseTable implements BaseColumns {
  private static final String TABLE_NAME = "expenses";
  private static final String AMOUNT = "amount";
  private static final String TYPE = "type";
  private static final String DATE = "date";

  public static final String CREATE_TABLE_QUERY = "create table " + TABLE_NAME + " ("+
                                                      _ID + " INTEGER, "+
                                                      AMOUNT +" REAL,"+
                                                      TYPE +" TEXT, "+
                                                      DATE +" TEXT )";

}
