package com.example.sam.order_sound;

/**
 * Created by Sam on 2018/2/2.
 */
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class detect {
    private static ArrayList seqfr = new ArrayList();
    public static List wor = new ArrayList();
    private static boolean in=false,o=true;
    public static int isa=0;
    public detect() {}//
    private int g = 44100;
    private String s="";
    public static int ii=0;
    public void fre(double[] x) throws IOException, URISyntaxException {
        //System.out.println(x.length);
//		double[] x=new double[3528];
        int i2 = 0;
        int i3 = 18000;
        int i = 0;
        double d = 0.0d;


        for (int j = 0; j < x.length; j++) {
            x[j] = x[j] * ((0.42659d - (0.49659d * Math.cos((6.283185307179586d * ((double) j)) / ((double) x.length)))) + (0.076849d * Math.cos((12.566370614359172d * ((double) j)) / ((double) x.length))));
        }//
        while (i3 < 20000) {
            f fVar = new f((float) this.g, (float) i3, x);
            fVar.a();
            double c = fVar.c();
//            System.out.println(c);
            if (c > d) {
                i = i3;
                d = c;
            }
            int i4 = c > ((double) val.c) ? i2 + 1 : i2;
            if (i4 > 1) {
                val.c += 3000;
                i3 = i;
                i = i4;
                if (i2 == 1 && c > ((double) val.c)) {
                    ii=i3;
                    Map(i3);
                    return;
                }
                break;
            }

            i3=i3+50;
            i2 = i4;
        }

        i3 = i;
        i = i2;
        if (i == 1) {
            Map(i3);
        }


    }// fre

    private void Map(int freq) throws IOException, URISyntaxException{
        switch (freq)
        {
            case 18000:
                isa++;
//	      if(in==false) {
                wor=new ArrayList<String>();
                s="";
//	      }
                record.in=true;
                wor.add("A");
                in=true;
                break;
//            case 18050:
//                wor.add("B");
//	    	System.out.println("B 0 0 0 0 1");
//                break;
//            case 18100:
//                wor.add("C");
//	    	System.out.println("C 0 0 0 1 0");
//                break;
//            case 18150:
//                wor.add("D");
//	    	System.out.println("D 0 0 0 1 1");
//                break;
//            case 18200:
//                wor.add("E");
//	    	System.out.println("E 0 0 1 0 0");
//                break;
//            case 18250:
//                wor.add("F");
//	    	System.out.println("F 0 0 1 0 1");
//                break;
//            case 18300:
//                wor.add("G");
//	    	System.out.println("G 0 0 1 1 0");
//                break;
//            case 18350:
//                wor.add("H");
//	    	System.out.println("H 0 0 1 1 1");
//                break;
//            case 18400:
//                wor.add("I");
//	    	System.out.println("I 0 1 0 0 0");
//                break;
//            case 18450:
//                wor.add("J");
//	    	System.out.println("J 0 1 0 0 1");
//                break;
//            case 18500:
//                wor.add("K");
//	    	System.out.println("K 0 1 0 1 0");
//                break;
//            case 18550:
//                wor.add("L");
//	    	System.out.println("L 0 1 0 1 1");
//                break;
//            case 18600:
//                wor.add("M");
//	    	System.out.println("M 0 1 1 0 0");
//                break;
//            case 18650:
//                wor.add("N");
//	    	System.out.println("N 0 1 1 0 1");
//                break;
//            case 18700:
//                wor.add("O");
//	    	System.out.println("O 0 1 1 1 0");
//                break;
//            case 18750:
//                wor.add("P");
//	    	System.out.println("P 0 1 1 1 1");
//                break;
//            case 18800:
//                wor.add("Q");
//	    	System.out.println("Q 1 0 0 0 0");
//                break;
//            case 18850:
//                wor.add("R");
////	    	System.out.println("R 1 0 0 0 1");
//                break;
//            case 18900:
//                wor.add("S");
//                System.out.println("S 1 0 0 1 0");
//                break;
//            case 18950:
//                wor.add("T");
//                System.out.println("T 1 0 0 1 1");
//                break;
//            case 19000:
//                wor.add("U");
//                System.out.println("U 1 0 1 0 0");
//                break;
//            case 19050:
//                wor.add("V");
//                System.out.println("V 1 0 1 0 1");
//                break;
//            case 19100:
//                wor.add("W");
//                System.out.println("W 1 0 1 1 0");
//                break;
//            case 19150:
//                wor.add("X");
//                System.out.println("X 1 0 1 1 1");
//                break;
//            case 19200:
//                wor.add("Y");
//                System.out.println("Y 1 1 0 0 0");
//                break;
//            case 19250:
//                wor.add("Z");
//                System.out.println("Z 1 1 0 0 1");
//                break;
//            case 19300:
//                wor.add("a");
//                System.out.println("a 1 1 0 1 0");
//                break;
//            case 19350:
//                wor.add("b");
//                System.out.println("b 1 1 0 1 1");
//                break;
//            case 19400:
//                wor.add("c");
//                System.out.println("c 1 1 1 0 0");
//                break;
//            case 19450:
//                wor.add("d");
//                System.out.println("d 1 1 1 0 1");
//                break;
//            case 19500:
//                wor.add("e");
//                System.out.println("e 1 1 1 1 0");
//                break;
//            case 19550:
//                wor.add("f");
//                System.out.println("f 1 1 1 1 1");
//                break;
//            case 19600:
//                wor.add("g");
//                System.out.println("g");
//                break;
//            case 19650:
//                wor.add("h");
//                System.out.println("h");
//                break;
//            case 19700:
//                wor.add("i");
//                System.out.println("i");
//                break;
//            case 19750:
//                wor.add("j");
//                System.out.println("j");
//                break;
//            case 19800:
//                wor.add("k");
//                System.out.println("k");
//                break;
//            case 19850:
//                wor.add("l");
//                System.out.println("l");
//                break;
//            case 19900:
//                wor.add("i");
//                System.out.println("i");
//                break;
//            case 19950:
//                wor.add("m");
//                System.out.println("m");
//                break;
//            default:
//                o=false;
//                break;
        }
//		 for(int i=0;i<wor.size()&&o==true;i++){

//        if(o==true&&wor.size()>0){
//            if(wor.size()>=5)wor.remove(0);
//
//            s=s+wor.get(wor.size()-1);
//            if(cheak.che(s)&&o==true){
//                wor.clear();
////	   			    record.stopped=true;
//            }//if
//        }//if

//
        o=true;
        //s="";
    }//
}
