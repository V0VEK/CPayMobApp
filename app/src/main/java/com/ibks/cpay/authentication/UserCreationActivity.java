package com.ibks.cpay.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ibks.cpay.R;

public class UserCreationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_creation);
    }

    public void UserCreateButtonClicked(View v) {
        // TODO: Send request to the backend and check response

        StartLoginActivity();
    }

    private void StartLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void UserCreationExitClicked(View v) {
        BackToLoginActivity();
    }

    private void BackToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
