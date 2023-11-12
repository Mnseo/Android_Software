package com.duksung.android_software;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> meals;
    ArrayList<String> remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meals = new ArrayList<String>();
        remove = new ArrayList<String>();
        meals.add("Caprese Salad"); meals.add("Chicken and Potatoes"); meals.add("Pasta with Meatballs");


        ListView mealList = (ListView) findViewById(R.id.listView);
        EditText editText = (EditText) findViewById(R.id.editText);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, meals);

        mealList.setAdapter(adapter);

        mealList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), meals.get(i), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("meal", i);
                intent.putExtra("meal_name", meals.get(i));
                startActivity(intent);
            }
        });

        mealList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int which = i;
                AlertDialog.Builder dig = new AlertDialog.Builder(MainActivity.this);
                dig.setTitle("삭제?");
                dig.setMessage(meals.get(i));
                dig.setNegativeButton("취소", null);
                dig.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        remove.add(meals.get(which));
                        meals.remove(which);
                        adapter.notifyDataSetChanged();
                    }
                });
                dig.show();
                return true;
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newItem = editText.getText().toString();
                meals.add(newItem);
                adapter.notifyDataSetChanged();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //remove 리스트 확인
                if(!remove.isEmpty()) {
                    //가장 마지막 항목 들고오기
                    String newItem = remove.get(remove.size()-1);
                    meals.add(newItem);
                    //remove 리스트에서 제거
                    remove.remove(remove.size()-1);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
