package com.example.sam.order_sound;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
 
public class Http_Post extends Service {
 
    String strTxt=null;
    String postUrl=null;
    String strResult=null;
    String CardID = null ;
    String getStrResult ;
    public String Post(final String CardID, final String StrTxt, final String PostUrl){
        this.strTxt = StrTxt;
        this.postUrl = PostUrl;
        this.CardID = CardID ;
                //建立HttpClient物件
                InputStream inputStream = null;
                String result = "";
//                HttpPost httpPost = new HttpPost( PostUrl);
//                HttpClient httpclient = new DefaultHttpClient();

                HttpParams httpParameters = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(httpParameters, 10000);
                HttpConnectionParams.setSoTimeout(httpParameters, 10000+12000);

                DefaultHttpClient  client = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(postUrl);
                httppost.setParams(httpParameters);
                // 注意：httpPost方法時，傳遞變量必須用NameValuePair[]數據存儲，通過httpRequest.setEntity()方法來發出HTTP請求

                JSONArray json_arr = new JSONArray();
                JSONObject t = new JSONObject() ;
                try {
                    t.accumulate( "CardID",  CardID) ;
                    t.accumulate( "discount",   StrTxt) ;
                    String json = "";
                    // 4. convert JSONObject to JSON to String
                    json = t.toString();
                    Log.d("json: ", json);
//                String result ="" ;
                // 取得HTTP response
//
                    StringEntity se = new StringEntity(json, "BIG-5");
                    // 6. set httpPost Entity
                    httppost.setEntity(se);
                    // 7. Set some headers to inform server about the type of the content
                    httppost.setHeader("Accept", "application/json;charset=BIG-5");
                    httppost.setHeader("Content-type", "application/json");

                    // 8. Execute POST request to the given URL
//                    Log.d("bbb", "hh");

                    HttpResponse httpResponse = client.execute(httppost);
                    inputStream = httpResponse.getEntity().getContent();
//                // 10. convert inputstream to string
//                    Log.d("bbbb", result);

                    if(inputStream != null)
                    {
                        result = convertInputStreamToString(inputStream);
                        Log.d("echoHttp_Post: ", result);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }  catch (JSONException e) {
                    e.printStackTrace();
                }  catch (Exception e) {
                Log.e("InputStream", e.getLocalizedMessage());
                }

                getStrResult = result ;

                    //判斷Http Server是否回傳OK(200)
//                    if(httpResponse.getStatusLine().getStatusCode() == 200){
//                        //將Post回傳的值轉為String，將轉回來的值轉為UTF8，否則若是中文會亂碼
//                        strResult = EntityUtils.toString(httpResponse.getEntity(),HTTP.UTF_8);
//

//                        //使用MainActivity的static handler來丟Message
//                        coupontest.handler.sendMessage(msg);
//                    }

                return getStrResult ;
    }


    public String PostGetHave(final String CardID, final String PostUrl){
        this.postUrl = PostUrl;
        this.CardID = CardID ;
        Log.d("posthave", "PostGetHave: " + CardID );
        //建立HttpClient物件
        InputStream inputStream = null;
        String result = "";
//                HttpPost httpPost = new HttpPost( PostUrl);
//                HttpClient httpclient = new DefaultHttpClient();

        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, 10000);
        HttpConnectionParams.setSoTimeout(httpParameters, 10000+12000);

        DefaultHttpClient  client = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(postUrl);
        httppost.setParams(httpParameters);
        // 注意：httpPost方法時，傳遞變量必須用NameValuePair[]數據存儲，通過httpRequest.setEntity()方法來發出HTTP請求


        try {

            String msg = CardID.toString();
            // 4. convert JSONObject to JSON to String

            Log.d("CardID: ", msg);
//                String result ="" ;
            // 取得HTTP response
//
            StringEntity se = new StringEntity(msg, "BIG-5");
            // 6. set httpPost Entity
            httppost.setEntity(se);
            // 7. Set some headers to inform server about the type of the content
            httppost.setHeader("Accept", "application/json;charset=BIG-5");
            httppost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
//                    Log.d("bbb", "hh");

            HttpResponse httpResponse = client.execute(httppost);
            inputStream = httpResponse.getEntity().getContent();
//                // 10. convert inputstream to string
//                    Log.d("bbbb", result);

            if(inputStream != null)
            {
                result = convertInputStreamToString(inputStream);
                Log.d("Http_PostGET: ", result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }  catch (Exception e) {
            Log.e("InputStream", e.getLocalizedMessage());
        }

        getStrResult = result ;
        return getStrResult ;
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
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public String getResult(){
        return this.getStrResult ;
    }
}