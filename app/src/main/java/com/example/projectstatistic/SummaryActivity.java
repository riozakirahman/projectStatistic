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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectstatistic.model.Result;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    BottomNavigationView nav;
    private ArrayList<Result> listResult = new ArrayList<>();
    TextView title;
    EditText mean, median, modus, score;
    FloatingActionButton backBtn, editBtn;
    Spinner ySpinner;
    String itemID;
    Button showDB;
    String resID;
    private int year;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        setTitle("Summary Detail");
        mean = findViewById(R.id.idEdtMean);
        median = findViewById(R.id.idEdtMedian);
        modus = findViewById(R.id.idEdtModus);
        score = findViewById(R.id.idEdtScore);


        mAuth = FirebaseAuth.getInstance();
        String userID = mAuth.getCurrentUser().getUid();


        ySpinner = findViewById(R.id.idSpinY);


        // Spinner Drop down elements
        ArrayList<String> categories = new ArrayList<String>();
        categories.add("Select Year");
        categories.add("1st Year");
        categories.add("2nd Year");
        categories.add("3rd Year");
        categories.add("4th Year");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        ySpinner.setAdapter(dataAdapter);


        mDatabase.child("Result").orderByChild("userID").equalTo(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()) {
                    Result result = new Result();
                    result = item.getValue(Result.class);
                    result.setResultID(item.getKey());
                    listResult.add(result);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        editBtn = findViewById(R.id.idBtnEdit);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mean.getText().toString()) || TextUtils.isEmpty(median.getText().toString()) || TextUtils.isEmpty(modus.getText().toString()) || TextUtils.isEmpty(score.getText().toString())) {
                    Toast.makeText(SummaryActivity.this, "Select Year", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(SummaryActivity.this,EditSummaryActivity.class);
                    i.putExtra("resID", resID);
                    i.putExtra("year", year);
                    i.putExtra("yearName", itemID);
                    startActivity(i);
                    finish();
                }

            }
        });

        ySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                // On selecting a spinner item

                itemID = adapterView.getItemAtPosition(position).toString();
                if (itemID.equals("Select Year")) {
                    mean.setText(null);
                    median.setText(null);
                    modus.setText(null);
                    score.setText(null);
                }

                else if(itemID.equals("1st Year")){
                    mean.setText(null);
                    median.setText(null);
                    modus.setText(null);
                    score.setText(null);
                    for (Result item: listResult) {
                        if (item.getYear() == 1) {

                            mean.setText(String.valueOf(item.getMean()));
                            median.setText(String.valueOf(item.getMedian()));
                            modus.setText(String.valueOf(item.getModus()));
                            String score_data = item.getFirstScr() + ", " + item.getSecondScr() + ", " + item.getThirdScr() + ", " + item.getFrthScr() + ", " + item.getFifthScr();
                            score.setText(score_data);
                            resID = item.getResultID().toString();
                            year = 1;
                        }
                    }
                }
                else if(itemID.equals("2nd Year")){
                    mean.setText(null);
                    median.setText(null);
                    modus.setText(null);
                    score.setText(null);
                    for (Result item: listResult) {
                        if (item.getYear() == 2) {
                            mean.setText(String.valueOf(item.getMean()));
                            median.setText(String.valueOf(item.getMedian()));
                            modus.setText(String.valueOf(item.getModus()));
                            String score_data = "" + item.getFirstScr() + ", " + item.getSecondScr() + ", " + item.getThirdScr() + ", " + item.getFrthScr() + ", " + item.getFifthScr();
                            score.setText(score_data);
                            resID = item.getResultID().toString();
                            year = 2;
                        }
                    }

                } else if(itemID.equals("3rd Year")){
                    mean.setText(null);
                    median.setText(null);
                    modus.setText(null);
                    score.setText(null);
                    for (Result item: listResult) {
                        if (item.getYear() == 3) {
                            mean.setText(String.valueOf(item.getMean()));
                            median.setText(String.valueOf(item.getMedian()));
                            modus.setText(String.valueOf(item.getModus()));
                            String score_data = String.valueOf(item.getFirstScr()) + ", " + item.getSecondScr() + ", " + item.getThirdScr() + ", " + item.getFrthScr() + ", " + item.getFifthScr();
                            score.setText(score_data);
                            resID = item.getResultID().toString();
                            year = 3;
                        }
                    }
                } else if(itemID.equals("4th Year")){
                    mean.setText(null);
                    median.setText(null);
                    modus.setText(null);
                    score.setText(null);
                    for (Result item: listResult) {
                        if (item.getYear() == 4) {
                            mean.setText(String.valueOf(item.getMean()));
                            median.setText(String.valueOf(item.getMedian()));
                            modus.setText(String.valueOf(item.getModus()));
                            String score_data = String.valueOf(item.getFirstScr()) + ", " + item.getSecondScr() + ", " + item.getThirdScr() + ", " + item.getFrthScr() + ", " + item.getFifthScr();
                            score.setText(score_data);
                            resID = item.getResultID().toString();
                            year = 4;
                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        nav = findViewById(R.id.botnav);
        nav.getMenu().findItem(R.id.detail).setChecked(true);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home:

                        Intent i_home = new Intent(SummaryActivity.this,MainActivity.class);
                        startActivity(i_home);
                        break;
                    case R.id.detail:

//                        Intent i_detail = new Intent(SummaryActivity.this,SummaryActivity.class);
//                        startActivity(i_detail);

                        break;
                    case R.id.ranking:
                        Intent i_ranking = new Intent(SummaryActivity.this,RankingActivity.class);
                        startActivity(i_ranking);

                        break;
                    case R.id.setting:
                        Intent i_stg = new Intent(SummaryActivity.this,SettingActivity.class);
                        startActivity(i_stg);
                        break;

                    default:;
                }

                return true;
            }
        });






        backBtn = findViewById(R.id.idBtnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SummaryActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

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
                startActivity(new Intent(SummaryActivity.this,LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
