package com.route.test.greendaodemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by my301s on 2017/7/17.
 */
 class MyAdapter extends BaseAdapter{
    private Context context ;
    private List<User> list;

    public MyAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder  holder;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=LayoutInflater.from(context).inflate(R.layout.item_main,null);
            holder.img= (ImageView) convertView.findViewById(R.id.ima);
            holder.name= (TextView) convertView.findViewById(R.id.name);
            holder.chuban= (TextView) convertView.findViewById(R.id.chuban);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        User user = list.get(position);
        holder.name.setText(user.getName());
        holder.chuban.setText(user.getChuban());
        holder.img.setImageResource(R.drawable.b);
        return convertView;
    }
    static class ViewHolder {
        private TextView name;
        private TextView chuban;
        private ImageView img;
    }
}
