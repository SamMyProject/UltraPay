package com.example.sam.order_sound;



public class item {
    public String name;
    public String price;
    public int num = 0 ;

    public item(String name, String price, int num) {
        this.name = name;
        this.num = num ;
        this.price = price;

    }

    public String getName() {
        return name;
    }

    public int getnum() {
        return num;
    }

    public String getPrice() {
        return price;
    }


    public void setNum(int num){

        this.num = num ;
    }

}
