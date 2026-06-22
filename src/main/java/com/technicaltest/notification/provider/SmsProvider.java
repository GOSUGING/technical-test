package com.technicaltest.notification.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Proveedor de notificaciones por SMS
 */
public class SmsProvider implements NotificationProvider {
    private static final Logger logger = LoggerFactory.getLogger(SmsProvider.class);

    @Override
    public boolean send(String userId, String message) {
        try {
            // Validación: SMS tiene límite de caracteres
            if (message.length() > 160) {
                logger.warn("SMS para usuario {} excede 160 caracteres. Se truncará el mensaje.", userId);
            }

            // Simulación de envío de SMS
            logger.info("Enviando SMS a usuario: {} con mensaje: {}", userId, message);
            
            // Aquí iría la lógica real de envío (Twilio, AWS SNS, etc.)
            // Por ahora, simulamos que siempre tiene éxito
            
            logger.info("SMS enviado exitosamente a: {}", userId);
            return true;
        } catch (Exception e) {
            logger.error("Error al enviar SMS a {}: {}", userId, e.getMessage());
            return false;
        }
    }

    @Override
    public String getType() {
        return "sms";
    }
}
