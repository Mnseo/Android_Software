package com.duksung.android_software;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    boolean resultBoolean;
    int a, b, result;
    String resultString;
    EditText editText, editText2;
    Button plusButton, minusButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);

        plusButton = (Button) findViewById(R.id.button);
        minusButton = (Button) findViewById(R.id.button2);

        textView = (TextView) findViewById(R.id.textView);


        class ButtonListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                //입력받은 테스트 String으로 불러오기
                String aText = editText.getText().toString();
                String bText = editText2.getText().toString();

                //빈칸 자르기
                aText = aText.trim();
                bText = bText.trim();

                //비어있는 값 확인하기
                resultBoolean = checkTextInput(aText, bText);

                if (view == plusButton && resultBoolean == true) {
                        a = Integer.parseInt(aText);
                        b = Integer.parseInt(bText);
                        result = a + b;
                        resultString = Integer.toString(result);
                        textView.setText("계산 결과: " + resultString);

                }
                else if (view == minusButton && resultBoolean == true) {
                    a = Integer.parseInt(aText);
                    b = Integer.parseInt(bText);
                    result = a - b;
                    resultString = Integer.toString(result);
                    textView.setText("계산 결과: " + resultString);

                }
            }
        }
        ButtonListener listener = new ButtonListener();
        plusButton.setOnClickListener(listener);
        minusButton.setOnClickListener(listener);

    }

    private boolean checkTextInput(String a, String b) {
        //둘 다 비어있을때
        if(a.equals("") && b.equals("")) {
            Toast.makeText(getApplicationContext(), "숫자를 입력해주세요", Toast.LENGTH_SHORT).show();
            textView.setText("숫자 두개를 모두 입력하세요");
            return false;
        }

        //둘 중 하나만 비어있을때
        else if(a.equals("") || b.equals(""))
            {
                //숫자 1만 비어있을때
                if(a.equals("") || b.isEmpty() == false) {
                    Toast.makeText(getApplicationContext(), "숫자 1을 입력하세요", Toast.LENGTH_SHORT).show();
                    textView.setText("숫자 1을 입력하세요");
                    return false;
                }
                //숫자 2만 비어있을때
                else if (b.equals("") || a.isEmpty() == false) {
                Toast.makeText(getApplicationContext(), "숫자 2를 입력하세요", Toast.LENGTH_SHORT).show();
                textView.setText("숫자 2를 입력하세요");
                return false;
                }
            }
            return true;

    }
}
