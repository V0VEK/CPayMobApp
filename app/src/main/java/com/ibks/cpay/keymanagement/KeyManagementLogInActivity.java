package com.ibks.cpay.keymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ibks.cpay.R;
import com.ibks.cpay.authentication.LoginActivity;

public class KeyManagementLogInActivity extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    Button button2;
    Button button1;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_management_log_in);

        editText1 = findViewById(R.id.CustodianPassword1);
        editText2 = findViewById(R.id.CustodianPassword2);
        button2 = findViewById(R.id.CustodianInputButton2);
        button1 = findViewById(R.id.CustodianInputButton1);
        textView2 = findViewById(R.id.CustodianPassword2Text);

    }

    public void InputClicked1 (View v) {
        // TODO: Read password in memory
        ShowToast("First password inputted");


        editText1.setEnabled(false);
        editText1.setFocusable(false);
        button1.setVisibility(View.INVISIBLE);

        textView2.setVisibility(View.VISIBLE);
        editText2.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
    }

    public void InputClicked2 (View v) {
        // TODO: Read password in memory
        ShowToast("Second password inputted");

        // TODO: Send auth request
        StartKeyManagementInterfaceActivity();
    }

    private void StartKeyManagementInterfaceActivity() {
        Intent intent = new Intent(this, KeyManagementInterfaceActivity.class);
        startActivity(intent);
        finish();
    }

    public void KeyManagementLogInExitClicked(View v) {
        ReturnToPreviousActivity();
    }

    private void ReturnToPreviousActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void ShowToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
