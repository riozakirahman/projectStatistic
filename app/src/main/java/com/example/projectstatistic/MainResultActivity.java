package com.example.projectstatistic;

import static java.util.Arrays.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.projectstatistic.model.Result;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainResultActivity extends AppCompatActivity {
    private FloatingActionButton backBtn;
    private EditText edtMean, edtMedian, edtModus;
    private Button saveBtn;
    FirebaseAuth mAuth;

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private ArrayList<Result>  listResult  = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_result);
        setTitle("Result");

        edtMean = findViewById(R.id.idEdtMean);
        edtMedian = findViewById(R.id.idEdtMedian);
        edtModus = findViewById(R.id.idEdtModus);
        mAuth = FirebaseAuth.getInstance();


        //getData
        Intent getI = getIntent();
        int firstScore = getI.getIntExtra("firstScore", 0);
        int scdScore = getI.getIntExtra("scdScore", 0);
        int trdScore = getI.getIntExtra("trdScore", 0);
        int frthScore = getI.getIntExtra("frthScore", 0);
        int fifthScore = getI.getIntExtra("fifthScore", 0);
        String userID = mAuth.getCurrentUser().getUid();
        String userName = mAuth.getCurrentUser().getEmail().split("@")[0];


//        setTitle(userID);

        //setData
        int n = 5;
        int array_mode[] = {firstScore, scdScore,trdScore,frthScore,fifthScore};

        int mean = Mean(firstScore, scdScore, trdScore, frthScore, fifthScore);
        int median = Median(firstScore, scdScore, trdScore, frthScore, fifthScore);
        int modes = mode(array_mode,n);
        edtMean.setText(String.valueOf(mean));
        edtMedian.setText(String.valueOf(median));
        edtModus.setText(String.valueOf(modes));

        backBtn = findViewById(R.id.idBtnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainResultActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        mDatabase.child("Result").orderByChild("userID").equalTo(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot item : snapshot.getChildren()) {
                    Result result = new Result();
                    result = item.getValue(Result.class);
                    listResult.add(result);

                }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        saveBtn = findViewById(R.id.idBtnSave);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainResultActivity.this, saveBtn);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up, popupMenu.getMenu());

                popupMenu.show();


                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        Intent i = new Intent(MainResultActivity.this, MainActivity.class);
                        Boolean yState = true;
                        int year ;
                        switch (menuItem.getItemId()){
                            case R.id.menu_first:
                                    yState = true;
                                    year = 1;
                                    if (listResult.isEmpty()) {
                                        yState = true;
                                    }
                                    for(Result itemRes : listResult) {
                                        if(itemRes.getYear() == 1) {
                                            yState = false;
                                        }
                                    }
                                    if (yState) {
                                        mDatabase.child("Result").push().setValue(new Result(userID,userName,mean,median,modes,year,firstScore,scdScore,trdScore,frthScore,fifthScore)).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(MainResultActivity.this,"Data successfully added to 1st Year", Toast.LENGTH_SHORT).show();
                                                startActivity(i);
                                                finish();
                                            }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(MainResultActivity.this,"Failed to add data to 1st Year", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                    } else {
                                        Toast.makeText(MainResultActivity.this,"You already have data in the 1st Year", Toast.LENGTH_SHORT).show();

                                    }

                                return true;
                            case R.id.menu_scd:
                                    yState = true;
                                    year = 2;
                                    if (listResult.isEmpty()) {
                                        yState = true;
                                    }
                                    for(Result itemRes : listResult) {
                                        if(itemRes.getYear() == 2) {
                                            yState = false;
                                        }
                                    }
                                    if (yState) {
                                        mDatabase.child("Result").push().setValue(new Result(userID,userName,mean,median,modes,year,firstScore,scdScore,trdScore,frthScore,fifthScore)).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(MainResultActivity.this,"Data successfully added to 2nd Year", Toast.LENGTH_SHORT).show();
                                                startActivity(i);
                                                finish();
                                            }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(MainResultActivity.this,"Failed to add data to 2nd Year", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                    } else {
                                        Toast.makeText(MainResultActivity.this,"You already have data in the 2nd Year", Toast.LENGTH_SHORT).show();

                                    }

                                return true;
                            case R.id.menu_trd:
                               yState = true;
                                    year = 3;
                                    if (listResult.isEmpty()) {
                                        yState = true;
                                    }
                                    for(Result itemRes : listResult) {
                                        if(itemRes.getYear() == 3) {
                                            yState = false;
                                        }
                                    }
                                    if (yState) {
                                        mDatabase.child("Result").push().setValue(new Result(userID,userName,mean,median,modes,year,firstScore,scdScore,trdScore,frthScore,fifthScore)).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(MainResultActivity.this,"Data successfully added to 3rd Year", Toast.LENGTH_SHORT).show();
                                                startActivity(i);
                                                finish();
                                            }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(MainResultActivity.this,"Failed to add data to 3rd Year", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                    } else {
                                        Toast.makeText(MainResultActivity.this,"You already have data in the 3rd Year", Toast.LENGTH_SHORT).show();

                                    }

                                return true;
                            case R.id.menu_frth:
                                yState = true;
                                year = 4;
                                if (listResult.isEmpty()) {
                                    yState = true;
                                }
                                for(Result itemRes : listResult) {
                                    if(itemRes.getYear() == 4) {
                                        yState = false;
                                    }
                                }
                                if (yState) {
                                    mDatabase.child("Result").push().setValue(new Result(userID,userName,mean,median,modes,year,firstScore,scdScore,trdScore,frthScore,fifthScore)).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(MainResultActivity.this,"Data successfully added to 4th Year", Toast.LENGTH_SHORT).show();
                                            startActivity(i);
                                            finish();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(MainResultActivity.this,"Failed to add data to 4th Year", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    Toast.makeText(MainResultActivity.this,"You already have data in the 4th Year", Toast.LENGTH_SHORT).show();

                                }
                                return true;

                        }
                        return false;
                    }
                });
            }
        });


    }


    public int Mean(int one, int two, int three, int four, int five) {
        int mean = (one + two + three + four + five) / 5;
        return mean;
    }

    public int Median(int one, int two, int three, int four, int five) {

        int array[] = new int[]{
                one, two, three, four, five
        };
        sort(array);
        int median = array[2];
        return median;
    }

    public int mode(int a[], int n) {
        int maxValue = 0, maxCount = 0, i, j;

        for (i = 0; i < n; ++i) {
            int count = 0;
            for (j = 0; j < n; ++j) {
                if (a[j] == a[i])
                    ++count;
            }

            if (count > maxCount) {
                maxCount = count;
                maxValue = a[i];
            }
        }
        return maxValue;

    }
}

