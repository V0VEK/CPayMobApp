package com.ibks.cpay.payment;

import androidx.appcompat.app.AppCompatActivity;
import com.ibks.cpay.R;
import com.ibks.cpay.StartActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PaymentFinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_finish);

        // TODO: Make a good GUI
    }

    public void FinishActivityOkClicked(View v) {
        ReturnToMainActivity();
    }

    private void ReturnToMainActivity() {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        finish();
    }

}
