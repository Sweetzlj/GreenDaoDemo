package com.route.test.greendaodemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdataActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText up_name;
    private EditText up_chuban;
    private Button up_finish;
    private Button up_quxiao;
    private User user;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata);
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(UpdataActivity.this, "user.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getReadableDb());
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("data");
        initView();
        up_name.setText(user.getName());
        up_chuban.setText(user.getChuban());
    }

    private void initView() {
        up_name = (EditText) findViewById(R.id.up_name);
        up_chuban = (EditText) findViewById(R.id.up_chuban);
        up_finish = (Button) findViewById(R.id.up_finish);
        up_quxiao = (Button) findViewById(R.id.up_quxiao);

        up_finish.setOnClickListener(this);
        up_quxiao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.up_finish:
                user.setName(up_name.getText().toString());
                user.setChuban(up_chuban.getText().toString());
                userDao.update(user);
                Intent intent = new Intent(UpdataActivity.this,ListActivityActivity.class);
                startActivity(intent);
                break;
            case R.id.up_quxiao:
                finish();
                break;
        }
    }
}
