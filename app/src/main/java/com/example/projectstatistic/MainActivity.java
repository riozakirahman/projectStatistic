package com.example.projectstatistic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity  {
    BottomNavigationView nav;
    private EditText edFirst, edSecond, edThird, edFourth, edFifth;
    private Button showBtn;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        String userName = mAuth.getCurrentUser().getEmail().split("@")[0];
        setTitle("Welcome, " + userName);
        showBtn = findViewById(R.id.idShowBtn);
        edFirst = findViewById(R.id.idEdtNum1);
        edSecond = findViewById(R.id.idEdtNum2);
        edThird = findViewById(R.id.idEdtNum3);
        edFourth = findViewById(R.id.idEdtNum4);
        edFifth = findViewById(R.id.idEdtNum5);

        nav = findViewById(R.id.botnav);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home:
//                        Intent i_home = new Intent(MainActivity.this,MainActivity.class);
//                        startActivity(i_home);

                        break;
                    case R.id.detail:
                        Intent i_detail = new Intent(MainActivity.this,SummaryActivity.class);
                        startActivity(i_detail);


                        break;
                    case R.id.ranking:
          
                        Intent i_ranking = new Intent(MainActivity.this,RankingActivity.class);
                        startActivity(i_ranking);

                        break;
                    case R.id.setting:
                        Intent i_stg = new Intent(MainActivity.this,SettingActivity.class);
                        startActivity(i_stg);
                        break;

                    default:;
                }

                return true;
            }
        });



        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edFirst.getText().toString()) ||TextUtils.isEmpty(edSecond.getText().toString()) || TextUtils.isEmpty(edThird.getText().toString()) || TextUtils.isEmpty(edFourth.getText().toString()) || TextUtils.isEmpty(edFifth.getText().toString())) {
                    Toast.makeText(MainActivity.this,"Please fill the score", Toast.LENGTH_SHORT).show();
                } else {
                    int firstScore = Integer.parseInt(edFirst.getText().toString());
                    int scdScore = Integer.parseInt(edSecond.getText().toString());
                    int trdScore = Integer.parseInt(edThird.getText().toString());
                    int frthScore = Integer.parseInt(edFourth.getText().toString());
                    int fifthScore = Integer.parseInt(edFifth.getText().toString());

                    if (firstScore < 0 || firstScore > 100 || scdScore < 0 || scdScore > 100 || trdScore < 0 || trdScore > 100 || frthScore < 0 || frthScore > 100 || fifthScore < 0 || fifthScore > 100) {
                        Toast.makeText(MainActivity.this,"Score Must be 0 to 100", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent to_result = new Intent(MainActivity.this,MainResultActivity.class);
                        to_result.putExtra("firstScore", firstScore);
                        to_result.putExtra("scdScore",scdScore);
                        to_result.putExtra("trdScore", trdScore);
                        to_result.putExtra("frthScore", frthScore);
                        to_result.putExtra("fifthScore", fifthScore);
                        startActivity(to_result);
                        finish();
                    }



                }
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
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}