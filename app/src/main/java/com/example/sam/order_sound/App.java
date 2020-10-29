package com.example.sam.order_sound;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Sam on 2018/2/2.
 */

public class App {
    private String mans = "";
    private static double[] xy;
    public static String alter_to_stop="";
    private static int location = 0, l2 = 0, p2 = 0, frenm = 0;
    private static double[] outData, kepfre = new double[8];
    static public double maxAmp = Double.MIN_VALUE, big = 0;
    static public double minAmp = Double.MAX_VALUE;
    static public detect d = new detect();
    static public dec toDic = new dec();
    static public String s = "", bin = "", sbin = "";
    public static boolean in = false, fre = false, is = false;

    public void putin(double[] kepfre) throws InterruptedException {
        double[] num = new double[32];
        for (int i = 0;i < kepfre.length; i++) {
            if (kepfre[i] == 438) num[13]++;
            else if (kepfre[i] == 439) num[14]++;
            else if (kepfre[i] == 440) num[15]++;
            else if (kepfre[i] == 423) num[0]++;
            else if (kepfre[i] == 424) num[1]++;
            else if (kepfre[i] == 425) num[2]++;
            else if (kepfre[i] == 426) num[3]++;
            else if (kepfre[i] == 427) num[4]++;
            else if (kepfre[i] == 429 || kepfre[i] == 428) num[5]++;
            else if (kepfre[i] == 430) num[6]++;
            else if (kepfre[i] == 431) num[7]++;
            else if (kepfre[i] == 432) num[8]++;
            else if (kepfre[i] == 433) num[9]++;
            else if (kepfre[i] == 434) num[10]++;
            else if (kepfre[i] == 436 || kepfre[i] == 435) num[11]++;
            else if (kepfre[i] == 437) num[12]++;
            else if (kepfre[i] == 441) num[16]++;
            else if (kepfre[i] == 442 || kepfre[i] == 443) num[17]++;
            else if (kepfre[i] == 444) num[18]++;
            else if (kepfre[i] == 445) num[19]++;
            else if (kepfre[i] == 446) num[20]++;
            else if (kepfre[i] == 447) num[21]++;
            else if (kepfre[i] == 448) num[22]++;
            else if (kepfre[i] == 450 || kepfre[i] == 449) num[23]++;
            else if (kepfre[i] == 451) num[24]++;
            else if (kepfre[i] == 452) num[25]++;
            else if (kepfre[i] == 453) num[26]++;
            else if (kepfre[i] == 454) num[27]++;
            else if (kepfre[i] == 455) num[28]++;
            else if (kepfre[i] == 456) num[29]++;
            else if (kepfre[i] == 457 || kepfre[i] == 458) num[30]++;
            else if (kepfre[i] == 459) num[31]++;

        }
        for (int i = 0; i < num.length; i++) {

            if (num[i] >= 4) {
                if (i == 0) {
                    s = s + "B";
                    bin = bin + "0000";
                } else if (i == 1) {
                    s = s + "C";
                    bin = bin + "0001";
                } else if (i == 2) {
                    s = s + "D";
                    bin = bin + "0010";
                } else if (i == 3) {
                    s = s + "E";
                    bin = bin + "0011";
                } else if (i == 4) {
                    s = s + "F";
                    bin = bin + "0100";
                } else if (i == 5) {
                    bin = bin + "0101";
                    s = s + "G";
                } else if (i == 6) {
                    bin = bin + "0110";
                    s = s + "H";
                } else if (i == 7) {
                    bin = bin + "0111";
                    s = s + "I";
                } else if (i == 8) {
                    bin = bin + "1000";
                    s = s + "J";
                } else if (i == 9) {
                    bin = bin + "1001";
                    s = s + "K";
                } else if (i == 10) {
                    bin = bin + "1010";
                    s = s + "L";
                } else if (i == 11) {
                    bin = bin + "1011";
                    s = s + "M";
                } else if (i == 12) {
                    bin = bin + "1100";
                    s = s + "N";
                } else if (i == 13) {
                    bin = bin + "1101";
                    s = s + "O";
                } else if (i == 14) {
                    bin = bin + "1110";
                    s = s + "P";
                } else if (i == 15) {
                    bin = bin + "1111";
                    s = s + "Q";
                }
                if (bin.length() == 8) {
                    System.out.println(bin);
                    char c = (char) toDic.result(bin);
                    record.s = record.s + String.valueOf(c);
                    mans = String.valueOf(c);
                    sbin = sbin + bin + " ";
                    bin = "";
                }
            }
        }
        if ((!mans.endsWith("#") && s.length() > 1)) {
            if(MainActivity.errorcount>10){
                coupontest.handelmes.sendEmptyMessage(0);
            }//if
            if(mans.matches("[0-9]")||mans.matches("[a-zA-Z]")){
                MainActivity.message = MainActivity.message + mans;
            }//if
            MainActivity.errorcount++;
            MainActivity.ans = mans;
            System.out.println(mans);
            if (mans.equals("a")) {
                val.choose = "a";
            } //  if
            record.s = "";
            sbin = "";
            bin = "";
            s = "";
        }//if
        else if ((mans.endsWith("#") && s.length() > 1)||alter_to_stop.equals("#")) {
            System.out.println(mans);
            record.stopped = true;
            if(alter_to_stop.equals("#"))MainActivity.message="Press \"cancel\" Try again!";
            MainActivity.isRecording = false;
            record.read = false;
            record.s = "";
            sbin = "";
            bin = "";
            s = "";
            MainActivity.reset.reset();
            //Message message = MainActivity.mHandler.obtainMessage();
            //MainActivity.mHandler.sendEmptyMessage(0);
            coupontest.handelmes.sendEmptyMessage(0);
            MainActivity.ans = mans;
            alter_to_stop="";
        }//if

    }

    public int doApp(double[] data) throws Exception, IOException {
        double[] kepfre = new double[8];
        FFT fft = new FFT();
        // TODO Auto-generated method stub
        double[] rawData = data;//audioTest.getByteArray();
        int length = rawData.length;
        //initialize parameters for FFT
        int WS = 1024; //WS = window size
        int OF = 2;    //OF = overlap factor
        int windowStep = WS / OF;

        //calculate FFT parameters
        double SR = 44100;//audioTest.getSR();
        double time_resolution = WS / SR;
        double frequency_resolution = SR / WS;
        double highest_detectable_frequency = SR / 2.0;
        double lowest_detectable_frequency = 5.0 * SR / WS;

        //initialize plotData array
        int nX = (length - 0) / windowStep;
//            int nY = WS;
        int nY = WS / 2 + 1;
        double[][] plotData = new double[nX][nY];
        //apply FFT and find MAX and MIN amplitudes

        double amp_square;
        int p1 = 0;
        double[] inputImag = new double[length];

        for (int i = 0; i < nX; i++) {
            Arrays.fill(inputImag, 0.0);
            double[] WS_array = fft.fft(Arrays.copyOfRange(rawData, i * windowStep, i * windowStep + WS), inputImag, true);
            for (int j = 418; j < nY; j++) {
                amp_square = (WS_array[2 * j] * WS_array[2 * j]) + (WS_array[2 * j + 1] * WS_array[2 * j + 1]);
                if (amp_square == 0.0) {
                    plotData[i][j] = amp_square;
                } else {
                    plotData[i][j] = 10 * Math.log10(amp_square);
                }

                //find MAX and MIN amplitude
                if (plotData[i][j] > big) {
                    big = plotData[i][j];
                    if (big > maxAmp) maxAmp = big;
                    location = j;
                } else if (plotData[i][j] < minAmp) {
                    minAmp = plotData[i][j];

                }

            }

            kepfre[i] = location;
            if (location == 418) {
                frenm++;
            }

            if (location == 418 && l2 != 418 && frenm < 10) frenm = 0;

            if (location == 418 && frenm == 10) {
                fre = true;
            }
            if (fre == true && big < maxAmp / 2 && in == false) {
                in = true;
                p2 = i;
            }
            if (fre == true && (location != 418) && in == false) {
                in = true;
                p2 = i;
            }
            big = 0;
            l2 = location;
        }
        if (MainActivity.isRecording) putin(kepfre);//=============================================

        return p2;

    }

}
