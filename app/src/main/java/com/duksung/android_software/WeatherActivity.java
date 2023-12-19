package com.duksung.android_software;

import static com.duksung.android_software.MainActivity.requestQueue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WeatherActivity extends AppCompatActivity {
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        setTitle("일기예보");

        textView = findViewById(R.id.textView2);

        //화면에 들어오자마자 자동으로 로딩
        makeRequestWeather();

        button = findViewById(R.id.buttonAir);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherActivity.this, AirActivity.class);
                startActivity(intent);
            }
        });

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    //내부에서만 사용될것 같아 private function으로 설정했습니다
    public void makeRequestWeather() {
        String currentTime = getCurrentTimeFormatted();
        Log.d("currentTime", currentTime);
        int timeValue = Integer.parseInt(currentTime.substring(8));
        Log.d("timeValue", Integer.toString(timeValue));
        if(timeValue < 600) {
            textView.setText("not available until 0600");
            return;
        }

        String url = "https://apis.data.go.kr/1360000/MidFcstInfoService/getMidFcst?serviceKey=0mrYz8QX9i9o%2FGMlR5208qBFgnRr2FPD7Xa63zx6v5791p4t0J5BOuZFDc7VVpwzt6xyX2v42UAaDSePOVcSTQ%3D%3D&pageNo=1&numOfRows=10&dataType=JSON&stnId=109" +
                "&tmFc=";
        // 아닐시에 0600 url 추가
        url += currentTime.substring(0,8) + "0600";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseWeatherJson(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 -> " + error.getMessage());
                    }
                }
        );

        request.setShouldCache(false);
        requestQueue.add(request);
    }

    public static String getCurrentTimeFormatted() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        Calendar now = Calendar.getInstance();
        return formatter.format(now.getTime());
    }


    public void println(String data) {
        textView.append(data + "\n");
    }

    public void parseWeatherJson(String json) {
        try {
            JSONObject object1 = new JSONObject(json);
            JSONObject response = object1.getJSONObject("response");
            JSONObject body = response.getJSONObject("body");
            JSONObject items = body.getJSONObject("items");
            JSONArray array = items.getJSONArray("item");

            if (array.length() > 0) {
                JSONObject weatherInfo = array.getJSONObject(0);
                String textviewValue = weatherInfo.getString("wfSv");
                textView.setText(textviewValue);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
