package com.example.sam.order_sound;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpResponse;
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

/**
 * Created by Sam on 2018/8/2.
 */

public class Register extends Activity {//AppCompatActivity {
    public static Handler handler = null;
    private Button cancel;
    private EditText id, pwd;
    private String loginid, loginpwd;
    private Boolean loggedin = false;
    private static String uid,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        id = (EditText) findViewById(R.id.id);
        pwd = (EditText) findViewById(R.id.pwd);
        cancel = (Button) findViewById(R.id.cancel);
        MainActivity.loggedin = MainActivity.result.getBoolean("loggedin", false);
        Handel_for_login();
        if (MainActivity.result.contains("uid")&& MainActivity.result.contains("upwd")&& MainActivity.loggedin){
            uid= MainActivity.result.getString("uid","");
            pass= MainActivity.result.getString("upwd","");
            log();
        }//if
    }//onCreate

//    public boolean getAllValues(String idx,String pws) {
//        String temp = idx+"+"+pws;
//        Map<String, ?> allContent = MainActivity.result.getAll();
//        for (Map.Entry<String, ?> entry : allContent.entrySet()) {
//            if(entry.getValue().toString().equals(temp)){
//                return true;
//            }//
//        }//for
//        return false;
//    }//
//    public void login(View view) {
//        boolean in=getAllValues(id.getText().toString(),pwd.getText().toString());
//        if (in){//loginid.equals(id.getText().toString())) {
//            if (in){//loginpwd.equals(pwd.getText().toString())) {
////                MainActivity.CP.edit().putString("CID",id.getText().toString()).commit();
//                MainActivity.Card=id.getText().toString();
//                MainActivity.settings = getSharedPreferences(MainActivity.Card, 0);
//                MainActivity.result.edit().putBoolean("loggedin", true).commit();
//                setResult(RESULT_OK, getIntent());
//                finish();
//            }
//        }
//    }

    public void send3(View view) {
        new Thread(new Runnable() {
            public void run() {
                new HttpAsyncTask3().sent(MainActivity.allurl +"/hi",id.getText().toString(),pwd.getText().toString());
            }
        }).start();
    }//send3

    public void log() {
        new Thread(new Runnable() {
            public void run() {
                new HttpAsyncTask3().sent(MainActivity.allurl + "/hi",uid,pass);
            }
        }).start();
    }//log

    public void register(View view) {

            Intent it = new Intent(Register.this, Main2.class);
            startActivity(it);
    }
    public void Handel_for_login() {
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                MainActivity.result.edit().putBoolean("loggedin", true).commit();
                setResult(RESULT_OK, getIntent());
                finish();
            } // handleMessage
        };
    } // Handel_for_record()

    public class HttpAsyncTask3 {
        protected String sent(String u0,String id,String pass) {
            return POST3(u0,id,pass);
        }//sent
        // onPostExecute displays the results of the AsyncTask.
    }//HttpAsyncTask3

    public String POST3(String url,String id,String pass) {
        InputStream inputStream = null;
        String result = "";
        try {
            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            JSONArray json_arr = new JSONArray();
            JSONObject t = new JSONObject();
            t.accumulate("Card",id);
            t.accumulate("psw", pass);
            json_arr.put(t);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Data", json_arr);
            String json = "";
            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();
            RSAtest encode = new RSAtest(); // 加密
            String ans = encode.RSAencode( json );
            Log.d("loginRSA: ", ans);
            StringEntity se = new StringEntity( ans, "BIG-5");
            // 6. set httpPost Entity
            httpPost.setEntity(se);
            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json;charset=BIG-5");
            httpPost.setHeader("Content-type", "application/json");
            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);
//                 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
//                // 10. convert inputstream to string
            if (inputStream != null) {
                result = convertInputStreamToString(inputStream);
                if(result.equals("OK")){
                    System.out.println(result);
                    handler.sendEmptyMessage(0);
                }//if
                Log.d("echo: ", result);
            } else
                result = "Did not work!";
        } catch (Exception e) {
           // Log.e("InputStream", e.getLocalizedMessage());
        } // catch
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
}//class
