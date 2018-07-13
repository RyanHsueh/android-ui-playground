package com.example.ryanhsueh.uiplayground.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.ryanhsueh.uiplayground.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {


//    private static final String EMAIL_PATTERN =
//            "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\\\.[a-zA-Z0-9-]+)*$";

    private static final String EMAIL_PATTERN =
            "^[A-Za-z0-9+_.-]+@(.+)$";

    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        TextInputLayout tlUsername = findViewById(R.id.tl_username);
        TextInputLayout tlPassword = findViewById(R.id.tl_password);

        String username = tlUsername.getEditText().getText().toString();
        String password = tlPassword.getEditText().getText().toString();

        if (!validateUsername(username)) {
            tlUsername.setErrorEnabled(true);
            tlUsername.setError("Please type correct email format");
        } else if (!validatePassword(password)) {
            tlPassword.setErrorEnabled(true);
            tlPassword.setError("Password length must be more than 6");
        } else {
            tlUsername.setErrorEnabled(false);
            tlPassword.setErrorEnabled(false);
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
        }


    }

    private boolean validateUsername(String username) {
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    private boolean validatePassword(String password) {
        return password.length() > 6 ;
    }
}
