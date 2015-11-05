package ajitsingh.com.expensemanager.activity;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import ajitsingh.com.expensemanager.R;
import ajitsingh.com.expensemanager.adapter.DrawerListViewAdapter;
import ajitsingh.com.expensemanager.adapter.HomeViewPagerAdapter;
import ajitsingh.com.expensemanager.notification.FillExpenseNotificationScheduler;
import ajitsingh.com.expensemanager.presenter.NavigationDrawerPresenter;
import ajitsingh.com.expensemanager.view.NavigationDrawerItemView;


public class MainActivity extends FragmentActivity implements NavigationDrawerItemView, ActionBar.TabListener {

  private ActionBar actionBar;
  private ViewPager viewPager;
  private ActionBarDrawerToggle actionBarDrawerToggle;
  private DrawerLayout drawerLayout;
  public static final int ADD_NEW_CAT = 9991;
  private static Boolean isNotificationScheduled = false;
  private HomeViewPagerAdapter homeViewPagerAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    configureDrawer();
    configureActionBar();

    if (!isNotificationScheduled) scheduleReminder();
  }

  @Override
  public void render(Fragment fragment) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment existingFragment = fragmentManager.findFragmentById(R.id.main_frame);

    if(existingFragment != null && existingFragment.getClass() == fragment.getClass()) return;

    fragmentManager.beginTransaction()
      .addToBackStack(MainActivity.class.getSimpleName())
      .replace(R.id.main_frame, fragment, fragment.getClass().getSimpleName())
      .commit();

    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
  }

  @Override
  public void goToHome() {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
    if (backStackEntryCount == 0)
      actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    actionBar.setTitle(R.string.app_name);
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

    if (id == R.id.action_add_category) {
      Intent intent = new Intent(this, AddCategoryActivity.class);
      startActivityForResult(intent, ADD_NEW_CAT);
      return true;
    }

    return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == ADD_NEW_CAT) {
      viewPager.setAdapter(new HomeViewPagerAdapter(getSupportFragmentManager()));
      viewPager.setCurrentItem(0);
    }
  }

  public void onExpenseAdded() {
    viewPager.setAdapter(homeViewPagerAdapter);
    actionBar.setSelectedNavigationItem(1);
  }

  @Override
  public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    viewPager.setCurrentItem(tab.getPosition());
  }

  @Override
  public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

  }

  @Override
  public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

  }

  private void configureDrawer() {
    drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

    actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.mipmap.ic_menu_closed, R.string.app_name, R.string.action_settings) {
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

    ListView drawerList = (ListView) findViewById(R.id.drawer_list);
    drawerList.setAdapter(new DrawerListViewAdapter(this));

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

  private void configureActionBar() {
    actionBar = getActionBar();
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

    viewPager = (ViewPager) findViewById(R.id.view_pager);
    homeViewPagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager());
    viewPager.setAdapter(homeViewPagerAdapter);

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

  private void scheduleReminder() {
    new FillExpenseNotificationScheduler().schedule(this);
    isNotificationScheduled = true;
  }
}
