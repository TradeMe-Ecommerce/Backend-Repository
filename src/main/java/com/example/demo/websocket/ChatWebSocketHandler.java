package com.example.demo.websocket;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.demo.DTO.MessageDTO;
import com.example.demo.entity.Message;
import com.example.demo.entity.Room;
import com.example.demo.entity.User;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.DateFormatter;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final Map<Long, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MessageRepository messageRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Long userId = (Long) session.getAttributes().get("userId");

        if (userId != null) {
            sessions.put(userId, session);
            System.out.println("WebSocket connection established for user: " + userId);
        } else {
            session.close(CloseStatus.POLICY_VIOLATION.withReason("User not authenticated"));
            System.out.println("WebSocket connection attempt rejected. Authentication missing.");
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        Long senderId = (Long) session.getAttributes().get("userId");
        if (senderId == null) return;

        try {
            MessageDTO messageDTO = objectMapper.readValue(textMessage.getPayload(), MessageDTO.class);
            long roomId = messageDTO.getRoomId();

            Room room = roomRepository.findById(roomId).orElse(null);
            if (room == null) {
                System.err.println("Error: Room with ID " + roomId + " not found.");
                sendErrorMessage(session, "Room not found");
                return;
            }

            User sender = userRepository.findById(senderId).orElse(null);
            if (sender == null || (room.getUser().getId() != senderId && room.getPeerUser().getId() != senderId)) {
                System.err.println("Error: User " + senderId + " is not a participant in room " + roomId);
                sendErrorMessage(session, "Access denied to this room");
                return;
            }

            long recipientId = (room.getUser().getId() == senderId) 
                ? room.getPeerUser().getId() 
                : room.getUser().getId();

            Message savedMessage = saveMessage(sender, room, messageDTO.getMessage());

            MessageDTO responseDTO = new MessageDTO();
            responseDTO.setId(savedMessage.getId());
            responseDTO.setRoomId(roomId);
            responseDTO.setMessage(savedMessage.getMessage());
            responseDTO.setDate(DateFormatter.convertDateToString(savedMessage.getDate()));

            String responsePayload = objectMapper.writeValueAsString(responseDTO);

            WebSocketSession recipientSession = sessions.get(recipientId);
            if (recipientSession != null && recipientSession.isOpen()) {
                try {
                    recipientSession.sendMessage(new TextMessage(responsePayload));
                    System.out.println("Message sent to recipient: " + recipientId);
                } catch (IOException e) {
                    System.err.println("Error sending message to recipient " + recipientId + ": " + e.getMessage());
                    sessions.remove(recipientId);
                }
            } else {
                System.out.println("Recipient " + recipientId + " is not connected. Message saved in DB.");
            }

            // Send confirmation to sender
            try {
                session.sendMessage(new TextMessage(responsePayload));
                System.out.println("Confirmation sent to sender: " + senderId);
            } catch (IOException e) {
                System.err.println("Error sending confirmation to sender " + senderId + ": " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
            e.printStackTrace();
            sendErrorMessage(session, "Error processing message");
        }
    }

    private Message saveMessage(User sender, Room room, String content) {
        try {
            Message message = new Message();
            message.setRoom(room);
            message.setMessage(content);
            message.setDate(new Date());

            room.setUser(sender);

            Message savedMessage = messageRepository.save(message);
            System.out.println("Message saved with ID: " + savedMessage.getId());
            return savedMessage;
        } catch (Exception e) {
            System.err.println("Error saving message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error saving message", e);
        }
    }

    private void sendErrorMessage(WebSocketSession session, String errorMessage) {
        try {
            MessageDTO errorDTO = new MessageDTO();
            errorDTO.setId(-1);
            errorDTO.setRoomId(-1);
            errorDTO.setMessage("ERROR: " + errorMessage);
            errorDTO.setDate(DateFormatter.convertDateToString(new Date()));

            String errorPayload = objectMapper.writeValueAsString(errorDTO);
            session.sendMessage(new TextMessage(errorPayload));
        } catch (Exception e) {
            System.err.println("Error sending error message: " + e.getMessage());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Long userId = (Long) session.getAttributes().get("userId");
        if (userId != null) {
            sessions.remove(userId);
            System.out.println("WebSocket connection closed for user: " + userId + " with status: " + status);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        Long userId = (Long) session.getAttributes().get("userId");
        System.err.println("WebSocket transport error for user " + userId + ": " + exception.getMessage());

        if (userId != null) {
            sessions.remove(userId);
        }
        if (session.isOpen()) {
            session.close(CloseStatus.SERVER_ERROR);
        }
    }
}