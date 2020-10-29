package com.example.sam.order_sound;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class coupontest extends AppCompatActivity {
    private ListView lsv_main; //ListView 宣告
    private Button touch_button;
    private int num = 0;
    private ArrayList<Integer> mList = new ArrayList<>();

    private ListView listV;
    List<CouponClass> coupon_list ;
    private MyAdapter adapter;
    private static final int MY_PERMISSION_REQUEST_CODE = 10000;

    private String serverResult = "";
    private int totalmoney ;

    private ArrayList<CouponClass> ArrayCoupon = new ArrayList<>();  // 存放coupon
    public static  ArrayList<CouponClass> CoulduseCoupon = new ArrayList<>() ;
    private TextView test;
    private TextView Money ;
    private TextView totalMoneyView;
    private TextView note;
    private int hasCoupon ; // 已擁有的優惠卷數量
    private int dis_count = 0, almost_count = 0 ;
    private int gapCoint = 0 ;
    Handler mHandler;
    public static Handler handelmes;
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupontest);
        Han();
        Bundle bundle = getIntent().getExtras(); // 傳遞total money
        checkCoupon = false ;
        coupon_list = new LinkedList<CouponClass>();
        adapter = new MyAdapter(coupontest.this, (LinkedList<CouponClass>) coupon_list);
        listV = (ListView) findViewById(R.id.listview);
        listV.setAdapter(adapter);
        Log.d("money", "onCreate: " + totalmoney);
        totalmoney = bundle.getInt("total");
        findViews();
        viewUpdata();

        listV.setOnItemClickListener(onClickListView);

        useHttpUrlConnectionGetThread() ; // get server coupon _none endcode and decode
//        useHttpUrlConnectionPOSTThreadhaveCoupon() ; // get server be encode could use coupon

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch ( msg.what) {
                    case 1 :
                        // get拿到店家優惠卷
                        adapter.clear(); // 清空adapter
                        adapter.addList( ArrayCoupon ); // 新增優惠卷
                        setdismoney() ;
                        Log.d("test", "handleMessage: test" + msg.obj.toString());
                        break ;
                    case 2 :
                        // post拿到新的優惠卷
                        Log.d("test", "handleMessage: test" + msg.obj.toString());
                        viewUpdata();
                        AlertDialog alertDialog = new AlertDialog.Builder(coupontest.this).create();
                        alertDialog.setTitle(" 恭喜");
                        alertDialog.setMessage("獲得優惠卷，請至錢包查看");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "好!",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show() ;
                        Toast.makeText(coupontest.this, " 恭喜得到優惠卷，請至錢包查看" , Toast.LENGTH_SHORT).show();
                        break;
                    case 3 :
                        // post拿取可使用的所有優惠卷
                        break;
                    case 404:
                        //沒拿到優惠卷
                        AlertDialog alertDialog2 = new AlertDialog.Builder(coupontest.this).create();
                        alertDialog2.setTitle("恭喜");
                        alertDialog2.setMessage("沒有獲得優惠卷，想不到吧");
                        alertDialog2.setButton(AlertDialog.BUTTON_NEUTRAL, "幹!",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog2.show() ;
                        Toast.makeText(coupontest.this, "未獲得優惠卷，QQ" , Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        };
    }


    private void findViews() {

        // TODO Auto-generated method stu
        totalMoneyView = (TextView) findViewById(R.id.totalMY );
        test = (TextView) findViewById(R.id.name_coupon);           //EditText findView
        Money = (TextView) findViewById(R.id.MoneyTV);
        note = ( TextView ) findViewById( R.id.note ) ;
        totalMoneyView.setText( "");
        test.setText( "優惠卷");
        note.setText( "按下去有驚喜喔");
        Money.setText( "目前無優惠");
    }

    public void Thr() {
        new Thread(new Runnable() {
            public void run() {
                MainActivity.reset.restart();
            }
        }).start();
    }

    public void Han() {
         handelmes = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                if(MainActivity.errorcount>10||MainActivity.message.equals("Press \"cancel\" Try again!")){
                    MainActivity.errorcount=0;
                    MainActivity.message="";
                    App temp =new App();
                    double[] r = new double[3072];
                    System.out.println("stop!");
                    App.alter_to_stop="#";
                    try {
                        temp.doApp(r);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(coupontest.this, "TRY ANAIN!" , Toast.LENGTH_SHORT).show();
                }//if
                else{
                    System.out.println(MainActivity.message);
                    useHttpUrlConnectionPOSTThread();
                    Toast.makeText(coupontest.this, "SUCCESS!" , Toast.LENGTH_SHORT).show();
                    viewUpdata();
                }//else
            }
        };
    }
    public void getCoupon( View v ) throws Exception{
        Thr();
        SharedPreferences pref = getSharedPreferences("couponNUM", MODE_PRIVATE);
        pref.edit().putInt( "num", 0 ).apply(); ;
//        useHttpUrlConnectionPOSTThread();
//        viewUpdata();
    }  //


    private List<String> couponlist;
    private int singleChoiceIndex;
    private boolean checkCoupon ;
    public void useCoupon( View v) throws Exception{
        couponlist = new ArrayList<>() ;
        if ( coupontest.CoulduseCoupon.size() == 0 ) {
            AlertDialog alertDialog = new AlertDialog.Builder(coupontest.this).create();
            alertDialog.setTitle("警告");
            alertDialog.setMessage("無優惠卷");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "好!",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

        }
        else {
            for (CouponClass coupon : CoulduseCoupon) {
                couponlist.add(coupon.toString());
            }
            new AlertDialog.Builder(coupontest.this)
                    .setSingleChoiceItems(couponlist.toArray(new String[couponlist.size()]), singleChoiceIndex,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    singleChoiceIndex = which;
                                }
                            })
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(coupontest.this, "額滿" + CoulduseCoupon.get(singleChoiceIndex).getLimit() + "可使用", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    })
                    .show();
        }
    }

    public void setdismoney(){
        Log.d("setdismoney", "??");
       int d = 0 ,a = 0 ;
       d = adapter.getCan_dis( totalmoney) ;
       a = adapter.getAlmost_dis( totalmoney ) ;
       if ( d == -1 ) dis_count = -1 ;
       else dis_count = adapter.getItemCount(d) ;

       if ( a == -1 ) almost_count = -1 ;
       else {
           almost_count = adapter.getItemCount(a) ;
           gapCoint = adapter.getIteLimit( a ) - totalmoney ;
       }
        Log.d("setdismoney", "++");
    }
    private void viewUpdata(){
        Log.d("viewUpdata", "??");
        listV.setAdapter( null );
        listV.setAdapter(adapter);
        if ( dis_count == -1 )
            Money.setText( "目前無優惠");
        else
            Money.setText( "當前折扣" + dis_count + "折");
        if ( almost_count == -1 )
            totalMoneyView.setText( "乾爹" );
        else
            totalMoneyView.setText( "差" + gapCoint +"可獲得" + almost_count + "折" );
    }

    private void useHttpUrlConnectionGetThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpGetUnit HttpGetUnit = new HttpGetUnit() ;
               serverResult = HttpGetUnit.useHttpClientGet(MainActivity.allurl +"/downloadCoupon");
                parseJSON( serverResult ) ; // 解析json
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message() ;
                msg.what = 1 ;
                msg.obj = "update" ;
                mHandler.sendMessage(msg) ;
            }
        }).start();
    }

    public static void useHttpUrlConnectionPOSTThreadhaveCoupon() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http_Post HP = new Http_Post();
                HP.PostGetHave(MainActivity.result.getString("uid",""), MainActivity.allurl +"/FindCoupon");
                String postResult = HP.getResult();
                Log.d( "getall", postResult.toString() );


                if (postResult.equals("404")) {
                    Log.d("???", "run: " + postResult);
//                    Message msg = Message.obtain();
//                    //設定Message的內容
//                    msg.what = 404 ;
//                    mHandler.sendMessage(msg);
                }
                else {
                    Log.d( "coupontestGET", "\n" + postResult );
                    try {
                        JSONArray array = new JSONArray(postResult) ;
                        for (int i=0; i < array.length(); i++){
                            JSONObject obj = array.getJSONObject(i);
//                           JSONObject obj = new JSONObject( postResult) ;
                           String IV  = obj.getString("IV");
                           String RSA_AES_key= obj.getString("AESkey");
                           String AES_data = obj.getString("msg");
                            Log.d( "coupontestGET", "\n" + IV + "\\" + RSA_AES_key + "\\" + AES_data );
                           AddCouldUSE( IV, RSA_AES_key,  AES_data ) ;
//                        SharedPreferences pref = getSharedPreferences("coupon", MODE_PRIVATE);
//                        pref.edit().putString( "coupon", t.toString() ) ;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void useHttpUrlConnectionPOSTThread() {
        new Thread(new Runnable() {
            @SuppressLint("HandlerLeak")
            @Override
            public void run() {

                Http_Post HP = new Http_Post();
                HP.Post(MainActivity.result.getString("uid",""), MainActivity.message, MainActivity.allurl +"/sendCoupon");
                String postResult = HP.getResult();
                Log.d( "test", postResult.toString() );


                if (postResult.equals("404")) {
                    Log.d("???", "run: " + postResult);
                    Message msg = Message.obtain();
                    //設定Message的內容
                    msg.what = 404 ;
                    mHandler.sendMessage(msg);
                }
                else {
                    Log.d( "test", "12111111111 ");
                try {
//                    JSONArray array = new JSONArray(postResult) ;
//                    for (int i=0; i < array.length(); i++){
                        JSONObject obj = new JSONObject( postResult) ;
                        String IV  = obj.getString("IV");
                        String RSA_AES_key= obj.getString("AESkey");
                        String AES_data = obj.getString("msg");
                        Log.d("JSON:", IV +"/"+ RSA_AES_key +"/"+ AES_data );
                        CouponClass t = RSAcoupon( IV, RSA_AES_key, AES_data ) ;

                        Log.d( "coupon:  " , t.toString() ) ;
                        CoulduseCoupon.add(t);
                         Message msg = Message.obtain();
                    //設定Message的內容
                         msg.what = 2 ;
                         msg.obj= "666";
                         mHandler.sendMessage(msg);
//                        SharedPreferences pref = getSharedPreferences("coupon", MODE_PRIVATE);
//                        pref.edit().putString( "coupon", t.toString() ) ;
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                }
            }
        }).start();
    }


    private AdapterView.OnItemClickListener onClickListView = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Toast 快顯功能 第三個參數 Toast.LENGTH_SHORT 2秒  LENGTH_LONG 5
            adapter.setSelectItem( position );
            adapter.notifyDataSetInvalidated();
            Toast.makeText(coupontest.this, "點選 "  + adapter.getItemCount( position ) + "還差：" +
                    ( adapter.getIteLimit(position) - totalmoney ), Toast.LENGTH_SHORT).show();
        }
    };

    public  static void AddCouldUSE(String IV, String RSA_AES_key, String AES_data) throws Exception {
        RSAtest decode = new RSAtest() ;
        String  result = decode.AES_RSA_DataDecrypted(  IV,  RSA_AES_key,  AES_data ) ;
        parseHaveCoupon( result ) ;
    }


    private CouponClass RSAcoupon( String IV,String RSA_AES_key,String AES_data ) throws Exception {
        RSAtest decode = new RSAtest() ;
        String coupon = decode.AES_RSA_DataDecrypted(  IV,  RSA_AES_key,  AES_data ) ;
//            String coupon = "self85couponABCEtime20180909limit1000" ;
            Log.d("couponName", coupon);

                String self  = this.StringSpilt( coupon,coupon.lastIndexOf("self")+ 4, coupon.indexOf("coupon") );
                String s_coupon = this.StringSpilt( coupon,coupon.lastIndexOf("coupon") + 6, coupon.indexOf("time") );
                String time = this.StringSpilt( coupon,coupon.lastIndexOf("time") + 4, coupon.indexOf("limit") );
                String limit = this.StringSpilt( coupon,coupon.lastIndexOf("limit") + 5, coupon.length() );
                Log.d("JSON:", self + "/" + s_coupon +"/"+ time +"/"+ limit );
                CouponClass t = new CouponClass( self,s_coupon,time,limit);
                return t ;
    }

    private void parseJSON(String s) {
        try {
            JSONArray array = new JSONArray(s) ;
            for (int i=0; i < array.length(); i++){
                JSONObject obj = array.getJSONObject(i);
                String name  = obj.getString("self");
                String limit = obj.getString("limit");
                String time = obj.getString("time");
                String coupon = obj.getString("coupon") ;
                Log.d("JSON:", name +"/" +coupon + "/"+ time +"/"+ limit );
                CouponClass t = new CouponClass( name, coupon ,time,limit);
                ArrayCoupon.add(t);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public  static void parseHaveCoupon(String s) {
        try {
            JSONArray array = new JSONArray(s) ;
            for (int i=0; i < array.length(); i++){
                JSONObject obj = array.getJSONObject(i);
                String name  = obj.getString("self");
                String limit = obj.getString("limit");
                String time = obj.getString("time");
                String coupon = obj.getString("coupon") ;
                Log.d("JSON:", name +"/" +coupon + "/"+ time +"/"+ limit );
                CouponClass t = new CouponClass( name, coupon ,time,limit);
                CoulduseCoupon.add(t);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void ClearAdapter(){
        List<CouponClass> Clearcoupon_list = new LinkedList<CouponClass>() ;
        listV = (ListView) findViewById(R.id.listview);
        listV.setAdapter( null );

    }

    public String StringSpilt( String data,int start, int end ) {
        Log.d("?????", start + "  " + end );
        return data.substring(start, end);
    }


    @Override
    public void onBackPressed() {

        finish();
    }
}



