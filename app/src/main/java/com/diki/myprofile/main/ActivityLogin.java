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

public class ActivityLogin extends AppCompatActivity {


    private EditText mUsername,mUserpasswd;
    private Button mLogin;
    private TextView mRegister;
    private String Name,Password;
    private SharedPreferences mSharedPreferences;
    public static final String PREFERENCE= "preference";
    public static final String PREF_NAME = "name";
    public static final String PREF_PASSWD = "passwd";
    public static final String PREF_SKIP_LOGIN = "skip_login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        if(mSharedPreferences.contains(PREF_SKIP_LOGIN)){
            Intent intent = new Intent(ActivityLogin.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            mUsername = (EditText)findViewById(R.id.usernameLogin);
            mUserpasswd = (EditText)findViewById(R.id.passwordLogin);
            mLogin = (Button)findViewById(R.id.btnLogin);
            mRegister = (TextView)findViewById(R.id.signup);
            mLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(validUserData()){
                        if(mSharedPreferences.contains(PREF_NAME) && mSharedPreferences.contains(PREF_PASSWD)){
                            SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                            mEditor.putString(PREF_SKIP_LOGIN,"skip");
                            mEditor.apply();
                            Intent intent = new Intent(ActivityLogin.this,HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(),"Username Not Found, Please SignUp", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"Unable to Login Please Enter Valid Data !!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            mRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ActivityLogin.this,ActivitySignUp.class);
                    startActivity(intent);
                }
            });
        }
    }

    private boolean validUserData() {
        Name = mUsername.getText().toString().trim();
        Password = mUserpasswd.getText().toString().trim();
        return !(Name.isEmpty() || Password.isEmpty());
    }
}
