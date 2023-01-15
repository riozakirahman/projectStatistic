package com.example.projectstatistic;

import static java.util.Arrays.sort;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectstatistic.model.Result;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditSummaryActivity extends AppCompatActivity {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private TextView title;
    private EditText edFirst, edSecond, edThird, edFourth, edFifth;
    private Button updBtn;
    private String resID;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_summary);
        setTitle("Update Summary");
        title = findViewById(R.id.idTvTitle);
        updBtn = findViewById(R.id.idUpdBtn);
        edFirst = findViewById(R.id.idEdtNum1);
        edSecond = findViewById(R.id.idEdtNum2);
        edThird = findViewById(R.id.idEdtNum3);
        edFourth = findViewById(R.id.idEdtNum4);
        edFifth = findViewById(R.id.idEdtNum5);



        //get Result ID
        Intent getI = getIntent();
        resID = getI.getStringExtra("resID");

        //User ID
        String userID = mAuth.getCurrentUser().getUid();
        String userName = mAuth.getCurrentUser().getEmail().split("@")[0];

        //year
        int year = getI.getIntExtra("year", 0);
        String yearName = getI.getStringExtra("yearName");
        title.setText("Update Score in " + yearName);

        updBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edFirst.getText().toString()) ||TextUtils.isEmpty(edSecond.getText().toString()) || TextUtils.isEmpty(edThird.getText().toString()) || TextUtils.isEmpty(edFourth.getText().toString()) || TextUtils.isEmpty(edFifth.getText().toString())) {
                    Toast.makeText(EditSummaryActivity.this,"Please fill the score", Toast.LENGTH_SHORT).show();
                } else {
                    int firstScore = Integer.parseInt(edFirst.getText().toString());
                    int scdScore = Integer.parseInt(edSecond.getText().toString());
                    int trdScore = Integer.parseInt(edThird.getText().toString());
                    int frthScore = Integer.parseInt(edFourth.getText().toString());
                    int fifthScore = Integer.parseInt(edFifth.getText().toString());
                    int n = 5;
                    int array_mode[] = {firstScore, scdScore,trdScore,frthScore,fifthScore};
                    if (firstScore < 0 || firstScore > 100 || scdScore < 0 || scdScore > 100 || trdScore < 0 || trdScore > 100 || frthScore < 0 || frthScore > 100 || fifthScore < 0 || fifthScore > 100) {
                        Toast.makeText(EditSummaryActivity.this,"Score Must be 0 to 100", Toast.LENGTH_SHORT).show();
                    } else {
                        int mean = Mean(firstScore, scdScore, trdScore, frthScore, fifthScore);
                        int median = Median(firstScore, scdScore, trdScore, frthScore, fifthScore);
                        int modes = mode(array_mode,n);

                        Result result = new Result(userID,userName,mean,median,modes,year,firstScore,scdScore,trdScore,frthScore,fifthScore);

                        Map<String, Object> resValues = result.toMap();
                        Map<String, Object> childUpdates = new HashMap<>();
                        childUpdates.put("/Result/" + resID, resValues);

                        mDatabase.updateChildren(childUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(EditSummaryActivity.this,"Data Successfully Updated", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(EditSummaryActivity.this, SummaryActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(EditSummaryActivity.this,"Data failed to Update", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }


                }


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