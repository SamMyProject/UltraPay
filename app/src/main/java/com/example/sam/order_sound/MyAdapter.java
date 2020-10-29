package com.example.sam.order_sound;

import android.view.LayoutInflater;
import android.widget.BaseAdapter;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class MyAdapter extends BaseAdapter{
    private LayoutInflater myInflater;
    private LinkedList<CouponClass> coupon;

    private int selectItem = 0 ;
    public MyAdapter(Context context,LinkedList<CouponClass> _coupon){
        myInflater = LayoutInflater.from(context);
        this.coupon = _coupon;
    }
    /*private view holder class*/
    private class ViewHolder {
        TextView txtTitle;
        TextView txtTime;
        TextView txtLimit ;
        public ViewHolder(TextView txtTitle, TextView txtTime, TextView txtLimit){
            this.txtTitle = txtTitle;
            this.txtTime = txtTime;
            this.txtLimit = txtLimit ;
        }
    }

    @Override
    public int getCount() {
        return coupon.size();
    }

    @Override
    public Object getItem(int arg0) {
        return coupon.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return coupon.indexOf(getItem(position));
    }

    public int getItemCount( int position ) {
        return Integer.valueOf( coupon.get(position).getName())  ;
    }

    public int getIteLimit( int position ) {
        return Integer.valueOf( coupon.get(position).getLimit())  ;
    }
    public  void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }
    public void add(CouponClass data) {
        if ( coupon == null) {
            coupon = new LinkedList<>() ;
        }
        coupon.add(data);
        notifyDataSetChanged();
    }

    public void addList( ArrayList<CouponClass> t ) {
        Log.d("addlist", "addList:  " + "error");
        for ( int i = 0 ; i < t.size() ; i ++ ) {
            CouponClass data = new CouponClass();
            data = t.get(i) ;
            if ( coupon == null) {
                coupon = new LinkedList<>() ;
            }
            coupon.add( data);
            notifyDataSetChanged();
        }
        Log.d("addlist", "addList:  " + "??");
//        notifyDataSetChanged();
    }


    public void clear(){
        coupon.clear();
        notifyDataSetChanged();
    }

    public int getCan_dis( int money ){
        int less = 0, position = 0 ;
        boolean haveCount = false ;

        for( position = 0 ; position < coupon.size() ; position ++ ) {
            if ( money > Integer.valueOf( coupon.get(position).getLimit() ) ) ;
            else break ;
        }

        if ( position == 0 ) return -1 ;
        return position - 1  ;
    }

    public int getAlmost_dis( int money ){
        int less = 0, position = 0 ;

        for( position = 0 ; position < coupon.size() ; position ++ ) {
            if ( money > Integer.valueOf( coupon.get(position).getLimit() ) ) ;
            else break ;
        }

        if ( position == coupon.size() ) return -1 ;
        return position  ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView = myInflater.inflate(R.layout.item_coupon_list, null);
            holder = new ViewHolder(
                    (TextView) convertView.findViewById(R.id.CouponName),
                    (TextView) convertView.findViewById(R.id.CouponMoney),
                    (TextView) convertView.findViewById(R.id.limit)
            );
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        if (position == selectItem) {
            convertView.setBackgroundColor(Color.RED);
        }
        else {
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }

        CouponClass coupon = (CouponClass) getItem(position);
        //0 = movie, 1 = title, 2 = nine
        int color_title[] = {Color.BLACK,Color.WHITE,Color.YELLOW};
        int color_time[] = {Color.BLACK,Color.WHITE,Color.YELLOW};
        int color_back[] = {Color.WHITE,Color.BLUE,Color.BLACK};
        int time_vis[] = {View.VISIBLE,View.GONE,View.VISIBLE};

        int type_num = coupon.getType();
        holder.txtTitle.setText(coupon.getName() + " 折");
//        holder.txtTitle.setTextColor(color_title[type_num]);
//        holder.txtTitle.setBackgroundColor(color_back[type_num]);
        holder.txtTime.setText(coupon.getTime());
//        holder.txtTime.setTextColor(color_time[type_num]);
//        holder.txtTime.setVisibility(time_vis[type_num]);
        holder.txtLimit.setText( coupon.getLimit() );
//        holder.txtLimit.setTextColor( color_title[type_num] ) ;

        return convertView;
    }
}