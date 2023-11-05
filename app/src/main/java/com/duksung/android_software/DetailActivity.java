package com.duksung.android_software;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class DetailActivity extends AppCompatActivity {
    Integer[] meals = {R.drawable.meal0, R.drawable.meal1, R.drawable.meal2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView textView = (TextView)findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.button);

        Intent intent = getIntent();
        Integer position = intent.getIntExtra("meal", 0);
        String mealName = intent.getStringExtra("meal_name");
        imageView.setImageResource(meals[position]);
        textView.setText(mealName);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
