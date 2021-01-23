package ajitsingh.com.expensemanager.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import ajitsingh.com.expensemanager.R;

public class CurrencyPreferencesHelper {
    public static final String PREFERENCES_NAME = "currency_preferences";

    private final Context context;

    public CurrencyPreferencesHelper(Context context) {
        this.context = context;
    }

    public void setCurrency(int currency) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putInt("currency", currency);
        editor.commit();
    }

    public String getActualCurrency() {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        String[] currencies = context.getResources().getStringArray(R.array.currency_symbols);
        return currencies[preferences.getInt("currency", 0)];
    }

    public int getActualCurrencyPosition() {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return preferences.getInt("currency", 0);
    }
}
