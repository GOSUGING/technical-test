package com.technicaltest.notification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.technicaltest.notification.service.NotificationService;

/**
 * Tests para la Parte 2: API de Notificaciones
 */
@SpringBootTest
public class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @BeforeEach
    public void setUp() {
        // Crear una nueva instancia para cada test para aislar el estado
        notificationService = new NotificationService();
    }

    @Test
    public void testSendEmailNotification() {
        boolean result = notificationService.sendNotification(
            "user123",
            "Hola desde el sistema",
            "email"
        );

        assertTrue(result);
        assertEquals(1, notificationService.getNotificationHistory().size());
    }

    @Test
    public void testSendSmsNotification() {
        boolean result = notificationService.sendNotification(
            "user456",
            "Tu código es: 123456",
            "sms"
        );

        assertTrue(result);
        assertEquals(1, notificationService.getNotificationHistory().size());
    }

    @Test
    public void testNullUserId() {
        assertThrows(IllegalArgumentException.class, () ->
            notificationService.sendNotification(null, "mensaje", "email")
        );
    }

    @Test
    public void testNullMessage() {
        assertThrows(IllegalArgumentException.class, () ->
            notificationService.sendNotification("user123", null, "email")
        );
    }

    @Test
    public void testNullChannel() {
        assertThrows(IllegalArgumentException.class, () ->
            notificationService.sendNotification("user123", "mensaje", null)
        );
    }

    @Test
    public void testInvalidChannel() {
        assertThrows(IllegalArgumentException.class, () ->
            notificationService.sendNotification("user123", "mensaje", "telegram")
        );
    }

    @Test
    public void testEmptyParameters() {
        assertThrows(IllegalArgumentException.class, () ->
            notificationService.sendNotification("", "", "")
        );
    }

    @Test
    public void testGetUserNotifications() {
        notificationService.sendNotification("user123", "Mensaje 1", "email");
        notificationService.sendNotification("user123", "Mensaje 2", "sms");
        notificationService.sendNotification("user456", "Mensaje 3", "email");

        assertEquals(2, notificationService.getUserNotifications("user123").size());
        assertEquals(1, notificationService.getUserNotifications("user456").size());
    }

    @Test
    public void testGetNotificationsByChannel() {
        notificationService.sendNotification("user1", "Mensaje 1", "email");
        notificationService.sendNotification("user2", "Mensaje 2", "email");
        notificationService.sendNotification("user3", "Mensaje 3", "sms");

        assertEquals(2, notificationService.getNotificationsByChannel("email").size());
        assertEquals(1, notificationService.getNotificationsByChannel("sms").size());
    }

    @Test
    public void testHistoryPersistence() {
        notificationService.sendNotification("user1", "Mensaje 1", "email");
        notificationService.sendNotification("user2", "Mensaje 2", "sms");

        assertEquals(2, notificationService.getNotificationHistory().size());
    }
}
