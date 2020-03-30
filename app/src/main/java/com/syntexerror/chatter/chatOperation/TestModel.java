package com.syntexerror.chatter.chatOperation;

public class TestModel {

    String uid , msg , serverTime ;

    public TestModel() {
    }

    public TestModel(String uid, String msg, String serverTime) {
        this.uid = uid;
        this.msg = msg;
        this.serverTime = serverTime;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }
}
