package com.example.fragmentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SuccessActivity extends AppCompatActivity {

    private SharedPreferenceConfig sharedPreferenceConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());
    }

    public void userLogout(View view) {

        sharedPreferenceConfig.writeLoginStatus(false);
        startActivity(new Intent(this, Main2ActivitySharedPreference.class));
        finish();
    }
}
