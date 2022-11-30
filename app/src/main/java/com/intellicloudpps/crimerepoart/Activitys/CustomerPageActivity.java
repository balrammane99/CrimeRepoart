package com.intellicloudpps.crimerepoart.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.intellicloudpps.crimerepoart.Fragments.AccountFragment;
import com.intellicloudpps.crimerepoart.Fragments.ViewRepoartsFragment;
import com.intellicloudpps.crimerepoart.Fragments.NewRepoartFragment;
import com.intellicloudpps.crimerepoart.R;

public class CustomerPageActivity extends AppCompatActivity {

    BottomNavigationView cubottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_page);
        cubottomNavigationView = findViewById(R.id.CUBottomNavigationView);
        cubottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.F2Fragment, new NewRepoartFragment()).commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.Home:
                    fragment = new NewRepoartFragment();
                    break;
                case R.id.Crime:
                    fragment = new ViewRepoartsFragment();
                    break;
                case R.id.MyAccount:
                    fragment = new AccountFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.F2Fragment, fragment).commit();
            return true;
        }
    };
}