package com.diki.myprofile.main;
// 17 MEI
// 10116352
// DIKI SUPRIADI
// IF-8
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this,ActivityLogin.class);
        startActivity(intent);
        finish();
    }
}
