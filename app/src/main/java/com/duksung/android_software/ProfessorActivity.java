package com.duksung.android_software;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ProfessorActivity extends AppCompatActivity {
    String [] professor = {"정원호", "음두헌", "강남희", "임양미", "박태정"};
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);

        ListView departmentList = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, professor);
        departmentList.setAdapter(adapter);


        departmentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), professor[i], Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("professor", professor[i]);
                startActivity(intent);
            }
        });


    }
}
