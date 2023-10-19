package com.duksung.android_software;

import android.content.Intent;
import android.os.Build;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

\
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button button;
    RadioGroup radioGroup;
    EditText editText1, editText2;
    TextView textView;
    String symbol;
    int num1, num2;
    boolean resultBoolean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button1);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);

        textView = (TextView) findViewById(R.id.textView2);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                switch (checkId) {
                    case R.id.radioBtn1:
                        symbol = "+";
                        break;
                    case R.id.radioBtn2:
                        symbol = "-";
                        break;
                    case R.id.radioBtn3:
                        symbol = "*";
                        break;
                    case R.id.radioBtn4:
                        symbol = "/";
                        break;
                    default:
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //입력받은 테스트 String으로 불러오기
                String aText = editText1.getText().toString();
                String bText = editText2.getText().toString();

                //빈칸 자르기
                aText = aText.trim();
                bText = bText.trim();

                //비어있는 값 확인하기
                resultBoolean = checkTextInput(aText, bText);
                if (resultBoolean == true) {
                    num1 = Integer.parseInt(aText);
                    num2 = Integer.parseInt(bText);

                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("num1", num1);
                    intent.putExtra("num2", num2);
                    intent.putExtra("symbol", symbol);
                    startActivityForResult(intent, 0);
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode ,int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            String result = data.getStringExtra("result");
            textView.append(result +"\n");
        }

    }

    private boolean checkTextInput(String a, String b) {
        //둘 다 비어있을때
        if (a.equals("") && b.equals("")) {
            Toast.makeText(getApplicationContext(), "숫자를 입력해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }

        //둘 중 하나만 비어있을때
        else if (a.equals("") || b.equals("")) {
            //숫자 1만 비어있을때
            if (a.equals("") || b.isEmpty() == false) {
                Toast.makeText(getApplicationContext(), "첫번째 정수를 입력하세요", Toast.LENGTH_SHORT).show();
                return false;
            }
            //숫자 2만 비어있을때
            else if (b.equals("") || a.isEmpty() == false) {
                Toast.makeText(getApplicationContext(), "두번째 정수를 입력하세요", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

}
