package com.example.demo.DTO;

import java.util.List;

public class RoomDTO {
    private long id;
    private long userId;
    private String userName;
    private List<MessageDTO> messages;
    private long peerUserId;
    private String peerUserName;


    public RoomDTO() {
    }
    public RoomDTO(long id, long userId, String userName, List<MessageDTO> messages, long peerUserId, String peerUserName) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.messages = messages;
        this.peerUserId = peerUserId;
        this.peerUserName = peerUserName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> messages) {
        this.messages = messages;
    }

    public long getPeerUserId() {
        return peerUserId;
    }

    public void setPeerUserId(long peerUserId) {
        this.peerUserId = peerUserId;
    }

    public String getPeerUserName() {
        return peerUserName;
    }

    public void setPeerUserName(String peerUserName) {
        this.peerUserName = peerUserName;
    }
}
