package com.example.sam.order_sound;

/**
 * Created by Sam on 2018/8/2.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
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
import java.util.Map;

public class Main2 extends Activity {//AppCompatActivity{
    private EditText id, pwd;
    private static String uid,psw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.reg);
        id = (EditText) findViewById(R.id.id);
        pwd = (EditText) findViewById(R.id.pwd);
    }
    public boolean getAllValues(String idx,String pws) {
        String temp = idx+"+"+pws;
        Map<String, ?> allContent = MainActivity.result.getAll();
        for (Map.Entry<String, ?> entry : allContent.entrySet()) {
            if(entry.getValue().toString().equals(temp)){
                return true;
            }//
        }//for
        return false;
    }//
    public void registerok(View view) {
        String userid = id.getText().toString();
        String userpwd = pwd.getText().toString();
        uid=userid;
        psw=userpwd;
        boolean x=true;
        if(!getAllValues(userid,userpwd)) {
            MainActivity.result.edit()
                    .putString("uid", userid )
                    .putString("upwd", userpwd)
                    .commit();//4638441702

            finish();
        }
        else{
            AlertDialog.Builder d = new AlertDialog.Builder(Main2.this);
            d.setTitle("警告!")
                    .setMessage("已註冊")
                    .setCancelable(false);
            d.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            d.show();
        }

    }
    public void send3(View view) {
        new Thread(new Runnable() {
            public void run() {
                new HttpAsyncTask3().sent(  MainActivity.allurl +"/hi",uid,psw);
            }
        }).start();
    }//send3
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
//                // 10. convert inputstream to string
            if (inputStream != null) {
                result = convertInputStreamToString(inputStream);
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
    public void cancel(View view) {
        finish();
    }
}
