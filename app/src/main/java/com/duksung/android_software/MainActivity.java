package com.duksung.android_software;

import static java.sql.DriverManager.println;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AlertDialog;


public class MainActivity extends AppCompatActivity {
    EditText editText1, editText2;
    Button button;
    String idText, pwText;
    int responseReturn = 0;
    boolean resultBoolean; // 입력값 빈값 체크
    static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("서울 날씨 어때?");

        button = (Button) findViewById(R.id.buttonLogin);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idText = editText1.getText().toString().trim();
                pwText = editText2.getText().toString().trim();

                Log.d("idText", idText);
                Log.d("pwText", pwText);
                //비어있는 값 확인하기
                resultBoolean = checkTextInput(idText, pwText);
                if (resultBoolean == true) {
                    //값이 비어있지 않으면 volley 요청
                    makeLoginRequest();
                }

            }
        };
        button.setOnClickListener(listener);
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }
    private boolean checkTextInput(String a, String b) {
        //둘 다 비어있을때
        if(a.equals("") && b.equals("")) {
            Toast.makeText(getApplicationContext(), "값을 입력해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }

        //둘 중 하나만 비어있을때
        else if(a.equals("") || b.equals(""))
        {
            //숫자 1만 비어있을때
            if(a.equals("") || b.isEmpty() == false) {
                Toast.makeText(getApplicationContext(), "아이디를 입력하세요", Toast.LENGTH_SHORT).show();
                return false;
            }
            //숫자 2만 비어있을때
            else if (b.equals("") || a.isEmpty() == false) {
                Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Login Failed!");
        builder.setMessage("Please try again");
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //내부에서만 사용될것 같아 private function으로 설정했습니다
    public void makeLoginRequest() {
        String url = "http://203.252.213.36:8080/FinalProject/loginPro.jsp?";
        url = url + "id=" + idText + "&passwd=" + pwText;
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    //성공시
                    @Override
                    public void onResponse(String response) {
                        int responseTrim = Integer.parseInt(response.trim());
                        //response == 1 (로그인 성공)
                        if (responseTrim==1) {
                            navigateNextActivity();
                        }
                        else if (responseTrim == 0 || responseTrim == -1) {
                            responseReturn = responseTrim;
                            showDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    //실패시
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 -> " + error.getMessage());
                    }
                }
        );

        request.setShouldCache(false);
        requestQueue.add(request);
    }

    private void navigateNextActivity() {
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
        //finish() 로그인 화면으로 돌아가야할 상황을 생각하여 주석처리 했습니다
    }

}

