package com.example.demo.repository;

import com.example.demo.entity.Room;

import java.util.List;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByUserId(Long userId);
    boolean existsByUserIdAndPeerUserId(long userId, long peerUserId);
    List<Room> findByUserOrPeerUser(User user, User peerUser);
}
