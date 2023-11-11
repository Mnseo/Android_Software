package com.duksung.android_software;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class DetailActivity extends AppCompatActivity {
    Integer[] meals = {R.drawable.meal0, R.drawable.meal1, R.drawable.meal2};
    String mealName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView textView = (TextView)findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.button);



        Intent intent = getIntent();
        Integer position = intent.getIntExtra("meal", 0);
        mealName = intent.getStringExtra("meal_name");
        imageView.setImageResource(meals[position]);
        textView.setText(mealName);


        button.setOnClickListener(view -> {
            Intent intent1 = new Intent();
            intent1.setAction(Intent.ACTION_WEB_SEARCH);
            Log.d("meal_Name", mealName);
            intent1.putExtra(SearchManager.QUERY, mealName);
            startActivity(intent1);

        });
    }
}
