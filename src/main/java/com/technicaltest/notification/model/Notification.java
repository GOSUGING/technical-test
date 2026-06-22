package com.technicaltest.notification.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Modelo que representa una notificación enviada
 */
public class Notification {
    private String userId;
    private String message;
    private String channel;
    private LocalDateTime timestamp;

    public Notification(String userId, String message, String channel) {
        this.userId = userId;
        this.message = message;
        this.channel = channel;
        this.timestamp = LocalDateTime.now();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("Notification{userId='%s', message='%s', channel='%s', timestamp=%s}",
                userId, message, channel, timestamp.format(formatter));
    }
}
