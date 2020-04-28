package com.ibks.cpay.authentication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ibks.cpay.HexConverter;
import com.ibks.cpay.R;
import com.ibks.cpay.keymanagement.KeyManagementLogInActivity;
import com.ibks.cpay.payment.StartActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    private String login;
    private String password;
    private String passwordHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    // TODO: difference between key management activity and transactions processing

    // TODO: Check password and role
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void LogInClicked(View v) {
        EditText loginET = findViewById(R.id.appEntryLoginField);
        EditText passwordET = findViewById(R.id.appEntryPasswordField);

        login = loginET.getText().toString();
        password = passwordET.getText().toString();

        // TODO: delete this
        ShowToast(login + ":" + password);
        try {
            // Calculate SHA-256 of password
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte [] hash = messageDigest.digest(password.getBytes());
            HexConverter hc = new HexConverter();
            passwordHash = hc.FromByteToHex(hash);

            // TODO: Delete this
            //ShowToast("Hex: " + passwordHash);

            // TODO: Check values in login and password fields
            SendLoginRequest();

        } catch (NoSuchAlgorithmException exception) {
            // TODO: Handle it
            ShowToast("No SHA-256");
        }
    }

    public void KeyManagementClicked(View v) {
        StartKeyManagementLogInActivity();
    }

    private void SendLoginRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getString(R.string.SERVER) + getString(R.string.LOGIN_REQ);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(getString(R.string.APP_USER_LOGIN), login);
            jsonObject.put(getString(R.string.APP_USER_PASSWORD_HASH), passwordHash);
        } catch (JSONException e) {
            // TODO: handle it
            //e.printStackTrace();
        }

        ShowToast(jsonObject.toString());

        ShowToast("Request: " + url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            HandleResponse(response.getString(getString(R.string.SESSION_TOKEN)), response.getString(getString(R.string.ROLE_ID)));
                        } catch (JSONException e) {
                            // TODO: Handle it
                            //e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle it
                        ShowToast(error.toString());
                    }
                });
        queue.add(jsonObjectRequest);
    }

    private void StartBasicActivity() {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        finish();
    }

    private void StartKeyManagementLogInActivity() {
        Intent intent = new Intent(this, KeyManagementLogInActivity.class);
        startActivity(intent);
        finish();
    }

    private void HandleResponse(String token, String role) {
        // TODO: Check role and save token. Maybe need to delete role id
        ShowToast(role + ":" + token);

        StartBasicActivity();
    }

    public void SignInButtonClicked(View v) {
        StartSignInActivity();
    }

    private void StartSignInActivity() {

    }

    private void ShowToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
