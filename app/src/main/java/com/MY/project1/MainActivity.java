package com.MY.project1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements LoginFragment.UserAuthenticationListener{

    private FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        Fragment fragment = null;
        if(user != null){
            fragment = new EventFragment();
        }else{
            fragment = new LoginFragment();
        }
        FragmentTransaction ft = manager.beginTransaction();

        ft.add(R.id.fragmentContainer, fragment);
        ft.commit();
    }

    @Override
    public void onAuthComplete() {
        FragmentTransaction ft = manager.beginTransaction();
        EventFragment eventFragment = new EventFragment();
        ft.replace(R.id.fragmentContainer, eventFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}
