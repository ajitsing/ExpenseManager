package ajitsingh.com.expensemanager.presenter;

import android.content.Context;

import java.util.Currency;

import ajitsingh.com.expensemanager.database.CurrencyPreferencesHelper;
import ajitsingh.com.expensemanager.view.CurrencyView;

public class CurrencyPresenter {
    private final CurrencyView view;
    private final CurrencyPreferencesHelper preferencesHelper;

    public CurrencyPresenter(CurrencyView view, CurrencyPreferencesHelper preferencesHelper) {
        this.view = view;
        this.preferencesHelper = preferencesHelper;
    }

    public void setCurrency() {
        int currency = view.getCurrency();
        preferencesHelper.setCurrency(currency);
    }
}
