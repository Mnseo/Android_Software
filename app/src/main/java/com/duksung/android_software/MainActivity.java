package com.duksung.android_software;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fahrenStr = editText.getText().toString();
                fahrenStr = fahrenStr.trim();
                if(fahrenStr.equals("")) {
                    textView.setText("화씨값을 입력하세요~");
                    Toast.makeText(getApplicationContext(), "화씨값을 입력!!",
                            Toast.LENGTH_LONG).show();

                } else {
                    Double fahren = Double.parseDouble(fahrenStr);
                    Double celsius = (fahren - 32.0) / 1.8;
                    textView.setText("섭씨 " + celsius.toString());
                }
            }
        });
    }
}
