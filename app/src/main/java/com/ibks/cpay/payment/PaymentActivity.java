package com.ibks.cpay.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ibks.cpay.HexConverter;
import com.ibks.cpay.R;
import com.pro100svitlo.creditCardNfcReader.CardNfcAsyncTask;
import com.pro100svitlo.creditCardNfcReader.utils.CardNfcUtils;

public class PaymentActivity extends AppCompatActivity implements CardNfcAsyncTask.CardNfcInterface {

    private NfcAdapter mNfcAdapter;
    private CardNfcUtils mCardNfcUtils;
    private boolean mIntentFromCreate;
    private CardNfcAsyncTask mCardNfcAsyncTask;

    // TODO Make a good GUI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null){
            //Toast.makeText(getApplicationContext(), "No NFC :(", Toast.LENGTH_SHORT).show();
            //do something if there are no nfc module on device
        } else {
            //do something if there are nfc module on device

            //Toast.makeText(getApplicationContext(), "NFC found! :)", Toast.LENGTH_SHORT).show();
            mCardNfcUtils = new CardNfcUtils(this);
            //next few lines here needed in case you will scan credit card when app is closed
            mIntentFromCreate = true;
            onNewIntent(getIntent());
        }

        SendTestRequest();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mIntentFromCreate = false;
        if (mNfcAdapter != null && !mNfcAdapter.isEnabled()){
            //show some turn on nfc dialog here. take a look in the sample ;-)
        } else if (mNfcAdapter != null){
            //Toast.makeText(getApplicationContext(), "NFC found 2! :)", Toast.LENGTH_SHORT).show();
            mCardNfcUtils.enableDispatch();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mNfcAdapter != null) {
            mCardNfcUtils.disableDispatch();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (mNfcAdapter != null && mNfcAdapter.isEnabled()) {
            //this - interface for callbacks
            //intent = intent :)
            //mIntentFromCreate - boolean flag, for understanding if onNewIntent() was called from onCreate or not
            mCardNfcAsyncTask = new CardNfcAsyncTask.Builder(this, intent, mIntentFromCreate)
                    .build();
        }
    }
    @Override
    public void startNfcReadCard() {
        //notify user that scanning start
        String card = mCardNfcAsyncTask.getCardNumber();
        Toast.makeText(getApplicationContext(), "Start " + card, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cardIsReadyToRead() {
        String card = mCardNfcAsyncTask.getCardNumber();
        String expiredDate = mCardNfcAsyncTask.getCardExpireDate();
        String cardType = mCardNfcAsyncTask.getCardType();
        byte [] track2 = mCardNfcAsyncTask.getTrack2Data();
        HexConverter hc = new HexConverter();
        // TODO: Delete this
        Toast.makeText(getApplicationContext(), "Card is ready to read! " + hc.encodeHexString(track2), Toast.LENGTH_LONG).show();
    }

    @Override
    public void doNotMoveCardSoFast() {
        //notify user do not move the card
        ShowToast("Do not move so fast");
    }

    @Override
    public void unknownEmvCard() {
        //notify user that current card has unknown nfc tag
        ShowToast("Unknown nfc tag");
    }

    @Override
    public void cardWithLockedNfc() {
        //notify user that current card has locked nfc tag
        ShowToast("Locked nfc tag");
    }

    @Override
    public void finishNfcReadCard() {
        //notify user that scanning finished
        ShowToast("Finished scanning");
        FinishReadCard();
    }

    private void ShowToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    private void FinishReadCard() {
        Intent intent = new Intent(this, PaymentFinishActivity.class);
        startActivity(intent);
        finish();
    }

    // TODO: Delete this
    private void SendTestRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getString(R.string.SERVER) + getString(R.string.HELLO_WORLD_REQ);


        ShowToast("\"" + url + "\"");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ShowToast(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ShowToast("Some error " + error.getMessage());
            }
        });
        queue.add(stringRequest);
    }
}
