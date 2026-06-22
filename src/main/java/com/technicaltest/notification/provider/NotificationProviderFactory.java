package com.technicaltest.notification.provider;

/**
 * Factory para seleccionar el proveedor de notificación correcto
 * Implementa el patrón Factory Method
 */
public class NotificationProviderFactory {
    
    /**
     * Obtiene el proveedor según el canal especificado
     * 
     * @param channel tipo de canal: "email" o "sms"
     * @return instancia del proveedor correspondiente
     * @throws IllegalArgumentException si el canal no es válido
     */
    public static NotificationProvider getProvider(String channel) {
        if (channel == null || channel.trim().isEmpty()) {
            throw new IllegalArgumentException("El canal no puede estar vacío");
        }

        String normalizedChannel = channel.toLowerCase().trim();

        switch (normalizedChannel) {
            case "email":
                return new EmailProvider();
            case "sms":
                return new SmsProvider();
            default:
                throw new IllegalArgumentException("Canal no válido: " + channel + 
                    ". Canales permitidos: email, sms");
        }
    }
}
