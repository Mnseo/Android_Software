package com.duksung.android_software;

import static com.duksung.android_software.MainActivity.requestQueue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


/* 자치구 행정 api의 조회가 1-5 page가 최대이기에 어떻게 저장할지 고민하다 Map을 사용했습니다
해당 Map 생성은 AirActivity 내용이 길어질것 같아 District.java 내용으로 뺐습니다
교수님께서 이전 card.java(Week5)에서 알려주신 내용은 자치구-코드 두가지 string값을 사용하는데 있어 어려움을 느껴 map을 사용했으므로 양해해주시면 감사드리겠습니다 */

public class AirActivity extends AppCompatActivity {
    TextView textTime,textValue;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> districtNames;
    ArrayList<String> districtCodes;
    District district;
    String selectedDistrictCode = null; //마지막으로 선택된 지역 코드


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air);
        setTitle("자치구별 대기환경오염정보");

        //Map Value & keyset - ArrayList에 삽입
        district = new District();
        districtNames = new ArrayList<>(district.getDistrictMap().values());
        districtCodes = new ArrayList<>(district.getDistrictMap().keySet());

        textTime = findViewById(R.id.textView);
        textValue = findViewById(R.id.textView1);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, districtNames);
        listView.setAdapter(adapter);

        textTime.setText(getCurrentTimeFormatted2().toString());

        //클릭시 textViewUpdate
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //선택한 districtName - code 할당
                String districtName = districtNames.get(position);
                String districtCode = findDistrictCodeByName(districtName);
                if (districtCode != null) {
                    makeAirRequest(districtCode); //code가 null이 아니면 request 보내기
                } else {
                    //만약 코드를 찾지 못하면 toast 띄우기
                    Toast.makeText(AirActivity.this, "District code not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    // 해당 자치구의 MAP을 돌아서 일치하는 name이 있으면 key-value 들고오기
    private String findDistrictCodeByName(String name) {
        for (Map.Entry<String, String> entry : district.getDistrictMap().entrySet()) {
            if (entry.getValue().equals(name)) {
                return entry.getKey();
            }
        }
        return null; // 코드가 없으면 NULL처리
    }

    //받는 형식이 달라졌으므로 새로 선언을 했습니다
    public static String getCurrentTimeFormatted2() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 HH시");
        Calendar now = Calendar.getInstance();
        return formatter.format(now.getTime()) + " 기준";
    }

    private void makeAirRequest(String districtCode) {
        selectedDistrictCode = districtCode;
        String url = "http://openapi.seoul.go.kr:8088/4c72556674746c7334345973577a68/json/ListAirQualityByDistrictService/1/5/" + districtCode;
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    //성공시
                    @Override
                    public void onResponse(String response) {
                        parseJsonAir(response);
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

    //누를때마다 쌓이는것을 방지하기 위해 append->setText
    public void println(String data) {
        textValue.setText(data + "\n");
    }

    public void parseJsonAir(String json) {
        try {
            JSONObject object1 = new JSONObject(json);
            JSONObject object2 = object1.getJSONObject("ListAirQualityByDistrictService");
            JSONArray array = object2.getJSONArray("row");
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                //비어있을때-> N/A처리
                String grade = obj.getString("GRADE");
                String nitrogen = obj.getString("NITROGEN");
                String ozone = obj.getString("OZONE");
                String carbon = obj.getString("CARBON");
                String sulfurous = obj.getString("SULFUROUS");
                String pm10 = obj.getString("PM10");
                String pm25 = obj.getString("PM25");

                String output = String.format(
                        "통합대기환경지수 등급: N/A\n 이산화질소: %s\n 오존: %s\n 일산화탄소: %s\n 아황산가스: %s\n 미세먼지: %s\n 초미세먼지: %s",
                        nitrogen, ozone, carbon, sulfurous, pm10, pm25);
                println(output);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.reservation) {
            if (selectedDistrictCode != null) {
                //selectedDistrictCode가 비어있는게 아니라면
                makeAirRequest(selectedDistrictCode);
            } else {
                Toast.makeText(this, "Please select a district first", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

