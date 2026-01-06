# 📇 Agenda de Contactos

Una aplicación de consola en Java para gestionar una agenda telefónica con capacidad máxima de 10 contactos. Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los contactos de manera simple e intuitiva.

## 📋 Tabla de Contenidos

- [Características](#-características)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación](#-instalación)
- [Uso](#-uso)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Explicación Detallada del Código](#-explicación-detallada-del-código)
- [Funcionalidades](#-funcionalidades)
- [Tecnologías](#-tecnologías)

## ✨ Características

- ✅ Añadir contactos (máximo 10)
- 📋 Listar todos los contactos
- 🔍 Buscar contacto por nombre
- 🗑️ Eliminar contactos
- ✏️ Modificar número telefónico
- 📊 Ver espacios disponibles en la agenda

## 🔧 Requisitos Previos

- **Java Development Kit (JDK)** 8 o superior
- Un IDE como IntelliJ IDEA, Eclipse, o NetBeans (opcional)
- Terminal o línea de comandos

## 📥 Instalación

1. Clona el repositorio:
```bash
git clone https://github.com/DiegoAndresVF/agenda-contactos.git
```

2. Navega al directorio del proyecto:
```bash
cd agenda-contactos
```

3. Compila el proyecto:
```bash
javac src/*.java
```

4. Ejecuta la aplicación:
```bash
java -cp src Main
```

## 🚀 Uso

Al ejecutar la aplicación, verás un menú interactivo:

```
--- AGENDA TELEFÓNICA ---
1. Añadir contacto
2. Listar contactos
3. Buscar contacto
4. Eliminar contacto
5. Modificar teléfono
6. Ver espacios libres
0. Salir
```

### Ejemplos de Uso

**Añadir un contacto:**
```
Ingrese número de opción: 1
Ingrese nombre: Juan
Ingrese apellido: Pérez
Ingrese teléfono: 123456789
> Contacto añadido
```

**Buscar un contacto:**
```
Ingrese número de opción: 3
Ingrese nombre a buscar: Juan
> El telefono de contacto es: 123456789
```

## 📁 Estructura del Proyecto

```
agenda-contactos/
│
├── src/
│   ├── Main.java          # Clase principal con menú interactivo
│   ├── Agenda.java        # Lógica de gestión de la agenda
│   └── Contacto.java      # Modelo de datos del contacto
│
├── .idea/                 # Configuración de IntelliJ IDEA
├── .gitignore            # Archivos ignorados por Git
├── agenda-de-contactos.iml  # Módulo de IntelliJ
└── README.md             # Este archivo
```

## 🔍 Explicación Detallada del Código

### 1. Clase `Contacto.java`

Esta clase representa el **modelo de datos** de un contacto individual.

```java
public class Contacto {
  private String nombre;
  private String apellido;
  private String telefono;
```

**Propósito:** Encapsula la información de un contacto usando tres atributos privados.

**Constructor:**
```java
public Contacto(String nombre, String apellido, String telefono)
```
Inicializa un nuevo contacto con los datos proporcionados.

**Métodos Getters y Setters:**
- `getNombre()`, `setNombre()`: Acceso al nombre
- `getApellido()`, `setApellido()`: Acceso al apellido
- `getTelefono()`, `setTelefono()`: Acceso al teléfono

**Patrón utilizado:** Encapsulamiento (Principio de POO)

---

### 2. Clase `Agenda.java`

Esta clase gestiona la **colección de contactos** y toda la lógica de negocio.

#### Atributo Principal
```java
private List<Contacto> contactos;
```
Utiliza un `ArrayList` para almacenar dinámicamente los contactos.

#### Constructor
```java
public Agenda() {
  this.contactos = new ArrayList<>();
}
```
Inicializa la lista de contactos vacía al crear una nueva agenda.

#### Métodos Principales

**`anadirContacto(Contacto contacto)`**
```java
void anadirContacto(Contacto contacto) {
  if(contactos.size() >= 10) {
    System.out.println("> Agenda llena");
  } else {
    this.contactos.add(contacto);
    System.out.println("> Contacto añadido");
  }
}
```
- **Función:** Añade un contacto si hay espacio disponible
- **Validación:** Verifica que no se excedan los 10 contactos
- **Complejidad:** O(1)

**`existeContacto(Contacto c)`**
```java
boolean existeContacto(Contacto c) {
  return contactos.contains(c);
}
```
- **Función:** Verifica si un contacto existe en la agenda
- **Retorna:** `true` si existe, `false` si no
- **Uso:** Validación antes de eliminar o modificar

**`listarContacto()`**
```java
void listarContacto() {
  for (Contacto contacto : contactos) {
    System.out.println("nombre: " + contacto.getNombre() + 
                       "\napellido: " + contacto.getApellido() + 
                       "\ntelefono: " + contacto.getTelefono());
  }
}
```
- **Función:** Muestra todos los contactos en consola
- **Patrón:** Enhanced for loop para iteración

**`buscarContacto(String nombre)`**
```java
String buscarContacto(String nombre) {
  Contacto contacto = this.contactos.stream()
    .filter(c -> c.getNombre().equals(nombre))
    .findFirst()
    .orElse(null);
  
  if(existeContacto(contacto)) {
    System.out.println("> El telefono de contacto es: " + contacto.getTelefono());
    return contacto.getTelefono();
  } else {
    return "> El contacto no existe";
  }
}
```
- **Función:** Busca un contacto por nombre y retorna su teléfono
- **API Stream:** Utiliza programación funcional de Java 8+
- **Operaciones:** `filter()` → `findFirst()` → `orElse()`

**`buscarContactoEliminar(String nombre)`**
```java
Contacto buscarContactoEliminar(String nombre) {
  return this.contactos.stream()
    .filter(c -> c.getNombre().equals(nombre))
    .findFirst()
    .orElse(null);
}
```
- **Función:** Busca y retorna el objeto Contacto completo
- **Uso específico:** Para la operación de eliminación

**`eliminarContacto(Contacto deleteContact)`**
```java
void eliminarContacto(Contacto deleteContact) {
  if (this.existeContacto(deleteContact)) {
    this.contactos.remove(deleteContact);
    System.out.println("> Contacto eliminado");
  }
}
```
- **Función:** Elimina un contacto de la agenda
- **Validación:** Verifica existencia antes de eliminar

**`modificarTelefono(String nombre, String apellido, String nuevoTelefono)`**
```java
void modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
  this.contactos.stream()
    .filter(c -> nombre.equalsIgnoreCase(c.getNombre()) && 
                 apellido.equalsIgnoreCase(c.getApellido()))
    .findFirst()
    .ifPresentOrElse(
      c -> {
        c.setTelefono(nuevoTelefono);
        System.out.println("Teléfono actualizado a " + c.getTelefono());
      },
      () -> System.out.println("Contacto no encontrado")
    );
}
```
- **Función:** Modifica el teléfono de un contacto existente
- **Búsqueda:** Por nombre Y apellido (más preciso)
- **Características:** 
  - Búsqueda case-insensitive (`equalsIgnoreCase`)
  - Usa `ifPresentOrElse()` para manejar ambos casos

**`agendaLlena()`**
```java
void agendaLlena() {
  if (contactos.size() >= 10) {
    System.out.println("Agenda llena");
  } else {
    System.out.println("Agenda Disponible");
  }
}
```
- **Función:** Verifica si la agenda alcanzó su capacidad máxima

**`espaciosLibres()`**
```java
void espaciosLibres() {
  int freeSpace = 10 - contactos.size();
  if (freeSpace > 0) {
    System.out.println("Espacios disponibles: " + freeSpace);
  }
}
```
- **Función:** Calcula y muestra espacios disponibles
- **Cálculo:** Capacidad máxima (10) - contactos actuales

---

### 3. Clase `Main.java`

Clase principal que contiene el **punto de entrada** y la **interfaz de usuario** por consola.

#### Estructura Principal

```java
public static void main(String[] args) {
  Agenda agenda1 = new Agenda();
  Scanner sc = new Scanner(System.in);
  int op;
  String nombre, apellido, telefono;
```

**Inicialización:**
- Crea una instancia de `Agenda`
- Inicializa `Scanner` para entrada de usuario
- Declara variables para opciones y datos

#### Bucle Principal

```java
do {
  // Mostrar menú
  // Capturar opción
  // Ejecutar acción según opción
} while(op != 0);
```

**Patrón:** Do-While loop que se ejecuta hasta que el usuario elija salir (opción 0)

#### Switch Expression (Java 14+)

```java
switch (op) {
  case 1 -> { /* Añadir contacto */ }
  case 2 -> agenda1.listarContacto();
  case 3 -> { /* Buscar contacto */ }
  case 4 -> { /* Eliminar contacto */ }
  case 5 -> { /* Modificar teléfono */ }
  case 6 -> { agenda1.espaciosLibres(); }
}
```

**Características:**
- Sintaxis moderna de switch con flecha (`->`)
- Casos sin `break` necesario
- Bloques de código con `{}`

#### Casos Detallados

**Caso 1: Añadir Contacto**
```java
case 1 -> {
  System.out.print("Ingrese nombre: ");
  nombre = sc.next();
  System.out.print("Ingrese apellido: ");
  apellido = sc.next();
  System.out.print("Ingrese teléfono: ");
  telefono = sc.next();
  
  Contacto contacto1 = new Contacto(nombre, apellido, telefono);
  agenda1.anadirContacto(contacto1);
}
```
- Solicita datos al usuario
- Crea objeto `Contacto`
- Llama al método `anadirContacto()`

**Caso 4: Eliminar Contacto**
```java
case 4 -> {
  System.out.print("Ingrese nombre del contacto a eliminar: ");
  nombre = sc.next();
  Contacto contactoEliminar = agenda1.buscarContactoEliminar(nombre);
  agenda1.eliminarContacto(contactoEliminar);
}
```
- Proceso en dos pasos: buscar primero, luego eliminar
- Maneja caso de contacto no encontrado en el método `eliminarContacto()`

## 🎯 Funcionalidades

### Capacidad Limitada
La agenda tiene un **límite de 10 contactos** como restricción de negocio, simulando una agenda física.

### Operaciones CRUD Completas
- **Create:** Añadir contactos
- **Read:** Listar y buscar contactos
- **Update:** Modificar teléfono
- **Delete:** Eliminar contactos

### Validaciones Implementadas
- ✅ Verificación de agenda llena
- ✅ Verificación de existencia de contacto
- ✅ Búsqueda case-insensitive
- ✅ Manejo de contactos no encontrados

## 💻 Tecnologías

- **Lenguaje:** Java
- **Versión mínima:** Java 8 (recomendado Java 14+ para switch expressions)
- **Colecciones:** ArrayList
- **API Streams:** Para operaciones funcionales
- **I/O:** Scanner para entrada de consola

## 📚 Conceptos de POO Aplicados

1. **Encapsulamiento:** Atributos privados con getters/setters
2. **Abstracción:** Separación de modelo (`Contacto`) y lógica (`Agenda`)
3. **Modularidad:** Cada clase tiene una responsabilidad única
4. **Programación Funcional:** Uso de Streams API

## 🔄 Flujo de Ejecución

```
Inicio → Crear Agenda → Mostrar Menú → Capturar Opción 
   ↓
Ejecutar Acción (switch) → Actualizar Agenda
   ↓
¿Opción = 0? → NO: Volver al Menú
   ↓
   SÍ: Fin del Programa
```

## 🐛 Posibles Mejoras

- [ ] Persistencia de datos (archivo o base de datos)
- [ ] Validación de formato de teléfono
- [ ] Búsqueda por apellido o teléfono
- [ ] Edición completa de contactos
- [ ] Manejo de contactos duplicados
- [ ] Interfaz gráfica (GUI)
- [ ] Exportar/importar contactos
- [ ] Capacidad configurable de la agenda

## 👥 Autores

**Equipo GUI2**
- Tiago Alcazar
- Héctor Chacón
- Francis Chávez
- Brahim González
- Diego Villagrán
- Belén Almendros

## 📄 Licencia

Este proyecto es de código abierto y está disponible para fines educativos.

---

⭐ Si te fue útil este proyecto, considera darle una estrella en GitHub
