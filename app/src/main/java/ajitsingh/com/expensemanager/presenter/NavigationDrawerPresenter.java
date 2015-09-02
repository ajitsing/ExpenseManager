package ajitsingh.com.expensemanager.presenter;

import ajitsingh.com.expensemanager.activity.CurrentWeekExpenseActivity;
import ajitsingh.com.expensemanager.view.NavigationDrawerItemView;

public class NavigationDrawerPresenter {

  private NavigationDrawerItemView view;

  public NavigationDrawerPresenter(NavigationDrawerItemView view) {
    this.view = view;
  }

  public void onItemSelected(String drawerItem) {
    switch (drawerItem){
      case "This Week":
        view.render(CurrentWeekExpenseActivity.class);
    }
  }
}
