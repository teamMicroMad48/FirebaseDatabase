package com.MY.project1;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment implements EventAdapter.ItemActionListener{
    private Button addBtn, logoutBtn;
    private DatabaseReference rootRef;
    private DatabaseReference userRef;
    private DatabaseReference userIDRef;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private EventAdapter adapter;
    private List<Event>events = new ArrayList<>();

    public EventFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        rootRef = FirebaseDatabase.getInstance().getReference();
        userRef = rootRef.child("Users");
        userIDRef = userRef.child(auth.getCurrentUser().getUid());

        userIDRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                events.clear();


//                for(DataSnapshot data : dataSnapshot.getChildren()){
//
//                    Event event = data.getValue(Event.class);
//                    events.add(event);
//
//
//
//                }

//                adapter.updateList(events);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        addBtn = view.findViewById(R.id.addEvent);
        recyclerView = view.findViewById(R.id.eventList);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new EventAdapter(getActivity(), events, this);
        recyclerView.setAdapter(adapter);
        logoutBtn = view.findViewById(R.id.btnLogout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(auth != null){
                    auth.signOut();
                }
            }
        });



        setHasOptionsMenu(true);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String keyId = userIDRef.push().getKey();
//                Event event = new Event(keyId,"test","22/11/2018","name");
//                assert keyId != null;
//                userIDRef.child(keyId).setValue(event);

                Intent intent=new Intent(getActivity(),AddEventActivity.class);
                intent.putExtra("userid",auth.getCurrentUser().getUid());
                startActivity(intent);


            }
        });
        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemEdit(String eventID) {
        Event event = new Event(eventID, "Bangladesh Tour", "30/11/2018","mname");
        userIDRef.child(eventID).setValue(event);
    }

    @Override
    public void onItemDelete(String eventID) {
        userIDRef.child(eventID).removeValue();
    }
}
