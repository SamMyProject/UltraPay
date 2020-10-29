package com.example.sam.order_sound;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import java.util.Map;
import java.util.Random;

/**
 * Created by Sam on 2018/7/31.
 */

public class StoreData extends AppCompatActivity {

    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(5);
        char tempChar;
        for (int i = 0; i < randomLength; i++) {
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }

    public void saveData(SharedPreferences settings, String data) {
        String key = "";
        key = random();
        while (settings.contains(key)) {
            key = random();
        }//
        settings.edit().putString(key, data).commit();
    }//

    public void removeData(SharedPreferences settings, String key) {
        settings.edit().remove(key).apply();
    }//

    public void getAllValues(SharedPreferences settings) {
        Map<String, ?> allContent = settings.getAll();
        Clist.orders.clear();
        for (Map.Entry<String, ?> entry : allContent.entrySet()) {
            Clist.orders.add(entry.getValue().toString());
            Clist.key.add(entry.getKey().toString());
        }
    }//
}
