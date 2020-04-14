package com.syntexerror.chatter.chatOperation;

public class chatHistoryModel {
    String user1 , user2 , frindShipId ;
    long timestamp  ;

    public chatHistoryModel() {
    }

    public chatHistoryModel(String user1, String user2, String frindShipId, long timestamp) {
        this.user1 = user1;
        this.user2 = user2;
        this.frindShipId = frindShipId;
        this.timestamp = timestamp;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getFrindShipId() {
        return frindShipId;
    }

    public void setFrindShipId(String frindShipId) {
        this.frindShipId = frindShipId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
