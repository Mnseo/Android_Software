package com.duksung.android_software;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    int num1, num2, result;
    String symbol,result_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button2);

        num1 = getIntent().getIntExtra("num1", 0);
        symbol = getIntent().getStringExtra("symbol");
        num2 = getIntent().getIntExtra("num2", 0);


        switch (symbol) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {result = num1 / num2;}
                else { Toast.makeText(this, "0으로는 나눌 수 없습니다", Toast.LENGTH_SHORT).show();}
                break;
            default:
        }

        result_string = "("+num1+")" + symbol + "("+num2+")" + "=" + result;
        textView.setText(result_string);
        button.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra("result", result_string);
            setResult(Activity.RESULT_OK, intent);
            finish();  // SecondActivity를 종료합니다.
        });
    }
}