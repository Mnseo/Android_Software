package com.duksung.android_software;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        btn1 = (Button) findViewById(R.id.btn1); btn2 = (Button) findViewById(R.id.btn2);
        radioGroup = findViewById(R.id.radioGroup);
        imageView = (ImageView) findViewById(R.id.imageView);

        View.OnClickListener buttonClickListener = view -> {
            String text = editText.getText().toString().trim();
            switch (view.getId()) {
                case R.id.btn1:
                    // btn1이 클릭됐을 때의 동작
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn2:
                    // btn2가 클릭됐을 때의 동작 (암시적 인텐트)
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(text));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "No application can handle this URL!", Toast.LENGTH_SHORT).show();
                    }
            }
        };

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio1:
                        imageView.setImageResource(R.drawable.oreo);
                        imageView.setVisibility(View.VISIBLE);
                        Log.d("image Resource", "radio1");
                        break;
                    case R.id.radio2:
                        imageView.setImageResource(R.drawable.pie);
                        imageView.setVisibility(View.VISIBLE);
                        Log.d("image Resource", "radio2");
                        break;
                }
            }
        });

        btn1.setOnClickListener(buttonClickListener);
        btn2.setOnClickListener(buttonClickListener);

    }
}

