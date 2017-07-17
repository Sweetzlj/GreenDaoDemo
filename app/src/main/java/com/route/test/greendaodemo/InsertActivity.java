package com.route.test.greendaodemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_chuban;
    private Button bt_finish;
    private Button bt_quxiao;
    private UserDao userDao;
    private String name;
    private String chuban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(InsertActivity.this, "user.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getReadableDb());
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
        initView();

    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_chuban = (EditText) findViewById(R.id.et_chuban);
        bt_finish = (Button) findViewById(R.id.bt_finish);
        bt_quxiao = (Button) findViewById(R.id.bt_quxiao);

        bt_finish.setOnClickListener(this);
        bt_quxiao.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_finish:
                name = et_name.getText().toString().trim();
                chuban = et_chuban.getText().toString().trim();
                User user = new User();
                user.setName(name);
                user.setChuban(chuban);
                userDao.insert(user);
                Intent  intent  = new Intent(InsertActivity.this,ListActivityActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_quxiao:
                finish();
                break;
        }
    }
}
