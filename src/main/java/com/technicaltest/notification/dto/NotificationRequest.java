package com.technicaltest.notification.dto;

/**
 * DTO para solicitud de envío de notificación
 */
public class NotificationRequest {
    private String userId;
    private String message;
    private String channel; // "email" o "sms"

    // Constructor vacío (necesario para JSON deserialization)
    public NotificationRequest() {
    }

    public NotificationRequest(String userId, String message, String channel) {
        this.userId = userId;
        this.message = message;
        this.channel = channel;
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
}
