package com.technicaltest.notification.provider;

/**
 * Interfaz para proveedores de notificación (patrón Strategy)
 */
public interface NotificationProvider {
    /**
     * Envía una notificación
     * 
     * @param userId ID del usuario
     * @param message Mensaje a enviar
     * @return true si se envió exitosamente, false en caso contrario
     */
    boolean send(String userId, String message);

    /**
     * Obtiene el tipo de proveedor
     * 
     * @return tipo del proveedor (email, sms, etc.)
     */
    String getType();
}
