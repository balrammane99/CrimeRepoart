package com.intellicloudpps.crimerepoart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.intellicloudpps.crimerepoart.Fragments.AdminFragment;
import com.intellicloudpps.crimerepoart.Fragments.LoginFragment;
import com.intellicloudpps.crimerepoart.Fragments.RegisterFragment;

 public class MainActivity extends AppCompatActivity {
     BottomNavigationView bottomNavigationView;


     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

         setContentView(R.layout.activity_main);

         bottomNavigationView = findViewById(R.id.MPBottomNavigationView);
         bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
         getSupportFragmentManager().beginTransaction().replace(R.id.F1Fragment, new LoginFragment()).commit();
     }

     BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
         @Override
         public boolean onNavigationItemSelected(@NonNull MenuItem item) {
             Fragment fragment = null;
             switch (item.getItemId()) {
                 case R.id.Login:
                     fragment = new LoginFragment();
                     break;
                 case R.id.Register:
                     fragment = new RegisterFragment();
                     break;
                 case R.id.Admin:
                     fragment = new AdminFragment();
                     break;
             }
             getSupportFragmentManager().beginTransaction().replace(R.id.F1Fragment, fragment).commit();
             return true;
         }
     };
 }