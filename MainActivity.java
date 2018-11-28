package com.example.andro.firebasetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
Button b,r;
EditText name,age;
DatabaseReference f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=findViewById(R.id.b);
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        r=findViewById(R.id.r);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f= FirebaseDatabase.getInstance().getReference("test1").child("user");
                f.push().setValue(new v(name.getText().toString(),age.getText().toString()));
                Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(MainActivity.this,listview.class));
            }
        });

    }

}
class v{
    private String Name,Age;

    public v(String name, String age) {
        Name = name;
        Age = age;
    }
public v(){}
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }
}