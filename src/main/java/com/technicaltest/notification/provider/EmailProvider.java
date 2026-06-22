package com.technicaltest.notification.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Proveedor de notificaciones por Email
 */
public class EmailProvider implements NotificationProvider {
    private static final Logger logger = LoggerFactory.getLogger(EmailProvider.class);

    @Override
    public boolean send(String userId, String message) {
        try {
            // Simulación de envío de email
            logger.info("Enviando email a usuario: {} con mensaje: {}", userId, message);
            
            // Aquí iría la lógica real de envío (SMTP, servicios como SendGrid, etc.)
            // Por ahora, simulamos que siempre tiene éxito
            
            logger.info("Email enviado exitosamente a: {}", userId);
            return true;
        } catch (Exception e) {
            logger.error("Error al enviar email a {}: {}", userId, e.getMessage());
            return false;
        }
    }

    @Override
    public String getType() {
        return "email";
    }
}
