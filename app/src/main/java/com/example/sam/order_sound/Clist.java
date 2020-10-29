package com.example.sam.order_sound;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Sam on 2018/7/31.
 */

public class Clist extends Activity {//AppCompatActivity {
    public String[] str = {"新北市", "台北市", "台中市", "台南市", "高雄市"};
    public static ArrayList<String> orders = new ArrayList<String>();
    public static ArrayList<String> key = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_SWIPE_TO_DISMISS);
        setContentView(R.layout.list);

        ListView listview = (ListView) findViewById(R.id.listview);

        //android.R.layout.simple_list_item_1 為內建樣式，還有其他樣式可自行研究
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                orders);
        listview.setAdapter(adapter);
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                new AlertDialog.Builder(Clist.this)
                        .setTitle("使用優惠?")
                        .setMessage("..... " + orders.get(position) + " .....")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MainActivity.cHandler.sendEmptyMessage(0);
                                orders.remove(position);
                                MainActivity.share.removeData(MainActivity.settings, key.get(position));
                                key.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();

                return false;
            }
        });
    }
}//
