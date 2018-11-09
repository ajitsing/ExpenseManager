package ajitsingh.com.expensemanager.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import ajitsingh.com.expensemanager.R;
import ajitsingh.com.expensemanager.database.CurrencyPreferencesHelper;
import ajitsingh.com.expensemanager.presenter.CurrencyPresenter;
import ajitsingh.com.expensemanager.view.CurrencyView;

import static ajitsingh.com.expensemanager.activity.MainActivity.SELECT_ANOTHER_CURRENCY;

public class ChangeCurrencyActivity extends FragmentActivity implements CurrencyView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chane_currency);
        setSpinnerPosition();
    }

    private void setSpinnerPosition() {
        CurrencyPreferencesHelper preferencesHelper = new CurrencyPreferencesHelper(this);
        ((Spinner) findViewById(R.id.currency)).setSelection(preferencesHelper.getActualCurrencyPosition());
    }

    public void selectCurrency(View v) {
        CurrencyPreferencesHelper preferencesHelper = new CurrencyPreferencesHelper(this);
        CurrencyPresenter presenter = new CurrencyPresenter(this, preferencesHelper);
        presenter.setCurrency();
        Toast.makeText(this, R.string.currency_change_success, Toast.LENGTH_LONG).show();
        finishActivity(SELECT_ANOTHER_CURRENCY);
    }

    @Override
    public int getCurrency() {
        Spinner spinner = (Spinner) findViewById(R.id.currency);
        return spinner.getSelectedItemPosition();
    }
}
