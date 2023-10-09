package com.duksung.android_software;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    int num1, num2, result;
    String symbol;

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

        textView.setText("("+num1+")" + symbol + "("+num2+")" + "=" + result);
        button.setOnClickListener(view -> {
            finish();
        });
    }
}