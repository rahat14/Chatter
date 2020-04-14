package com.syntexerror.chatter.chatOperation;

public class chatMsgModel {
    String msg , msg_id , uid    ;
    Long time ;


    public chatMsgModel() {
    }


    public chatMsgModel(String msg, String msg_id, String uid, Long time) {
        this.msg = msg;
        this.msg_id = msg_id;
        this.uid = uid;
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
