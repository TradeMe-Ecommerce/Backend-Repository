package com.example.demo.DTO;

import java.util.Date;

public class MessageDTO {
     private long id;
     private long roomId;
     private String message;
     private String date;

     public MessageDTO() {

     }

    public MessageDTO(long id, long roomId, String message, String date) {
        this.id = id;
        this.roomId = roomId;
        this.message = message;
        this.date = date;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
