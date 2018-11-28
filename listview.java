package com.example.andro.firebasetest;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listview extends ListActivity {
Button re,back,delete;
String name;
    public static ArrayList list=new ArrayList<String>();
    DatabaseReference f;
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        name=(String) list.get(position);
        delete.setEnabled(true);
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        read();
        setListAdapter(new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,list));
        setContentView(R.layout.activity_listview);
        re=findViewById(R.id.research);
        back=findViewById(R.id.d);
        delete=findViewById(R.id.delete);
        delete.setEnabled(false);
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                read();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDelete();
                delete.setEnabled(false);
              }
        });

    }
    void setDelete() {
        DatabaseReference a = FirebaseDatabase.getInstance().getReference("test1").child("user");
        final Query query = a.orderByChild("name").equalTo(name);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.getChildren().iterator().next().getRef().removeValue();
                    read();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
        public void read(){

            f= FirebaseDatabase.getInstance().getReference("test1").child("user");
            f.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    list.clear();
                    for (DataSnapshot g : dataSnapshot.getChildren()) {
                        v o=g.getValue(v.class);
                        list.add(o.getName());
                    }
                    setListAdapter(new ArrayAdapter(listview.this,android.R.layout.simple_expandable_list_item_1,list));
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
}
