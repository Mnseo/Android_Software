package com.duksung.android_software;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;


public class DetailActivity extends AppCompatActivity {
    private Document doc;
    ArrayList<String> employee;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        employee = new ArrayList<String>();
        ListView listView = (ListView)findViewById(R.id.listView1);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, employee);
        listView.setAdapter(adapter);

        String urlString = "http://203.252.213.36:8080/FinalProject/advisorPro2.jsp";
        Intent intent = getIntent();
        String professor = intent.getStringExtra("professor");
        urlString = urlString + "?advisor=" + professor;
        Log.d("url", urlString);
        JsoupAsyncTask task = new JsoupAsyncTask();
        task.execute(urlString);
    }

    private class JsoupAsyncTask extends AsyncTask<String, Void, Document> {
        private Exception exception = null;
        @Override
        protected Document doInBackground(String... params) {;
            try {
                doc = Jsoup.connect(params[0]).get();
                Log.d("doInbackground", "try 성공");
            } catch (Exception e) {
                exception = e;
                Log.d("doInbackground", "Exception: " + e.getMessage());
                Log.d("doInbackground", "catch 성공");
            }
            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {
            if(exception != null) {
                Toast.makeText(getBaseContext(), "network error", Toast.LENGTH_SHORT).show();
            } else {
                if (doc != null) {
                    Elements h5_elements = doc.select("h5");
                    Elements i_elements = doc.select("i");
                    for (int i = 0; i < h5_elements.size(); i++) {
                        String h5Text = h5_elements.get(i).text();
                        String iText = (i_elements.size() > i) ? i_elements.get(i).text() : "";
                        String combinedText = h5Text + " / " + iText;

                        employee.add(combinedText);
                        Log.d("pass", combinedText);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getBaseContext(), "doc is null", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
