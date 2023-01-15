package com.example.projectstatistic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class SettingActivity extends AppCompatActivity {
    BottomNavigationView nav;
    private EditText edtuName, edtEmail;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle("Setting");


        edtuName = findViewById(R.id.idEdtuName);
        edtEmail = findViewById(R.id.idEdtEmail);
        mAuth = FirebaseAuth.getInstance();

        //set Data
        String username = mAuth.getCurrentUser().getEmail().split("@")[0].toString();
        String email = mAuth.getCurrentUser().getEmail().toString();
        edtuName.setText(username);
        edtEmail.setText(email);

        nav = findViewById(R.id.botnav);
        nav.getMenu().findItem(R.id.setting).setChecked(true);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home:
                        Intent i_home = new Intent(SettingActivity.this,MainActivity.class);
                        startActivity(i_home);

                        break;
                    case R.id.detail:

                        Intent i_detail = new Intent(SettingActivity.this,SummaryActivity.class);
                        startActivity(i_detail);


                        break;
                    case R.id.ranking:

                        Intent i_ranking = new Intent(SettingActivity.this,RankingActivity.class);
                        startActivity(i_ranking);

                        break;
                    case R.id.setting:

                        break;

                    default:;
                }

                return true;
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.idLogout:

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(SettingActivity.this,LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}