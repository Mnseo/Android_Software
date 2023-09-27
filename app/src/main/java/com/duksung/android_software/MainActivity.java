package com.duksung.android_software;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        android.util.Log.i("lifecycle", "Memo:onCreate");

        final TextView textView = (TextView) findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.button);
        final EditText editText = (EditText) findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String appointment = intent.getStringExtra("appointment");
                String memo = editText.getText().toString();
                textView.setText(memo + " : " + appointment);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        android.util.Log.i("lifecycle", "Memo:onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        android.util.Log.i("lifecycle", "Memo:onStop");
    }
}

