package ajitsingh.com.expensemanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ajitsingh.com.expensemanager.model.Expense;
import ajitsingh.com.expensemanager.model.ExpenseType;
import ajitsingh.com.expensemanager.table.ExpenseTable;
import ajitsingh.com.expensemanager.table.ExpenseTypeTable;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class ExpenseDatabaseHelper extends SQLiteOpenHelper {
  public ExpenseDatabaseHelper(Context context) {
    super(context, "expense", null, 1);
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(ExpenseTable.CREATE_TABLE_QUERY);
    sqLiteDatabase.execSQL(ExpenseTypeTable.CREATE_TABLE_QUERY);
    seedExpenseTypes(sqLiteDatabase);
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

  }

  private void seedExpenseTypes(SQLiteDatabase sqLiteDatabase) {
    List<ExpenseType> expenseTypes = ExpenseTypeTable.seedData();
    for (ExpenseType expenseType : expenseTypes) {
      ContentValues contentValues = new ContentValues();
      contentValues.put(ExpenseTypeTable.TYPE, expenseType.getType());

      sqLiteDatabase.insert(ExpenseTypeTable.TABLE_NAME, null, contentValues);
    }
  }

  public List<String> getExpenseTypes() {
    ArrayList<String> expenseTypes = new ArrayList<>();

    SQLiteDatabase database = this.getWritableDatabase();
    Cursor cursor = database.rawQuery(ExpenseTypeTable.SELECT_ALL, null);

    if(isCursorPopulated(cursor)){
      do {
        String type = cursor.getString(cursor.getColumnIndex(ExpenseTypeTable.TYPE));
        expenseTypes.add(type);
      } while(cursor.moveToNext());
    }

    return expenseTypes;
  }

  public void addExpense(Expense expense) {
    SQLiteDatabase database = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(ExpenseTable.AMOUNT, expense.getAmount());
    values.put(ExpenseTable.TYPE, expense.getType());
    values.put(ExpenseTable.DATE, expense.getDate());

    database.insert(ExpenseTable.TABLE_NAME, null, values);
  }

  public List<Expense> getExpenses() {
    List<Expense> expenses = new ArrayList<>();
    SQLiteDatabase database = this.getWritableDatabase();
    Cursor cursor = database.rawQuery(ExpenseTable.SELECT_ALL, null);

    if(isCursorPopulated(cursor)){
      do {
        String type = cursor.getString(cursor.getColumnIndex(ExpenseTable.TYPE));
        String amount = cursor.getString(cursor.getColumnIndex(ExpenseTable.AMOUNT));
        String date = cursor.getString(cursor.getColumnIndex(ExpenseTable.DATE));
        String id = cursor.getString(cursor.getColumnIndex(ExpenseTable._ID));

        expenses.add(new Expense(parseInt(id), parseLong(amount), type, date));
      } while(cursor.moveToNext());
    }

    return expenses;
  }

  private boolean isCursorPopulated(Cursor cursor) {
    return cursor != null && cursor.moveToFirst();
  }
}
