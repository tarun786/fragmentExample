package com.example.fragmentexample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main2ActivitySharedPreference extends AppCompatActivity {

    private SharedPreferenceConfig sharedPreferenceConfig;
    private EditText UserName,UserPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_shared_preference);
        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        UserName = findViewById(R.id.user_name);
        UserPassword = findViewById(R.id.user_password);

        if(sharedPreferenceConfig.readLoginStatus())
        {
            startActivity(new Intent(this,SuccessActivity.class));
            finish();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void LoginUser(View view) {

        String userName = UserName.getText().toString();
        String userPass = UserPassword.getText().toString();
        boolean loginStatus = validUser(userName,userPass);
        if(loginStatus)
        {
            startActivity(new Intent(this,SuccessActivity.class));
            sharedPreferenceConfig.writeLoginStatus(true);
            finish();
        }
        else
        {
            showToast();
            //Toast.makeText(this,"Login Failed... Please Try Again..",Toast.LENGTH_SHORT).show();
            UserName.setText("");
            UserPassword.setText("");
        }
    }

    private boolean validUser(String userName,String userPass)
    {
        boolean validUserStaus = false;
        if(userName.equals(getResources().getString(R.string.user_name)) && userPass.equals(getResources().getString(R.string.user_password)))
        {
            validUserStaus = true;
        }
        return validUserStaus;
    }

    private void showToast()
    {
        String info = "Wrong credentails..";
        Toast toast = Toast.makeText(this, Html.fromHtml("<font color='#e3f2fd' ><b>" + info + "</b></font>"), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM ,0, 0);
        //View view =toast.getView();
        //view.setBackgroundColor(Color.GREEN); //any color your want
        toast.show();

    }



}
