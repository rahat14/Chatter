package com.syntexerror.chatter.models;

public class UserModel {
    String username  , name , uid , profileLink , joinTimeStamp , isOnline   ;

    public UserModel() {
    }

    public UserModel(String username, String name, String uid, String profileLink, String joinTimeStamp, String isOnline) {
        this.username = username;
        this.name = name;
        this.uid = uid;
        this.profileLink = profileLink;
        this.joinTimeStamp = joinTimeStamp;
        this.isOnline = isOnline;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getProfileLink() {
        return profileLink;
    }

    public void setProfileLink(String profileLink) {
        this.profileLink = profileLink;
    }

    public String getJoinTimeStamp() {
        return joinTimeStamp;
    }

    public void setJoinTimeStamp(String joinTimeStamp) {
        this.joinTimeStamp = joinTimeStamp;
    }

    public String getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }
}
