//package com.example.sam.order_sound;
////要修改IP的話,在161行和247行
//import android.Manifest;
//import android.animation.ObjectAnimator;
//import android.annotation.TargetApi;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.media.AudioRecord;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.os.Message;
//import android.provider.Settings;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import org.apache.http.*;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.text.NumberFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//    public static record rec = new record();
//    public static Handler mHandler = null;
//    public static int i = 0;
//    public static Context mContext;
//    public static AudioRecord aud = null;
//    public static boolean isRecording = false;
//    private static final int MY_PERMISSION_REQUEST_CODE = 10000;
//    public static String ans = "wait...";
//    public InputStream is = null;
//    public static String message = "";
//    public static Reset reset = new Reset();
//    // ===================================================================
//    protected float orderTotal = 0;
//    protected ArrayList<String> orderItems = new ArrayList<String>();
//    protected ArrayList<item> Item = new ArrayList<item>();
//    protected ArrayList<item> temp = new ArrayList<>();
//    protected String custName = "00000000";
//    protected String CardID = "";
//
//    ///
//    protected int Use_Coupon ;
//    protected boolean IfuseCoupon ;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        mContext = getApplicationContext();
//        Log.d("TAG", "***********1");
//        permission();
//        //Thr();
//        Han();
//        // couponButton();
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//        // Construct the data source
//        ArrayList<MenuItem> arrayOfMenuItems = new ArrayList<MenuItem>();
//        // Create the adapter to convert the array to views
//        MenuItemAdapter adapter = new MenuItemAdapter(this, arrayOfMenuItems);
//        // Attach the adapter to a ListView
//        ListView listView = (ListView) findViewById(R.id.lvMenuItem);
//        listView.setAdapter(adapter);
//        EditText nameField = (EditText) findViewById(R.id.custName);
//
//        MenuItem newItem = new MenuItem("雞翅", "兩塊", "55", "/nophoto");
//        adapter.add(newItem);
//        newItem = new MenuItem("披薩", "6吋", "120", "/nophoto");
//        adapter.add(newItem);
//        newItem = new MenuItem("蘋果派", "for your health", "90", "/nophoto");
//        adapter.add(newItem);
//        newItem = new MenuItem("薯條", "讚啦", "55", "/nophoto");
//        adapter.add(newItem);
//        newItem = new MenuItem("雞塊", "讚啦", "60", "/nophoto");
//        adapter.add(newItem);
//        newItem = new MenuItem("可樂", "Fat", "45", "/nophoto");
//        adapter.add(newItem);
//        adapter.add(newItem);
//        adapter.add(newItem);
//        adapter.add(newItem);
//
//
//    } // oncreate
//
//    private List<String> couponlist;
//    private int singleChoiceCoulduse = -1 ;
//    public void useCoupon( View v) throws Exception{
//        couponlist = new ArrayList<>() ;
//        if ( coupontest.CoulduseCoupon.size() == 0 ) {
//            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
//            alertDialog.setTitle("警告");
//            alertDialog.setMessage("無優惠卷");
//            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "好!",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
//            alertDialog.show();
//
//        }
//        else {
//            for (CouponClass coupon : coupontest.CoulduseCoupon) {
//                couponlist.add(coupon.toString());
//            }
//            new AlertDialog.Builder(MainActivity.this)
//                    .setSingleChoiceItems(couponlist.toArray(new String[couponlist.size()]), singleChoiceCoulduse,
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
////                                    singleChoiceCoulduse = which;
//                                    singleChoiceCoulduse = which;
//                                    Log.d("single", "onClick: " + singleChoiceCoulduse);
//                                }
//                            })
//                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                            if (orderTotal >= Float.parseFloat(coupontest.CoulduseCoupon.get(singleChoiceCoulduse).getLimit())) {
//                                Toast.makeText(MainActivity.this, "訂餐後使用\n額滿" + coupontest.CoulduseCoupon.get(singleChoiceCoulduse).getLimit() + "可使用", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(MainActivity.this, "無法使用\n額滿" + coupontest.CoulduseCoupon.get(singleChoiceCoulduse).getLimit() + "可使用", Toast.LENGTH_SHORT).show();
//                                singleChoiceCoulduse = -1;
//                            }
//                            dialog.dismiss();
//                        }
//                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int whichButton) {
//                    singleChoiceCoulduse = -1;
//                    dialog.dismiss();
//                }
//            })
//                    .show();
//        }
//    }
//
//    public void Han() {
//        mHandler = new Handler(Looper.getMainLooper()) {
//            @Override
//            public void handleMessage(Message message) {
//                Dia();
//            }
//        };
//    }
//
//    public void Thr() {
//        new Thread(new Runnable() {
//            public void run() {
//                reset.restart();
//            }
//        }).start();
//    }
//    public void Dia() {
//        Coupon();
//    }
//
//    public void CouponCheck( View v )throws Exception {
//        Log.d("money", orderTotal + "%%%%??");
//        Intent intent = new Intent();
//
//        intent.setClass(MainActivity.this , coupontest.class);
//        Bundle bundle = new Bundle(); //new一個Bundle物件，並將要傳遞的資料傳入
//        bundle.putInt("total", (int)orderTotal); //傳遞int
//
//        intent.putExtras(bundle);
//        startActivity(intent);
//    }
//    public void Coupon() {
//        String CID="Coupon ID :";
//        if( message.equals("Press \"cancel\" Try again!") ) CID="Nothing!!\n";
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        final View formElementsView = inflater.inflate(R.layout.form_elements,
//                null, false);
//        final EditText nameEditText = (EditText) formElementsView
//                .findViewById(R.id.nameEditText);
//        new android.app.AlertDialog.Builder(MainActivity.this).setView(formElementsView)
//                .setTitle("Coupon!!")
//                .setCancelable(false)
//                .setMessage(CID+message)
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @TargetApi(11)
//                    public void onClick(DialogInterface dialog, int id) {
//                       Thr();
//                    }//
//                })
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @TargetApi(11)
//                    public void onClick(DialogInterface dialog, int id) {
//                        String toastString = "";
//                        toastString += nameEditText.getText();
//                        Toast.makeText(MainActivity.this, toastString, Toast.LENGTH_LONG).show();
//                        HttpAsyncTask2 con2 = new HttpAsyncTask2();
//                        con2.execute("http://192.168.0.2/coupon",toastString,message);
//                        Thr();
//                        dialog.cancel();
//                        /*    */
//                    }//
//                }).show();
//    }//Coupon
//
//    public void ANS(View v) throws Exception {
//        App temp =new App();
//        double[] r = new double[3072];
//        System.out.println("stop!");
//        App.alter_to_stop="#";
//        temp.doApp(r);
//      //  }//if
//
//    }
//
//    public void addToOrder(String name, String price) {
//        if (Item.isEmpty()) {
//            item newitem = new item(name, price, 1);
//            Item.add(newitem);
//
//
//        } else {
//            boolean find = false;
//            for (item item : Item) {
//                if (item.getName() == name) {
//                    item.setNum(item.getnum() + 1);
//                    find = true;
//                    break;
//                }
//
//            }
//            if (!find) {
//                item newitem = new item(name, price, 1);
//                Item.add(newitem);
//            }
//
//
//        }
//
//        float cost = Float.parseFloat(price);
//        orderTotal = orderTotal + cost;
//
//        orderItems.add(name);
//
//        TextView totalView = (TextView) findViewById(R.id.orderTotal);
//        NumberFormat cF = NumberFormat.getCurrencyInstance();
//        totalView.setText((int) orderTotal + "$");
//    }
//
//    public void placeOrder(View v) {
//        EditText nameField = (EditText) findViewById(R.id.custName);
//        EditText Card_id = (EditText) findViewById(R.id.card_id);
//
//
//
//
//        if (nameField.getText().toString().equals("統編")) {
//            custName = "00000000";
//        } else {
//            custName = nameField.getText().toString();
//        }
//
//        CardID = Card_id.getText().toString();
//        String items = "";
//
//        for (String item : orderItems) {
//            if (items.length() > 0) {
//                items = items + ", ";
//            }
//            items = items + item;
//        }
//
//        for (item item : Item) {
//
//            Log.d(item.getName(), item.getPrice() + "數量: " + item.getnum());
//
//
//        }
//
//        NumberFormat cF = NumberFormat.getCurrencyInstance();
//        Order newOrder = new Order(custName, cF.format(orderTotal), items, 0);
//        OrderActivity.addOrder(newOrder);
////        Thread Connection = new client(items, String.valueOf(orderTotal), custName) ;
////        Connection.start();
//        temp = new ArrayList<>(Item);
////        new HttpAsyncTask().execute("http://hmkcode.appspot.com/jsonservlet");
//        HttpAsyncTask  con1 = new HttpAsyncTask() ;
//        con1.execute("http://192.168.0.2/post");
//
//        orderTotal = 0;
//        orderItems.clear();
//        Item.clear();
//
//        nameField.setText("00000000");
//        TextView totalView = (TextView) findViewById(R.id.orderTotal);
//        totalView.setText("0$");
//
//        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
//        alertDialog.setTitle("訂單成立!");
//        alertDialog.setMessage("訂單已經成立，請付錢^_^");
//        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "好!",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//        alertDialog.show();
//    }
//
//    public void viewOrders(View v) {
//        Intent intent = new Intent(MainActivity.this, OrderActivity.class);
//        startActivity(intent);
//    }
//
//    public void Clear(View v) {
//        orderTotal = 0;
//        orderItems.clear();
//        TextView totalView = (TextView) findViewById(R.id.orderTotal);
//        totalView.setText("0$");
//
//    }
//
//    public class HttpAsyncTask2 extends AsyncTask<String, Void, String> {
//        @Override
//        protected String doInBackground(String... urls) {
//
//            return POST2(urls[0], temp ,urls[1], urls[2]);
//        }
//
//        // onPostExecute displays the results of the AsyncTask.
//        @Override
//        protected void onPostExecute(String result) {
//            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
//            this.cancel(true) ;
//        }
//
//
//    }
//
//    public class HttpAsyncTask extends AsyncTask<String, Void, String> {
//        @Override
//        protected String doInBackground(String... urls) {
//
//            return POST(urls[0], temp);
//        }
//
//        // onPostExecute displays the results of the AsyncTask.
//        @Override
//        protected void onPostExecute(String result) {
//            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
//             this.cancel(true ) ;
//
//
//        }
//
//
//    }
//    public String POST2(String url, ArrayList<item> Item, String id, String discount) {
//        InputStream inputStream = null;
//        String result = "";
//        try {
//            // 1. create HttpClient
//            HttpClient httpclient = new DefaultHttpClient();
//
//            // 2. make POST request to the given URL
//            HttpPost httpPost = new HttpPost(url);
//
//            JSONArray json_arr = new JSONArray();
//            JSONObject t = new JSONObject();
//            t.accumulate("discount", discount);
//            t.accumulate("CardId", id);
//            json_arr.put(t);
//
//
//
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("Data", json_arr);
//
//
//            String json = "";
//            // 4. convert JSONObject to JSON to String
//            json = jsonObject.toString();
//            Log.d("json: ", json);
//
//            StringEntity se = new StringEntity(json, "BIG-5");
//
//            // 6. set httpPost Entity
//            httpPost.setEntity(se);
//
//            // 7. Set some headers to inform server about the type of the content
//
//
//            httpPost.setHeader("Accept", "application/json;charset=BIG-5");
//            httpPost.setHeader("Content-type", "application/json");
//
//            // 8. Execute POST request to the given URL
//            HttpResponse httpResponse = httpclient.execute(httpPost);
//
////                 9. receive response as inputStream
//            inputStream = httpResponse.getEntity().getContent();
////
////                // 10. convert inputstream to string
//            if (inputStream != null) {
//                result = convertInputStreamToString(inputStream);
//                Log.d("echo: ", result);
//            } else
//                result = "Did not work!";
//        } catch (Exception e) {
//            Log.e("InputStream", e.getLocalizedMessage());
//        }
//
//        // 11. return result
//        return result;
//    }
//
//
//
//    public String POST(String url, ArrayList<item> Item){
//        InputStream inputStream = null;
//        String result = "";
//        try {
//            // 1. create HttpClient
//            HttpClient httpclient = new DefaultHttpClient();
//
//            // 2. make POST request to the given URL
//            HttpPost httpPost = new HttpPost(url);
//
//            JSONArray json_arr = new JSONArray();
//            JSONObject t = new JSONObject() ;
//            t.accumulate( "InvoiceNumber",  custName ) ;
//            t.accumulate( "CardId", CardID) ;
//
//
//
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
//
//            //取得現在時間
//
//            Date dt=new Date();
//
//            //透過SimpleDateFormat的format方法將Date轉為字串
//
//            String time=sdf.format(dt);
//            String tt = time.substring( 0,4 ) ;
//            int yy =   Integer.parseInt( tt) ;
//            yy = yy- 1911 ;
//            int total_item_number = Item.size() ;
//
//            time =  Integer.toString(yy) + time.substring( 4);
//
//            Log.d( "this",time+ "" );
//            t.accumulate( "time",  time ) ;
//
//            t.accumulate( "itemNum", total_item_number) ;
//            json_arr.put(t) ;
//
//
//
//
//            for( item item : Item )
//            {
//                Log.d(item.getName(),item.getPrice()+ "why數量: " + item.getnum());
//
//                // 3. build jsonObject
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.accumulate("item", item.getName());
//                jsonObject.accumulate("price", item.getPrice());
//                jsonObject.accumulate("num", item.getnum());
//                json_arr.put(jsonObject) ;
//
//            }
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("Buyer", json_arr ) ;
//            //////    加密折價卷
//            if ( singleChoiceCoulduse != -1 ) {
//                String couponPost = coupontest.CoulduseCoupon.get(singleChoiceCoulduse).getName() +
//                        coupontest.CoulduseCoupon.get(singleChoiceCoulduse).getCoupon() +
//                        coupontest.CoulduseCoupon.get(singleChoiceCoulduse).getTime() +
//                        coupontest.CoulduseCoupon.get(singleChoiceCoulduse).getLimit();
//                RSAtest encode = new RSAtest(); // 加密
//                encode.AES_RSA_DataEncrypted(couponPost);
//
//                jsonObject.put("IV", encode.getIvRSAencryptedData());
//                jsonObject.put("AESkey", encode.getAESencryptedData());
//                jsonObject.put("msg", encode.getAESkeyRSAencryptedData());
//                jsonObject.put("couponID", coupontest.CoulduseCoupon.get(singleChoiceCoulduse).getCoupon());
//                coupontest.CoulduseCoupon.remove( singleChoiceCoulduse ) ;
//                Log.d("size", "POST:  " + coupontest.CoulduseCoupon.size());
//            }
//            else {
//                jsonObject.put("IV","404");
//                jsonObject.put("AESkey", "404");
//                jsonObject.put("msg", "404");
//            }
//            ////// 折價卷送出
//            String json = "";
//            // 4. convert JSONObject to JSON to String
//            json = jsonObject.toString();
//            Log.d("json: ", json);
//            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
//            // ObjectMapper mapper = new ObjectMapper();
//            // json = mapper.writeValueAsString(person);
//
//            // 5. set json to StringEntity
//            StringEntity se = new StringEntity(json, "BIG-5");
//
//            // 6. set httpPost Entity
//            httpPost.setEntity(se);
//
//            // 7. Set some headers to inform server about the type of the content
//
//
//            httpPost.setHeader("Accept", "application/json;charset=BIG-5");
//            httpPost.setHeader("Content-type", "application/json");
//
//            // 8. Execute POST request to the given URL
//            Log.d("bbb", "hh");
//
//            HttpResponse httpResponse = httpclient.execute(httpPost);
//
//
//            Log.d("bbb", "hello");
////                 9. receive response as inputStream
//            inputStream = httpResponse.getEntity().getContent();
////
////                // 10. convert inputstream to string
//            Log.d("bbbb", result);
//
//            if(inputStream != null)
//            {
//                result = convertInputStreamToString(inputStream);
//                Log.d("echoyo: ", result);
//            }
////
//            else
//                result = "Did not work!";
//        } catch (Exception e) {
//            Log.e("InputStream", e.getLocalizedMessage());
//        }
//
//        // 11. return result
//        return result;
//    }
//
//    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//        String line = "";
//        String result = "";
//        while ((line = bufferedReader.readLine()) != null)
//            result += line;
//
//        inputStream.close();
//        return result;
//
//    }
//
//    public void permission() {
//        boolean isAllGranted = checkPermissionAllGranted(
//                new String[]{
//                        Manifest.permission.READ_CONTACTS,
//                        Manifest.permission.RECORD_AUDIO,
//                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.INTERNET
//                }
//        );
//        if (isAllGranted) {
//            return;
//        } // if
//
//        ActivityCompat.requestPermissions(
//                this,
//                new String[]{
//                        Manifest.permission.READ_CONTACTS,
//                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.RECORD_AUDIO,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.INTERNET
//                },
//                MY_PERMISSION_REQUEST_CODE
//        );
//
//    } // permission
//
//    public void click(View view) {
//        boolean isAllGranted = checkPermissionAllGranted(
//                new String[]{
//                        Manifest.permission.READ_CONTACTS,
//                        Manifest.permission.RECORD_AUDIO,
//                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.INTERNET
//                }
//        );
//        if (isAllGranted) {
//            return;
//        }
//
//        ActivityCompat.requestPermissions(
//                this,
//                new String[]{
//                        Manifest.permission.READ_CONTACTS,
//                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.RECORD_AUDIO,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.INTERNET
//                },
//                MY_PERMISSION_REQUEST_CODE
//        );
//    }
//
//    private boolean checkPermissionAllGranted(String[] permissions) {
//        for (String permission : permissions) {
//            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode == MY_PERMISSION_REQUEST_CODE) {
//            boolean isAllGranted = true;
//            for (int grant : grantResults) {
//                if (grant != PackageManager.PERMISSION_GRANTED) {
//                    isAllGranted = false;
//                    break;
//                }
//            }
//
//            if (isAllGranted) {
//
//            } else {
//                openAppDetails();
//            }
//        }
//    }
//
//    private void openAppDetails() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage(" ");
//        builder.setPositiveButton(" ", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Intent intent = new Intent();
//                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                intent.addCategory(Intent.CATEGORY_DEFAULT);
//                intent.setData(Uri.parse("package:" + getPackageName()));
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//                startActivity(intent);
//            }
//        });
//        builder.setNegativeButton("取消", null);
//        builder.show();
//    }
//
//
//}
//===========================================================================================================
package com.example.sam.order_sound;
//要修改IP的話,在152行和238行

import android.Manifest;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.AudioRecord;
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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {//AppCompatActivity

    public static String allurl = "http://192.168.1.105" ;
    public static String Card = "";
    private static Boolean isExit = false;
    private static Boolean hasTask = false;
    public static SharedPreferences settings, result;
    public static StoreData share = new StoreData();
    public static record rec = new record();
    public static Handler mHandler = null, cHandler = null;
    public static int i = 0,errorcount=0;
    public static Context mContext;
    public static AudioRecord aud = null;
    public static boolean isRecording = false, loggedin = true;
    private static final int MY_PERMISSION_REQUEST_CODE = 10000;
    public static String ans = "wait...";
    public InputStream is = null;
    public static String message = "";
    public static Reset reset = new Reset();
    public static final int FUNC_LOGIN = 1;
    // ===================================================================
    protected float orderTotal;
    protected ArrayList<String> orderItems = new ArrayList<String>();
    protected ArrayList<item> Item = new ArrayList<item>();
    protected ArrayList<item> temp = new ArrayList<>();
    protected String custName = "00000000";
    protected String CardID = "";
    Timer timerExit = new Timer();
    TimerTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        settings = getSharedPreferences("DATA", 0);
//        CP=getSharedPreferences("CID", 0);
        result = getSharedPreferences("personal", 0);
        result.edit().putBoolean("loggedin", true).commit();
        Intent it = new Intent(this, Register.class);
        startActivityForResult(it, FUNC_LOGIN);
        permission();
//        Thr();
//        if (!loggedin) {
//            Intent it = new Intent(this, Register.class);
//            startActivityForResult(it, FUNC_LOGIN);
//            permission();
//        }//if
//        else {
//            Card=CP.getString("CID","");
//            settings = getSharedPreferences(Card, 0);
//            permission();
//            Thr();
//        }//
        Handel_for_record();
        Handel_for_coupon();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        // Construct the data source
        ArrayList<MenuItem> arrayOfMenuItems = new ArrayList<MenuItem>();
        // Create the adapter to convert the array to views
        MenuItemAdapter adapter = new MenuItemAdapter(this, arrayOfMenuItems);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lvMenuItem);
        listView.setAdapter(adapter);
        EditText nameField = (EditText) findViewById(R.id.custName);


        MenuItem newItem = new MenuItem("雞翅", "兩塊", "55", "/nophoto");
        adapter.add(newItem);
        newItem = new MenuItem("披薩", "6吋", "120", "/nophoto");
        adapter.add(newItem);
        newItem = new MenuItem("蘋果派", "for your health", "90", "/nophoto");
        adapter.add(newItem);
        newItem = new MenuItem("薯條", "讚啦", "55", "/nophoto");
        adapter.add(newItem);
        newItem = new MenuItem("雞塊", "讚啦", "60", "/nophoto");
        adapter.add(newItem);
        newItem = new MenuItem("可樂", "Fat", "45", "/nophoto");
        adapter.add(newItem);
        adapter.add(newItem);
        adapter.add(newItem);
        adapter.add(newItem);
        Timer timerExit = new Timer();


        //coupontest.useHttpUrlConnectionPOSTThreadhaveCoupon() ;
        task = new TimerTask() {

            @Override

            public void run() {
                isExit = false;
                hasTask = true;
            }
        };
    } // oncreate

    private List<String> couponlist;
    private int singleChoiceCoulduse = -1 ;
    public void useCoupon( View v) throws Exception{
        couponlist = new ArrayList<>() ;
        if ( coupontest.CoulduseCoupon.size() == 0 ) {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
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
            for (CouponClass coupon : coupontest.CoulduseCoupon) {
                couponlist.add(coupon.toString());
            }
            new AlertDialog.Builder(MainActivity.this)
                    .setSingleChoiceItems(couponlist.toArray(new String[couponlist.size()]), singleChoiceCoulduse,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                                    singleChoiceCoulduse = which;
                                    singleChoiceCoulduse = which;
                                    Log.d("single", "onClick: " + singleChoiceCoulduse);
                                }
                            })
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if (orderTotal >= Float.parseFloat(coupontest.CoulduseCoupon.get(singleChoiceCoulduse).getLimit())) {
                                Toast.makeText(MainActivity.this, "訂餐後使用\n額滿" + coupontest.CoulduseCoupon.get(singleChoiceCoulduse).getLimit() + "可使用", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "無法使用\n額滿" + coupontest.CoulduseCoupon.get(singleChoiceCoulduse).getLimit() + "可使用", Toast.LENGTH_SHORT).show();
                                singleChoiceCoulduse = -1;
                            }
                            dialog.dismiss();
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    singleChoiceCoulduse = -1;
                    dialog.dismiss();
                }
            })
                    .show();
        }
    }

    public void CouponCheck( View v )throws Exception {
        Log.d("money", orderTotal + "%%%%??");
        Intent intent = new Intent();

        intent.setClass(MainActivity.this , coupontest.class);
        Bundle bundle = new Bundle(); //new一個Bundle物件，並將要傳遞的資料傳入
        bundle.putInt("total", (int)orderTotal); //傳遞int

        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void Logout(View v){
        loggedin=false;
        result.edit().remove("loggedin").apply();
        Intent it = new Intent(this, Register.class);
        startActivityForResult(it, FUNC_LOGIN);
        //finish();
    }//
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit == false) {
                isExit = true; //記錄下一次要退出
                Toast.makeText(this, "再按一次Back退出APP", Toast.LENGTH_SHORT).show();
                if (!hasTask) {
                    timerExit.schedule(task, 2000);
                }
            } else {
                finish();
                System.exit(0);
            }
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FUNC_LOGIN) {
            if (resultCode == RESULT_OK) {
                //Thr();
                coupontest.useHttpUrlConnectionPOSTThreadhaveCoupon() ;
                Card = result.getString("uid", "");
            } else {
                finish();
                System.exit(0);
            }
        }
    }


    public void Handel_for_record() {
        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                Dia();
            }
        };
    } // Handel_for_record()

    public void Handel_for_coupon() {
        cHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                send(Card);
            }
        };
    } // Handel_for_record()

    public void Thr() {
        new Thread(new Runnable() {
            public void run() {
                reset.restart();
            }
        }).start();
    }

    public void send(final String toastString) {
        new Thread(new Runnable() {
            public void run() {
                new HttpAsyncTask2().sent(allurl + "/coupon", toastString, message);
            }
        }).start();
    }

    public void send2() {
        new Thread(new Runnable() {
            public void run() {
                new HttpAsyncTask().send(allurl + "/post");
            }
        }).start();
    }

    public void Dia() {
        Coupon();
    }

    public void Coupon() {
        String CID = "Coupon ID :";
        if (message.equals("Press \"cancel\" Try again!")) CID = "Nothing!!\n";
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.form_elements,
                null, false);
        final EditText nameEditText = (EditText) formElementsView
                .findViewById(R.id.nameEditText);
        nameEditText.setTextColor(Color.parseColor("#5599FF"));
        nameEditText.setText(Card, TextView.BufferType.EDITABLE);
        new android.app.AlertDialog.Builder(MainActivity.this).setView(formElementsView)
                .setTitle("Coupon!!")
                .setCancelable(false)
                .setMessage(CID + message)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {
                        Thr();
                    }//
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {
                        String toastString = "";
                        toastString += nameEditText.getText();
                        Toast.makeText(MainActivity.this, toastString, Toast.LENGTH_LONG).show();
                        if (!message.equals("Press \"cancel\" Try again!"))
                            share.saveData(settings, message);
                        reset.restart();
//                        send(toastString);
//                        HttpAsyncTask2 con2 = new HttpAsyncTask2();
//                        con2.execute("http://192.168.1.19/coupon",toastString,message);
//                        Thr();
                        dialog.cancel();
                        /*    */
                    }//
                }).show();
    }//Coupon

    public void ANS(View v) throws Exception {
        App temp = new App();
        double[] r = new double[3072];
        System.out.println("stop!");
        App.alter_to_stop = "#";
        temp.doApp(r);
        //  }//if

    }

    public void addToOrder(String name, String price) {
        if (Item.isEmpty()) {
            item newitem = new item(name, price, 1);
            Item.add(newitem);
        } else {
            boolean find = false;
            for (item item : Item) {
                if (item.getName() == name) {
                    item.setNum(item.getnum() + 1);
                    find = true;
                    break;
                }
            }
            if (!find) {
                item newitem = new item(name, price, 1);
                Item.add(newitem);
            }
        }

        float cost = Float.parseFloat(price);
        orderTotal = orderTotal + cost;

        orderItems.add(name);

        TextView totalView = (TextView) findViewById(R.id.orderTotal);
        NumberFormat cF = NumberFormat.getCurrencyInstance();
        totalView.setText((int) orderTotal + "$");
    }

    public void placeOrder(View v) {
        EditText nameField = (EditText) findViewById(R.id.custName);
        EditText Card_id = (EditText) findViewById(R.id.card_id);
        Card_id.setText(Card);
        if (nameField.getText().toString().equals("統編")) {
            custName = "00000000";
        } else {
            custName = nameField.getText().toString();
        }

        CardID = Card_id.getText().toString();
        String items = "";

        for (String item : orderItems) {
            if (items.length() > 0) {
                items = items + ", ";
            }
            items = items + item;
        }

        for (item item : Item) {

            Log.d(item.getName(), item.getPrice() + "數量: " + item.getnum());


        }

        NumberFormat cF = NumberFormat.getCurrencyInstance();
        Order newOrder = new Order(custName, cF.format(orderTotal), items, 0);
        OrderActivity.addOrder(newOrder);
//        Thread Connection = new client(items, String.valueOf(orderTotal), custName) ;
//        Connection.start();
        temp = new ArrayList<>(Item);
        send2();
//        new HttpAsyncTask().execute("http://hmkcode.appspot.com/jsonservlet");
//        HttpAsyncTask  con1 = new HttpAsyncTask() ;
//        con1.execute("http://192.168.1.19/post");

        orderTotal = 0;
        orderItems.clear();
        Item.clear();


        nameField.setText("00000000");
        TextView totalView = (TextView) findViewById(R.id.orderTotal);
        totalView.setText("0$");

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("訂單成立!");
        alertDialog.setMessage("訂單已經成立，請付錢^_^");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "好!",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void viewOrders(View v) {
        Intent intent = new Intent(MainActivity.this, OrderActivity.class);
        startActivity(intent);
    }//

    public void viewcoupon(View v) {
        share.getAllValues(settings);
        Intent intent = new Intent(MainActivity.this, Clist.class);
        startActivity(intent);
    }//

    public void Clear(View v) {
        orderTotal = 0;
        orderItems.clear();
        TextView totalView = (TextView) findViewById(R.id.orderTotal);
        totalView.setText("0$");
    }//

    public class HttpAsyncTask2 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return POST2(urls[0], temp, urls[1], urls[2]);
        }

        protected String sent(String u0, String u1, String u2) {
            //Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
            return POST2(u0, temp, u1, u2);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
            this.cancel(true);
        }


    }

    public class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return POST(urls[0], temp);
        }

        protected String send(String u1) {

            return POST(u1, temp);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
            this.cancel(true);


        }


    }




    public String POST2(String url, ArrayList<item> Item, String id, String discount) {
        InputStream inputStream = null;
        String result = "";
        try {
            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            JSONArray json_arr = new JSONArray();
            JSONObject t = new JSONObject();
            t.accumulate("discount", discount);
            t.accumulate("CardId", id);
            json_arr.put(t);



            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Data", json_arr);


            String json = "";
            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();
            Log.d("json: ", json);

            StringEntity se = new StringEntity(json, "BIG-5");

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content


            httpPost.setHeader("Accept", "application/json;charset=BIG-5");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

//                 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
//
//                // 10. convert inputstream to string
            if (inputStream != null) {
                result = convertInputStreamToString(inputStream);
                Log.d("echo: ", result);
            } else
                result = "Did not work!";
        } catch (Exception e) {
            Log.e("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }



    public String POST(String url, ArrayList<item> Item){
        InputStream inputStream = null;
        String result = "";
        try {
            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            JSONArray json_arr = new JSONArray();
            JSONObject t = new JSONObject() ;
            t.accumulate( "InvoiceNumber",  custName ) ;
            t.accumulate( "CardId", CardID) ;



            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");

            //取得現在時間

            Date dt=new Date();

            //透過SimpleDateFormat的format方法將Date轉為字串

            String time=sdf.format(dt);
            String tt = time.substring( 0,4 ) ;
            int yy =   Integer.parseInt( tt) ;
            yy = yy- 1911 ;
            int total_item_number = Item.size() ;

            time =  Integer.toString(yy) + time.substring( 4);

            Log.d( "this",time+ "" );
            t.accumulate( "time",  time ) ;

            t.accumulate( "itemNum", total_item_number) ;
            json_arr.put(t) ;




            for( item item : Item )
            {
                Log.d(item.getName(),item.getPrice()+ "why數量: " + item.getnum());

                // 3. build jsonObject
                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("item", item.getName());
                jsonObject.accumulate("price", item.getPrice());
                jsonObject.accumulate("num", item.getnum());
                json_arr.put(jsonObject) ;

            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Buyer", json_arr ) ;
            //////    加密折價卷
            if ( singleChoiceCoulduse != -1 ) {
                String couponPost = coupontest.CoulduseCoupon.get(singleChoiceCoulduse).getName() +
                        coupontest.CoulduseCoupon.get(singleChoiceCoulduse).getCoupon() +
                        coupontest.CoulduseCoupon.get(singleChoiceCoulduse).getTime() +
                        coupontest.CoulduseCoupon.get(singleChoiceCoulduse).getLimit();
                RSAtest encode = new RSAtest(); // 加密
                encode.AES_RSA_DataEncrypted(couponPost);

                jsonObject.put("IV", encode.getIvRSAencryptedData());
                jsonObject.put("AESkey", encode.getAESkeyRSAencryptedData());
                jsonObject.put("msg",    encode.getAESencryptedData() );
                jsonObject.put("couponID", coupontest.CoulduseCoupon.get(singleChoiceCoulduse).getCoupon());
                coupontest.CoulduseCoupon.remove( singleChoiceCoulduse ) ;
                singleChoiceCoulduse = -1;
                Log.d("size", "POST:  " + coupontest.CoulduseCoupon.size());
            }
            else {
                jsonObject.put("IV","404");
                jsonObject.put("AESkey", "404");
                jsonObject.put("msg", "404");
            }
            ////// 折價卷送出
            String json = "";
            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();
            Log.d("json: ", json);
            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json, "BIG-5");

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content


            httpPost.setHeader("Accept", "application/json;charset=BIG-5");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            Log.d("bbb", "hh");

            HttpResponse httpResponse = httpclient.execute(httpPost);


            Log.d("bbb", "hello");
//                 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
//
//                // 10. convert inputstream to string
            Log.d("bbbb", result);

            if(inputStream != null)
            {
                result = convertInputStreamToString(inputStream);
                Log.d("echoyo: ", result);
            }
//
            else
                result = "Did not work!";
        } catch (Exception e) {
            Log.e("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public void permission() {
        boolean isAllGranted = checkPermissionAllGranted(
                new String[]{
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET
                }
        );
        if (isAllGranted) {
            return;
        } // if

        ActivityCompat.requestPermissions(
                this,
                new String[]{
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET
                },
                MY_PERMISSION_REQUEST_CODE
        );

    } // permission

    public void click(View view) {
        boolean isAllGranted = checkPermissionAllGranted(
                new String[]{
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET
                }
        );
        if (isAllGranted) {
            return;
        }

        ActivityCompat.requestPermissions(
                this,
                new String[]{
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET
                },
                MY_PERMISSION_REQUEST_CODE
        );
    }

    private boolean checkPermissionAllGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSION_REQUEST_CODE) {
            boolean isAllGranted = true;
            for (int grant : grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false;
                    break;
                }
            }

            if (isAllGranted) {

            } else {
                openAppDetails();
            }
        }
    }

    private void openAppDetails() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(" ");
        builder.setPositiveButton(" ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }


}
