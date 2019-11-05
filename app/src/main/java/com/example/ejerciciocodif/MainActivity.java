package com.example.ejerciciocodif;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    //Declaring text fields and the button
    EditText inputText, outputText;
    Button buttonOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText = findViewById(R.id.inputText);
        outputText = findViewById(R.id.outputText);
        buttonOutput = findViewById(R.id.buttonOutput);
    }

    public void getOutput(View view) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        outputText.setText("");
        String input = inputText.getText().toString();

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] digestHash = md.digest(input.getBytes("UTF-8"));

        outputText.setText(convertToHex(digestHash));
    }

    private static String convertToHex(byte[] hashInBytes){

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<hashInBytes.length;i++){
            sb.append(Integer.toString((hashInBytes[i] & 0xff)+0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
