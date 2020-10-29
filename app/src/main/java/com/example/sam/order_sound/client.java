package com.example.sam.order_sound;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class client extends Thread {
    private Thread thread;                //執行緒
    private Socket clientSocket;        //客戶端的socket
    private BufferedWriter bw;            //取得網路輸出串流
    private BufferedReader br;            //取得網路輸入串流
    private String tmp;                    //做為接收時的緩存

    //連結socket伺服器做傳送與接收
        String item = "" ;
        String money = "" ;
        String name = "" ;

        public  client(String items,String money, String name){
            this.item = items ;
            this.money = money ;
            this.name = name ;
        }

        @Override
        public void run() {

            // TODO Auto-generated method stub
            BufferedWriter bw;            //取得網路輸出串流
            BufferedReader br;            //取得網路輸入串流

            try{
                // IP為Server端
                InetAddress serverIp = InetAddress.getByName("192.168.1.9");
                int serverPort = 36000;
                clientSocket = new Socket(serverIp, serverPort);
                //取得網路輸出串流
                bw = new BufferedWriter( new OutputStreamWriter(clientSocket.getOutputStream()));
                // 取得網路輸入串流
                br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // 當連線後
                if (clientSocket.isConnected()) {
                    bw.write(name+ "\n");
                    bw.write(item+ "\n");
                    bw.write(money+ "\n");
                    bw.flush();
                }
            }catch(Exception e){
                //當斷線時會跳到catch,可以在這裡寫上斷開連線後的處理
                e.printStackTrace();
                Log.e("text","Socket連線="+e.toString());
//                finish();    //當斷線時自動關閉房間
            }

    };
}