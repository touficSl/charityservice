package com.service.charity.service;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;


@Service
public class NotifServiceImpl implements NotifService {

    public String sendnotification(String token, String title, String body) {
        try {
            Message message = Message.builder()
                    .setToken(token)
                    .setNotification(Notification.builder()
                            .setTitle(title)
                            .setBody(body)
                            .build())
                    .build();

            String response = FirebaseMessaging.getInstance().send(message);
            return "Notification sent successfully: " + response;
        } catch (Exception e) {
            return "Failed to send notification: " + e.getMessage();
        }
    }

}
