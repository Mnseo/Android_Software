package com.duksung.android_software;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


}
