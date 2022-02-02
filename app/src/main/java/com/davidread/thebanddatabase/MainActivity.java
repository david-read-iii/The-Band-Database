package com.davidread.thebanddatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

/**
 * {@link MainActivity} represents a user interface with a primary-detail design pattern. The design
 * half shows a {@link ListFragment} and the detail half shows a {@link DetailFragment}.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Callback method invoked when this activity is initially created. It simply sets up the
     * primary-detail design pattern used to show a list of bands and details about a selected band.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();
            AppBarConfiguration appBarConfig = new AppBarConfiguration.
                    Builder(navController.getGraph()).build();
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig);
        }
    }

    /**
     * Callback method invoked when the up button is clicked. It simply navigates the user back to
     * {@link ListFragment}.
     *
     * @return True if up navigation is completed successfully and this activity was finished.
     */
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}