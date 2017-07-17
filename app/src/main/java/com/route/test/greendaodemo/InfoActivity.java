package com.route.test.greendaodemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private TextView tv_name;
    private TextView tv_chuban;
    private User data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Intent intent = getIntent();
        data = (User) intent.getSerializableExtra("data");
        initView();

    }

    private void initView() {
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_chuban = (TextView) findViewById(R.id.tv_chuban);
        tv_name.setText(data.getName());
        tv_chuban.setText(data.getChuban());
    }
}
