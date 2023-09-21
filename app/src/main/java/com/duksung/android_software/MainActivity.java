package com.duksung.android_software;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button btn1, btn2;
    RadioButton radio1, radio2;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xml - 부착
        editText = (EditText) findViewById(R.id.editText);
        btn1 = (Button) findViewById(R.id.btn1); btn2 = (Button) findViewById(R.id.btn2);
        radio1 = (RadioButton) findViewById(R.id.radio1); radio2 = (RadioButton) findViewById(R.id.radio2);
        imageView = (ImageView) findViewById(R.id.imageView);

        View.OnClickListener buttonClickListener = view -> {
            String text = editText.getText().toString().trim();
            String url = "";
            switch (view.getId()) {
                case R.id.btn1:
                    // btn1이 클릭됐을 때의 동작
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn2:
                    //주소창으로 옮길 수 있는지 확인
                    if (!text.startsWith("http://") && !text.startsWith("https://")) {
                        url = editText.getText().toString().trim();
                    }
                    // btn2가 클릭됐을 때의 동작 (암시적 인텐트)
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    break;

                //Radio 버튼 객체 클릭 이벤트
                case R.id.radio1:
                    imageView.setImageResource(R.drawable.oreo);
                    imageView.setVisibility(View.VISIBLE);
                case R.id.radio2:
                    imageView.setImageResource(R.drawable.pie);
                    imageView.setVisibility(View.VISIBLE);
            }
        };

        btn1.setOnClickListener(buttonClickListener);
        btn2.setOnClickListener(buttonClickListener);

        radio1.setOnClickListener(buttonClickListener);
        radio2.setOnClickListener(buttonClickListener);
    }
}
