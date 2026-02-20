# LoginAppExtra – Aplicación Android con Firebase Authentication y Firestore

## Descripción del proyecto

LoginAppExtra es una aplicación móvil desarrollada en Android utilizando Kotlin y Jetpack Compose. La aplicación implementa un sistema de autenticación básica mediante Firebase Authentication y utiliza Firebase Firestore como base de datos en la nube para almacenar y mostrar información de los usuarios registrados.

Este proyecto fue desarrollado como parte del Examen Extraordinario II de la materia Tecnologías de la Información I de la Licenciatura en Informática.

La aplicación permite a los usuarios registrarse, iniciar sesión, almacenar sus datos en la nube y visualizar la información en una segunda pantalla.

---

## Objetivo

Desarrollar una aplicación móvil que consuma servicios en la nube mediante Firebase, implementando autenticación de usuarios y almacenamiento de datos en una base de datos remota.

---

## Tecnologías utilizadas

* Kotlin
* Android Studio
* Jetpack Compose
* Firebase Authentication
* Firebase Firestore Database
* Git y GitHub

---

## Funcionalidades implementadas

La aplicación incluye las siguientes funcionalidades:

### 1. Registro de usuario

El usuario puede registrarse utilizando:

* Correo electrónico
* Contraseña

Firebase Authentication crea una cuenta segura en la nube.

---

### 2. Inicio de sesión

El usuario puede iniciar sesión con su correo y contraseña previamente registrados.

Firebase valida las credenciales y permite el acceso a la aplicación.

---

### 3. Almacenamiento de datos en la nube

Después del inicio de sesión, la aplicación guarda en Firebase Firestore los siguientes datos:

* Nombre (puede configurarse o asignarse automáticamente)
* Correo electrónico
* Fecha de registro

Estos datos se almacenan en la colección:

```
usuarios
```

---

### 4. Visualización de usuarios registrados

La aplicación muestra una lista de los usuarios almacenados en Firebase Firestore.

Se visualiza:

* Correo electrónico
* Fecha de registro

Esto permite verificar que la aplicación consume servicios en la nube correctamente.

---

### 5. Cerrar sesión

El usuario puede cerrar sesión mediante un botón.

Firebase elimina la sesión activa y el usuario regresa a la pantalla de inicio de sesión.

---

## Arquitectura de la aplicación

La aplicación está estructurada en los siguientes componentes principales:

```
loginAppExtra
│
├── MainActivity.kt
│   Maneja el login y registro con Firebase Authentication
│
├── HomeScreen.kt
│   Muestra los datos almacenados en Firestore
│   Permite cerrar sesión
│
├── ui/theme/
│   Contiene los estilos visuales de la aplicación
│
├── google-services.json
│   Archivo de configuración de Firebase
```

---

## Funcionamiento de Firebase

### Firebase Authentication

Se utiliza para:

* Registrar usuarios
* Iniciar sesión
* Validar credenciales
* Mantener la sesión activa

Método utilizado:

```
createUserWithEmailAndPassword()
signInWithEmailAndPassword()
signOut()
```

---

### Firebase Firestore

Se utiliza como base de datos en la nube para almacenar información de los usuarios.

Datos almacenados:

```
correo
fecha
```

Métodos utilizados:

```
collection("usuarios").set()
collection("usuarios").get()
```

---

## Interfaz de usuario

La aplicación utiliza Jetpack Compose para crear la interfaz gráfica.

Incluye:

Pantalla de Login:

* Campo correo electrónico
* Campo contraseña
* Botón iniciar sesión
* Botón registrar usuario

Pantalla Home:

* Lista de usuarios
* Fecha de registro
* Botón cerrar sesión

---

## Cómo ejecutar el proyecto

### Requisitos

* Android Studio instalado
* Cuenta Firebase configurada
* Conexión a internet
* Archivo google-services.json configurado

---

### Pasos para ejecutar

1. Clonar el repositorio

```
git clone https://github.com/ElioCruzO/logginApp.git
```

2. Abrir el proyecto en Android Studio

3. Esperar a que Gradle sincronice

4. Ejecutar la aplicación en:

* Emulador Android
* o dispositivo físico

---

## Cómo generar el APK

En Android Studio:

```
Build → Build APK
```

Ruta del archivo:

```
app/build/outputs/apk/debug/app-debug.apk
```

Este archivo puede instalarse en cualquier dispositivo Android.

---

## Evidencias del funcionamiento

La aplicación cumple con los requisitos solicitados:

* Autenticación mediante Firebase
* Base de datos en la nube
* Visualización de datos
* Registro de usuarios
* Inicio de sesión
* Cierre de sesión
* Consumo de servicios en la nube
* Uso de repositorio GitHub
* Generación de APK

---

## Autor

Nombre: Elio Justino Cruz Ortetga
Licenciatura en Informática
Materia: Tecnologías de la Información I

---

## Conclusión

Este proyecto demuestra la implementación de una aplicación móvil Android conectada a servicios en la nube mediante Firebase. Se logró implementar autenticación de usuarios, almacenamiento de datos en Firestore y visualización de información en tiempo real.

El uso de Firebase facilita la gestión segura de usuarios y el almacenamiento remoto, permitiendo desarrollar aplicaciones modernas con arquitectura basada en la nube.
