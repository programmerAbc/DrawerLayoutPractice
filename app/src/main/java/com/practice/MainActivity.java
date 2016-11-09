package com.practice;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements LeftMenuFragment.OnMenuItemSelectedListener {
    public static final String tag = MainActivity.class.getSimpleName();
    private LeftMenuFragment mMenuFragment;
    private LeftDrawerLayout mLeftDrawerLayout;
    private TextView mContentTv;
    private Button openDrawerBtn;
    private View contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLeftDrawerLayout = (LeftDrawerLayout) findViewById(R.id.id_drawerlayout);
        mContentTv = (TextView) findViewById(R.id.id_content_tv);
        openDrawerBtn = (Button) findViewById(R.id.openDrawerBtn);
        contentView = findViewById(R.id.contentView);
        FragmentManager fm = getSupportFragmentManager();
        mMenuFragment = (LeftMenuFragment) fm.findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null) {
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment = new LeftMenuFragment()).commit();
        }
        mMenuFragment.setListener(this);
        openDrawerBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mLeftDrawerLayout.openDrawer();
            }
        });
        contentView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e(tag, "Clicked");
                mLeftDrawerLayout.closeDrawer();
            }
        });

    }

    @Override
    public void menuItemSelected(String title) {
        mLeftDrawerLayout.closeDrawer();
        mContentTv.setText(title);
    }
}
