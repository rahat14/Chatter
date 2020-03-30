package com.syntexerror.chatter.models;

public class FriendRequestModel {
    String username , uid , timestamp  , userProfileLink ;

    public FriendRequestModel() {
    }

    public FriendRequestModel(String username, String uid, String timestamp, String userProfileLink) {
        this.username = username;
        this.uid = uid;
        this.timestamp = timestamp;
        this.userProfileLink = userProfileLink;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserProfileLink() {
        return userProfileLink;
    }

    public void setUserProfileLink(String userProfileLink) {
        this.userProfileLink = userProfileLink;
    }
}
