package com.duksung.android_software;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


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
            String url = "https://www.google.com/search?q=" + Uri.encode(mealName);
            Intent intent1 = new Intent(Intent.ACTION_VIEW);
            intent1.setData(Uri.parse(url));
            startActivity(intent1);

        });
    }
}
