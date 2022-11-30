package com.intellicloudpps.crimerepoart.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.intellicloudpps.crimerepoart.Fragments.AccountFragment;
import com.intellicloudpps.crimerepoart.Fragments.AdminMyAccountFragment;
import com.intellicloudpps.crimerepoart.Fragments.AdminViewReportFragment;
import com.intellicloudpps.crimerepoart.Fragments.NewRepoartFragment;
import com.intellicloudpps.crimerepoart.Fragments.ViewRepoartsFragment;
import com.intellicloudpps.crimerepoart.R;

public class AdminPageActivity extends AppCompatActivity {
    BottomNavigationView adminBottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        adminBottomNavigationView = findViewById(R.id.adminBottomNavigationView);
        adminBottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.F3Fragment, new ViewRepoartsFragment()).commit();
    }
    BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.adminCrime:
                    fragment = new AdminViewReportFragment();
                    break;
                case R.id.adminMyAccount:
                    fragment = new AdminMyAccountFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.F3Fragment, fragment).commit();
            return true;
        }
    };

}