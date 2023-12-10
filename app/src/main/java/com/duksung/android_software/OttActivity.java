package com.duksung.android_software;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class OttActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    ArrayList<String> items; // 영화 이름
    ArrayList<String> urls; // url
    static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ott);

        items = new ArrayList<String>();
        urls = new ArrayList<String>();
        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), items.get(i), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("Movie", items.get(i));
                intent.putExtra("url", urls.get(i));
                startActivity(intent);
            }
        });
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        makeRequest();
    }


    public void makeRequest() {
        String url = "https://movie.daum.net/ranking/ott";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseHtml(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "network error",
                                Toast.LENGTH_LONG).show();
                    }
                }
        );

        request.setShouldCache(false);
        requestQueue.add(request);
    }

    public void parseHtml(String html) {
        Document doc = Jsoup.parse(html);
        Elements itemElements = doc.select(" strong.tit_item > a");   // XPath *[@id="mainContent"]/div/div[2]/ol/li[1]/div/div[2]/strong/a
        for(Element element : itemElements) {
            urls.add(element.attr("href").trim());
            items.add(element.text().trim());
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reservation:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
