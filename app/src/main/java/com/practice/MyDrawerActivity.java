package com.practice;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by user1 on 2016/11/9.
 */

public class MyDrawerActivity extends AppCompatActivity {
    Button openDrawerBtn;
    Button closeDrawerBtn;
    MyDrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydrawerlayout);
        drawerLayout= (MyDrawerLayout) findViewById(R.id.drawerLayout);
        openDrawerBtn= (Button) findViewById(R.id.openDrawerBtn);
        closeDrawerBtn=(Button)findViewById(R.id.closeDrawerBtn);

        openDrawerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer();
            }
        });
        closeDrawerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer();
            }
        });
    }
}
