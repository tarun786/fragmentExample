package com.example.sqlitepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fragmentexample.R;
import com.example.sqlitepractice.HomeFragment.OnDbOpListner;

public class SqliteExample extends AppCompatActivity implements OnDbOpListner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_example);

        if(findViewById(R.id.fragment_container) != null)
        {
            if(savedInstanceState != null)
            {
                return;
            }

            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, homeFragment).commit();
        }
    }

    @Override
    public void dbOpPerform(int method) {

        switch (method)
        {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddContactFrag()).addToBackStack(null).commit();
                break;
        }
    }
}
