package com.example.sam.order_sound;

public class CouponClass {
        private int type = 1 ;
        private String name;
        private String time;
        private String limit ;
        private String coupon ;
        public CouponClass(){}
        public CouponClass( String name,String time, String limit) {
            this.limit = limit ;
            this.name = name;
            this.time = time;
            this.coupon = "" ;
        }

        public CouponClass( String name,String coupon,String time,String limit) {
            this.limit = limit ;
            this.name = name;
            this.time = time;
            this.coupon = coupon ;
        }
        public int getType(){
            return type;
        }
        public void setType(int type){
            this.type = type;
        }
        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getTime(){
            return time;
        }
        public void setTime(String time){
            this.time = time;
        }
        public String getLimit(){ return limit ; }
        public void setLimit( String _limit ) { this.limit = _limit ; }

        public String toString(){
          return this.name + "折\n額滿" + this.getLimit() + "可使用\n使用期限：" + this.getTime() ;
        }
    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }
}
