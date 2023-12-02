package com.duksung.android_software;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nativeApp:
                // nativeApp 메뉴 아이템을 클릭했을 때
                Intent intent1 = new Intent(this, ProfessorActivity.class);
                startActivity(intent1);
                return true;

            case R.id.WebApp:
                // WebApp 메뉴 아이템을 클릭했을 때
                String url = "http://203.252.213.36:8080/FinalProject/advisorForm.jsp";
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                intent2.setData(Uri.parse(url));
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
