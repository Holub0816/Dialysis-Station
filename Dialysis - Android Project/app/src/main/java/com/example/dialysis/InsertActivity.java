package com.example.dialysis;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Parcelable;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Locale;

public class InsertActivity extends AppCompatActivity {

    TextView uf;
    TextView rr;
    TextView puls;
    TextView pesel;
    TextView cz;
    TextView ct;
    TextView tmp;
    Button dodaj_dane_button;
    ImageButton rr_button;
    ImageButton uf_button;
    ImageButton puls_button;
    ImageButton cz_button;
    ImageButton ct_button;
    ImageButton tmp_button;
    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    IntentFilter writeTagFilters[];
    boolean writeMode;
    Tag myTag;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        context = this;

        uf = findViewById(R.id.uf);
        rr = findViewById(R.id.rr);
        puls = findViewById(R.id.puls);
        pesel = findViewById(R.id.pesel);
        ct = findViewById(R.id.ct);
        cz = findViewById(R.id.cz);
        tmp = findViewById(R.id.tmp);
        rr_button = findViewById(R.id.rrButton);
        uf_button = findViewById(R.id.ufButton);
        puls_button = findViewById(R.id.pulsButton);
        cz_button = findViewById(R.id.czButton);
        ct_button = findViewById(R.id.czButton);
        tmp_button = findViewById(R.id.tmpButton);
        dodaj_dane_button = (Button) findViewById(R.id.dodaj_dane_button);
        dodaj_dane_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pesel_str = pesel.getText().toString();
                String uf_str = uf.getText().toString();
                String puls_str = puls.getText().toString();
                String rr_str = rr.getText().toString();
                String ct_str = ct.getText().toString();
                String cz_str = cz.getText().toString();
                String tmp_str = tmp.getText().toString();

                uf_str = uf_str.replace(",", ".");
                rr_str = rr_str.replace(",", ".");
                ct_str = ct_str.replace(",", ".");
                cz_str = cz_str.replace(",", ".");
                tmp_str = tmp_str.replace(",", ".");

                if (!emptyValidate(uf_str, puls_str, rr_str, pesel_str, ct_str, cz_str, tmp_str)) {

                    DoPost doPost = new DoPost(context);
                    doPost.execute(pesel_str, uf_str, puls_str, rr_str, ct_str, cz_str, tmp_str);
                } else {
                    Toast.makeText(getApplicationContext(), "Należy wypełnić wszystkie pola.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


        nfcAdapter = NfcAdapter.getDefaultAdapter(this); // Ta linijka zwraca defaultowy adapter NFC (urządzenia mają zazwyczaj jeden)
        if (nfcAdapter == null) {

            Toast.makeText(this, "To urządzenie nie posiada modułu NFC.", Toast.LENGTH_LONG).show();
            finish();
        }
        readFromIntent(getIntent());

        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        // If set, the activity will not be launched if it is already running at the top of the history stack.
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        tagDetected.addCategory(Intent.CATEGORY_DEFAULT);
        writeTagFilters = new IntentFilter[]{tagDetected};
    }

    private void readFromIntent(Intent intent) {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] msgs = null;
            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                }
            }
            buildTagViews(msgs);
        }
    }

    @SuppressLint("SetTextI18n")
    private void buildTagViews(NdefMessage[] msgs) {
        if (msgs == null || msgs.length == 0) return;

        String text = "";
        String tagId = new String(msgs[0].getRecords()[0].getType());
        byte[] payload = msgs[0].getRecords()[0].getPayload();
        String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16"; // Get the Text Encoding
        int languageCodeLength = payload[0] & 0063; // Get the Language Code, e.g. "en"
        // String languageCode = new String(payload, 1, languageCodeLength, "US-ASCII");

        try {
            // Get the Text
            text = new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
        } catch (UnsupportedEncodingException e) {
            Log.e("UnsupportedEncoding", e.toString());
        }

        String finalText;
        finalText = text.substring(text.lastIndexOf(" "));
        finalText = finalText.replaceAll("\\s+", "");
        pesel.setText(finalText);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        readFromIntent(intent);
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            myTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        WriteModeOff();
    }

    @Override
    public void onResume() {
        super.onResume();
        WriteModeOn();
    }

    private void WriteModeOn() {
        writeMode = true;
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, writeTagFilters, null);
    }

    private void WriteModeOff() {
        writeMode = false;
        nfcAdapter.disableForegroundDispatch(this);
    }

    private boolean emptyValidate(String uf_str, String rr_str, String puls_str, String pesel_str, String ct_str, String cz_str, String tmp_str) {
        return (uf_str.isEmpty() || puls_str.isEmpty() || rr_str.isEmpty() || pesel_str.isEmpty() || ct_str.isEmpty() || cz_str.isEmpty() || tmp_str.isEmpty());
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void powrot(View view) {
        openMainActivity();
    }

    public void getSpeechInput(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());


        if (intent.resolveActivity(getPackageManager()) != null) {
            if (view.getId() == R.id.rrButton) {
                startActivityForResult(intent, 10);
            } else if (view.getId() == R.id.ufButton) {
                startActivityForResult(intent, 20);
            } else if (view.getId() == R.id.pulsButton) {
                startActivityForResult(intent, 30);
            } else if (view.getId() == R.id.ctButton) {
                startActivityForResult(intent, 40);
            } else if (view.getId() == R.id.czButton) {
                startActivityForResult(intent, 50);
            } else if (view.getId() == R.id.tmpButton) {
                startActivityForResult(intent, 60);
            }
        } else {
            Toast.makeText(this, "Twoje urządzenie nie wspiera wprowadzania tekstu za pomocą mowy", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    rr.setText(result.get(0));
                }
                break;
            case 20:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    uf.setText(result.get(0));
                }
                break;
            case 30:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    puls.setText(result.get(0));
                }
                break;
            case 40:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    ct.setText(result.get(0));
                }
                break;
            case 50:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    cz.setText(result.get(0));
                }
                break;
            case 60:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    tmp.setText(result.get(0));
                }
                break;
        }
    }

}



