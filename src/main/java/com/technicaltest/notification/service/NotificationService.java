package com.technicaltest.notification.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.technicaltest.notification.model.Notification;
import com.technicaltest.notification.provider.NotificationProvider;
import com.technicaltest.notification.provider.NotificationProviderFactory;

/**
 * Servicio para gestionar notificaciones
 * Responsable de:
 * - Validar parámetros
 * - Seleccionar el proveedor correcto
 * - Guardar historial
 */
@Service
public class NotificationService {
    
    // Historial en memoria (en producción, se guardaría en BD)
    private final List<Notification> notificationHistory = new ArrayList<>();

    /**
     * Envía una notificación y la guarda en el historial
     * 
     * @param userId ID del usuario (obligatorio)
     * @param message Mensaje a enviar (obligatorio)
     * @param channel Canal: "email" o "sms" (obligatorio)
     * @return true si se envió exitosamente, false en caso contrario
     * @throws IllegalArgumentException si algún parámetro es inválido
     */
    public boolean sendNotification(String userId, String message, String channel) {
        // Validar parámetros
        validateParameters(userId, message, channel);

        // Obtener el proveedor según el canal
        NotificationProvider provider = NotificationProviderFactory.getProvider(channel);

        // Enviar la notificación
        boolean sent = provider.send(userId, message);

        // Guardar en historial si se envió exitosamente
        if (sent) {
            Notification notification = new Notification(userId, message, channel);
            notificationHistory.add(notification);
        }

        return sent;
    }

    /**
     * Obtiene el historial de todas las notificaciones
     * 
     * @return lista de notificaciones enviadas
     */
    public List<Notification> getNotificationHistory() {
        return new ArrayList<>(notificationHistory);
    }

    /**
     * Obtiene el historial de notificaciones de un usuario específico
     * 
     * @param userId ID del usuario
     * @return lista de notificaciones del usuario
     */
    public List<Notification> getUserNotifications(String userId) {
        List<Notification> userNotifications = new ArrayList<>();
        for (Notification notification : notificationHistory) {
            if (notification.getUserId().equals(userId)) {
                userNotifications.add(notification);
            }
        }
        return userNotifications;
    }

    /**
     * Obtiene el historial filtrado por canal
     * 
     * @param channel canal a filtrar
     * @return lista de notificaciones del canal
     */
    public List<Notification> getNotificationsByChannel(String channel) {
        List<Notification> channelNotifications = new ArrayList<>();
        for (Notification notification : notificationHistory) {
            if (notification.getChannel().equalsIgnoreCase(channel)) {
                channelNotifications.add(notification);
            }
        }
        return channelNotifications;
    }

    /**
     * Valida que todos los parámetros requeridos sean válidos
     * 
     * @throws IllegalArgumentException si algún parámetro es nulo o vacío
     */
    private void validateParameters(String userId, String message, String channel) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("userId es obligatorio y no puede estar vacío");
        }

        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("message es obligatorio y no puede estar vacío");
        }

        if (channel == null || channel.trim().isEmpty()) {
            throw new IllegalArgumentException("channel es obligatorio y no puede estar vacío");
        }

        // Validar que channel sea válido
        String normalizedChannel = channel.toLowerCase().trim();
        if (!normalizedChannel.equals("email") && !normalizedChannel.equals("sms")) {
            throw new IllegalArgumentException("channel debe ser 'email' o 'sms'");
        }
    }
}
