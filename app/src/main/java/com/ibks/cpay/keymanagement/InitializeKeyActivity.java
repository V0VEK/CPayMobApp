package com.ibks.cpay.keymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ibks.cpay.R;

public class InitializeKeyActivity extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    Button button1;
    Button button2;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initialize_key);

        editText1 = findViewById(R.id.CustodianComponent1);
        editText2 = findViewById(R.id.CustodianComponent2);

        button1 = findViewById(R.id.ComponentInputButton1);
        button2 = findViewById(R.id.ComponentInputButton2);

        textView2 = findViewById(R.id.CustodianComponent2Text);
    }

    public void ComponentInputButton1Clicked(View v) {
        ShowToast("First component was inputted");
        // TODO: Remember key component
        editText1.setFocusable(false);
        editText1.setEnabled(false);
        button1.setVisibility(View.INVISIBLE);

        editText2.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.VISIBLE);
    }

    public void ComponentInputButton2Clicked(View v) {
        // TODO: Remember key component
        // TODO: Send request to backend service
        // TODO: Implement secure storage for key components

        ReturnToKeyManagement();
    }

    // TODO: Maybe some activity doesn't need to be finished because after some actions we will comeback to it
    private void ReturnToKeyManagement() {
        Intent intent = new Intent(this, KeyManagementInterfaceActivity.class);
        startActivity(intent);
        finish();
    }


    public void KeyInitializeExitClicked(View v) {

        ReturnToKeyManagement();
    }

    private void ShowToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
