package com.example.sam.order_sound;

import android.media.AudioRecord;

import java.util.ArrayList;

/**
 * Created by Sam on 2018/2/4.
 */

public class n implements AudioRecord.OnRecordPositionUpdateListener
        {
            public static ArrayList<short[]> al=new ArrayList<short[]>();
            public static boolean b=false;


            n(record paramm)
            {
            }

            @Override
            public void onMarkerReached(AudioRecord recorder) {

            }

            @Override
            public void onPeriodicNotification(AudioRecord recorder) {
                if(MainActivity.aud!=null&& MainActivity.aud.getState()==1) record.read=true;
                else record.read=false;
                if(!MainActivity.isRecording){
                    if(MainActivity.aud!=null&& MainActivity.aud.getState()==1){
                        MainActivity.rec=null;
                        MainActivity.aud.stop();
                        MainActivity.aud.release();
                        MainActivity.aud=null;
                        record.stopped=false;
                        record.sy=false;
                        detect.isa=0;
                        record.read=false;
                    }
                }

            }
        }
