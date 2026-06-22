package com.technicaltest.notification.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technicaltest.notification.dto.NotificationRequest;
import com.technicaltest.notification.model.Notification;
import com.technicaltest.notification.service.NotificationService;

/**
 * Controlador REST para gestionar notificaciones
 * 
 * Endpoints:
 * - POST /notifications: enviar una notificación
 * - GET /notifications: obtener historial de notificaciones
 */
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * Endpoint para enviar una notificación
     * 
     * POST /notifications
     * 
     * @param request DTO con userId, message, channel
     * @return ResponseEntity con resultado del envío
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> sendNotification(@RequestBody NotificationRequest request) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Validar que el request no sea nulo
            if (request == null) {
                response.put("success", false);
                response.put("message", "El request body no puede estar vacío");
                return ResponseEntity.badRequest().body(response);
            }

            // Enviar la notificación
            boolean sent = notificationService.sendNotification(
                request.getUserId(),
                request.getMessage(),
                request.getChannel()
            );

            if (sent) {
                response.put("success", true);
                response.put("message", "Notificación enviada exitosamente");
                response.put("userId", request.getUserId());
                response.put("channel", request.getChannel());
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Error al enviar la notificación");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", "Error de validación: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error inesperado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Endpoint para obtener el historial de notificaciones
     * 
     * GET /notifications
     * 
     * @return ResponseEntity con lista de notificaciones
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getNotifications() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<Notification> notifications = notificationService.getNotificationHistory();

            response.put("success", true);
            response.put("count", notifications.size());
            response.put("data", notifications);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al obtener notificaciones: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Endpoint para obtener notificaciones de un usuario específico
     * 
     * GET /notifications/user/{userId}
     * 
     * @param userId ID del usuario
     * @return ResponseEntity con notificaciones del usuario
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getUserNotifications(@PathVariable String userId) {
        Map<String, Object> response = new HashMap<>();

        try {
            List<Notification> notifications = notificationService.getUserNotifications(userId);

            response.put("success", true);
            response.put("userId", userId);
            response.put("count", notifications.size());
            response.put("data", notifications);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al obtener notificaciones: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Endpoint para obtener notificaciones por canal
     * 
     * GET /notifications/channel/{channel}
     * 
     * @param channel tipo de canal (email o sms)
     * @return ResponseEntity con notificaciones del canal
     */
    @GetMapping("/channel/{channel}")
    public ResponseEntity<Map<String, Object>> getNotificationsByChannel(@PathVariable String channel) {
        Map<String, Object> response = new HashMap<>();

        try {
            List<Notification> notifications = notificationService.getNotificationsByChannel(channel);

            response.put("success", true);
            response.put("channel", channel);
            response.put("count", notifications.size());
            response.put("data", notifications);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al obtener notificaciones: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
