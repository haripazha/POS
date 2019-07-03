package com.example.acer.pos_user;

public class localhost {

    /*String ip_address = "pos.pascalineerp.com" +
     "";*/
    String ip_address = "192.168.42.31";

    String localhost = "http://"+ip_address+"/";


    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getLocalhost() {
        return localhost;
    }

    public void setLocalhost(String localhost) {
        this.localhost = localhost;
    }
}
