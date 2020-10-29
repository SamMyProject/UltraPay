package com.example.sam.order_sound;

/**
 * Created by Sam on 2018/2/2.
 */

import android.content.Context;
import android.os.AsyncTask;

//用doinbackground 將需要長時間運算的工作放裡面,在class n裡開啟多個執行緒(new record()),完成同時運算處理
//將一些非必要的變數設成非共用
public class record extends AsyncTask<Void, Void, Void> {
    public static boolean stopped = false, in = false, sy = false, can = false;
    public static Thread Run = null;
    public static boolean read = false;
    public static detect d = new detect();
    public static double c = 0;
    public static Context mContext;
    private static double[] xy;//非
    private static double[] outData;//非
    public static short[] sh;//非
    public static fol env = new fol(44100);//非
    public static String s = "";
    public static int b = 3072;

    public void gotit() {
        MainActivity.aud.startRecording();
        MainActivity.aud.setPositionNotificationPeriod(b * 2);
        MainActivity.aud.setRecordPositionUpdateListener(new n(this));
    }

    public double[] FIR(double[] signal) {
        double[] outData = new double[signal.length], xy = new double[val.b.length];
        ;
        for (int i = 0; i < signal.length; i++) {

            System.arraycopy(xy, 0, xy, 1, xy.length - 1);
            xy[0] = signal[i];

            double y = 0;
            for (int j = 0; j < val.b.length; j++) {
                y += val.b[j] * xy[j];
            }

            outData[i] = y;
        }
        return outData;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        fol env = new fol(44100);//非
        short[] sh = new short[b], data = new short[b];
        boolean can = false;
        double count = 0, hold = 0, k = 0;
        double[] r = new double[b], v = new double[b];
        detect d = new detect();
        App ap = new App();
        while (MainActivity.isRecording) {
            if (MainActivity.aud != null && read && MainActivity.aud.getState() == 1) {
                if (MainActivity.isRecording) {
                    int x = MainActivity.aud.read(sh, 0, b);
                    MainActivity.aud.read(data, 0, 456);
                } else return null;

                for (int i = 0; i < b; i++) {
                    r[i] = sh[i];
                } // for
                if (detect.isa > 0) c++;
                if (c <= 8) {
                    r = FIR(r);
                }
                if (c <= 8) v = env.calculateEnvelope(r);

                try {
                    if (sy == true) ap.doApp(r);
                    if (sy == false) d.fre(r);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                label2:
                for (int j = 0; j < b && sy == false && c <= 8; j++) {
                    if (v[j] > count) {
                        count = v[j];
                        k = count * 0.3;
                    } else if (v[j] < k) {
                        hold = j;
                        can = true;
                        break label2;
                    }

                }
                if (c <= 8) k = count * 0.3;

                label1:
                for (int j = (int) hold + 1; can == true && detect.isa >= 5 && sy == false && in == true && j < v.length; j++) {
                    if (v[j] < k) {
                        System.out.println(j);
                        MainActivity.aud.read(data, 0, j);
                        can = false;
                        sy = true;
                        detect.isa = 1;
                        break label1;
                    }
                }
            }//if
            count = 0;
            hold = 0;
            k = 0;
        }//while
        return null;
    }
}
