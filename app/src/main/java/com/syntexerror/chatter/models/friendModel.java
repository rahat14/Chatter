package com.syntexerror.chatter.models;

public class friendModel {
    String friendUserID, friendShipID ;

    public friendModel() {
    }

    public friendModel(String friendUserID, String friendShipID) {
        this.friendUserID = friendUserID;
        this.friendShipID = friendShipID;
    }

    public String getFriendUserID() {
        return friendUserID;
    }

    public String getFriendShipID() {
        return friendShipID;
    }

    public void setFriendUserID(String friendUserID) {
        this.friendUserID = friendUserID;
    }

    public void setFriendShipID(String friendShipID) {
        this.friendShipID = friendShipID;
    }
}
