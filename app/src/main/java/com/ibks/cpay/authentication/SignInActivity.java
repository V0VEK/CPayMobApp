package com.ibks.cpay.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ibks.cpay.R;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void SendConfirmationCodeClicked (View v) {
        // TODO: Send request with confirmation code and check response for starting user adding activity

        StartUserCreationActivity();
    }


    private void StartUserCreationActivity() {
        Intent intent = new Intent(this, UserCreationActivity.class);
        startActivity(intent);
        finish();
    }
}
