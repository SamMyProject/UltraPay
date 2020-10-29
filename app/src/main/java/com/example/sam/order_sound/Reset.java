package com.example.sam.order_sound;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Sam on 2018/6/2.
 */

public class Reset extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void reset() {
        record.c=0;
        MainActivity.ans="wait...";
        record.s="";
        val.delay=0;
        App.s="";
        App.bin="";
        App.sbin="";
        record.sy=false;
        detect.isa=0;
        MainActivity.isRecording = false;
        record.read=false;
        MainActivity.errorcount=0;
        App.alter_to_stop="";
    }//reset

    public void restart() {
        if(MainActivity.mHandler!=null ) MainActivity.mHandler.removeMessages(0);
        MainActivity.message="";
        reset();
        MainActivity.isRecording = true;

        if(MainActivity.aud!=null&& MainActivity.aud.getState()==1){
            MainActivity.rec=null;
            MainActivity.aud.stop();
            MainActivity.aud.release();
            MainActivity.aud=null;
        } // if
        MainActivity.rec=new record();
        int size=AudioRecord.getMinBufferSize(44100,AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        System.out.println(size);
        MainActivity.aud=new AudioRecord(MediaRecorder.AudioSource.MIC, 44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT,88200*22);
        MainActivity.rec.execute();
        try {
            MainActivity.rec.gotit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//start

}
