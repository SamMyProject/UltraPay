package com.example.sam.order_sound;

/**
 * Created by Sam on 2018/2/2.
 */

public class epd {
    public static double[] sig=null;
    public static int x=0;
    public static int[] y=new int[16];
    public epd(double[] sid){sig=sid;}

    public int[] doepd(){
        for(int i=0;i<16;i++){
            for(int j=(i*512/2);j<512/2+(i*512/2)-1;j++){
                if((sig[j]>=0&&sig[j+1]<0)||(sig[j]<0&&sig[j+1]>=0)){
                    x++;
                }
            }
            System.out.println(x);
            y[i]=x;
            x=0;
        }
        return y;
    }
}
