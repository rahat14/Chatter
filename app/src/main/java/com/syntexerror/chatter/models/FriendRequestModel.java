package com.syntexerror.chatter.models;

public class FriendRequestModel {
    String  requsetedFriendUsername, requsetedFriendUid, timestamp,  requsetedFriendProfileLink, postId;

    public FriendRequestModel() {
    }

    public FriendRequestModel(String requsetedFriendUsername, String requsetedFriendUid, String timestamp, String requsetedFriendProfileLink, String postId) {
        this.requsetedFriendUsername = requsetedFriendUsername;
        this.requsetedFriendUid = requsetedFriendUid;
        this.timestamp = timestamp;
        this.requsetedFriendProfileLink = requsetedFriendProfileLink;
        this.postId = postId;
    }

    public String getRequsetedFriendUsername() {
        return requsetedFriendUsername;
    }

    public void setRequsetedFriendUsername(String requsetedFriendUsername) {
        this.requsetedFriendUsername = requsetedFriendUsername;
    }

    public String getRequsetedFriendUid() {
        return requsetedFriendUid;
    }

    public void setRequsetedFriendUid(String requsetedFriendUid) {
        this.requsetedFriendUid = requsetedFriendUid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getRequsetedFriendProfileLink() {
        return requsetedFriendProfileLink;
    }

    public void setRequsetedFriendProfileLink(String requsetedFriendProfileLink) {
        this.requsetedFriendProfileLink = requsetedFriendProfileLink;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}