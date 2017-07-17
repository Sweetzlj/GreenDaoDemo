package com.route.test.greendaodemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.List;

public class ListActivityActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_insert;
    private ListView recycler;
    private UserDao userDao;
    private MyAdapter myAdapter;
    private ProgressDialog dialog;
    private List<User> list_query;
    private PopupWindow popupWindow;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_activity);
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(ListActivityActivity.this, "user.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getReadableDb());
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();

        initView();
        initData();

        recycler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivityActivity.this, InfoActivity.class);
                intent.putExtra("data", list_query.get(position));
                startActivity(intent);
            }
        });
        recycler.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            private Button bt_updata;
            private Button bt_delete;

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                final View popup = getLayoutInflater().inflate(R.layout.mypup, null);
                bt_delete = (Button) popup.findViewById(R.id.bt_delete);
                bt_updata = (Button) popup.findViewById(R.id.bt_updata);
                bt_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userDao.delete(list_query.get(position));
                        list_query.remove(position);
                        myAdapter.notifyDataSetChanged();
                        if(popupWindow.isShowing()){
                            popupWindow. dismiss();
                        }
                    }
                });
                bt_updata.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ListActivityActivity.this,UpdataActivity.class);
                        intent.putExtra("data",list_query.get(position));
                        startActivity(intent);
                    }
                });
                popupWindow = new PopupWindow(popup, 400, 200);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.GREEN));
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
                return true;
            }
        });

    }

    private void initData() {
        dialog = new ProgressDialog(this);
        dialog.setTitle("正在加载...");
        dialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    dialog.dismiss();
                    list_query = userDao.queryBuilder().build().list();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            myAdapter = new MyAdapter(ListActivityActivity.this, list_query);
                            recycler.setAdapter(myAdapter);

                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        handler.sendEmptyMessage(1);

    }

    private void initView() {
        bt_insert = (Button) findViewById(R.id.bt_insert);
        recycler = (ListView) findViewById(R.id.recycler);

        bt_insert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_insert:
                Intent intent = new Intent(ListActivityActivity.this, InsertActivity.class);
                startActivity(intent);
                break;
        }
    }
}
