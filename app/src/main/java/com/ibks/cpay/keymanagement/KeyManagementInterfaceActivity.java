package com.ibks.cpay.keymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ibks.cpay.R;

public class KeyManagementInterfaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_management_interface);
    }


    public void InitializeKeyButtonClicked(View v) {
        Intent intent = new Intent(this, InitializeKeyActivity.class);
        startActivity(intent);
        finish();
    }

    public void ChangeWorkKeysButtonClicked(View v) {
        // TODO: implement work keys change
        ShowToast("Work keys are changed");
    }

    public void DeleteAllKeysButtonClicked(View v) {
        // TODO: implement all keys deletion
        ShowToast("All keys are deleted");
    }

    private void ShowToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
