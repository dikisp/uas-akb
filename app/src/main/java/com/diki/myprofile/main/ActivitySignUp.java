package com.diki.myprofile.main;
//  3 Agustus
// 10116352
// DIKI SUPRIADI
// IF-8

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.diki.myprofile.Preferences;
import com.diki.myprofile.R;

public class ActivitySignUp extends AppCompatActivity {
    private EditText mName,mPasswd, mEmail;
    private TextView mRegister;
    private Button mRegisterBtn;
    private String Name,Email,Password;
    public static final String PREFERENCE= "preference";
    public static final String PREF_NAME = "name";
    public static final String PREF_EMAIL = "email";
    public static final String PREF_PASSWD = "passwd";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mName = (EditText)findViewById(R.id.usernameSignup);
        mPasswd = (EditText)findViewById(R.id.passwordLogin);
        mEmail = (EditText)findViewById(R.id.emailSignup);
        mRegisterBtn = (Button)findViewById(R.id.btnSignup);
        mRegister = (TextView) findViewById(R.id.login) ;
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validUserData()){
                    SharedPreferences mSharedPreference = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
                    SharedPreferences.Editor mEditor = mSharedPreference.edit();
                    mEditor.putString(PREF_NAME,Name);
                    mEditor.putString(PREF_PASSWD,Password);
                    mEditor.putString(PREF_EMAIL,Email);
                    mEditor.apply();
                    finish();
                }

            }

        });
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivitySignUp.this,ActivityLogin.class);
                startActivity(intent);
            }
        });
    }

    private boolean validUserData() {
        Name = mName.getText().toString().trim();
        Password = mPasswd.getText().toString().trim();
        Email = mEmail.getText().toString().trim();
        return !(Name.isEmpty() || Password.isEmpty() || Email.isEmpty());
    }
}
