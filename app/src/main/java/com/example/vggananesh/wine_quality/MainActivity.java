package com.example.vggananesh.wine_quality;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    TextView tv,tv2;
    EditText et1,et2,et3,et4,et5,et6,et7,et8,et9,et10,et11;
    Button bt;
    private DatabaseReference mDatabase;
    String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11;
    String quality="8";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
        bt=findViewById(R.id.bt);
        tv2=findViewById(R.id.tv2);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);
        et4=findViewById(R.id.et4);
        et5=findViewById(R.id.et5);
        et6=findViewById(R.id.et6);
        et7=findViewById(R.id.et7);
        et8=findViewById(R.id.et8);
        et9=findViewById(R.id.et9);
        et10=findViewById(R.id.et10);
        et11=findViewById(R.id.et11);
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                s1= et1.getText().toString().trim();
                s2= et2.getText().toString().trim();
                s3= et3.getText().toString().trim();
                s4= et4.getText().toString().trim();
                s5= et5.getText().toString().trim();
                s6= et6.getText().toString().trim();
                s7= et7.getText().toString().trim();
                s8= et8.getText().toString().trim();
                s9= et9.getText().toString().trim();
                s10= et10.getText().toString().trim();
                s11= et11.getText().toString().trim();


                if(et1.getText().toString().trim().equals("")||et2.getText().toString().trim().equals("")||et3.getText().toString().trim().equals("")||et4.getText().toString().trim().equals("")||et5.getText().toString().trim().equals("")||et7.getText().toString().trim().equals("")||et6.getText().toString().trim().equals("")||et8.getText().toString().trim().equals("")||et9.getText().toString().trim().equals("")||et10.getText().toString().trim().equals("")||et11.getText().toString().trim().equals(""))
                {
                    Toast.makeText(MainActivity.this, "PLZ FILL ALL DETAILS", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //mDatabase = FirebaseDatabase.getInstance().getReference("users");
                    mDatabase.child("fa").setValue(s1);
                    mDatabase.child("va").setValue(s2);
                    mDatabase.child("ca").setValue(s3);
                    mDatabase.child("rs").setValue(s4);
                    mDatabase.child("ch").setValue(s5);
                    mDatabase.child("fsd").setValue(s6);
                    mDatabase.child("tsd").setValue(s7);
                    mDatabase.child("d").setValue(s8);
                    mDatabase.child("pH").setValue(s9);
                    mDatabase.child("sul").setValue(s10);
                    mDatabase.child("al").setValue(s11);
                   // Toast.makeText(MainActivity.this, "Thanks for using our app", Toast.LENGTH_SHORT).show();


                    DatabaseReference database=FirebaseDatabase.getInstance().getReference().child("users");
                    database.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                quality=dataSnapshot.child("qual").getValue().toString();
                                Toast.makeText(MainActivity.this, "your wine quality "+quality, Toast.LENGTH_SHORT).show();
                                tv2.setText("your wine quality is : "+quality);


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(MainActivity.this, "database error", Toast.LENGTH_SHORT).show();

                        }
                    });



                }

                
            }
        });

        Toast.makeText(this, quality, Toast.LENGTH_SHORT).show();
    }
}
