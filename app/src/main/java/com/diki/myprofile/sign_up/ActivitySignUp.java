package com.diki.myprofile.sign_up;
//  3 Agustus
// 10116352
// DIKI SUPRIADI
// IF-8

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.diki.myprofile.Preferences;
import com.diki.myprofile.R;

public class ActivitySignUp extends AppCompatActivity {
//    private EditText mName,mPasswd, mEmail;
//    private TextView mRegister;
//    private Button mRegisterBtn;
//    private String Name,Email,Password;
//    public static final String PREFERENCE= "preference";
//    public static final String PREF_NAME = "name";
//    public static final String PREF_EMAIL = "email";
//    public static final String PREF_PASSWD = "passwd";
//    @Override
//    protected void onCreate(final Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up);
//        mName = (EditText)findViewById(R.id.usernameSignup);
//        mPasswd = (EditText)findViewById(R.id.passwordLogin);
//        mEmail = (EditText)findViewById(R.id.emailSignup);
//        mRegisterBtn = (Button)findViewById(R.id.btnSignup);
//        mRegister = (TextView) findViewById(R.id.login) ;
//        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(validUserData()){
//                    SharedPreferences mSharedPreference = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
//                    SharedPreferences.Editor mEditor = mSharedPreference.edit();
//                    mEditor.putString(PREF_NAME,Name);
//                    mEditor.putString(PREF_PASSWD,Password);
//                    mEditor.putString(PREF_EMAIL,Email);
//                    mEditor.apply();
//                    finish();
//                }
//
//            }
//
//        });
//        mRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ActivitySignUp.this, ActivityLogin.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//    private boolean validUserData() {
//        Name = mName.getText().toString().trim();
//        Password = mPasswd.getText().toString().trim();
//        Email = mEmail.getText().toString().trim();
//        return !(Name.isEmpty() || Password.isEmpty() || Email.isEmpty());
//    }


    private EditText mViewUser, mViewPassword, mViewRepassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        /* Menginisialisasi variable dengan Form User, Form Password, dan Form Repassword
        dari Layout RegisterActivity */
        mViewUser =findViewById(R.id.emailSignup);
        mViewPassword =findViewById(R.id.usernameSignup);
        mViewRepassword =findViewById(R.id.passwordSignup);

        /* Menjalankan Method razia() jika merasakan tombol SignUp di keyboard disentuh */
        mViewRepassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    razia();
                    return true;
                }
                return false;
            }
        });
        /* Menjalankan Method razia() jika merasakan tombol SignUp disentuh */
        findViewById(R.id.btnSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                razia();
            }
        });
    }

    /** Men-check inputan User dan Password dan Memberikan akses ke MainActivity */
    private void razia(){
        /* Mereset semua Error dan fokus menjadi default */
        mViewUser.setError(null);
        mViewPassword.setError(null);
        mViewRepassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        /* Mengambil text dari Form User, Password, Repassword dengan variable baru bertipe String*/
        String repassword = mViewRepassword.getText().toString();
        String user = mViewUser.getText().toString();
        String password = mViewPassword.getText().toString();

        /* Jika form user kosong atau MEMENUHI kriteria di Method cekUser() maka, Set error di Form-
         * User dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
        if (TextUtils.isEmpty(user)){
            mViewUser.setError("This field is required");
            fokus = mViewUser;
            cancel = true;
        }else if(cekUser(user)){
            mViewUser.setError("This Username is already exist");
            fokus = mViewUser;
            cancel = true;
        }

        /* Jika form password kosong dan MEMENUHI kriteria di Method cekPassword maka,
         * Reaksinya sama dengan percabangan User di atas. Bedanya untuk Password dan Repassword*/
        if (TextUtils.isEmpty(password)){
            mViewPassword.setError("This field is required");
            fokus = mViewPassword;
            cancel = true;
        }else if (!cekPassword(password,repassword)){
            mViewRepassword.setError("This password is incorrect");
            fokus = mViewRepassword;
            cancel = true;
        }

        /** Jika cancel true, variable fokus mendapatkan fokus. Jika false, maka
         *  Kembali ke LoginActivity dan Set User dan Password untuk data yang terdaftar */
        if (cancel){
            fokus.requestFocus();
        }else{
            Preferences.setRegisteredUser(getBaseContext(),user);
            Preferences.setRegisteredPass(getBaseContext(),password);
            finish();
        }
    }

    /** True jika parameter password sama dengan parameter repassword */
    private boolean cekPassword(String password, String repassword){
        return password.equals(repassword);
    }

    /** True jika parameter user sama dengan data user yang terdaftar dari Preferences */
    private boolean cekUser(String user){
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }
}
