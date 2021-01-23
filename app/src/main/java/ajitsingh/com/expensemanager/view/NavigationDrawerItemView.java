package ajitsingh.com.expensemanager.view;


import androidx.fragment.app.Fragment;

public interface NavigationDrawerItemView {
    void render(Fragment fragment);

    void goToHome();
}
