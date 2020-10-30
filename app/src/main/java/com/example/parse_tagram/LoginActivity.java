package com.example.parse_tagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity
{
    public static final String TAG = "LoginActivity";
    EditText etUsername;
    EditText etPassword;
    Button btnLogin;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (ParseUser.getCurrentUser() != null)
        {
            goMainActivity();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.i(TAG, "onClick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                logInUser(username, password);

                // btnLogin.setVisibility(View.GONE);
                // btnLogin.setEnabled(username.length() > 5 && password.length() > 5);
            }
        });

        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser();

        btnSignup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.i(TAG, "onClick signup button");

                signUpUser();            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            if (bundle.getString("login") != null)
            {
                Toast.makeText(getApplicationContext(), "data: " + bundle.getString("login"), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void logInUser(final String username, String password)
    {
        Log.i(TAG, "Attempting to login user " + username);

        ParseUser.logInInBackground(username, password, new LogInCallback()
        {
            @Override
            public void done(ParseUser user, ParseException e)
            {
                if (e != null)
                {
                    Log.e(TAG, "Issue with login", e);
                    Toast.makeText(LoginActivity.this, "Invalid username/password", Toast.LENGTH_SHORT).show();
                    // ParseUser.logOut();
                    return;
                }
                // Navigate to the main activity if the user has signed in properly
                goMainActivity();
                Toast.makeText(LoginActivity.this, " Login Success!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goMainActivity()
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void signUpUser()
    {
        ParseUser user = new ParseUser();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        user.setUsername(username);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with Signup", e);
                    Toast.makeText(LoginActivity.this, "Failed to Signup", Toast.LENGTH_SHORT).show();
                    // ParseUser.logOut();
                    return;
                }

                goMainActivity();
                Toast.makeText(LoginActivity.this, "Sign up Success!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}