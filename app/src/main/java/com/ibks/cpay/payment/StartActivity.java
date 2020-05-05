package com.ibks.cpay.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ibks.cpay.R;
import com.ibks.cpay.authentication.LoginActivity;
import com.ibks.cpay.payment.PaymentActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // TODO: Make a good GUI

    }

    public void StartPaymentButtonClicked(View view) {
        StartPaymentActivity();
    }


    public void StartActivityExitClicked(View v) {

        ReturnToPreviousActivity();
    }

    private void ReturnToPreviousActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void StartPaymentActivity () {
        Intent intent = new Intent(this, PaymentActivity.class);
        startActivity(intent);
        finish();
    }
}
