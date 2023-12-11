package com.duksung.android_software;

import android.content.Intent;
import android.os.Build;
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

        setContentView(R.layout.activity_main);
//        Log.i("lifecycle", "Main:onCreate");

        button = (Button) findViewById(R.id.button);
        rdoDate = (RadioButton) findViewById(R.id.radioButton);
        rdoTime = (RadioButton) findViewById(R.id.radioButton2);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        // 처음에는 두 picker를 안보이게 설정
        timePicker.setVisibility(View.INVISIBLE);
        datePicker.setVisibility(View.INVISIBLE);

        rdoDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timePicker.setVisibility(View.INVISIBLE);
                datePicker.setVisibility(View.VISIBLE);
            }
        });

        rdoTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timePicker.setVisibility(View.VISIBLE);
                datePicker.setVisibility(View.INVISIBLE);
            }
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                    date = i + "/" + (i1+1) + "/" + i2;
                }
            });
        }

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
                time = timePicker.getHour() + ":" + timePicker.getMinute();
                String appointment = date + " " + time;
                Toast.makeText(getApplicationContext(), appointment, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MemoActivity.class);
                intent.putExtra("appointment", appointment);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        android.util.Log.i("lifecycle", "Main:onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        android.util.Log.i("lifecycle", "Main:onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        android.util.Log.i("lifecycle", "Main:onRestart");
    }

}

