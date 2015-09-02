package ajitsingh.com.expensemanager.activity;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import ajitsingh.com.expensemanager.R;
import ajitsingh.com.expensemanager.adapter.HomeViewPagerAdapter;
import ajitsingh.com.expensemanager.presenter.NavigationDrawerPresenter;
import ajitsingh.com.expensemanager.view.NavigationDrawerItemView;


public class MainActivity extends FragmentActivity implements NavigationDrawerItemView, ActionBar.TabListener {

  private ActionBar actionBar;
  private ViewPager viewPager;
  private ActionBarDrawerToggle actionBarDrawerToggle;
  private DrawerLayout drawerLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    configureDrawer();
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
  public void render(Fragment fragment) {
    getSupportFragmentManager()
      .beginTransaction()
      .replace(R.id.main_frame, fragment, fragment.getClass().getSimpleName())
      .commit();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    actionBarDrawerToggle.onConfigurationChanged(newConfig);
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    actionBarDrawerToggle.syncState();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_settings) {
      return true;
    }

    return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
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

  private void configureDrawer() {
    drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

    actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.mipmap.ic_menu_closed, R.string.app_name, R.string.action_settings){
      @Override
      public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        drawerView.bringToFront();
      }
    };
    drawerLayout.setDrawerListener(actionBarDrawerToggle);
    drawerLayout.setDrawerShadow(R.mipmap.drawer_shadow, GravityCompat.START);
    getActionBar().setHomeButtonEnabled(true);
    getActionBar().setDisplayHomeAsUpEnabled(true);
    onDrawerItemSelected();
  }

  private void onDrawerItemSelected() {
    final ListView drawerList = (ListView) findViewById(R.id.drawer_list);
    final NavigationDrawerPresenter navigationDrawerPresenter = new NavigationDrawerPresenter(this);
    drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String[] drawerItems = getResources().getStringArray(R.array.drawer_items);
        drawerList.setItemChecked(position, true);
        getActionBar().setTitle(drawerItems[position]);
        drawerLayout.closeDrawer(GravityCompat.START);
        drawerList.bringToFront();
        navigationDrawerPresenter.onItemSelected(drawerItems[position]);
        FrameLayout mainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mainFrame.bringToFront();
      }
    });
  }
}
