# Prueba Técnica - Entry Level

Solución completa para la prueba técnica de Entry Level que incluye dos partes principales:

## 📋 Contenido

1. **Parte 1: Lógica de Programación** - Eliminar duplicados y ordenar una lista
2. **Parte 2: API REST** - Sistema de notificaciones con patrón de proveedores

---

## 🔧 Requisitos Previos

- Java 17 o superior
- Maven 3.6+

---

## 📦 Estructura del Proyecto

```
technical-test/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/com/technicaltest/
    │   │   ├── Application.java
    │   │   ├── logic/
    │   │   │   └── ListProcessor.java
    │   │   └── notification/
    │   │       ├── controller/
    │   │       │   └── NotificationController.java
    │   │       ├── dto/
    │   │       │   └── NotificationRequest.java
    │   │       ├── model/
    │   │       │   └── Notification.java
    │   │       ├── provider/
    │   │       │   ├── EmailProvider.java
    │   │       │   ├── NotificationProvider.java
    │   │       │   ├── NotificationProviderFactory.java
    │   │       │   └── SmsProvider.java
    │   │       └── service/
    │   │           └── NotificationService.java
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/com/technicaltest/
            ├── logic/
            │   └── ListProcessorTest.java
            └── notification/
                └── NotificationServiceTest.java
```

---

## 📝 Parte 1: Lógica de Programación

### Descripción
Implementar un método que:
- Elimine duplicados de una lista de números
- Ordene la lista de menor a mayor
- **Sin usar** funciones nativas como `sort()`, `sorted()`, `set`, `distinct`, etc.

### Implementación

**Archivo**: `src/main/java/com/technicaltest/logic/ListProcessor.java`

```java
public static List<Integer> removeDuplicatesAndSort(List<Integer> input)
```

### Algoritmo

1. **Eliminar duplicados**: Iterar sobre la lista original y agregar solo los elementos que no estén presentes
2. **Ordenar**: Implementar Bubble Sort manualmente

### Ejemplo

```
Entrada:  [4, 2, 7, 2, 4, 9, 1]
Salida:   [1, 2, 4, 7, 9]
```

### Tests

Ejecutar tests de la Parte 1:

```bash
mvn test -Dtest=ListProcessorTest
```

**Casos de prueba cubiertos**:
- ✅ Ejemplo del enunciado
- ✅ Lista vacía
- ✅ Lista null
- ✅ Un solo elemento
- ✅ Todos duplicados
- ✅ Números negativos
- ✅ Lista ya ordenada
- ✅ Lista en orden inverso
- ✅ Números grandes

---

## 🌐 Parte 2: API REST - Sistema de Notificaciones

### Descripción
API REST para envío de notificaciones que:
- Soporta múltiples canales (email, SMS)
- Usa el patrón Strategy para seleccionar proveedores
- Mantiene historial de notificaciones
- Valida todos los parámetros

### Arquitectura y Patrones

#### Patrón Strategy
El sistema utiliza el patrón Strategy para seleccionar el proveedor correcto:

```
NotificationProvider (interfaz)
    ├── EmailProvider
    └── SmsProvider

NotificationProviderFactory -> selecciona el proveedor según el canal
```

#### Flujo de Envío

```
1. POST /notifications (NotificationRequest)
2. Validación de parámetros
3. NotificationProviderFactory.getProvider(channel)
4. Provider.send(userId, message)
5. Guardar en historial
6. Respuesta JSON
```

### API Endpoints

#### 1. Enviar Notificación
```http
POST /notifications
Content-Type: application/json

{
  "userId": "123",
  "message": "Hola",
  "channel": "email"
}
```

**Respuesta exitosa (200 OK)**:
```json
{
  "success": true,
  "message": "Notificación enviada exitosamente",
  "userId": "123",
  "channel": "email"
}
```

**Validación**:
- ❌ `userId` vacío → 400 Bad Request
- ❌ `message` vacío → 400 Bad Request
- ❌ `channel` no válido → 400 Bad Request
- ❌ `channel` debe ser "email" o "sms" → 400 Bad Request

---

#### 2. Obtener Historial Completo
```http
GET /notifications
```

**Respuesta (200 OK)**:
```json
{
  "success": true,
  "count": 3,
  "data": [
    {
      "userId": "123",
      "message": "Hola",
      "channel": "email",
      "timestamp": "2026-06-22 15:30:45"
    },
    ...
  ]
}
```

---

#### 3. Obtener Notificaciones de un Usuario
```http
GET /notifications/user/{userId}
```

**Ejemplo**:
```http
GET /notifications/user/123
```

**Respuesta (200 OK)**:
```json
{
  "success": true,
  "userId": "123",
  "count": 2,
  "data": [...]
}
```

---

#### 4. Obtener Notificaciones por Canal
```http
GET /notifications/channel/{channel}
```

**Ejemplo**:
```http
GET /notifications/channel/email
```

**Respuesta (200 OK)**:
```json
{
  "success": true,
  "channel": "email",
  "count": 5,
  "data": [...]
}
```

---

### Proveedores

#### EmailProvider
- Simula envío de email
- Logs: "Enviando email a usuario: X con mensaje: Y"
- En producción: integración con SMTP, SendGrid, AWS SES, etc.

#### SmsProvider
- Simula envío de SMS
- Validación: Aviso si el mensaje excede 160 caracteres
- Logs: "Enviando SMS a usuario: X con mensaje: Y"
- En producción: integración con Twilio, AWS SNS, etc.

---

## 🚀 Cómo Ejecutar

### 1. Compilar el Proyecto
```bash
cd technical-test
mvn clean compile
```

### 2. Ejecutar Tests
```bash
# Todos los tests
mvn test

# Solo Parte 1
mvn test -Dtest=ListProcessorTest

# Solo Parte 2
mvn test -Dtest=NotificationServiceTest
```

### 3. Ejecutar la Aplicación
```bash
mvn spring-boot:run
```

La aplicación estará disponible en: **http://localhost:8080**

---

## 🧪 Ejemplos de Uso

### Ejemplo 1: Enviar Email
```bash
curl -X POST http://localhost:8080/notifications \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "user123",
    "message": "Bienvenido al sistema",
    "channel": "email"
  }'
```

**Respuesta**:
```json
{
  "success": true,
  "message": "Notificación enviada exitosamente",
  "userId": "user123",
  "channel": "email"
}
```

---

### Ejemplo 2: Enviar SMS
```bash
curl -X POST http://localhost:8080/notifications \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "user456",
    "message": "Tu código es: 654321",
    "channel": "sms"
  }'
```

---

### Ejemplo 3: Obtener Historial
```bash
curl http://localhost:8080/notifications
```

**Respuesta**:
```json
{
  "success": true,
  "count": 2,
  "data": [
    {
      "userId": "user123",
      "message": "Bienvenido al sistema",
      "channel": "email",
      "timestamp": "2026-06-22 15:30:45"
    },
    {
      "userId": "user456",
      "message": "Tu código es: 654321",
      "channel": "sms",
      "timestamp": "2026-06-22 15:31:20"
    }
  ]
}
```

---

### Ejemplo 4: Filtrar por Usuario
```bash
curl http://localhost:8080/notifications/user/user123
```

---

### Ejemplo 5: Filtrar por Canal
```bash
curl http://localhost:8080/notifications/channel/email
```

---

## 📊 Validaciones Implementadas

### Parte 1
- ✅ Eliminación de duplicados
- ✅ Ordenamiento correcto (menor a mayor)
- ✅ Manejo de listas vacías/null
- ✅ Números negativos y cero
- ✅ Números grandes

### Parte 2
- ✅ Validación de todos los parámetros (userId, message, channel)
- ✅ Validación de canal (solo email o sms)
- ✅ Patrón Strategy para seleccionar proveedores
- ✅ Historial en memoria (thread-safe con ArrayList)
- ✅ Filtrado por usuario
- ✅ Filtrado por canal
- ✅ Manejo de excepciones
- ✅ Respuestas JSON estructuradas

---

## 🔍 Cobertura de Tests

### Parte 1: ListProcessorTest
- 8 casos de prueba
- Cobertura: 100% de métodos

### Parte 2: NotificationServiceTest
- 10 casos de prueba
- Cobertura: 100% de métodos de lógica

---

## 🏗️ Decisiones de Diseño

### Parte 1
1. **Bubble Sort**: Elegido por su sencillez y porque cumple los requisitos (sin librerías)
2. **Eliminación de duplicados**: Con `contains()` para comprobar antes de agregar

### Parte 2
1. **Patrón Strategy**: Para desacoplar la lógica de selección de proveedores
2. **Factory Pattern**: Para centralizar la creación de proveedores
3. **Historial en memoria**: Para mantener la simplicidad; fácilmente extensible a BD
4. **Spring Boot**: Framework moderno y producción-ready
5. **DTOs**: Para separar la API de los modelos internos

---

## 📈 Posibles Mejoras Futuras

### Parte 1
- Implementar algoritmos más eficientes (Quick Sort, Merge Sort)
- Permitir diferentes órdenes de ordenamiento (ascendente/descendente)
- Soportar otros tipos de datos (String, Double, etc.)

### Parte 2
- Persistencia en base de datos (JPA/Hibernate)
- Autenticación y autorización (JWT, OAuth2)
- Rate limiting
- Retry logic para fallos de envío
- Colas de mensaje (RabbitMQ, Kafka)
- Integración real con proveedores (SendGrid, Twilio)
- Paginación en GET /notifications
- Filtros avanzados (fecha, estado, etc.)
- Swagger/OpenAPI documentation
- Métricas y monitoring

---

## 👤 Autor Aron SUy

Solución desarrollada para la Prueba Técnica Entry Level

---

## 📄 Licencia

Este proyecto es de uso educativo.
