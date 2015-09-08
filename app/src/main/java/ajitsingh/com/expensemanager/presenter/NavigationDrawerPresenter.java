package ajitsingh.com.expensemanager.presenter;

import ajitsingh.com.expensemanager.activity.CurrentMonthExpenseFragment;
import ajitsingh.com.expensemanager.activity.CurrentWeekExpenseFragment;
import ajitsingh.com.expensemanager.view.NavigationDrawerItemView;

public class NavigationDrawerPresenter {

  private NavigationDrawerItemView view;

  public NavigationDrawerPresenter(NavigationDrawerItemView view) {
    this.view = view;
  }

  public void onItemSelected(String drawerItem) {
    switch (drawerItem){
      case "This Week":
        view.render(new CurrentWeekExpenseFragment());
        break;
      case "This Month":
        view.render(new CurrentMonthExpenseFragment());
        break;
      case "Home":
        view.goToHome();
        break;
    }
  }
}
