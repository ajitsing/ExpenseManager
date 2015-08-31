package ajitsingh.com.expensemanager.activity;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import ajitsingh.com.expensemanager.R;
import ajitsingh.com.expensemanager.adapter.HomeViewPagerAdapter;


public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

  private ActionBar actionBar;
  private ViewPager viewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    actionBar = getActionBar();
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

    viewPager = (ViewPager) findViewById(R.id.view_pager);
    viewPager.setAdapter(new HomeViewPagerAdapter(getSupportFragmentManager()));

    addTabs();

    viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int i, float v, int i2) {
      }

      @Override
      public void onPageSelected(int i) {
        actionBar.setSelectedNavigationItem(i);
      }

      @Override
      public void onPageScrollStateChanged(int i) {
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    viewPager.setAdapter(new HomeViewPagerAdapter(getSupportFragmentManager()));
    viewPager.setCurrentItem(tab.getPosition());
  }

  @Override
  public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

  }

  @Override
  public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

  }

  private void addTabs() {
    ActionBar.Tab addNewExpenseTab = actionBar.newTab();
    addNewExpenseTab.setTabListener(this);
    addNewExpenseTab.setText("Add New");
    actionBar.addTab(addNewExpenseTab);

    ActionBar.Tab todayTab = actionBar.newTab();
    todayTab.setTabListener(this);
    todayTab.setText("Today");
    actionBar.addTab(todayTab);
  }
}
