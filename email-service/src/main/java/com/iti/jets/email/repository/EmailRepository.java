package com.iti.jets.email.repository;

import com.iti.jets.email.dto.UserRegisterEventDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class EmailRepository {

    private final List<UserRegisterEventDTO> notifications =
            new CopyOnWriteArrayList<>();

    public void save(UserRegisterEventDTO notification) {
        notifications.add(notification);
    }

    public List<UserRegisterEventDTO> findAll() {
        return notifications;
    }

    public UserRegisterEventDTO findLatest() {

        if (notifications.isEmpty()) {
            return null;
        }

        return notifications.getLast();
    }
}