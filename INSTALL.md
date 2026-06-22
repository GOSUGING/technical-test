# Guía de Instalación y Ejecución

## 📋 Requisitos

- **Java**: 17 o superior
- **Maven**: 3.6+  
- **Git**: (opcional, para clonar)

---

## 🚀 Instalación Rápida

### 1. Descargar el Proyecto

```bash
# Navegar al directorio del proyecto
cd technical-test
```

---

## 📦 Compilación

### Compilar sin ejecutar tests
```bash
mvn clean compile
```

### Compilar y construir JAR
```bash
mvn clean package
```

---

## ✅ Tests

### Ejecutar todos los tests
```bash
mvn test
```

### Ejecutar solo tests de Parte 1 (Lógica)
```bash
mvn test -Dtest=ListProcessorTest
```

### Ejecutar solo tests de Parte 2 (API)
```bash
mvn test -Dtest=NotificationServiceTest
```

---

## ▶️ Ejecutar la Aplicación

### Opción 1: Usar Maven (Recomendado para desarrollo)
```bash
mvn spring-boot:run
```

### Opción 2: Usar JAR (Producción)
```bash
java -jar target/technical-test-1.0.0.jar
```

**La aplicación estará disponible en**: `http://localhost:8080`

---

## 🧪 Pruebas Manuales

### 1. Enviar Email

```bash
curl -X POST http://localhost:8080/notifications \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "user123",
    "message": "Bienvenido al sistema",
    "channel": "email"
  }'
```

### 2. Enviar SMS

```bash
curl -X POST http://localhost:8080/notifications \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "user456",
    "message": "Tu código: 123456",
    "channel": "sms"
  }'
```

### 3. Obtener Historial Completo

```bash
curl http://localhost:8080/notifications
```

### 4. Obtener Notificaciones de un Usuario

```bash
curl http://localhost:8080/notifications/user/user123
```

### 5. Obtener Notificaciones por Canal

```bash
curl http://localhost:8080/notifications/channel/email
```

---

## 📊 Estructura de Directorios

```
technical-test/
├── pom.xml                 # Configuración Maven
├── README.md              # Documentación principal
├── INSTALL.md             # Este archivo
├── .gitignore             # Archivos a ignorar en git
├── src/
│   ├── main/
│   │   ├── java/          # Código fuente
│   │   └── resources/     # Recursos (properties)
│   └── test/
│       └── java/          # Tests unitarios
└── target/                # Directorio generado (compilación)
    └── technical-test-1.0.0.jar  # JAR ejecutable
```

---

## 🔧 Configuración

### Cambiar Puerto de la Aplicación

Editar `src/main/resources/application.properties`:

```properties
server.port=9090
```

Luego recompilar:
```bash
mvn spring-boot:run
```

---

## 📝 Verificación de Instalación

Para verificar que todo está instalado correctamente:

```bash
# Verificar Java
java -version

# Verificar Maven
mvn -version

# Compilar proyecto
mvn clean compile

# Ejecutar tests
mvn test
```

Si todos los comandos se ejecutan sin errores, ¡la instalación está completa!

---

## 🐛 Solución de Problemas

### Error: "Maven no encontrado"
- Asegúrate de tener Maven 3.6+ instalado
- Verifica que esté en la variable PATH

### Error: "Java 17 no encontrado"
- Descarga Java 17+ desde [oracle.com](https://www.oracle.com/java/technologies/downloads/) o [adoptopenjdk.net](https://adoptopenjdk.net/)
- Configura JAVA_HOME

### Puerto 8080 en uso
- Cambiar puerto en `application.properties`
- O usar: `mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=9090"`

---

## 📞 Soporte

Para más detalles, consulta:
- `README.md` - Documentación completa
- `src/` - Código fuente comentado
