package com.example.projectstatistic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.projectstatistic.model.PersonRank;
import com.example.projectstatistic.model.Result;
import com.example.projectstatistic.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class RankingActivity extends AppCompatActivity {
    private ArrayList<Result> listResult = new ArrayList<>();

    private ArrayList<User> listUser = new ArrayList<User>();

    private String userName;
    private RecyclerView rv;
    private BottomNavigationView nav;
    private String itemID;
    private AdapterRank adapterRank;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    private Spinner ySpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        setTitle("Ranking");

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

        ySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                itemID = adapterView.getItemAtPosition(position).toString();
                ArrayList<PersonRank> listRank = new ArrayList<>();

                if (itemID.equals("Select Year")){
                    rv.setAdapter(null);
                } else if (itemID.equals("1st Year")) {

                    for (Result item: listResult) {
                        if (item.getYear() == 1) {

                            PersonRank rank = new PersonRank(item.getUserName(), item.getMean());
                            listRank.add(rank);


                        }
                    }


                    Collections.sort(listRank, Collections.reverseOrder());
                    adapterRank = new AdapterRank(listRank, RankingActivity.this);
                    rv.setAdapter(null);
                    rv.setAdapter(adapterRank);

                } else if (itemID.equals("2nd Year")) {

                    for (Result item: listResult) {
                        if (item.getYear() == 2) {
                            PersonRank rank = new PersonRank(item.getUserName(), item.getMean());
                            listRank.add(rank);


                        }
                    }
                    Collections.sort(listRank, Collections.reverseOrder());
                    adapterRank = new AdapterRank(listRank, RankingActivity.this);
                    rv.setAdapter(null);
                    rv.setAdapter(adapterRank);

                } else if (itemID.equals("3rd Year")) {
                    for (Result item: listResult) {
                        if (item.getYear() == 3) {
                            PersonRank rank = new PersonRank(item.getUserName(), item.getMean());
                            listRank.add(rank);


                        }
                    }
                    Collections.sort(listRank, Collections.reverseOrder());
                    adapterRank = new AdapterRank(listRank, RankingActivity.this);
                    rv.setAdapter(null);
                    rv.setAdapter(adapterRank);
                } else if (itemID.equals("4th Year")) {
                    for (Result item: listResult) {
                        if (item.getYear() == 4) {
                            PersonRank rank = new PersonRank(item.getUserName(), item.getMean());
                            listRank.add(rank);


                        }
                    }
                    Collections.sort(listRank, Collections.reverseOrder());
                    adapterRank = new AdapterRank(listRank, RankingActivity.this);
                    rv.setAdapter(null);
                    rv.setAdapter(adapterRank);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        rv = findViewById(R.id.idRV);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayout);
        rv.setItemAnimator(new DefaultItemAnimator());

        mDatabase.child("Result").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
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
        nav = findViewById(R.id.botnav);
        nav.getMenu().findItem(R.id.ranking).setChecked(true);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home:

                        Intent i_home = new Intent(RankingActivity.this,MainActivity.class);
                        startActivity(i_home);
                        break;
                    case R.id.detail:

                        Intent i_detail = new Intent(RankingActivity.this,SummaryActivity.class);
                        startActivity(i_detail);

                        break;
                    case R.id.ranking:
//                        Intent i_ranking = new Intent(RankingActivity.this,RankingActivity.class);
//                        startActivity(i_ranking);

                        break;
                    case R.id.setting:
                        Intent i_stg = new Intent(RankingActivity.this,SettingActivity.class);
                        startActivity(i_stg);
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
                startActivity(new Intent(RankingActivity.this,LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}