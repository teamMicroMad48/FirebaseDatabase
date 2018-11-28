package com.MY.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddEventActivity extends AppCompatActivity implements View.OnClickListener {

    private String UserId;
    private EditText name,startdate,enddate;
    private Button save;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_event);
        if (getIntent()!=null)
        {
            UserId=getIntent().getStringExtra("userid");
        }

        name=findViewById(R.id.eventName);
        startdate=findViewById(R.id.eventStartDate);
        enddate=findViewById(R.id.eventEndDate);
        save=findViewById(R.id.next);
        save.setOnClickListener(this);
        mDatabase=FirebaseDatabase.getInstance().getReference().child("Users").child(UserId);

    }


    @Override
    public void onClick(View v) {


        String eneventnme=name.getText().toString();
        String eventSartdate=startdate.getText().toString();
        String eventendDate=enddate.getText().toString();

        if (eneventnme.isEmpty()||eventSartdate.isEmpty()||eventendDate.isEmpty())

        {
            Toast.makeText(this, "Check Value", Toast.LENGTH_SHORT).show();
            return;
        }

        String keyId = mDatabase.push().getKey();
        Event event = new Event(eventendDate,keyId,eneventnme,eventSartdate);
        assert keyId != null;
        mDatabase.child(keyId).setValue(event);
        finish();

    }
}
